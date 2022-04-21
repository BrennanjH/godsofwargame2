/* 
    Methods in this javascript file send Unit information for server side confirmation after doing client side check
 */
function unitCreationPossible(unit){
    
    console.log("unit creation possible entered");
    sendText(JSON.stringify(unit));
}
function movePossibleServer(command){//Sends information to Server for processing move commands
    
    sendText(JSON.stringify(command));
  
}
function movePossibleClient(){//runs first then sends a check to server
    let unit = findZero(getUnitsAt(x1,y1));
    console.log(unit.uzPos + " z pos");
    //Validate the unit object and terrainRules with move request
    console.log(unit);
    if (typeof unit !== undefined && compareTerrainToUnit(getTerrainIndex(unit.uxPos, unit.uyPos) , unit) && unit.OWNER === playerID) {
        let movement = convertTerrainToInt(unit);
        let grid = new PF.Grid(movement);
        let finder = new PF.AStarFinder({diagonalMovement: PF.DiagonalMovement.Always});
        let path = finder.findPath(unit.uxPos, unit.uyPos, x2, y2, grid); //I used unit.val because the pathing is related to that unit as opposed to the x1,y1 coords
        let moveUnit = new moveUnitCommand(unit, path);
        console.log("pathing results");
        console.log(path);
        movePossibleServer(moveUnit);
    } else {
        console.log("Unit Movement Failed");
    }
    
}
function readyStatePossible(readyStateCommand){ //Should perform logic to get ready state to become inverted
    //Desired logic is not yet designed
    commandPossibleServer(readyStateCommand);
}
function compareTerrainToUnit(terrain, unit){
    for(j=0; j<unit.terrainRules.badSpots.length; j++){
        if( terrain.type === unit.terrainRules.badSpots[j]) {
            return false;
        }
    }
    return true;
}
function commandPossibleServer(command){
    console.log(command);
    sendText(JSON.stringify(command));
}