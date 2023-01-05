const Skill = require('../models/skill');
const User = require('../models/user');
const mongoose = require('mongoose');

exports.get_user_skills= async(req,res,next)=>{
    try{
        const user = await Skill.find({userName:req.params.userName});
        console.log(user);
        res.status(200).json({
            result:user,
            message:'Getting all skills'
        })
    }catch(err){
        console.log(err);
        return res.status(404).json({
            message:"error while getting  skills",
            error:err
        })
    }
    
}

exports.post_user_skill = async(req,res,next)=>{
    try{    
        const isUser = await User.find({userName:req.params.userName});
        console.log(isUser.length);
        if(isUser.length > 0){
            console.log(req.body);
            try{
                const username = req.params.userName;
                const skill =await Skill.create({
                    _id: mongoose.Types.ObjectId(),
                    userName:username,
                    domainName:req.body.domainName,
                    skillName:req.body.skillName,
                    yearsOfExp:req.body.yearsOfExp,
                    skillLevel:req.body.skillLevel,
                });
                console.log("e");
                // const result = await skill.save();
                return res.status(201).json({
                    message:'adding new skill',
                    skill:skill
                })
            }catch(err){
                console.log(err);
                return res.status(404).json({
                    message:"error in adding new skill",
                    error:err 
                })
            }
            
        }
        else{
            return res.status(404).json({
                message:"error in finding"
            })
        }
    }catch(err){
        return res.status(404).json({
            message:"Error in finding user!",
            error:err
        })
    }   
}