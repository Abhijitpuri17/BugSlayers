const express = require('express');
const Skill = require('../models/skill');
const User = require('../models/user');
const router = express.Router();
const mongoose = require('mongoose');
const SkillData = require('../controllers/skills');


router.get('/:userName',SkillData.get_user_skills);

router.post('/:userName',SkillData.post_user_skill);

router.get('/:skillId',(req,res,next)=>{
    const id = req.params.skillId;
    res.status(200).json({
        id:id
    })
});

router.patch('/:skillId',(req,res,next)=>{
    const id = req.params.skillId;
    res.status(200).json({
        id:id
    })
});

router.delete('/:skillId',(req,res,next)=>{
    const id = req.params.skillId;
    res.status(200).json({
        id:id
    })
});

module.exports = router;