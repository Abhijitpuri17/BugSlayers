const express = require('express');
const router = express.Router();

router.get('/',(req,res,next)=>{
    res.status(200).json({
        message:'Getting all skills'
    })
});

router.post('/',(req,res,next)=>{
    const skill = {
        name: req.body.name,
        experience:req.body.experience
    }
    res.status(201).json({
        message:'adding new skill',
        skill:skill
    })
});

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