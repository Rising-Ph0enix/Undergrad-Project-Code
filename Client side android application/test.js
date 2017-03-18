var routeNo = "";
var currentStop = "";
var destinationStop = "";
var latitude = 0.0;
var longitude = 0.0;
var MongoClient = require('mongodb').MongoClient;
// mongodb://<dbuser>:<dbpassword>@ds161099.mlab.com:61099/tnstc
//var url = 'mongodb://localhost/TNSTC';
// heroku config:set MONGOLAB_URI='mongodb://ganesh:venkat007@ds161099.mlab.com:61099/tnstc'
var url = 'mongodb://ganesh:venkat007@ds161099.mlab.com:61099/tnstc'
var express = require("express");
var myParser = require("body-parser");
var app = express();
app.use(myParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.post("/gps",function (req, res){
    console.log("Welcome Master Wizard Ganesh to the gps service");
    MongoClient.connect(url, function(err, db){
        routeNo = req.body.routeNo;
        latitude = req.body.latitude;
        longitude = req.body.longitude;
        db.collection('BusInfo').update(
          { "RouteNo" : routeNo },
          {
            $set: {
              "Latitude" : latitude,
              "Longitude" : longitude
            }
          },
          { upsert: true });
    });
    res.end();
});
app.post("/ticket",function (req, res){
    console.log("Welcome Master Wizard Ganesh to the ticket service");
    MongoClient.connect(url, function(err, db){
      routeNo = req.body.routeNo;
      currentStop = req.body.currentStop;
      destinationStop = req.body.destinationStop;
      db.collection('BusInfo').update(
        { "RouteNo" : routeNo },
        {
          $push: {
            "Ticket" : {
                  "From" : currentStop,
                  "To" : destinationStop
              }
            }
        },
        { upsert: true });
    });
    res.end();
});
app.post("/currentStop",function (req, res){
    console.log("Welcome Master Wizard Ganesh to the update currentStop service");
    MongoClient.connect(url, function(err, db){
    routeNo = req.body.routeNo;
    currentStop = req.body.currentStop;
    db.collection('BusInfo').update(
      { "RouteNo" : routeNo },
      {  $set: {
            "CurrentStop" : currentStop
          }
      },
      { upsert: true });
    db.collection('BusInfo').update(
        {"RouteNo" : routeNo },
        { $pull : {
            "Ticket" : {"To" : currentStop }
          }
        });
    });
    res.end();
});
app.post("/getLocation",function (req, res){
    console.log("Welcome Master Wizard Ganesh to the update currentStop service");
    res.writeHead(200, {"Content-Type": "application/json"});
    MongoClient.connect(url, function(err, db){
    routeNo = req.body.routeNo;
    db.collection('BusInfo').findOne({"RouteNo" : routeNo}, (function(err, docs){
          latitude= docs.Latitude;
        });
    });
    res.json({ "Latitude": latitude });
    res.end();
});
app.listen(process.env.PORT || 5000);
