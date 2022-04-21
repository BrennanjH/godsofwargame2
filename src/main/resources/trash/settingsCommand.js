/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Deprecated, remove after confirmation
function settingsCommand() {
    let s = new setting();
    sendText(JSON.stringify(s));
}
function setting(){
    this.className = "settingsCommand";
    this.unitType = "UnitTank";
    this.units = {
        //WARNING setting this to anything other than a blank unit will probably crash the backend.
        unitObject : new Unit()//WARNING This severely limits command variation.
    };
    this.data = {
        info : {
            notReal : 0
        }
    };
}