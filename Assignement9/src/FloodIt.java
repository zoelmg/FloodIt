import java.util.ArrayList;
import java.util.Random;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;


//class to represent the flood it game
class FloodItWorld extends World {

  //all the cells of the game
  ArrayList<ArrayList<Cell>> board;

  //a list of already flooded cells
  ArrayList<Cell> floodedCell;

  //index variable to iterate through the cells for 
  //waterfall effects
  int index;

  //size of the board
  int size;

  //array of colors to choose from
  ArrayList<Color> colorList;

  //number of clicks the user did
  int steps;

  //number of maximum clicks allowed
  int maxSteps;

  //current color user has clicked
  Color currentColor;

  //the original color of the cell before user presses
  //for a new color
  Color oldColor;

  //the amount of times the player has won without losing
  int winStreak;

  //keeps the lowest amount of clicks that the user won a game of
  int bestCount;


  //constructor
  FloodItWorld(int size, int numCol) {
    this.size = size;
    this.maxSteps = (25 * ((size * 2) * numCol) / ((14 + 14) * 4));
    this.winStreak = 1;
    this.bestCount = 0;
    this.floodedCell = new ArrayList<Cell>();
    this.index = 0;
    startGame(numCol);

  }

  //constructor for testing when randomness is removed
  FloodItWorld(int size, int numCol, int seedr, int seedg, int seedb) {
    this.size = size;
    this.maxSteps = (25 * ((size * 2) * numCol) / ((14 + 14) * 4));
    this.winStreak = 1;
    this.bestCount = 0;
    this.floodedCell = new ArrayList<Cell>();
    this.index = 0;
    this.board = new ArrayList<ArrayList<Cell>>(size);
    this.colorList = new ArrayList<Color>(numCol);
    this.steps = 0;
    this.floodedCell = new ArrayList<Cell>();
    this.index = 0;

    //for-loop to generate colors
    for (int i = 0; i < size; i++) {
      Color c = new Color(seedr, seedg, seedb);
      seedr += 10;
      seedg += 50;
      seedb += 20;
      colorList.add(c);
    }

    //for-loop to make board
    for (int j = 0; j < this.size; j++) {      
      this.board.add(new ArrayList<Cell>());
      for (int i = 0; i < this.size; i++) {
        this.board.get(j).add(new Cell((50 * j + 50 / 2), 
            (50 * i + 50 / 2), colorList.get(i), false));
      }
    }
    this.connect();
    this.board.get(0).get(0).flooded = true;
    this.floodedCell.add(this.board.get(0).get(0));
    this.board.get(0).get(0).flood(this.board.get(0).get(0).color, this.floodedCell);
    this.oldColor = this.board.get(0).get(0).color;
    this.currentColor = this.board.get(0).get(0).color;
  }



  //generates a new game
  public void startGame(int numCol) {
    this.board = new ArrayList<ArrayList<Cell>>(size);
    this.colorList = new ArrayList<Color>(numCol);
    this.steps = 0;
    this.floodedCell = new ArrayList<Cell>();
    this.index = 0;

    //for-loop to generate colors
    for (int i = 0; i < numCol; i++) {
      int randr = new Random().nextInt(254);
      int randg = new Random().nextInt(254);
      int randb = new Random().nextInt(254);
      Color c = new Color(randr, randg, randb);
      colorList.add(c);
    }

    //for-loop to make board
    for (int j = 0; j < this.size; j++) {
      this.board.add(new ArrayList<Cell>());
      for (int i = 0; i < this.size; i++) {
        int randc = new Random().nextInt(colorList.size());
        this.board.get(j).add(new Cell((50 * j + 50 / 2), 
            (50 * i + 50 / 2), colorList.get(randc), false));
      }
    }
    this.connect();
    this.board.get(0).get(0).flooded = true;
    this.floodedCell.add(this.board.get(0).get(0));
    this.board.get(0).get(0).flood(this.board.get(0).get(0).color, this.floodedCell);
    this.oldColor = this.board.get(0).get(0).color;
    this.currentColor = this.board.get(0).get(0).color;
  }


  //connects the cells to the other cells in the board
  //(which cells are adjacent to which)
  public void connect() {
    for (int j = 0; j < board.size(); j++) {
      for (int i = 0; i < board.get(0).size(); i++) {
        Cell left = new Cell();
        Cell top = new Cell();
        Cell right = new Cell();
        Cell bottom = new Cell();
        if (j - 1 >= 0) {
          left = this.board.get(j - 1).get(i);
        }
        //top
        if (i - 1 >= 0) {
          top = this.board.get(j).get(i - 1);
        }
        //right
        if (j + 1 < this.size) {
          right = this.board.get(j + 1).get(i);
        }
        //bottom
        if (i + 1 < this.size) {
          bottom = this.board.get(j).get(i + 1);
        }
        this.board.get(j).get(i).connectCell(left, top, right, bottom);
      }
    }
  }



