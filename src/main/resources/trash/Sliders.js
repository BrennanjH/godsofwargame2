/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var speedSlide = document.getElementById("SpeedSlider");
var speedPut = document.getElementById("speedVal");
var speedPricePut = document.getElementById("speedPrice");

var rangeSlide = document.getElementById("RangeSlider");
var rangePut = document.getElementById("rangeVal");
var rangePricePut = document.getElementById("rangePrice");

var healthSlide = document.getElementById("HealthSlider");
var healthPut = document.getElementById("healthVal");
var healthPricePut = document.getElementById("healthPrice");

var damageSlide = document.getElementById("DamageSlider");
var damagePut = document.getElementById("damageVal");
var damagePricePut = document.getElementById("damagePrice");

var totalPricePut = document.getElementById("UnitPrice");

speedPut.innerHTML = speedSlide.value;
speedPricePut.innerHTML = speedSlide.value * 30;

rangePut.innerHTML = rangeSlide.value;
rangePricePut.innerHTML = rangeSlide.value * 50;

healthPut.innerHTML = healthSlide.value;
healthPricePut.innerHTML = healthSlide.value * 10;

damagePut.innerHTML = damageSlide.value;
damagePricePut.innerHTML = damageSlide.value * 10;



rangeSlide.oninput = function() {
  rangePut.innerHTML = this.value;
  rangePricePut.innerHTML = this.value * 50;
  updateUnitPrice();
};

speedSlide.oninput = function() {
  speedPut.innerHTML = this.value;
  speedPricePut.innerHTML = this.value * 30;
  updateUnitPrice();
};

healthSlide.oninput = function() {
  healthPut.innerHTML = this.value;
  healthPricePut.innerHTML = this.value * 10;
  updateUnitPrice();
};

damageSlide.oninput = function() {
  damagePut.innerHTML = this.value;
  damagePricePut.innerHTML = this.value * 10;
  updateUnitPrice();
};
function updateUnitPrice(){
    totalPricePut.innerHTML = parseInt(findTypePrice()) +
        parseInt(rangePricePut.innerHTML) + parseInt(speedPricePut.innerHTML) +
        parseInt(healthPricePut.innerHTML) + parseInt(damagePricePut.innerHTML);
}
function getslideDamage(){
    return damageSlide.value;
}
function getslideHealth(){
    return healthSlide.value;
}
function getslideRange(){
    return rangeSlide.value;
}
function getslideSpeed(){
    return speedSlide.value;
}
function findTypePrice(){
    switch(type){
        case "UnitTank":
            return 50;
            break;
        case "UnitCommandStructure":
            return 550;
            break;
        case "UnitAntiAir":
            return 50;
            break;
        case "UnitPlane":
            return 100;
            break;
    }
}