/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tankButtonHold = false;
var planeButtonHold = false;
var UCSButtonHold = false;
var AAButtonHold = false;
//TODO Find or make actual images for both units and buttons to press down and become
//////////////The Following code is for Tanks/////////////////////////////////////////////////////////////////////////////////////


function setNextUnit(){

     //setClass("tank");//Currently unused as since input is impossible
     setSpeed(getslideSpeed());
     setHealth(getslideHealth());
     setRange(getslideRange());
     setDamage(getslideDamage());
     removeMoveListener();
     setType(type);
     
     addUnitEventListener();
     console.log("setNextUnitCalled");
 }
/////////////////////The following code is for Tanks//////////////////////////////////////////////////////////////////////////
function handleMDownTank() { //button press for unit
    document.images["tankButton"].src = "wolfmeme.PNG";
  
    return true;
}

function handleMUpTank(){
    resetImages();
    tankButtonHold = true;
    type = "UnitTank";
    updateUnitPrice();
    //property = "GROUND"; //really doesn't matter as it is overwritten by java
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["tankButton"].src = "placeholder.png";
  
    return true;
}
function changeImageTank(){
    if(!tankButtonHold){
        document.images["tankButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackTank(){
    if(!tankButtonHold){
        document.images["tankButton"].src = "UnitTank.png";
    }
    return true;
}
/////////////////////The following code is for Planes//////////////////////////////////////////////////////////////////////////
function handleMDownPlane() //button press for unit
{
    document.images["planeButton"].src = "wolfmeme.PNG";
  
  //Note that there are no places a plane can't go so there are no rules here.
    return true;
}

function handleMUpPlane() 
{
    resetImages();
    planeButtonHold = true;
    type = "UnitPlane";
    //property = "air";
    updateUnitPrice();
    document.images["planeButton"].src= "placeholder.png";
    return true;
}
function changeImagePlane()
{
    
    if(!planeButtonHold){
        document.images["planeButton"].src= "plane_when_mOver.png";
    }
    return true;
}
function changeImageBackPlane()
{
    if(!planeButtonHold){
        document.images["planeButton"].src = "UnitPlane.png";
    }
    return true;
}
/////////////////////The Following code is for Anti-air//////////////////////////////////////////////////////////////////////////
function handleMDownAA() //button press for unit
{   
    document.images["AAButton"].src = "wolfmeme.PNG";
  
    return true;
}

function handleMUpAA() 
{
    resetImages();
    AAButtonHold = true;
    type = "UnitAntiAir";
    property = "ground";
    updateUnitPrice();
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["AAButton"].src= "placeholder.png";
    return true;
}
function changeImageAA()
{
    if(!AAButtonHold){
        document.images["AAButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackAA()
{
    if(!AAButtonHold){
        document.images["AAButton"].src = "UnitAntiAir.png";
    }
    return true;
}
/////////////////////The Following Code is for UnitCommandStructures ///////////////////////////////////////////////////////
function handleMDownUCS(){
    document.images["commandUnitButton"].src = "wolfmeme.PNG";
    
    return true;
}
function handleMUpUCS(){
    resetImages();
    UCSButtonHold = true;
    type = "UnitCommandStructure";
    updateUnitPrice();
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["commandUnitButton"].src = "placeholder.png";
    
    return true;
}
function changeImageUCS(){
    if(!UCSButtonHold){
        document.images["commandUnitButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackUCS(){
    if(!UCSButtonHold){
        document.images["commandUnitButton"].src = "UnitCommandStructure.png";
    }
    return true;
}
//Images need to go back to their original when a new one is pressed.
function resetImages(){
    resetButtonHolders();
    changeImageBackTank();
    changeImageBackAA();
    changeImageBackPlane();
    changeImageBackUCS();
}
function resetButtonHolders(){
    tankButtonHold = false;
    planeButtonHold = false;
    UCSButtonHold = false;
    AAButtonHold = false;
}