import java.awt.Color;
import java.util.ArrayList;

import javalib.worldimages.*;

//Represents a single square of the game area
class Cell {

  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  //color of cell
  Color color;
  //if the cell has been flooded
  boolean flooded;
  // the four adjacent cells to this one 
  Cell left;
  Cell top;
  Cell right;
  Cell bottom;

  //constructor for initializing an empty cell
  //with a color that will never be randomly generated
  Cell() {
    this.x = 0;
    this.y = 0;
    this.color = new Color(255, 255, 255);
    this.flooded = false;
    this.left = this;
    this.top = this;
    this.right = this;
    this.bottom = this;
  }

  //constructor to initialize a cell
  Cell(int x, int y, Color color, boolean flooded) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.flooded = flooded;
    this.left = new Cell();
    this.top = new Cell();
    this.right = new Cell();
    this.bottom = new Cell();
  }


  //returns a worldimage that draws the cell
  WorldImage drawSquare() {
    return new RectangleImage(50, 50, OutlineMode.SOLID, this.color);
  }

  //mutates the cell so that it connects itself to other cells in the arraylist
  public void connectCell(Cell left, Cell top, Cell right, Cell bottom) {
    this.top = top;
    this.right = right;
    this.left = left;
    this.bottom = bottom;
  }

  //method that floods the cell and add the cell to a list of flooded cells
  public void flood(Color c, ArrayList<Cell> floodedCells) {
    if (this.color.equals(c) && !this.flooded) {
      this.flooded = true;
      if (!floodedCells.contains(this)) {
        floodedCells.add(this);
      }
    }
  }

  //changes the color of this cell if it is flooded
  public void floodCell(Color oldColor, Color currentColor) {
    if (this.flooded && this.color.equals(oldColor)) {
      this.color = new Color(currentColor.getRGB());
    }
  }


}