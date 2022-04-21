/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var playerID;
var money; //Even if changed by USER server won't care as it keeps it's own copy set to the proper amount
var teamName;
var readyState = "false";

function setID(ID) {
    playerID = ID;
}
function setTeamName(name){
    teamName = name;
}
/*
function playerData() {
    this.playerID;
    this.money;
    this.teamName;
    this.getMoney = function(){
        return money;
    };
}
*/
 