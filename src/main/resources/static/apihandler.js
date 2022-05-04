//init global state storage for non-transient data
let units = new Array();
let terrain = new Array();
let players = new Array();

//init websocket
var wsUri = "ws://localhost:8649/socket" ;//+ document.location.host;// + document.location.pathname + "godsofwargame";//TODO oncreation of new matchmaking lobby change this to be dynamic
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

    parseData(evt.data);
    //drawImageText(evt.data);
}

/**
 * A function that takes a message and parses it into valid js objects
 * @param message - a string of the entire incoming message
 */
function parseData (json){
    let message = JSON.parse(json);

    parseChanged(message.changedObjects);
    parseCreated(message.newObjects);
    parseDeleted(message.removedObjects);
}

//Create model object mappers
function StandardUnitModelMapper(json){

    //Assign variables based on available json values
    let movementPlatform;
    if (json.movementPlatform != null) {
        movementPlatform = json.movementPlatform;
    }

    let turretPlatform;
    if(json.turretPlatform != null){
        turretPlatform = json.turretPlatform;
    }

    let meta;
    if(json.meta != null){
        meta = json.meta;
    }

    let fullPositionalCoord;
    if(json.fullPositionalCoord != null){
        fullPositionalCoord = json.fullPositionalCoord;
    }
    return new StandardUnit(movementPlatform,turretPlatform,meta,fullPositionalCoord);
}


function PlayerValuesModelMapper(json){

    let uid = json.uid;

    let points = json.points;

    let currency = json.currency;

    let readyState = json.readyState;

    return new PlayerValues(uid, points, currency,readyState);


}

function CommandStructureModelMapper(json){
    let fullPositionCoord = json.fullPositionalCoord;

    let meta = json.meta;

    return new CommandStructure(meta,fullPositionCoord);
}

function PlayerProfileModelMapper(json){

    let serverRole;
    if(json.serverRole != null){
        serverRole = json.serverRole;
    }

    let uid;
    if(json.uid != null){
        uid = json.uid;
    }

    let teams;
    if(json.teams != null){
        teams = json.teams;
    }

    return new PlayerProfile(serverRole, uid, teams);
}

//parse websocket data
function parseChanged(changedList) {
    for(let i =0; i < changedList.length; i++){

        let gameObject = jsonToObject(changedList); //TODO - create a function to handle the updating of units with this value
        gameObject.updateExisting();
    }
}

function parseCreated(createdList) {
    for(let i =0; i < changedList.length; i++){

        let gameObject = jsonToObject(changedList[i]);
        gameObject.createNew();
    }
}

function parseDeleted(deletedList) {
    for(let i =0; i < changedList.length; i++){

        let gameObject = jsonToObject(changedList[i]);
        gameObject.deleteExisting();
    }
}

//Define gameState objects
class StandardUnit{
    constructor(movementPlatform, turretPlatform, meta, fullPositionalCoord) {
        this.movementPlatform = movementPlatform;
        this.turretPlatform = turretPlatform;
        this.meta = meta; //the instance id of the unit
        this.fullPositionalCoord = fullPositionalCoord; //the location of the unit
    }
    updateExisting(){
        //TODO
    }
    deleteExisting(){
        //TODO
    }
    createNew(){
        addUnitObject(this);
    }
}
class CommandStructure{
    constructor(meta, fullPositionalCoord) {
        this.meta = meta;
        this.fullPositionalCoord = fullPositionalCoord;
    }
    updateExisting(){
        //TODO
    }
    deleteExisting(){
        //TODO
    }
    createNew(){
        addUnitObject(this);
    }

}

class PlayerProfile{
    constructor(serverRole, uid, teams) {
        this.serverRole = serverRole;
        this.uid = uid;
        this.teams = teams;
    }
    updateExisting(){
        //TODO
    }
    deleteExisting(){
        //TODO
    }
    createNew(){
        addPlayerProfile(this);
    }
}
class PlayerValues{
    constructor(uid,points,currency,readyState) {
        this.uid= uid;
        this.points = points;
        this.currency = currency;
        this.readyState = readyState;
    }
    updateExisting(){
        //TODO
    }
    deleteExisting(){
        //TODO
    }
    createNew(){
        console.log("ERROR: PlayerValues is not capable of being a new object")
    }
}

function updateModel(jsonToExistingObject){

}

/** A function that returns a js object
 *
 * @param jsonToModel - a serial object that needs to be transformed from json to a js object
 * @returns {any}
 */
function jsonToObject(jsonToModel){
    switch (jsonToModel.className){
        case ("StandardUnitModel") :
            return new StandardUnitModelMapper(jsonToModel);
            break;
        case ("PlayerValuesModel") :
            return new PlayerValuesModelMapper(jsonToModel);
            break;
        case ("CommandStructureModel") :
            return new CommandStructureModelMapper(jsonToModel);
            break;
        case ("PlayerProfileModel") :
            return new PlayerProfileModelMapper(jsonToModel);
            break;
    }
}

/**
 * simply adds passed parameter to it's relevant list
 * @param gameObject
 */
function addUnitObject(gameObject) {

    units[units.length] = gameObject;
}

/**
 * simply adds passed parameter to terrain list
 * @param newLocation - a new piece of terrain that needs to be added to terrain list
 */
function addTerrain(newLocation){
    terrain[terrain.length] = newLocation;
}

/**
 * simply adds passed parameter players list
 * @param playerProfile
 */
function addPlayerProfile(playerProfile) {
    players[players.length] = playerProfile;
}