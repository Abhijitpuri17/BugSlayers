const express = require('express');
const skillRoutes = require('./api/routes/skills'); 
const userRoutes = require('./api/routes/users'); 
const morgan = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const connectDB = require('./db');
require("dotenv").config();

connectDB();
mongoose.Promise = global.Promise;

const app = express();

app.use(morgan('dev'));
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

app.use((req,res,next)=>{
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Headers',
    "Origin, X-Requested-With, Content-type, Accept, Authorization");
    if(req.method ==='OPTIONS'){
        res.header('Access-Control-Allow-Methods','PUT, POST, PATCH, DELETE, GET');
        return res.status(200).json({});
    }
    next();
})

app.use('/skills',skillRoutes);
app.use('/users',userRoutes);

app.use((req,res,next)=>{
    const error = new Error('Not Found');
    error.status=404;
    next(error);
});

app.use((error,req,res,next)=>{
     res.status(error.status || 500);
     res.json({
        error:{
            message:error.message
        }
     })
})
module.exports = app;