import java.awt.Color;
import java.util.Random;

import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import tester.Tester;
import java.util.ArrayList;

//examples class for testing
class ExamplesFlood {
  //Corresponding seeds for RBG for the colors of the cells
  int seedr;
  int seedg;
  int seedb;

  //2X2 Game Scene
  WorldScene x;

  //3X3 Game Scene
  WorldScene y;

  //2X2 Game World
  FloodItWorld xGame;

  //3X3 Game Scene
  FloodItWorld yGame;

  //Constructor for example class
  ExamplesFlood() {}


  // ============  INITIAL EXAMPLES FOR FLOODIT GAMES =================
  void initFloodIt() {
    //  seeds for the colors of the cells
    this.seedr = new Random(1).nextInt(225);
    this.seedg = new Random(90).nextInt(225);
    this.seedb = new Random(80).nextInt(225);

    // 2X2 GAME WORLD
    this.xGame = new FloodItWorld(2, 2, seedr, seedg, seedb);

    // 3X3 GAME WORLD
    this.yGame = new FloodItWorld(3, 3, seedr, seedg, seedb);

    // 2X2 GAME SCENE
    this.x = new WorldScene(100, 130);

    x.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr, seedg, seedb)), 25, 25);

    x.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 10, seedg + 50, seedb + 20)), 25, 75);

    x.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
        50 , 108);

    x.placeImageXY(new TextImage(0 + " / " + 1, Color.black), 
        50, 112);

    x.placeImageXY(new TextImage("Quickest Win " + 0, Color.black), 
        50, 116);

    x.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr, seedg, seedb)), 75, 25);


    x.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 10, seedg + 50, seedb + 20)), 75, 75);


    x.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
        50 , 108);

    x.placeImageXY(new TextImage(0 + " / " + 1, Color.black), 
        50, 112);

    x.placeImageXY(new TextImage("Quickest Win " + 0, Color.black), 
        50, 116);



    // 3X3 GAME SCENE
    this.y = new WorldScene(150, 195);
    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr, seedg, seedb)), 25, 25);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 10, seedg + 50, seedb + 20)), 25, 75);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 20, seedg + 100, seedb + 40)), 25, 125);

    y.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
        75 , 162);

    y.placeImageXY(new TextImage(0 + " / " + 4, Color.black), 
        75, 168);

    y.placeImageXY(new TextImage("Quickest Win " + 0, Color.black), 
        75, 174);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr, seedg, seedb)), 75, 25);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 10, seedg + 50, seedb + 20)), 75, 75);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 20, seedg + 100, seedb + 40)), 75, 125);

    y.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
        75 , 162);

    y.placeImageXY(new TextImage(0 + " / " + 4, Color.black), 
        75, 168);

    y.placeImageXY(new TextImage("Quickest Win " + 0, Color.black), 
        75, 174);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr, seedg, seedb)), 125, 25);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 10, seedg + 50, seedb + 20)), 125, 75);

    y.placeImageXY(new RectangleImage(50, 50, OutlineMode.SOLID, 
        new Color(seedr + 20, seedg + 100, seedb + 40)), 125, 125);

    y.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
        75 , 162);

    y.placeImageXY(new TextImage(0 + " / " + 4, Color.black), 
        75, 168);

    y.placeImageXY(new TextImage("Quickest Win " + 0, Color.black), 
        75, 174);
  }

  //=====================   TESTS FOR ALL METHODS IN CELL CLASS=========================

  //test for drawSquare which draws a cell
  void testDrawSquare(Tester t) {
    Cell cell1 = new Cell(50, 50, Color.RED, true);
    t.checkExpect(cell1.drawSquare(), new RectangleImage(50, 50, OutlineMode.SOLID, Color.RED));

    Cell cell2 = new Cell(50, 50, Color.GREEN, true);
    t.checkExpect(cell2.drawSquare(), new RectangleImage(50, 50, OutlineMode.SOLID, Color.GREEN));

    Cell cell3 = new Cell(50, 50, Color.BLUE, false);
    t.checkExpect(cell3.drawSquare(), new RectangleImage(50, 50, OutlineMode.SOLID, Color.BLUE));
  }

  //test for connectCell which connects the neighboring cells to each other on the board
  void testConnectCell(Tester t) {
    Cell cell1 = new Cell(50, 50, Color.RED, true);
    Cell cell2 = new Cell(50, 50, Color.GREEN, true);
    Cell cell3 = new Cell(50, 50, Color.BLUE, false);
    Cell cell4 = new Cell(50, 50, Color.BLACK, false);

    t.checkExpect(cell3.left, new Cell());
    t.checkExpect(cell3.top, new Cell());
    t.checkExpect(cell3.right, new Cell());
    t.checkExpect(cell3.bottom, new Cell());

    cell3.connectCell(cell1, cell2, cell3, cell4);
    t.checkExpect(cell3.left, cell1);
    t.checkExpect(cell3.top, cell2);
    t.checkExpect(cell3.right, cell3);
    t.checkExpect(cell3.bottom, cell4);
  }


  //tests for flooding a cell, i.e changing the cell's flood status
  //from false to true
  void testFlood(Tester t) {
    Cell ex1 = new Cell(222, 245, Color.RED, false);
    ArrayList<Cell> arr = new ArrayList<Cell>();
    ex1.flood(Color.RED, arr);
    t.checkExpect(arr.size(), 1);
    t.checkExpect(ex1.flooded, true);
    ex1.flood(Color.RED, arr);
    t.checkExpect(arr.size(), 1);

    Cell ex2 = new Cell(400, 234, Color.BLUE, false);
    ex2.flood(Color.RED, arr);
    t.checkExpect(arr.size(), 1);
    t.checkExpect(ex2.flooded, false);

    Cell ex3 = new Cell(2, 3, Color.BLACK, false);
    ex3.flood(Color.BLACK, arr);
    t.checkExpect(arr.size(), 2);
    t.checkExpect(ex3.flooded, true);

    Cell ex4 = new Cell(100, 300, Color.BLACK, true);
    ex4.flood(Color.RED, arr);
    t.checkExpect(arr.size(), 2);
    t.checkExpect(ex4.flooded, true);

    Cell ex5 = new Cell(1, 500, Color.WHITE, true);
    ex5.flood(Color.WHITE, arr);
    t.checkExpect(arr.size(), 2);
    t.checkExpect(ex5.flooded, true);
  }


  //checks that once a cell is flooded, it will change to the current color
  void testFloodCell(Tester t) {
    Cell ex1 = new Cell(23, 56, Color.RED, true);
    ex1.floodCell(Color.RED, Color.WHITE);
    t.checkExpect(ex1.color, Color.WHITE);
    ex1.floodCell(Color.WHITE, Color.RED);
    t.checkExpect(ex1.color, Color.RED);

    Cell ex2 = new Cell(45, 65, Color.BLUE, false);
    ex2.floodCell(Color.BLUE, Color.YELLOW);
    t.checkExpect(ex2.color, Color.BLUE);
    ex2.floodCell(Color.YELLOW, Color.BLUE);
    t.checkExpect(ex2.color, Color.BLUE);
  }

  //===================== END TESTS FOR METHODS IN CELL CLASS =========================




  //====================== TESTS FOR METHODS IN FLOODITWORLD CLASS =====================

  //check that every instance of the cells are connected correct on the board
  void testConnect(Tester t) {
    //initialize a 12 by 12 board with 24 colors 
    FloodItWorld starterWorld = new FloodItWorld(12, 24);

    //Top-left most corner cell
    t.checkExpect(starterWorld.board.get(0).get(0).left, new Cell());
    t.checkExpect(starterWorld.board.get(0).get(0).top, new Cell());
    t.checkExpect(starterWorld.board.get(0).get(0).right, starterWorld.board.get(1).get(0));
    t.checkExpect(starterWorld.board.get(0).get(0).bottom, starterWorld.board.get(0).get(1));

    //Bottom-left most corner cell 
    t.checkExpect(starterWorld.board.get(0).get(11).left, new Cell());
    t.checkExpect(starterWorld.board.get(0).get(11).top, starterWorld.board.get(0).get(10));
    t.checkExpect(starterWorld.board.get(0).get(11).right, starterWorld.board.get(1).get(11));
    t.checkExpect(starterWorld.board.get(0).get(11).bottom, new Cell());

    //Top-Right most corner cell
    t.checkExpect(starterWorld.board.get(11).get(0).left, starterWorld.board.get(10).get(0));
    t.checkExpect(starterWorld.board.get(11).get(0).top, new Cell());
    t.checkExpect(starterWorld.board.get(11).get(0).right, new Cell());
    t.checkExpect(starterWorld.board.get(11).get(0).bottom, starterWorld.board.get(11).get(1));

    //Bottom-Right most corner cell
    t.checkExpect(starterWorld.board.get(11).get(11).left, starterWorld.board.get(10).get(11));
    t.checkExpect(starterWorld.board.get(11).get(11).top, starterWorld.board.get(11).get(10));
    t.checkExpect(starterWorld.board.get(11).get(11).right, new Cell());
    t.checkExpect(starterWorld.board.get(11).get(11).bottom, new Cell());

    //Left-Middle cells
    t.checkExpect(starterWorld.board.get(0).get(1).left, new Cell());
    t.checkExpect(starterWorld.board.get(0).get(1).top, starterWorld.board.get(0).get(0));
    t.checkExpect(starterWorld.board.get(0).get(1).right, starterWorld.board.get(1).get(1));
    t.checkExpect(starterWorld.board.get(0).get(1).bottom, starterWorld.board.get(0).get(2));

    t.checkExpect(starterWorld.board.get(0).get(5).left, new Cell());
    t.checkExpect(starterWorld.board.get(0).get(5).top, starterWorld.board.get(0).get(4));
    t.checkExpect(starterWorld.board.get(0).get(5).right, starterWorld.board.get(1).get(5));
    t.checkExpect(starterWorld.board.get(0).get(5).bottom, starterWorld.board.get(0).get(6));

    ///Right-Middle cells
    t.checkExpect(starterWorld.board.get(11).get(5).left, starterWorld.board.get(10).get(5));
    t.checkExpect(starterWorld.board.get(11).get(5).top, starterWorld.board.get(11).get(4));
    t.checkExpect(starterWorld.board.get(11).get(5).right, new Cell());
    t.checkExpect(starterWorld.board.get(11).get(5).bottom, starterWorld.board.get(11).get(6));

    //Top-middle cells
    t.checkExpect(starterWorld.board.get(1).get(0).left, starterWorld.board.get(0).get(0));
    t.checkExpect(starterWorld.board.get(1).get(0).top, new Cell());
    t.checkExpect(starterWorld.board.get(1).get(0).right, starterWorld.board.get(2).get(0));
    t.checkExpect(starterWorld.board.get(1).get(0).bottom, starterWorld.board.get(1).get(1));

    //Bottom-middle cells
    t.checkExpect(starterWorld.board.get(1).get(11).left, starterWorld.board.get(0).get(11));
    t.checkExpect(starterWorld.board.get(1).get(11).top, starterWorld.board.get(1).get(10));
    t.checkExpect(starterWorld.board.get(1).get(11).right, starterWorld.board.get(2).get(11));
    t.checkExpect(starterWorld.board.get(1).get(11).bottom, new Cell());

    //Middle cells with all adjacent corners
    t.checkExpect(starterWorld.board.get(1).get(5).left, starterWorld.board.get(0).get(5));
    t.checkExpect(starterWorld.board.get(1).get(5).top, starterWorld.board.get(1).get(4));
    t.checkExpect(starterWorld.board.get(1).get(5).right, starterWorld.board.get(2).get(5));
    t.checkExpect(starterWorld.board.get(1).get(5).bottom, starterWorld.board.get(1).get(6));
  }

  //test for makeScene with seeded colors
  void testmakeScene(Tester t) {

    initFloodIt();
    //test the size of a 2X2 size board , there should be 2 columns
    t.checkExpect(new FloodItWorld(2, 3).board.size(), 2);

    //test the size of 3X3 size board, there should be 3 columns
    t.checkExpect(new FloodItWorld(3, 3).board.size(), 3);

    t.checkExpect((new FloodItWorld(2, 2, seedr, seedg, seedb)).makeScene(), x);
    t.checkExpect((new FloodItWorld(3, 3, seedr, seedg, seedb)).makeScene(), y);
  }

  //test to determine if all of the cells in a game are flooded
  void testAllFloodIt(Tester t) {
    initFloodIt();

    //check that the initial state of the two games, not all the cells are flooded
    t.checkExpect(this.xGame.allFloodIt(), false);
    t.checkExpect(this.yGame.allFloodIt(), false);

    //uses onMouseClicked until the game was won, 
    //which would mean all the cells are flooded
    this.yGame.onMousePressed(new Posn(25, 25));
    this.yGame.onMousePressed(new Posn(125, 75));
    this.yGame.onMousePressed(new Posn(75, 125));

    this.xGame.onMousePressed(new Posn(25, 75));
    this.xGame.onMousePressed(new Posn(75, 25));


    //check now that the entire boards are flooded
    t.checkExpect(this.yGame.allFloodIt(), true);
    t.checkExpect(this.xGame.allFloodIt(), true);

  }


  //tests to determine if onMousePress is recieves the color of the cell that is 
  //pressed and correctly flood the neighboring cell to the respective color
  void testOnMousePress(Tester t) {
    FloodItWorld starterWorld = new FloodItWorld(12, 24);
    starterWorld.onMousePressed(new Posn(49, 49));
    t.checkExpect(starterWorld.currentColor, starterWorld.board.get(0).get(0).color);
    starterWorld.onMousePressed(new Posn(49, 599));
    t.checkExpect(starterWorld.currentColor, starterWorld.board.get(0).get(11).color);
    starterWorld.onMousePressed(new Posn(300, 250));
    t.checkExpect(starterWorld.currentColor, starterWorld.board.get(6).get(5).color);
    starterWorld.onMousePressed(new Posn(599, 599));
    t.checkExpect(starterWorld.currentColor, starterWorld.board.get(11).get(11).color);
    starterWorld.onMousePressed(new Posn(599, 49));
    t.checkExpect(starterWorld.currentColor, starterWorld.board.get(11).get(0).color);
  }


  //tests to check that only on key "r" clicked, a new game would be generated
  void testOnKeyEvent(Tester t) {

    initFloodIt();

    //feed in a key that is not R (which means it would do nothing to the state of the world
    FloodItWorld originalXGame = new FloodItWorld(2, 2, seedr, seedg, seedb);
    FloodItWorld originalYGame = new FloodItWorld(3, 3, seedr, seedg, seedb);
    this.xGame.onKeyEvent("t");
    this.yGame.onKeyEvent("a");
    t.checkExpect(this.xGame, originalXGame);
    t.checkExpect(this.yGame, originalYGame);


    //checks that when "r" is pressed, a new game would be generated
    //which means the colors of the tiles would not equal to the original colors
    this.xGame.onKeyEvent("r");
    this.yGame.onKeyEvent("r");

    //the colors in the available colors list of the updated world does not equal to the 
    //original seeded colors in the seeded colors list
    t.checkExpect((this.xGame.colorList.get(0) != originalXGame.colorList.get(0)), true);
    t.checkExpect((this.xGame.colorList.get(1) != originalXGame.colorList.get(1)), true);
    t.checkExpect((this.yGame.colorList.get(0) != originalYGame.colorList.get(0)), true);
    t.checkExpect((this.yGame.colorList.get(1) != originalYGame.colorList.get(1)), true);
    t.checkExpect((this.yGame.colorList.get(2) != originalYGame.colorList.get(2)), true);

  }

  //tests to check that Start Game will generate a new board with new random colors
  //but keeps the size of the board and amount of available colors the same
  void testStartGame(Tester t) {

    initFloodIt();
    //feed in a key that is not R (which means it would do nothing to the state of the world
    FloodItWorld originalXGame = new FloodItWorld(2, 2, seedr, seedg, seedb);
    FloodItWorld originalYGame = new FloodItWorld(3, 3, seedr, seedg, seedb);
    this.xGame.startGame(2);
    this.yGame.startGame(3);

    //check that the board size of the updated game and the original game are the same
    t.checkExpect((this.xGame.board.size() == originalXGame.board.size()), true);
    t.checkExpect((this.yGame.board.size() == originalYGame.board.size()), true);

    //the colors in the available colors list of the updated world should not equal to the 
    //original seeded colors in the seeded colors list since a new board as been generated 
    //by startGame
    t.checkExpect((this.xGame.colorList.get(0) != originalXGame.colorList.get(0)), true);
    t.checkExpect((this.xGame.colorList.get(1) != originalXGame.colorList.get(1)), true);
    t.checkExpect((this.yGame.colorList.get(0) != originalYGame.colorList.get(0)), true);
    t.checkExpect((this.yGame.colorList.get(1) != originalYGame.colorList.get(1)), true);
    t.checkExpect((this.yGame.colorList.get(2) != originalYGame.colorList.get(2)), true);

  }

  //tests to check that the world floods correctly
  void testOnTick(Tester t) {

    initFloodIt();
    this.xGame.oldColor = Color.red;
    this.xGame.board.get(1).get(0).color = this.xGame.oldColor;
    this.xGame.board.get(1).get(0).flooded = true;
    this.xGame.board.get(0).get(1).color = this.xGame.oldColor;
    this.xGame.board.get(0).get(1).flooded = true;

    //checks to see if the two cells adjacent to the one that has the currentColor
    //changes to the current color, given that they are flooded cells and are of the
    //old color. ie. they flood properly
    this.xGame.onTick();
    t.checkExpect(this.xGame.board.get(0).get(0).right.color, this.xGame.currentColor);
    t.checkExpect(this.xGame.board.get(0).get(0).bottom.color, this.xGame.currentColor);

    //since origin cell (top left) is not yet the current color, 
    //the two adjacent cells should not change to the current color
    //all three should remain the oldColor
    this.yGame.oldColor = Color.green;
    this.yGame.board.get(0).get(0).color = this.yGame.oldColor;
    this.yGame.board.get(1).get(0).color = this.yGame.oldColor;
    this.yGame.board.get(1).get(0).flooded = true;
    this.yGame.board.get(0).get(1).color = this.yGame.oldColor;
    this.yGame.board.get(0).get(1).flooded = true;

    this.yGame.onTick();
    t.checkExpect(this.yGame.board.get(0).get(0).right.color, this.yGame.oldColor);
    t.checkExpect(this.yGame.board.get(0).get(0).bottom.color, this.yGame.oldColor);
    t.checkExpect(this.yGame.board.get(0).get(0).color, this.yGame.oldColor);
  }


  //====================== END TESTS FOR METHODS IN FLOODITWORLD CLASS =====================




  //====================== PLAY THE FLOOD IT GAME ====================
  //INSTRUCTION:
  //1) INPUT DESIRED SIZE OF THE BOARD & NUMBER OF RANDOMLY GENERATED COLOR
  //2) FASTEN BIGBANG TICKER FOR LARGER BOARDS FOR MORE EFFICIENT WATERFALL EFFECT
  //====================== ENJOY!!! ==================================

  //initialize the bigBang for Floodit game
  void testFloodIt(Tester t) {
    FloodItWorld starterWorld = new FloodItWorld(12, 7);
    int sceneSize = starterWorld.size;
    starterWorld.bigBang(sceneSize * 50, sceneSize * 65, 0.005);
  }

}
