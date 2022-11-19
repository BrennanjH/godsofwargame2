/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8649/socket";//TODO oncreation of new matchmaking lobby change this to be dynamic
var websocket = new WebSocket(wsUri);


websocket.onError = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
websocket.onmessage = function(evt) { onMessage(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
function onMessage(evt) {
    console.log("received: " + evt.data);
}