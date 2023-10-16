import java.util.LinkedList;
import  java.lang.Math;
public class Board{
    /*Implements adjacency list as data structure */
    private LinkedList<Integer> grid[];
    private int grid_size = 81; //81 squares in total

    public Board(int difficulty){
            for(int row = 0; row < 9; row++){
                grid[row] = new LinkedList<Integer>();
            }
        }
    
    public void fill_initial_board(int grid_size, int difficulty){
        int lower_bound = grid_size / difficulty - 6; 
        int upper_bound = grid_size / difficulty - 1; 

        //Generate random number of initial values to fill
        int num_of_initial_values = (int) (Math.random() * (upper_bound - lower_bound));
        
        /*To ensure that the initial fill is completely random each time, we will
        * once again use Math.Random() to generate a random column.*/
        for(LinkedList<Integer> row : grid){
            int column = (int) (Math.random() * 9); 
            row.add(column, )

        }
    }
    
    
    

}
