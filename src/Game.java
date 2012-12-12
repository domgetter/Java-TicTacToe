public class Game {
  //This is the Game class.  It hold the current state of the game
  //with the help of the Cell class.
  
  public boolean finished = false;
  public boolean draw = false;
  public Cell[] grid = new Cell[9];
  public Cell[][] rows = new Cell[3][3];
  public Cell[][] columns = new Cell[3][3];
  public Cell[][] diagonals = new Cell[2][3];
  
  public Game() {
    for (int i = 0; i < grid.length; i++) {
    grid[i] = new Cell();
    //System.out.println(grid[i].output());
  }
  }
  
  public String output() {
  
    //Cell[] grid = new Cell[9];
    String map = "Map goes here.";
    
    if(checkForTicTacToe()){
      //draw winning map
      map = "\n\t\t  1   2   3\n";
      map += "\t\t    |   |   \n";
      map += "\t\tA " + grid[0].output() + " | " + grid[1].output() + " | " + grid[2].output() + " \n";
      map += "\t\t    |   |   \n";
      map += "\t\t ---+---+---\n";
      map += "\t\t    |   |   \n";
      map += "\t\tB " + grid[3].output() + " | " + grid[4].output() + " | " + grid[5].output() + " \n";
      map += "\t\t    |   |   \n";
      map += "\t\t ---+---+---\n";
      map += "\t\t    |   |   \n";
      map += "\t\tC " + grid[6].output() + " | " + grid[7].output() + " | " + grid[8].output() + " \n";
      map += "\t\t    |   |   \n";
    } else {
      
      // Ideal sort of output
      /*
      map  = "\\  |   |   \n";
      map += " X |   |   \n";
      map += "  \\|   |   \n";
      map += "-----------\n";
      map += "   |\\  |   \n";
      map += "   | X | O \n";
      map += "   |  \\|   \n";
      map += "-----------\n";
      map += "   |   |\\  \n";
      map += "   | O | X \n";
      map += "   |   |  \\\n";
      */
      
      // Hardcoded ouput for 3x3 game

      map = "\n\t\t  1   2   3\n";
      map += "\t\t    |   |   \n";
      map += "\t\tA " + grid[0].output() + " | " + grid[1].output() + " | " + grid[2].output() + " \n";
      map += "\t\t    |   |   \n";
      map += "\t\t ---+---+---\n";
      map += "\t\t    |   |   \n";
      map += "\t\tB " + grid[3].output() + " | " + grid[4].output() + " | " + grid[5].output() + " \n";
      map += "\t\t    |   |   \n";
      map += "\t\t ---+---+---\n";
      map += "\t\t    |   |   \n";
      map += "\t\tC " + grid[6].output() + " | " + grid[7].output() + " | " + grid[8].output() + " \n";
      map += "\t\t    |   |   \n";

    }
    return map;
  }
  
  public boolean setCell(int index){ //This is terrible.  I need to make a player class
    if (grid[index].empty) {         //that keeps track of whose turn it is
      grid[index].placeX();
      return true;
    } else {
      return false;
    }
  }
  public boolean setCellO(int index){
    if (grid[index].empty) {
      grid[index].placeO();
      return true;
    } else {
      return false;
    }
  }
  
  private boolean checkForTicTacToe() {
  
    if(!grid[0].empty && !grid[1].empty && !grid[2].empty && !grid[3].empty && !grid[4].empty && !grid[5].empty && !grid[6].empty && !grid[7].empty && !grid[8].empty){
      finished = true;
      draw = true;
    } else {
      for(int i=0;i<3;i+=1){ //all these 3's replace with gridSize later
        for(int j=0;j<3;j+=1){
          rows[i][j] = grid[3*i+j];
        }
      }
      for(int i=0;i<3;i+=1){
        for(int j=0;j<3;j+=1){
          columns[i][j] = grid[i+3*j];
        }
      }
      diagonals[0][0] = grid[0]; //increment of size+1
      diagonals[0][1] = grid[4]; //increment of size+1
      diagonals[0][2] = grid[8]; //increment of size+1
      diagonals[1][0] = grid[2]; //increment of size-1
      diagonals[1][1] = grid[4]; //increment of size-1
      diagonals[1][2] = grid[6]; //increment of size-1
      
      for (Cell[] row : rows) {
        //if the row elements are all the same and not empty
        //set finished to true
        //System.out.println(row.toString());
        if(row[0].output()==row[1].output() && row[1].output()==row[2].output() && !row[0].empty && !row[1].empty && !row[2].empty) {
          finished = true;
        }
        
          //System.out.println(row.toString());
        for (Cell element : row){
          //System.out.println(element);
        }
      }
      for (Cell[] column : columns) {
        //if the column elements are all the same and not empty
        //set finished to true
        //System.out.println(column.toString());
        if(column[0].output()==column[1].output() && column[1].output()==column[2].output() && !column[0].empty && !column[1].empty && !column[2].empty) {
          finished = true;
        }
        
          //System.out.println(column.toString());
        for (Cell element : column){
          //System.out.println(element);
        }
      }
      for (Cell[] diagonal : diagonals) {
        //if the diagonal elements are all the same and not empty
        //set finished to true
        //System.out.println(diagonal.toString());
        if(diagonal[0].output()==diagonal[1].output() && diagonal[1].output()==diagonal[2].output() && !diagonal[0].empty && !diagonal[1].empty && !diagonal[2].empty) {
          finished = true;
        }
        
          //System.out.println(diagonal.toString());
        for (Cell element : diagonal){
          //System.out.println(element);
        }
      }
    }
    return finished;
  }
  
}