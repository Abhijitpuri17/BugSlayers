const express = require('express');
const router = express.Router();
const UserControls = require('../controllers/users')

router.post('/signup',UserControls.create_user);
router.post('/login',UserControls.user_login);

module.exports = router;