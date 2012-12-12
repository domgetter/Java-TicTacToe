/*
 * Write a tic-tac-toe program where a human user
 * can play against  an AI bot, and where two  AI 
 * bots can play against each other.  Write input
 * and output code so that it's intuitive for the 
 * user.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

public class TicTacToe {

  public static void main(String[] args){
  
    boolean turn;
    boolean valid_input;
    boolean out_of_bounds;
    int gameMode;
    int column;
    int row;
    int index;
    int count = 0; //If I set this to randomly be 1 or 0, I can have the computer play first in a human/ai game
  
    System.out.println("\n\tWelcome to this wonderful and lovely game of TicTacToe.");
    System.out.println("\n\tPlease select your Game mode.");
    System.out.println("\n\t    (1) Human vs. Computer");
    System.out.println("\n\t    (2) Computer vs. Computer");
    String user_input = getInput("\n\tWhich mode would you like to play? (1/2): ");

    //Keep asking for an answer from the user until we get a 1 or a 2
    valid_input = false;
    while(!valid_input){
      if((user_input.length() == 1) && (user_input.substring(0,1).matches("[1-2]"))){
        valid_input = true;
      } else {
        user_input = getInput("\n\tYou must enter '1' or '2' for the game mode: ");
      }
    }
    
    //Create a new Game instance
    Game game = new Game();
    
    //Set user input to gameMode for use later
    gameMode = Integer.parseInt(user_input);
    
    //Draw the blank board initially to show user which columns and rows to choose from
    System.out.println(game.output());
    
    //if human mode, do human logic
    if (gameMode == 1) {
    
      System.out.println("\tPlease place an X on the grid.  You can");
      user_input = getInput("\tdo this by typing 1A, 1B, 1C, 2A, etc.: ");
      
      //until the turn is over, do stuff for player 1's turn
      turn = true;
      while(turn){
        if(valid_input(user_input)){
          column = Integer.parseInt(user_input.substring(0,1));
          row = letterToNumber(user_input.substring(1,2));
          index = 3*(row-1)+(column-1); //3 is grid size *wink* *wink*
          
          //check to see if user chose cell out of bounds
          out_of_bounds = (index > 8 || index < 0);
          if(out_of_bounds){
            //if they did, keep asking for input until it's valid
            user_input = getInput("That's not a valid spot!  Please choose another spot: ");
          } else {
            //if selection was in bounds, place mark and end turn
            game.setCell(index);
            turn = false;
          }
        } else {
          // if input was invalid (not a string of length 2 which
          // contains one number and one letter, in that order)
          // then keep asking for input until you get a valid match
          user_input = getInput("That's not valid input.  Please choose another spot: ");
        }
        
      }
      //draw grid at end of player 1's turn.
      //game.output() also checks to see if endgame condition has been met.
      System.out.println(game.output());
    } else {
      //computer plays here
      System.out.print("\tThe computer will now make a move.."); //this is a print to maintain placement of the grid
      delay(1000); //pauses for 1 second to appear as if computer is thinking
      
      //computer finds a random empty cell, and places a mark
      turn = true;
      while(turn) {
        //computer keeps randomly selecting cells until an empty one is found
        index = (int)Math.round((3*3-1)*Math.random());
        if(game.setCell(index)){
          turn = false;
        }
      }
      
      //redraw map to include most recent move
      System.out.println("\n" + game.output());
    }
    
    //until the game has reached an endgame condition, keep going between players
    while (!game.finished) {
      
      //this modulo switches between players.  count is increased at the end of this code block
      if(count%2==0){
      
        System.out.print("\tThe computer will now make a move.."); //this is a print to maintain placement of the grid
        //pauses for 1 second to appear as if computer is thinking
        delay(1000);
        
        // computer finds a random empty cell, and places a mark
        
        turn = true;
        while(turn) {
          index = (int)Math.round((3*3-1)*Math.random());
          if(game.setCellO(index)){
            turn = false;
          }
        }
        //redraws map to include most recent move
        System.out.println("\n" + game.output());
        
      } else {
        //game mode 1 for human mode
        if(gameMode == 1){
          //uesr input or cell selection
          user_input = getInput("\tPlease place an X on the grid: ");
          turn = true;
          
          //while it's the player's turn...
          while(turn) {
          
          
            //validate user input
            if(valid_input(user_input)){
            
              column = Integer.parseInt(user_input.substring(0,1));
              row = letterToNumber(user_input.substring(1,2));
              index = 3*(row-1)+(column-1);
              
              if(index > 8 || index < 0){
                user_input = getInput("That's not a valid spot!  Please choose another spot: ");
              } else {
              
                //if valid input, and cell isn't taken already,
                //place mark in selected cell and end turn
                if(game.setCell(index)){
                  turn = false;
                }else{
                  user_input = getInput("That space is already in play!  Please choose another spot: ");
                }
              }
            } else {
              user_input = getInput("That's not valid input.  Please choose another spot: ");
            }

            //finds corrosponding cell and places an X
            System.out.println(game.output());
          }
        } else {
        
          //computer plays
          System.out.print("\tThe computer will now make a move.."); //this is a print to maintain placement of the grid
          
          //pauses for 1 second to appear as if computer is thinking
          delay(1000);
          
          //computer finds a random empty cell, and places an O
          turn = true;
          while(turn) {
            index = (int)Math.round((3*3-1)*Math.random());
            if(game.setCell(index)){
              turn = false;
            }
          }
          
          //redraws map to include most recent move
          System.out.println("\n" + game.output()); 
        }
      }
      count += 1;
    }
    //end game condition has been met.  either X's won, O's won, or it was a Draw (a.k.a. "Cat's Game")
    if (game.draw) {
      System.out.println("\n\tCat's game!");
    } else {
      //count variable from earlier is used to decide who went last and therefore won.
      if(count%2==0){
        System.out.println("\n\tX's win!");
      } else {
        System.out.println("\n\tO's win!");
      }
    }
  }
  
  //converts the letter input for row/column selection into a usable number
  private static int letterToNumber(String str) {
    return ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str))%26+1;
  }
  
  //encapsulated code for input stream buffer
  private static String getInput(String prompt) {
    BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
    
    System.out.print(prompt);
    System.out.flush();
    
    try {
      return stdin.readLine();
    } catch (Exception e) {
      return "Error: " + e.getMessage();
    }
  }
  
  //encapsulated code for AI delay behavior
  private static void delay(int amount) {
    try {
      Thread.sleep(amount);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
  
  //encapsulated code for user input validation
  //it checks to make sure the input was two characters long,
  //and that it contained one digit, followed by one lower
  //case or upper case letter
  private static boolean valid_input(String user_input) {
    return (user_input.length() == 2) && (user_input.substring(0,1).matches("[0-9]") && user_input.substring(1,2).matches("[a-zA-Z]"));
  }
}