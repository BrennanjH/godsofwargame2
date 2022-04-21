/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global context, unitList */

var myimgobj = document.images["jsbutton"];
//var canvas = document.getElementById("myCanvas");
//var context = canvas.getContext("2d");
var arrayHeight;
var arrayWidth;
var width;
var height;
var TerrainMap = new Array();
var territoryMap = new Array();


function drawUnit(Unit){
    //console.log("drawUnitcalled");
    //console.log(Unit.uzPos);
    if (Unit.uzPos === 0){
        let img = new Image();
        img.src = Unit.UnitType + ".png";
        context.drawImage(img, Unit.uxPos*width, Unit.uyPos*height,width,height );
        
        //Change Image src for Turret
        /*
        img.src = Unit.UnitType + "-Turret.png";
        context.drawImage(img, Unit.uxPos*width, Unit.uyPos*height,width,height);
        console.log(playerID);
        */
        
        if( Unit.ownerMember.factionName === teamName && Unit.ownerMember.Id !== playerID){
            context.fillStyle = 'rgba(255, 0, 255, .3)';
            context.fillRect(Unit.uxPos*width, Unit.uyPos*height,width,height);
        }
        else if(Unit.ownerMember.Id !== playerID){
            
            context.fillStyle = 'rgba(75, 75, 75, 0.5)';
            context.fillRect(Unit.uxPos*width, Unit.uyPos*height,width,height);
        } 
    }
}


function tintSquare(x,y){//adds tinting to clicked square
    context.fillStyle = "rgba(255,165,0,.5)";
    context.fillRect(x* width,y * height,width,height);
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//getters and setters down yonder
function setArrayHeight(gameHeight){
    arrayHeight = gameHeight;
}
function setArrayWidth(gameWidth){
    arrayWidth = gameWidth;
}
function getHeight(){
    return arrayHeight;
}
function getWidth(){
    return arrayWidth;
}

function setImageWidth() {
    width = canvas.width/arrayWidth;
}
function setImageHeight(){
    height = canvas.height/arrayHeight;
}
function getImageWidth(){
    return width;
}
function getImageHeight(){
    return height;
}
function setTerrainMap(ArrayList){

    TerrainMap = ArrayList;
    //console.log(TerrainMap);
}
function getTerrainMap(){
    return TerrainMap;
}
function getTerrainIndex(x,y){
    for(let i=0; i<TerrainMap.length;i++){
        if(TerrainMap[i].xVal === x && TerrainMap[i] === y){
            return i;
        }
    }
    return -1;
}
function refresh(){
    //console.log("refresh Called");
    initGrid();
    for(i=0;i<TerrainMap.length;i++){
        //console.log("refresh Draw Terrain Called");
        //console.log(TerrainMap[i]);
        drawTerrain(TerrainMap[i]);
        //console.log(TerrainMap[i]);
    }
        
    for(let i = 0; i < territoryMap.length ;i++ ){
        
        //console.log(territoryMap[i]);
        drawTerritory(territoryMap[i]);
    }
    
    for(i=0;i<unitList.length;i++){
        //console.log(unitList[i]);
        /*
        var movement = convertTerrainToInt(unitList[i]);
        var grid = new PF.Grid(movement);
        var finder = new PF.AStarFinder();
        var path = finder.findPath(unitList[i].xVal, unitList[i].yVal)
         * 
         */
        drawUnit(unitList[i]);
    }
    
}
function drawTerritory(territory){
    if(territory !== null){
        //Identify if player is on team of land
        let isOnTeam = (teamName === territory.Owner.teamName);
        /*
        console.log("teamname of territ: " + territory.Owner.teamName);
        console.log("teamname on client: "  + teamName);
        console.log(isOnTeam);
         * 
         */
        //get tint location
        let x = territory.pos.xPos;
        let y = territory.pos.yPos;
        //Draw tint
        if(isOnTeam){
            context.fillStyle = "rgba(255,0,0,.5)";
            context.fillRect(x* width,y * height,width,height);
        } else{
            context.fillStyle = "rgba(0,0,0,.5)";
            context.fillRect(x* width,y * height,width,height);
        }
        
        
    }
}

function drawTerrain(terrain){
    let unitImage = new Image();
    //console.log(terrain.type.toString() + ".png");
    //console.log(terrain.type + ".png");
    
    unitImage.src = terrain.type.toString() + ".png";
    //console.log(width);
    context.drawImage(unitImage,(terrain.xVal*width),(terrain.yVal*height) ,width, height);
}