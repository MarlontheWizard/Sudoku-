
/*Represents a single entry on the board. 
 *Each square can be thought of as a coordinate. 
 *Likewise, each quadrant can be thought of as a quadrant. 
 * 
 */
public class Square{


    private int column_coordinate; 
    private int row_coordinate;
    private int value; 
    private int quadrant; 


    public Square(){
        this.column_coordinate = -1;
        this.row_coordinate = -1;
        this.value = -1;
        this.quadrant = -1;
    }

    public Square(int value){
        this.column_coordinate = -1;
        this.row_coordinate = -1;
        this.quadrant = -1; 
        this.value = value;
    }

    public Square(int value, int x_coordinate, int y_coordinate, int quadrant){
        this.column_coordinate = x_coordinate;
        this.row_coordinate = y_coordinate; 
        this.value = value;
        this.quadrant = quadrant; 
    }

    public int get_value(){
        return this.value;
    }

    public int get_x_coordinate(){
        if(this.row_coordinate < 0){
            System.out.println("Square has not been assigned an index");
            return -1;
        }
        
     
        return this.row_coordinate;
    }

    public int get_y_coordinate(){
        
        if(this.column_coordinate < 0 || this.row_coordinate < 0){
            System.out.println("Square has not been assigned an index");
            return -1;
        }
        
        return this.column_coordinate;
    }

    public String toString(){
        
        String square_str = "Coordinates: (" + this.get_x_coordinate() +", " + this.get_y_coordinate() + ") Value: " + get_value(); 
        return square_str;
    }

    public void setQuadrant(int quad_toSet){
        
        this.quadrant = quad_toSet;
        return;
    }



    public int getQuadrant(){
        
        return this.quadrant;
    }


}

