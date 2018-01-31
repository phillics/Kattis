/*
Christian Phillips
1/10/2018

Questions:
	submit .java or .jar?


perform initial test to make sure there are possible solutions
	create array of possible moves for each position
Sudoku Solver
*/
import java.util.*;

public class SudokuUnique{

    private int[][] solution;
    private int[][] firstSolution;
    private boolean solved;
    private boolean unique;
    private final int dimension = 9;

    public SudokuUnique(){
        solution = new int[dimension][dimension];
        firstSolution = new int[dimension][dimension];
        Scanner scanner = new Scanner(System.in);
        int count = 1;

        while(scanner.hasNextInt()){
            solved = false;
            unique = true;
            readSudoku(scanner);
			if(initialTest())
            	solveIt(0);

            //output
            if(solved && unique)
                printIt();
            else if(!solved)
                System.out.println("Find another job\n");
            else
                System.out.println("Non-unique\n");

        }
    }

    public static void main(String[] args){

        SudokuUnique mySudoku = new SudokuUnique();
        //program done
    }
	private boolean initialTest(){

		int count = 0;
		for(int i = 0; i < 81; i++){
			int row = i / dimension;
	        int col = i % dimension;
			if(solution[row][col] != 0){
				count++;
				if(!isValid(i))
					return false;
			}
		}
		if(count >= 17)
			return true;
		return false;
	}

    private void readSudoku(Scanner scanner){
        for(int r = 0; r < dimension; r++)
            for(int c = 0; c < dimension; c++)
                solution[r][c] = scanner.nextInt();
    }

    private void printIt(){
        for(int r = 0; r < dimension; r++){
            for(int c = 0; c < dimension; c++)
                System.out.printf("%d ", firstSolution[r][c]);

            System.out.println();
        }
        System.out.println();
    }
    private void copySolution(){
        for(int r = 0; r < dimension; r++)
            for(int c = 0; c < dimension; c++)
                firstSolution[r][c] = solution[r][c];
    }

    private void solveIt(int position){
        //if puzzle already solved
        //if(solved) return; // only find first valid solution...

        //if last spot has been filled, puzzle is solved
        if(position >= 81){
            if(solved == true)
                unique = false;
            else
                copySolution();
            solved = true;
            return;
        }

        int row = position / dimension;
        int col = position % dimension;

        /*if current position is already occupied, skip it*/
        if(solution[row][col] != 0){
            if(!isValid(position))
                return;
            solveIt(position + 1);
            return;
        }

        //else, need to go through all options
        for(int i = 1; i < 10; i++){

            //try applying change
            solution[row][col] = i;

            //check if valid move, proceed to next spot
            if(isValid(position)){

                //solve the rest of the puzzle
                solveIt(position + 1);
            }

            //quick escape from loop if puzzle is solved
            if(!unique)
                return;
        }

        //undo changes for backtracking
        solution[row][col] = 0;
    }

    //check if value at index collides with anything in
    //row, column, or box
    private boolean isValid(int index){
        int row = index / dimension; //integer division
        int col = index % dimension;
        int value = solution[row][col];
        int i, r, c;

        //check row and col
        for( i = 0; i < dimension; i++){
            if(i != col && solution[row][i] == value)
                return false;
			if(i != row && solution[i][col] == value)
	        	return false;
		}

        /*  check box
            0   3   6       0,1,2 -> 0
            3
            6

            min                 max
            (row / 3 ) * 3      (row / 3) * 3 + 3
        */
        int rMax = (row / 3) * 3 + 3;
        int cMax = (col / 3) * 3 + 3;

        for(r = (row / 3)*3; r < rMax; r++)
            for(c = (col / 3)*3; c < cMax; c++)
                if(solution[r][c] == value && (r != row || c != col))
                    return false;

        return true;

    }
}
