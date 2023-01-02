const express = require('express');
const skillRoutes = require('./api/routes/skills'); 
const morgan = require('morgan');
const app = express();

app.use(morgan('dev'));

app.use('/skills',skillRoutes);

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