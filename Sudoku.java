public class Sudoku {
    private Board board; 
    private int difficulty; //Difficulty ranges from 1-9. See Documentation For More Info. 

    public Sudoku(){
        set_difficulty();
        this.board = new Board(difficulty); 


    }

    public void set_difficulty(){

        int input; 
        System.out.println("Please enter the desired difficulty: ");
        this.difficulty = input;
    }


    
}
