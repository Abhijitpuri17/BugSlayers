const mongoose = require('mongoose');
const skillSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userName:{
        type:String,
        required:true
    },
    domainName:{
        type:String,
        required:true
    },
    skillName:{
        type:String,
        required:true
    },
    yearsOfExp:{
        type:Number
    },
    skillLevel:{
        type:String
    },
    endorsment:{
        type:Number,
        default:0
    },
    endorsedBy:[String]
});

module.exports = mongoose.model('Skill', skillSchema);