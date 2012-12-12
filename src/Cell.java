public class Cell {
  //This is one cell of the game grid
  
  public String contents;
  public boolean empty;
  
  public Cell() {
    this.contents = " ";
    this.empty = true;
  }
  
  public String output() {
    return this.contents;
  }
  
  public void placeX() {
    this.contents = "X";
    this.empty = false;
  }
  
  public void placeO() {
    this.contents = "O";
    this.empty = false;
  }
  
}