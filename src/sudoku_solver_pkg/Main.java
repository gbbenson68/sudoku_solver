package sudoku_solver_pkg;
/**
 * @author GuyBBenson
 *
 */

import java.util.*;

public class Main {
	// TODO Move constants to be accessible across all classes of the package.
	public static final int ROW_TYPE = 1;
	public static final int COLUMN_TYPE = 2;
	public static final int SQUARE_TYPE = 3;
	
	// TODO - Write a more robust puzzle generator.
	public static void main(String[] args) {
		/**
		 * Here's the test grid that we want to solve
		 * 
		 *  1     |5 9 3 |    4
		 *    3   |      |5 1  
		 *  2 5 7 |    6 |     
		 *  --------------------
		 *      4 |6     |8 3   
		 *  6 2   |      |  5 9
		 *    1 3 |    4 |6    
		 *  --------------------  
		 *        |7     |1 8 2
		 *    9 2 |      |  4  
		 *  7     |8 5 2 |    3      
		 * 
		 * and here's the solution:
		 * 
		 *  1 8 6 |5 9 3 |2 7 4
		 *  4 3 9 |2 7 8 |5 1 6
		 *  2 5 7 |4 1 6 |3 9 8 
		 *  --------------------
		 *  9 7 4 |6 2 5 |8 3 1 
		 *  6 2 8 |1 3 7 |4 5 9
		 *  5 1 3 |9 8 4 |6 2 7 
		 *  --------------------  
		 *  3 6 5 |7 4 9 |1 8 2
		 *  8 9 2 |3 6 1 |7 4 5
		 *  7 4 1 |8 5 2 |9 6 3 
		 *  		         
		 */
		String rawGrid = "1,0,0,5,9,3,0,0,4\n" +
						 "0,3,0,0,0,0,5,1,0\n" +
						 "2,5,7,0,0,6,0,0,0\n" +
						 "0,0,4,6,0,0,8,3,0\n" +
						 "6,2,0,0,0,0,0,5,9\n" +
						 "0,1,3,0,0,4,6,0,0\n" +
						 "0,0,0,7,0,0,1,8,2\n" +
						 "0,9,2,0,0,0,0,4,0\n" +
						 "7,0,0,8,5,2,0,0,3";
		
		Grid myGrid = new Grid(rawGrid);
//		myGrid.listGridValues(1); // by rows
//		myGrid.listGridValues(2); // by columns
//		myGrid.listGridValues(3); // by squares
		
		// Display input grid
		System.out.println("==================== STARTING GRID ====================");
		myGrid.displayGrid();
		
		Solver Solver = new Solver(myGrid);
		Solver.solveGrid();
		
		System.out.println("===================== GRID SOLVED =====================");
		System.out.println("===================== FINAL GRID ======================");
		myGrid.displayGrid();

	}
}

