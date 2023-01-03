const User = require('../models/user');
const bcrypt = require('bcrypt');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken'); 

exports.create_user = (req,res,next)=>{
    bcrypt.hash(req.body.password,10,async(err,hash)=>{
        if(err){
            return res.status(500).json({
                error:err
            })
        }
        else{
            const user = new User({
                _id:mongoose.Types.ObjectId(),
                userName:req.body.userName,
                name:req.body.name,
                email:req.body.email,
                password:hash
            });
            try{
                const result = await user.save();
                res.status(201).json({
                    message:"Succesfully registered",
                    result:result
                })
            }catch(err){
                console.log(err);
                res.status(409).json({
                    message:err.message
                })
            }
            
        }
    })
    
    
};

exports.user_login = async(req,res,next)=>{
    try{
    const user = await User.find({userName:req.body.userName}).exec()
        if(user.length<1){
            return res.status(401).json({
                message:"Auth failed"
            })
        }
        bcrypt.compare(req.body.password,user[0].password,(err,resp)=>{
            if(err){
                return res.status(401).json({
                    message:"Auth failed"
                })
            }
            if(resp){
               const token =  jwt.sign({
                    userName:user[0].userName,
                    name:user[0].name,
                    email:user[0].email,
                    userId:user[0]._id
                },process.env.JWT_KEY,
                {
                    expiresIn:"2h"
                }
                )
                return res.status(200).json({
                    message:"Succesfully Logged in!",
                    token:token
                })
            }
            return res.status(401).json({
                message:"Auth failed"
            })
        })
    }catch(err){
        console.log(err);
        res.status(409).json({
            message:err.message
        })
    }
};