/*
Christian Phillips
1/10/2018

Sudoku Solver
*/
import java.util.*;

public class Sudoku{

	private int[][] solution;
	private boolean solved;
	private final int dimension = 9;

	public Sudoku(){
		solution = new int[dimension][dimension];
		Scanner scanner = new Scanner(System.in);
		int repitions = scanner.nextInt();
		int count = 1;

		while(repitions > 0){
			solved = false;
			readSudoku(scanner);

			if(initialTest())
				solveIt(0);

			System.out.printf("Test case %d:\n\n", count++);
			printIt();

			repitions--;
		}
	}

	public static void main(String[] args){
		Sudoku mySudoku = new Sudoku();
	}

	//check to make sure input puzzle is valid
	private boolean initialTest(){
		for(int i = 0; i < 81; i++){
			int row = i / dimension;
	        int col = i % dimension;
			if(solution[row][col] != 0)
				if(!isValid(i))
					return false;
		}
		return true;
	}

	private void readSudoku(Scanner scanner){
		for(int r = 0; r < dimension; r++)
			for(int c = 0; c < dimension; c++)
				solution[r][c] = scanner.nextInt();
	}

	private void printIt(){

		if(!solved){
			System.out.println("No solution possible.\n\n");
			return;
		}

		for(int r = 0; r < dimension; r++){
			for(int c = 0; c < dimension; c++)
				System.out.printf("%d ", solution[r][c]);

			System.out.println();
		}
		System.out.printf("\n\n");
	}

	/*check if number inserted at current position is valid*/
	private void solveIt(int position){
		//if puzzle already solved
		if(solved) return; // only find first valid solution...

		//if last spot has been filled, puzzle is solved
		if(position >= 81){
			solved = true;
			return;
		}

		int row = position / dimension;
		int col = position % dimension;

		/*if current position is already occupied, skip it*/
		if(solution[row][col] != 0){
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
			if(solved)
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
			(row / 3 ) * 3		(row / 3) * 3 + 3		*/
		int rMax = (row / 3) * 3 + 3;
		int cMax = (col / 3) * 3 + 3;

		for(r = (row / 3)*3; r < rMax; r++)
			for(c = (col / 3)*3; c < cMax; c++)
				if(solution[r][c] == value && (r != row || c != col))
					return false;

		return true;
	}
}
