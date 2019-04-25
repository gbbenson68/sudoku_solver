package sudoku_solver_pkg;

import java.util.*;

public class Solver {
	// TODO Move constants to be accessible across all classes of the package.
	public static final int ROW_TYPE = 1;
	public static final int COLUMN_TYPE = 2;
	public static final int SQUARE_TYPE = 3;
	
	private Grid inputGrid;

	/**
	 * public Solver(Grid inputGrid) - main grid solver.
	 * @param inputGrid
	 */
	public Solver(Grid inputGrid) {
		this.inputGrid = inputGrid;
	}
	
	/**
	 * public void solveGrid() {} - main solver procedure
	 */
	public void solveGrid() {
		int cnt = 0;
		while (!inputGrid.getSolved()) {
			if (cnt == 1000) {
				System.out.println("****** COULD NOT RESOLVE GRID AFTER 1000 STEPS!");
				break;
			}
			// First step - reduce until cannot reduce any farther.
			reduceGrid();
			cnt++;
			
			// TODO - More complex code for more difficult puzzles.
		}
	}
	
	/**
	 *  public void reduceGrid() {} - reduce number of penciled values.
	 */
	public void reduceGrid() {
		Cell thisCell;
		Element thisElement;
		List<Cell> unsolvedCells;
		List<Integer> solvedVals;
		List<Element> unsolvedElements = new ArrayList<Element>();
		
		// Loop through all elements and reduce as necessary.
		
		for (int i = 0; i < inputGrid.allElements.size(); i++) {
			if (!inputGrid.allElements.get(i).getSolved()) {
				unsolvedElements.add(inputGrid.allElements.get(i));
			}
		}
		
		if (unsolvedElements.size() == 0) { // Grid is solved, nothing to do!
			inputGrid.setSolved(true);
			return;
		}
		
		for (int i = 0; i < inputGrid.allElements.size(); i++) {
			
			thisElement = inputGrid.allElements.get(i);
			solvedVals = new ArrayList<Integer>();
			unsolvedCells = new ArrayList<Cell>();
			
			// Loop through the individual elements and determine which cells
			// have already been solved.
			for (int j = 0; j < thisElement.cells.size(); j++) {
				if (thisElement.cells.get(j).getSolved()) {
					solvedVals.add(thisElement.cells.get(j).getCellVal());
				} else {
					unsolvedCells.add(thisElement.cells.get(j));
				}
			}
			
			if (unsolvedCells.size() == 0) {
			thisElement.setSolved(true);
			continue;
		}
			
			// For all unsolved cells, reduce the list of possible "penciled" values. 
			for (int j = 0; j < unsolvedCells.size(); j++) {		
				thisCell = unsolvedCells.get(j);
				for (int k = 0; k < solvedVals.size(); k++) {
					thisCell.cellPencVals.remove(solvedVals.get(k));
				}
			}
			
			// Lastly, if there is only one "penciled" value left, solve the cell 
			// with that value.
			for (int j = 0; j < unsolvedCells.size(); j++) {		
				thisCell = unsolvedCells.get(j);
				if (thisCell.cellPencVals.size() == 1) {
					thisCell.setCellVal(thisCell.cellPencVals.get(0));
					thisCell.cellPencVals = null;
					thisCell.setSolved(true);
				}
			}
			
		}
	
	}
	
}
