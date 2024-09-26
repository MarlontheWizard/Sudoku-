import java.io.IOException;

public class Sudoku {
    private Board board; 
    private int difficulty; //Difficulty ranges from 1-9. See Documentation For More Info. 
    private int players;

    public Sudoku(){
        
        
        try{

            set_difficulty();
        } 
        
        catch (IOException err) {
        
            err.printStackTrace();
            System.out.println("Error retrieving input values for initialization of difficulty on startup. Please restart application.");
            
        }
        
        try{

            setPlayers();
        } 
        
        catch (IOException err) {
        
            err.printStackTrace();
            System.out.println("Initialization of players failed on startup. Please restart application.");
        }
    
    
        this.board = new Board(this.difficulty);
    }


    void display_Initialization(){

        System.out.println("Welcome to Sudoku!");
        System.out.println("@Author: Marlon Dominguez\n@Version: 0.01 Demo");
        System.out.println("Initializing Game...");
    }


    void print_Termination_Request(){

        System.out.println("****Enter 0 to terminate game****");
        return;
    }

    void terminate_Game(){

        System.exit(0);
    }




    /*
     * Getter Methods
     */

    int getDifficulty(){

        return this.difficulty;
    }

    Board getBoard(){

        return this.board;
    }

    int getPlayers(){

        return players; 
    }

    /*
     * Setter Methods
     */

    boolean setPlayers() throws IOException{

        System.out.print("Please enter the number of players: ");


        int num_ofPlayers = System.in.read();


        if(num_ofPlayers < 1){

            System.out.println("Need atleast 1 player to initialize game.");
            return false; 
        }

        this.players = num_ofPlayers;

        return true; 
    }

    boolean setPlayers(int num_ofPlayers){

        if(num_ofPlayers < 1){

            System.out.println("Need atleast 1 player to initialize game.");
            return false; 
        }

        this.players = num_ofPlayers;

        return true; 
    }



    public boolean set_difficulty() throws IOException{

        System.out.print("Please enter the desired difficulty: ");


        int response = System.in.read();

        while(response < 1 || response > 9){

            System.out.println("Difficulty does not exist.\nDifficulty levels range from 1 to 9, the hardest difficulty.");
            
            print_Termination_Request();

            response = System.in.read();

            if(response == 0){

                return false; 
            }
        }

        this.difficulty = response;

        return true; 
    }
    
}
