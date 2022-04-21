/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
var hasClicked = false;
var desiredReadyState = false;

var x1,y1,x2,y2;
//TODO add hotkeys and button prompts to manually change event listeners for commands
function placeUnit(event){
    setXYPositions(event);
    setNextUnitPos(x1,y1);
    var newUnit = new createUnitCommand();
    unitCreationPossible(newUnit);

    removeUnitEventListener();
    addMoveEventListener(); //TODO when manual buttons are added remove this
}
function moveClick(event){//TODO create method to check if a unit was selected
    let pressedX = mouseCordsRelativeToCanvasX(event);
    let pressedY = mouseCordsRelativeToCanvasY(event);
    console.log("moveClick called");
    console.log(hasClicked);
    if (hasClicked === false ){
        
        hasClicked = true;
        
        x1 = getTrueMousePositionx(pressedX);
        y1 = getTrueMousePositiony(pressedY);
        //console.log("GEttings x1, y1");
        //console.log(x1 + " " + y1);
        if (getFirstUnitIndex(x1,y1) === -1){
            //console.log("Got inside second if ");
            //console.log(getFirstUnitIndex(x1,y1));
            hasClicked = false;
        } else{
            tintSquare(x1,y1);
        }
        
        
    }
    else{
        hasClicked = false;
        x2 = getTrueMousePositionx(pressedX);
        y2 = getTrueMousePositiony(pressedY);
        
        //onClick(x2,y2);//no real need to tint since it will immediatly clear after clicking
        
        movePossibleClient();
        
       
       //fillSpot(y1,x1); //remove tinting if needed but for now keep it
    }
   //console.log(getUnitList());
   
}
function mouseCordsRelativeToCanvasX(mouseEvent){
    
    let cRect = canvas.getBoundingClientRect();
    let canvasX = Math.round(mouseEvent.clientX - cRect.left);
    console.log("canvasX: " + canvasX);
    return canvasX;
    
}
function mouseCordsRelativeToCanvasY(mouseEvent){
   
    let cRect = canvas.getBoundingClientRect();
    let canvasY = Math.round(mouseEvent.clientY - cRect.top);
    console.log("canvasY: " + canvasY);
    return canvasY;
    
}
function selectSquare(event){//TODO create a display that will show characteristics of pressed tile
    
}
//redundant solution but ok
function setXYPositions(event){
    let grid = canvas.getBoundingClientRect();
    
    console.log(event.clientX);
    x1 = getTrueMousePositionx(event.clientX - grid.left);
    y1 = getTrueMousePositiony(event.clientY - grid.top);
    console.log("x: " + x1 + " y: " + y1);
}
function getTrueMousePositionx(x){ //finds the top left corner of pressed square x
   
    let setx = Math.trunc(x/getImageWidth());
    console.log("trueX: " + setx);
    return setx;
     
}
function getTrueMousePositiony(y){ //finds the top left corner of pressed squares y
    
    let sety = Math.trunc(y/getImageHeight());
    console.log("trueY: " + sety);
    return sety;
     
     
}

function addMoveEventListener(){
    console.log("Switched to Move event Listener");
    canvas.addEventListener("click", moveClick ,false);
}
function removeMoveListener(){
    console.log("removed Move event Listener");
    canvas.removeEventListener("click", moveClick,false);
}
function addUnitEventListener(){
    console.log("switched to add Unit Event Listener");
    canvas.addEventListener("click", placeUnit,false);
}
function removeUnitEventListener(){
    console.log("removeUnitEvent");
    canvas.removeEventListener("click", placeUnit,false);
}
function addselectSquareEventListener(){
    console.log("addSquareEvent");
    canvas.addEventListener("click", selectSquare,false);
}
function removeselectSquareEventListener(){
    console.log("removeSelectSquare");
    canvas.removeEventListener("click", selectSquare, false);
}
function removeAllListeners(){
    console.log("removeAllListeners");
    canvas.removeEventListener("click", selectSquare, false);
    canvas.removeEventListener("click", moveClick,false);
    canvas.removeEventListener("click", placeUnit,false);
}
function readyUp(){
    desiredReadyState = !(readyState === true);
    console.log("readyState: " + desiredReadyState);
    let command  = new readyCommand(desiredReadyState);
    readyStatePossible(command);
    //ready = true; ready is set to true when the servers response returns and is stored in playerdata object
    
}
function joinTeam(){
    let a = document.getElementById("tName").value;
    console.log(a);
    var joinTeam = new joinCoalitionCommand(a);
    //Performvalidation if it ever is needed in future here
    
    //Set new teamName on both front and backend
    
    setTeamName(a);
    console.log(teamName);
    commandPossibleServer(joinTeam);
}
function showPlayers(){
    
}