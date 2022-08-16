//init global state storage for non-transient data
let units = new Array();
let terrain = new Array();
let players = new Array();
let commandUnits = new Array();


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
    let playerValues = json.playerValues;

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

    return new PlayerProfile(playerValues, serverRole, uid, teams);
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
        for(let i = 0; i < units[units.length] ; i++){
            if(compareInstanceMeta(this.meta, units[i].meta) ){
                units[i] = this;
                //end function if unit does exist
                return;
            }
        }
    }

    deleteExisting(){
        this.deleteStandardUnit(this);
    }

    deleteStandardUnit(standardUnit) {
        for(let i = 0; i < units[units.length] ; i++){
            if(compareInstanceMeta(standardUnit.meta, units[i].meta) ){
                units[i] = null;
                cleanArray(units);
                //end function if unit does exist
                return;
            }
        }
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
        for(let i = 0; i < commandUnits[commandUnits.length] ; i++){
            if(compareInstanceMeta(this.meta, commandUnits[i].meta) ){
                commandUnits[i] = this;
                //end function if unit does exist
                return;
            }
        }
    }

    deleteExisting(){
        for(let i = 0; i < commandUnits[commandUnits.length] ; i++){
            if(compareInstanceMeta(this.meta, commandUnits[i].meta) ){
                commandUnits[i] = null;
                cleanArray(commandUnits);
                return;
            }
        }
    }
    createNew(){
        addCommandUnitObject(this);
    }

}
class PlayerProfile {
    constructor(playerValues, serverRole, uid, teams) {
        this.playerValues = playerValues;
        this.serverRole = serverRole;
        this.uid = uid;
        this.teams = teams;
    }
    updateExisting(){
        for(let i = 0; i < players[players.length] ; i++){
            if(compareUserIdentity(this.uid, players[i].uid) ){ //doesn't compare playerValue uid since that's the same
                players[i] = this;
                return;
            }
        }
    }
    deleteExisting(){
        for(let i = 0; i < players[players.length] ; i++){
            if(compareUserIdentity(this.uid, players[i].uid) ){ //doesn't compare playerValue uid since that's the same
                players[i] = null;
                //end function if unit does exist
                cleanArray(players);
                return;
            }
        }
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
        for(let i = 0; i < players[players.length] ; i++){
            if(compareUserIdentity(this.uid, players[i].uid) ){ //doesn't compare playerValue uid since that's the same
                players[i].playerValues = this;
                return;
            }
        }
    }
    deleteExisting(){
        console.log("ERROR: PlayerValues is not capable of being removed")
    }
    createNew(){
        console.log("ERROR: PlayerValues is not capable of being a new object")
    }
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
 * simply adds passed parameter to its relevant list
 * @param gameObject
 */
function addUnitObject(gameObject) {

    units[units.length] = gameObject;
}

/**
 * simply adds passed parameter to its relevant list
 * @param gameObject
 */
function addCommandUnitObject(gameObject){
    commandUnits[commandUnits.length] = gameObject;
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

/**
 * compares two instanceIds, returns boolean values, parameter entry order doesn't matter
 * @param meta - the first instanceId
 * @param meta2 - the second instanceId
 * @returns {boolean} - true if equal false if different
 */
function compareInstanceMeta(meta, meta2) {
    if (meta.ownerNickName === meta2.ownerNickName && meta.instanceId === meta2.instanceId) {
        return true;
    }
    return false;
}

/**
 * compares two UserIdentities, returns boolean values, parameter entry order doesn't matter
 * @param id1 - the first id to compare
 * @param id2 - the second id to compare
 * @returns {boolean} - true if equal false if different
 */
function compareUserIdentity(id1, id2){
    if( id1.nickname === id2.nickname && id1.id === id2.id ){
        return true;
    }
    return false;
}

/**
 * A method that removes null values from an array and properly shifts remaining elements down
 * @param arrayToClean - the array that presumably has null values;
 */
function cleanArray(arrayToClean){
    let tempArray = new Array();
    //Create fill array with old values
    for(let i = 0, x = 0; i < arrayToClean.length;i++){
        if(arrayToClean[i] !== null ){
            tempArray[x] = arrayToClean[i];
            x++;
        }
    }
    arrayToClean = tempArray;
    return arrayToClean;
}