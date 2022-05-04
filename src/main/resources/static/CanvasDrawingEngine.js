
//Get a canvas object and context so that drawings can be placed on canvas
let canvas = document.getElementById("board");
let context = canvas.getContext("2d");

let screenLength = 0;
let screenHeight = 0;

//Any field with an all caps ending in its name is a specification of units defined by the all caps
    //A view is the canvas' interpretation of the game map
//The length of the x and y of the entire map in tiles (terrain squares)
let totalViewXLength_TILES = 100;
let totalViewYLength_TILES = 100;

//The current views top left corner in relation to Origin (0,0) measured in Tiles
let distanceOfXFromOrigin_TILES = 0;
let distanceOfYFromOrigin_TILES = 0;
//Current views size (measured in
let viewXLength_TILES = 10;
let viewYLength_TILES = 10;
//How many pixels a tile is in the current view
let viewTileXLength_PIXELS = context.width / viewXLength_TILES;
let viewTileYLength_PIXELS = context.height / viewYLength_TILES;




function Coordinates(x,y){
    this.x = x;
    this.y = y;
}

//Instill gameState objects into a map



//Interact with canvas
//When the mouse presses down perform some action
canvas.onmousedown = function (evt){
    console.log("Testing new feature");
}
canvas.onmouseenter = function (evt){
    canvas.scrollIntoView();
    if(screenLength != window.screen.availWidth || screenHeight != window.screen.availHeight){
        console.log("redrawing canvas")
        let newCanvasSizex = window.screen.availWidth * (3/5);
        let newCanvasSizey = window.screen.availHeight * (2/3);

        canvas.style.width = newCanvasSizex.toString() + "px";
        canvas.style.height = newCanvasSizey.toString() + "px";
        redrawView();
    }

    window.onscroll = () => {
        window.scroll(0,0);
    };
}
canvas.onmouseleave = () => {
    window.onscroll = "";
}

/**
 * Uses the current view values to redraw the view to fit
 */
function redrawView(){

}

function renderImage(image, fullViewTILESX, fullViewTILESY){
    //Check if x and y values fit inside current view
    if((fullViewTILESX >= distanceOfXFromOrigin_TILES && fullViewTILESX <= viewXLength_TILES)
        && ( fullViewTILESY >= distanceOfYFromOrigin_TILES && fullViewTILESY <= viewYLength_TILES)){

        //decide image size for current view
        let tilePixelWidth = canvas.width/viewXLength_TILES;
        let tilePixelHeight = canvas.height / viewYLength_TILES;

        //draw image onto current view
        context.drawImage(image,
            (fullViewTILESX - distanceOfXFromOrigin_TILES) * tilePixelWidth, //Image position x
            (fullViewTILESY - distanceOfYFromOrigin_TILES) * tilePixelHeight,//Image position Y
            tilePixelWidth, //Image width & height
            tilePixelHeight);

    } else {
        console.log("image Passed did not fit in view");
        console.log("image x: " + fullViewTILESX.toString());
        console.log("image y: " + fullViewTILESY.toString());
    }

}

//Transform mouse press x,y pos on canvas to the game boards x,y coords
function translateMousePixelsToCurrentViewGrid(mouseX,mouseY){
    //Translate mouseX (in pixels) to TILES in reference to current context
    let mouseXTILES = Math.trunc(  mouseX / viewTileXLength_PIXELS );

    //Translate mouseY (in pixels) to TILES in reference to current context
    let mouseYTILES = Math.trunc(  mouseY / viewTileYLength_PIXELS );

    return new Coordinates(mouseXTILES,mouseYTILES);
}
function translateMousePixelsToFullViewGrid(mouseX,mouseY){
    let coords = translateMousePixelsToCurrentViewGrid(mouseX,mouseY);

    //Change x value to be a measure of the total views tiles by adding the offset caused by the view
    coords.x = distanceOfXFromOrigin_TILES + coords.x;

    //change y value to be a measure of the total views tiles by adding the offset caused by the view
    coords.y = distanceOfYFromOrigin_TILES + coords.y;

    return coords;
}

//Scroll in and out

/**
 * Alters the fields to specify a views boundaries but does not redraw them directly
 * @param viewWidthTILES - The width of the new view
 * @param viewHeightTILES - the height of the new view
 */
function alterViewSize(viewWidthTILES, viewHeightTILES){
    //Set the current views tile length in pixels
    viewXLength_TILES = viewWidthTILES;
    viewYLength_TILES = viewHeightTILES;

    //Set the current views tile size in pixels
    viewTileXLength_PIXELS = context.width / viewXLength_TILES;
    viewTileYLength_PIXELS = context.height / viewYLength_TILES;

}

//Pan across view

/**
 * A function that alters the values of the view so that a new view can be made from them, Does not actually update the view
 * the view must be updated by some other function
 * @param gridXCord - The x coordinate of the top left Tile in the new views position
 * @param gridYCord - The y coordinate of the top left Tile in the new views position
 */
function alterViewPosition(gridXCord,gridYCord){
    distanceOfXFromOrigin_TILES = gridXCord;
    distanceOfYFromOrigin_TILES = gridYCord;
}

