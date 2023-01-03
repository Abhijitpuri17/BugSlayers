const mongoose = require('mongoose');

const userVerificationSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userId:{
        type:String,
        required:true
    },
    uniqueString:{
        type:String,
        required:true
    },
    createdAt: {
        type:Date,
        required:true,
    },
    expiresAt: {
        type:Date,
    }
});

module.exports = mongoose.model('UserVerification', userVerificationSchema);