  //generates the board for game play
  public WorldScene makeScene() {
    WorldScene background = new WorldScene(board.size() * 50, board.size() * 65);

    for (int j = 0; j < board.size(); j++) {
      for (int i = 0; i < board.get(0).size(); i++) {
        background.placeImageXY(board.get(j).get(i).drawSquare(),
            board.get(j).get(i).x, board.get(j).get(i).y);
      }

      if (this.steps > this.maxSteps) {
        this.winStreak = 0;
        background.placeImageXY(new TextImage("Game Over, You Lost! ", Color.black), 
            board.size() * 25, board.size() * 54);

        background.placeImageXY(new TextImage("Press r for new games", Color.black), 
            board.size() * 25, board.size() * 56);

        background.placeImageXY(new TextImage("Current Win Streak is " 
            + this.winStreak, Color.black), 
            board.size() * 25, board.size() * 58);

      } else if (allFloodIt() && this.steps <= this.maxSteps) {
        this.bestCount = this.steps;

        background.placeImageXY(new TextImage("Game Over, You Won! ", Color.black), 
            board.size() * 25,  board.size() * 53);

        background.placeImageXY(new TextImage("Press r for new game ", Color.black), 
            board.size() * 25, board.size() * 56);

        background.placeImageXY(new TextImage("Current Win Streak is " + this.winStreak,
            Color.black), 
            board.size() * 25, board.size() * 59);

        background.placeImageXY(new TextImage("Quickest Win " + this.bestCount, Color.black), 
            board.size() * 25, board.size() * 62);
      }
      else {

        background.placeImageXY(new TextImage( "Press r for new game anytime", Color.black), 
            board.size() * 25, board.size() * 54);

        background.placeImageXY(new TextImage(this.steps + " / " + this.maxSteps, Color.black), 
            board.size() * 25, board.size() * 56);

        background.placeImageXY(new TextImage("Quickest Win " + this.bestCount, Color.black), 
            board.size() * 25, board.size() * 58);

      }
    }

    return background;
  }

  //determines if all of the cells are flooded
  //which will be used to determine if the game should end
  public boolean allFloodIt() {
    for (int column = 0; column < this.board.size(); column++) {
      for (int row = 0; row < this.board.size(); row ++) {
        if (!this.board.get(column).get(row).flooded) {
          return false;
        } 
      }
    }
    return true;
  }


  //updates the state of the world by tick
  //go through each of the floodedCell and change its color
  public void onTick() {
    if (this.index < this.floodedCell.size()) {
      Cell x = this.floodedCell.get(index);
      if (x.color.equals(this.currentColor)) {
        x.right.floodCell(this.oldColor, this.currentColor);
        x.bottom.floodCell(this.oldColor, this.currentColor);
        x.bottom.flood(this.currentColor, this.floodedCell);
        x.right.flood(this.currentColor, this.floodedCell);
        x.top.flood(this.currentColor, this.floodedCell);
        x.left.flood(this.currentColor, this.floodedCell);
      }
      this.index++;
    }
  }


  //handles mouse clicks and is given the mouse location
  //determines color of the clicked cell and thus flood neighboring cells
  public void onMousePressed(Posn pos) {
    this.oldColor = new Color(this.currentColor.getRGB());
    this.currentColor = this.board.get(pos.x / 50).get(pos.y / 50).color;
    this.index = 0;

    if (!this.currentColor.equals(this.board.get(0).get(0).color)) {
      this.steps++;
    }
    this.board.get(0).get(0).color = this.currentColor;
    for (int j = 0; j < this.floodedCell.size(); j++) {
      Cell x = this.floodedCell.get(j);
      x.bottom.flood(this.currentColor, this.floodedCell);
      x.right.flood(this.currentColor, this.floodedCell);
      x.top.flood(this.currentColor, this.floodedCell);
      x.left.flood(this.currentColor, this.floodedCell);
    }
  }

  //starts a new game when r is pressed
  public void onKeyEvent(String key) {
    if (key.equals("r") || key.equals("R")) {
      this.winStreak += 1;
      startGame(this.colorList.size());
    }
  }
}

