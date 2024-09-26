/*
 * Author: Marlon Dominguez
 * Sudoku Board Implementation 
 */

import java.util.LinkedList;
import  java.lang.Math;


public class Board{

    /*Implements adjacency list as data structure */
    private Square[][] grid;
    private int grid_size; //81 squares in total
    private int difficulty; 

    public Board(int difficulty){

        this.grid = new Square[9][9]; //9x9 board filled with 0's
        this.grid_size = 81; 
        this.difficulty = difficulty;
    }

    
    /*
     * Fill board in a deterministic form. 
     */
    public void fill_initial_board(int grid_size){
        
        int lower_bound = grid_size / difficulty - 6; //81 - 6
        int upper_bound = grid_size / difficulty - 1; //81 - 1 

        //Generate random number of initial values to fill
        int num_of_initial_values = (int) (Math.random() * (upper_bound - lower_bound));

        //Generate initial deterministic values 
        int rand_val = (int)(Math.random() * 9);

        int rand_column = (int)(Math.random() * 9); 

        int rand_row = (int)(Math.random() * 9); 

        int rand_quadrant = (int)(Math.random() * 9);
        
        /*To ensure that the initial fill is completely random each time, we will
         * once again use Math.Random() to generate a random column, row, and value.*/
        for(int i = 0; i < num_of_initial_values; i++){
             
            //Check if generated value satisfies the requirements (e.g no duplicates in the same row or column)
            while(!validate_square(rand_val, rand_column, rand_row, rand_quadrant)){
                
                //If not, regenerate a new combination.
                rand_val = (int)(Math.random() * 9);

                rand_column = (int)(Math.random() * 9); 
            
                rand_row = (int)(Math.random() * 9); 

                rand_quadrant = (int)(Math.random() * 9);    
            }

            //At this point, our random value is acceptable and ready to insert
            if(!insert_value(rand_val, rand_column, rand_row, rand_quadrant)){
                
                System.out.println("Value: " + rand_val + " X-Cord: " + rand_column + " Y-Cord: " + rand_row) + "\nValue not accepted."); 
            }

            else{

                System.out.println("Value: " + rand_val + " X-Cord: " + rand_column + " Y-Cord: " + rand_row) + "\nValue accepted and inserted."); 
            }
 

        }

        return;

    }


    /* -------------ALSO KNOWN AS OUR SUDOKU SOLVER-----------------
     * Depth First Search used to validate move/connection/value.
     * 
     * Requirement #1: If the value to be inserted already exists in the 
     * same respective row, column, and/or quadrant then the value to be 
     * inserted is not acceptable. Otherwise, the value is acceptable. 
     * 
     * Requirement #2: Here is the most contributing factor to the NP-Completeness 
     * of a sudoku solver. A value at a given coordinate may satisfy the qualifications 
     * of requirement #1, but it is still possible for that value to give us an incorrect 
     * solution. Usually this is only found by continuing to fill in values until the 
     * realization occurs that a coordinate is empty and no value can fill it, generally 
     * in some other quadrant. This is a contradiction since our board must be completely 
     * filled. 
     * 
     * 
     * Use the insert_value(...) attribute to insert the value. 
     */
    public Boolean validate_square(Square val, int column, int row, int quadrant){    

        System.out.println("Validating row and column...");

        /*First validate that the value does not already exist in the corresponding quadrant
          Since validating the quadrant is done in O(1), it should be performed first. 
          This way, if the quadrant is invalid, we do not have to perform the expensive
          operation of validating rows and columns. 
        */

        /*
        DFS w/seperate chaining O(n)
        */

        //Base Case(s)
        if(quadrant >= 9){

            return true;     
        }

    

        //Recursive Case
        
        //Ensure that the quadrant in which the value is being inserted is not checked
        

        //Check all quadrants to check all rows and columns 
        if(val.get_x_coordinate() == this.grid[quadrant][val.get_value()].get_x_coordinate()){

            //Value already exists in row
            return false; 
        }

        else if(val.get_y_coordinate() == this.grid[quadrant][val.get_value()].get_y_coordinate()){

            //Value already exists in column
            return false; 
        }


        return validate_square(val, column, row, quadrant++);
    }

    public Boolean validate_quadrant(int val, int column, int row, int quadrant){    

        System.out.println("Validating quadrant...");

        //validate that the value does not already exist in the corresponding quadrant. 

        return false;

    }



    public int calculate_quadrant(int column, int row){

        int quadrant_row = -1;
        int quadrant_column = -1; 

        
        /*If: x<3 -> first column of quadrants 
         *If: x>3 && x<6 -> second column of quadrants
         *If: x>6 && x<9 -> third column of quadrants 

         *If: y<3 -> first row of quandrants 
         *If: y>3 && y<6 -> second row of quadrants
         *If: y>6 && y<9 -> third row of quadrants 
         */

        //Assuming the first column or row is 1
        for(int x = 3; x < 9; x+=3){

            quadrant_column++; 

            if(column <= x){

                //We have narrowed our quadrant search to be in the xth column of quadrants 
                for(int y = 3; y < 9; y+=3){

                    quadrant_row++;

                    if(row <= y){

                        //We now have the yth row of quadrants
                        int calculated_quadrant = (quadrant_row * 3) + (quadrant_column + 1);
                        return calculated_quadrant;                            
                    }

                     

                }

            }   
            
        }

        return 0;
    }



    /*
     *Inserts a value, or square object, into our data structure.
     *
     *The rows represent the 9 different quadrants. 
     *The columns represent the 9 squares, or value positions, inside each quadrant.
     *Hence, what we store is a square object which consists of the value, xcord, and ycord. 
     *
     *In this manner, we can place each value in the square equal to the value. 
     *In other words, a value of 8 in quadrant 4 would be placed in the eigth column of the 
     *fourth row, the exact position is preserved by the square object. 
     *
     *This mapping will allow us to easily verify if a value already exists in a quadrant in 
     *O(1), or constant time, using random memory access in each respective array. 
     *
     *Then, validating that there does not already exist a value in the same row and/or column
     *can be done using Depth First Search, and hence our validation algorithm operates in O(n), or linear time. 
     *Please check validate_value(...) to find the DFS implementation. 
     * 
     */


    public Boolean insert_value(int val, int xcord, int ycord, int quadrant){

        Square val_toInsert = new Square(val, xcord, ycord, quadrant);
        
        //Insert value into board data structure
        this.grid[quadrant][val] = val_toInsert;

        return true; 
    }

    public void display_board(){

        System.out.println(" " + "  C1 C2 C3 C4 C5 C6 C7 C8 C9 ");

        for(int x = 0; x < 9; x++){

            System.out.print("R" + (x + 1) + " "); 

            for(int y = 0; y < 9; y++){

                System.out.print("  [" + this.grid[x][y] + "] "+ " ");
            }

        }

        System.out.println();
    }

    void clear_board(){

        this.grid = new Square[9][9]; //9x9 board filled with 0's
        this.grid_size = 81; 

    }


    /*****************
     * Getter Methods*
     */
    
    Square[][] getGrid(){

        return this.grid;
    }


    int get_gridSize(){

        return this.grid_size; 
    }

}
