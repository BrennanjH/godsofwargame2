/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function JSONgameStateUpdater(json){//Properly updates gamestate (lists of units and terrain) for client
    var message = JSON.parse(json);
    console.log("entered into JSONLOGICFINDER");
    //turnOff(message.firstResponseData);
    
    unitHandling(message.unitList);
    playerDataHandling(message.playerInfo);
    propertiesHandling(message.properties);
    territoryHandling(message.territories);
    metaHandling(message.metaData);
    terrainHandling(message.terrainList);// Unlike UnitHandling which runs regardless of Unit presence TerrainHandling doesn't run if empty
}
function territoryHandling(data){
    if (typeof data !== "undefined"){
        if(Object.entries(data).length !== 0){
            territoryMap = data;
        }
    }
}
function playerDataHandling(data){
    if (typeof data !== "undefined"){
        if(Object.entries(data).length !== 0){
            playerID = data.playerMember.Id;
            money = data.money;
            readyState = data.readyState;
            teamName = data.playerMember.factionName;
            console.log(data.playerMember.Id);
            document.getElementById("money").textContent = money;
            
            if(readyState === true){
                
                document.getElementById("readyState").textContent = "Ready";
            } else {
                document.getElementById("readyState").textContent = "Not ready";
            }
            
        }
    }
}
function serverMessageHandling(message){
    console.log(message);
}

function initGrid(){
    setArrayHeight(gameSettings.cols);
    setArrayWidth(gameSettings.rows);
    setImageWidth();
    setImageHeight();
    addMoveEventListener();
}

function unitHandling(list){
    updateUnitList(list);//look in UnitObject.js for related methods and variables
}
function terrainHandling(list){
    if (typeof list !== "undefined"){
        if(Object.entries(list).length !== 0){
            console.log("Setting TerrainList");
            //console.log(list);
            setTerrainMap(list);//TODO change this to only change the terrain objects that conflict with new Terrain (may not be reasonable design choice)

            /*
            for(i =0; i< list.length; i++){
                drawTerrain(list[i]);
            }
            */
        }
    }
}
function propertiesHandling(properties){
    if (typeof properties !== "undefined"){
        if(Object.entries(properties).length !== 0){
            gameSettings = properties;
        }
    }
}
function metaHandling(data) {
    if (typeof data !== "undefined"){
        if(Object.entries(data).length !== 0){
            //reset the html tags so that old data is lost
            let teamData = document.getElementById("teams");
            teamData.innerHTML = "Team Names: ";
            let playerNames = document.getElementById("players");
            playerNames.innerHTML = "Players: ";
            
            //Get the new team information and place it into the tag
            for(let i = 0; i < data.teamList.length; i++){
                teamData.innerHTML = teamData.innerHTML + data.teamList[i].teamName + " || ";
                //get the player information on that team and display it
                for(let j=0; j < data.teamList[i].coalitionMembers.length;j++){
                    playerNames.innerHTML = playerNames.innerHTML + "Name: " + data.teamList[i].coalitionMembers[j].Id
                    + " On Team: " + data.teamList[i].coalitionMembers[j].factionName + " || ";
                }
                
            }
        }
    }
}
/*
//TODO see if there is a way to cut these methods out after first use
function turnOff(message){//this function quickly becomes 
    if (typeof message !== "undefined"){
        if(Object.entries(message).length !== 0){
                console.log("Setting first response data");
                console.log(typeof message);

                setPlayerId(message.ID);

                setTeamName(playerID);

            function setPlayerId(Id){//afterSettingsCommand is executed the ID should return along with SettingsData
                if(typeof Id !== "undefined"){
                    setID(Id);
                }
            }   

        }
    }
}
 * 
 */