/*
Christian Phillips
1/10/2018

Questions:
	submit .java or .jar?



Sudoku Solver
*/
import java.util.*;

public class Sudoku{

	private int[][] solution;
	private boolean valid;
	private final int dimension = 9;
	private final boolean debug = false; 	//for debugging

	public Sudoku(){
		solution = new int[dimension][dimension];
		Scanner scanner = new Scanner(System.in);
		int repitions = scanner.nextInt();
		int count = 1;

		while(repitions > 0){
			valid = false;
			readSudoku(scanner);
			solveIt(0);

			//output
			System.out.printf("Test case %d:\n", count++);
			if(valid)
				printIt();
			else
				System.out.println("No solution possible");

			repitions--;
		}
	}

	public static void main(String[] args){

		Sudoku mySudoku = new Sudoku();
		//program done
	}

	private void readSudoku(Scanner scanner){
		for(int r = 0; r < dimension; r++)
			for(int c = 0; c < dimension; c++)
				solution[r][c] = scanner.nextInt();
	}

	private void printIt(){
		for(int r = 0; r < dimension; r++){
			for(int c = 0; c < dimension; c++)
				System.out.printf("%d", solution[r][c]);

			System.out.println();
		}
		System.out.println();
	}


	/*
	Note:  there are a few debug statements left in because i think it is cool
	to trace through the backtracking
	*/
	private void solveIt(int position){
		//if puzzle already solved
		if(valid) return; // only find first valid solution...

		//if last spot has been filled, puzzle is solved
		if(position >= 81){
			valid = true;
			if(debug){
				System.out.printf("FOUND VALID SOLUTION\n");
				printIt();
			}
			return;
		}

		int row = position / dimension;
		int col = position % dimension;

		/*if current position is already occupied, skip it*/
		if(solution[row][col] != 0){
			solveIt(position + 1);
			return;
		}

		if(debug)
			for(int i = 0; i < position; i++)
				System.out.printf(" ");

		//else, need to go through all options
		for(int i = 1; i < 10; i++){
			if(debug)
				System.out.printf("%d", i);

			//try applying change
			solution[row][col] = i;

			//check if valid move, proceed to next spot
			if(isValid(position)){

				if(debug)
					System.out.println();

				//solve the rest of the puzzle
				solveIt(position + 1);

				//re-establish spacing
				if(debug)
					for(int j = 0; j < position; j++)
						System.out.printf(" ");
			}

			if(debug && i == 9)
				System.out.println();

			//quick escape from loop if puzzle is solved
			if(valid)
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
		int r,c;

		//check row
		for(c = 0; c < dimension; c++)
			if(c != col && solution[row][c] == value)
				return false;


		//check col
		for(r = 0; r < dimension; r++)
			if(r != row && solution[r][col] == value)
				return false;

		/*  check box
			0	3	6		0,1,2 -> 0
			3
			6

			min  				max
			(row / 3 ) * 3		(row / 3) * 3 + 3
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
