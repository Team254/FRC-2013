var express = require("express");
var net = require("net");
var app = express();
var server = require('http').createServer(app).listen(8081);
var io = require('socket.io').listen(server);

var HTTP_PORT = 8081;
var TCP_PORT = 41234;

app.use("/js", express.static(__dirname + '/js'));
app.use("/css", express.static(__dirname + '/css'));
app.use("/images", express.static(__dirname + '/images'));
app.get('/', function (req, res) {
  res.sendfile(__dirname + '/index.html');
});

var client = net.connect({host: "127.0.0.1", port: 41234},
    function() { //'connect' listener
  console.log('client connected');
  client.write('world!\r\n');
});

client.on('data', function(data) {
  broadcast(data);
  client.end();
});
client.on('end', function() {
  console.log('client disconnected');
});

// Send a message to all clients
function broadcast(message) {
  message = message.toString("utf8");
  io.sockets.emit('update', message);
  console.log(message);
}

console.log("Listening...");
