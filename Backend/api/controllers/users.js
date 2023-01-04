const User = require('../models/user');
const bcrypt = require('bcrypt');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');
const nodemailer = require('nodemailer');
const { v4: uuidv4 } = require('uuid');
const userVerification = require('../models/userVerification');
const path = require('path');
require("dotenv").config();

let transporter = nodemailer.createTransport({
    service: "gmail",
    host: 'smtp.gmail.com',
    secure: false,
    auth: {
        user: process.env.AUTH_EMAIL,
        pass: process.env.AUTH_TEST_PASWD,
    }
});

transporter.verify((err, success) => {
    if (err) {
        console.log(err)
    } else {
        console.log("Ready for messages");
        console.log(success);
    }
})

//signup page controller
exports.create_user = (req, res, next) => {
    bcrypt.hash(req.body.password, 10, async (err, hash) => {
        if (err) {
            console.log(err);
            return res.status(500).json({
                error: err
            })
        }
        else {
            const user = new User({
                _id: mongoose.Types.ObjectId(),
                userName: req.body.userName,
                name: req.body.name,
                email: req.body.email,
                verified: false,
                password: hash
            });
            try {
                const result = await user.save();
                SendVerificationEmail(result, res);
                // res.status(201).json({
                //     message:"Succesfully registered",
                //     result:result
                // })
            } catch (err) {
                console.log(err);
                res.status(409).json({
                    message: err.message
                })
            }

        }
    })
};

//send verification email:
const SendVerificationEmail = async ({ _id, email }, res) => {
    const currentURL = "https://bugslayers.onrender.com";
    const uniqueString = uuidv4() + _id;
    const mailOptions = {
        from: process.env.AUTH_EMAIL,
        to: email,
        subject: "Verify Your Email",
        html: `<p>verify your email address to complete the signup and login to your account</p>
                <p>This link <b>expires in 6 hours</b>.</p>
                <p>Press <a href=${currentURL + "users/verify/" + _id + "/" + uniqueString}>here
                </a> to proceed</p>`
    };
    const saltRounds = 10;
    try {
        bcrypt.hash(uniqueString, saltRounds, async (err, hash) => {
            if (err) {
                return res.status(500).json({
                    error: err
                })
            } else {
                const newVerification = new userVerification({
                    _id: mongoose.Types.ObjectId(),
                    userId: _id,
                    uniqueString: hash,
                    createdAt: Date.now(),
                    expiresAt: Date.now() + 21600000
                })
                const result = await newVerification.save();
                const send = await transporter.sendMail(mailOptions);
                res.status(200).json({
                    message: "pending"
                })
            }
        })
    } catch (err) {
        console.log(err);
        res.status(500).json({
            message: "error while hashing email data"
        })
    }

};

exports.verify_user = async (req, res, next) => {
    try {
        let { userId, uniqueString } = req.params;
        const result = await userVerification.find({ userId });
        if (result.length > 0) {

            const { expiresAt } = result[0];
            const hashedUniqueString = result[0].uniqueString;

            if (expiresAt < Date.now()) {
                try {
                    const del = await userVerification.deleteOne({ userId });
                    try {
                        const delUser = await User.deleteOne({ _id: userId });
                        let message = "Link has expired.Please signup again.";
                        res.redirect(`/users/verified/?error=true&message=${message}`);
                    } catch (err) {
                        let message = "Clearing User with Expired unique String failed.";
                        res.redirect(`/users/verified/?error=true&message=${message}`);
                    }

                } catch (err) {
                    let message = "An error occured while clearing expired user verification record.";
                    res.redirect(`/users/verified/?error=true&message=${message}`);
                }

            } else {
                try {
                    const str = await bcrypt.compare(uniqueString, hashedUniqueString);
                    if(str){
                        try{
                            const result = await User.updateOne({_id:userId},{$set:{verified:true}});
                           const userDel = await userVerification.deleteOne({userId});
                           res.sendFile(path.join(__dirname,"../../views/verified.html"));
                        }catch(err){
                            let message = "An error occured while showing your record to verified.";
                    res.redirect(`/users/verified/?error=true&message=${message}`);
                        }
                       
                    }else{
                        let message = "Invalid verification details passed. check your inbox.";
                    res.redirect(`/users/verified/?error=true&message=${message}`);
                    }
                } catch (err) {
                    let message = "An error occured while comparing uniqueString.";
                    res.redirect(`/users/verified/?error=true&message=${message}`);
                }

            }
        } else {
            let message = "Account record does not exist or has been verified already.Please signup or login";
            res.redirect(`/users/verified/?error=true&message=${message}`);
        }
    } catch (err) {
        console.log(err);
        let message = "An error occured while checking for existing user verification record";
        res.redirect(`/users/verified/?error=true&message=${message}`);
    }

};

exports.verified_user = async (req, res, next) => {
    res.sendFile(path.join(__dirname, '../../views/verified.html'));
}

exports.user_login = async (req, res, next) => {
    try {
        const user = await User.find({ userName: req.body.userName }).exec()
        if (user.length < 1) {
            return res.status(401).json({
                message: "Auth failed"
            })
        }
        if(!user[0].verified){
            return res.status(401).json({
                message:"Email verification left. Check your inbox."
            })
        }
        bcrypt.compare(req.body.password, user[0].password, (err, resp) => {
            if (err) {
                return res.status(401).json({
                    message: "Auth failed"
                })
            }
            if (resp) {
                const token = jwt.sign({
                    userName: user[0].userName,
                    name: user[0].name,
                    email: user[0].email,
                    userId: user[0]._id
                }, process.env.JWT_KEY,
                    {
                        expiresIn: "2h"
                    }
                )
                return res.status(200).json({
                    message: "Succesfully Logged in!",
                    token: token
                })
            }
            return res.status(401).json({
                message: "Auth failed"
            })
        })
    } catch (err) {
        console.log(err);
        res.status(409).json({
            message: err.message
        })
    }
};

exports.edit_user = async(req,res,next)=>{
    try{
        const username = req.params.userName;
        const updateUsr={};
        for(const usrs of req.body){
            updateUsr[usrs.propName]=usrs.value;
        }
        const result = await User.updateOne(
            {userName:username},{$set:updateUsr}).exec();
        res.status(200).json(result);
    }catch(err){
        console.log(err);
        res.status(500).json({
            message:"user profile not updated"
        })
    }
    
}