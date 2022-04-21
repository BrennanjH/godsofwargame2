/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* global playerID */

var unitList = new Array();
//the following are temp values that are assigned upon button press that get used by constructor

var speed;
var health;
var range;
var damage;
var TerrainRules;
//following three get assigned when the unit is actually placed onto the grid
var xPos;
var yPos;
var zPos;
var type = "UnitTank"; //default to tank so if the user forgets to input type it won't break server
var property;
 
function Unit() {
    this.UnitType = type;//will be set by methods later
    console.log(type);
    this.terrainRules = TerrainRules;
    this.OWNER = playerID;
    this.uhealth = health;
    this.uspeed = speed;
    this.urange = range;
    this.udamage = damage;
    this.property = property;
    this.uxPos = xPos;
    this.uyPos = yPos;
    this.uzPos = zPos;//this will also determine which unit is rendered. by default the first unit is chosen. //Currently obsolete

}

 function setNextUnitPos(mouseX,mouseY){
     xPos = mouseX;
     yPos = mouseY;
     
     //setzPos(mouseX,mouseY);
 }
 function updateUnitList(list){
     unitList = list;//Not sure if x,or y comes first
     /*
     console.log("updated unitList: ");
     console.log(unitList);
     */
 }
function setClass(setType){
    classType = setType;
}
function setSpeed(setVal){
    speed = setVal;
}
function setHealth(setVal){
    health = setVal;
}
function setRange(setVal){
    range = setVal;
}
function setDamage(setVal){
    damage = setVal;
}
function getClassType(){
    return classType;
}
function getRange(){
    return range;
}
function getHealth(){
    return health;
}
function getDamage(){
    return damage;
}
function getSpeed(){
    return speed;
}
function getUnitList(){
    return unitList;
}
function getFirstUnitIndex(x,y){
    //console.log(x + " " + y);
    for(let i=0; i< unitList.length;i++){
        //console.log("Inside For " + unitList[i].uxPos + " " + unitList[i].uyPos);
        if( unitList[i].uxPos === x && unitList[i].uyPos === y) {
            return i;
        }
    }
    return -1;
}
function addUnit(unit){
    unitList[xPos][yPos][zPos] = unit;
}
function setType(classname){
    type = classname;
}
/*
function setzPos(x,y){//a function which returns the first available slot for a unit in said cord, if no slot is available it will fail
    
    for (i = 0; i<unitList[x][y].length;i++){
        console.log("got this far");
        if (unitList[x][y][i] !== null){
            
            console.log(unitList[x][y][i]);
        }
        else{
            console.log("about 2 set z");
            zPos=i;
            break;
        }
    }
}
 
 */
function getUnitsAt(x, y) {
    console.log("**********************************");
    console.log("x: " + x + " y: " + y);
    var zArray = [];
    for(let i=0 , z=0; i<unitList.length; i++) {
        console.log(z + " z " + i + " i");
        console.log(unitList[i].uxPos + " ux");
        console.log(unitList[i].uyPos + " uy");
        if(unitList[i].uxPos === x && unitList[i].uyPos === y){
            console.log("Entered inside if");
            zArray[z] = unitList[i];
            z++;
        }
    }
    console.log("zArray");
    console.log(zArray);
    console.log("**********************************");
    return zArray;
}
function findZero(unitArray){
    console.log(unitArray);
    for(let i=0; i<unitArray.length;i++){
        if(unitArray[i].uzPos === 0){
            //returns the unit with zPos == 0;
            return unitArray[i]; 
        }
    }
    console.log("findZero: No return");
    //Returns nothing if no unit matches 
}