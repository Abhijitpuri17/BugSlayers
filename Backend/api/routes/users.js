const express = require('express');
const UserVerification = require('../models/userVerification');
const router = express.Router();
const UserControls = require('../controllers/users')

router.post('/signup',UserControls.create_user);
router.post('/login',UserControls.user_login);
router.patch('/edit/:userName',UserControls.edit_user);

//verify email
router.get('/verify/:userId/:uniqueString',UserControls.verify_user);
router.get('/verified',UserControls.verified_user)
module.exports = router;