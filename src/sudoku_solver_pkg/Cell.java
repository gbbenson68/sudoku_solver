package sudoku_solver_pkg;

import java.util.*;

public class Cell {
	private int cellId, rowId, colId, squareId;
	private boolean penciled, solved;
	private int cellVal;
	private static int idcnt = 0;
	public List<Integer> cellPencVals = new ArrayList<Integer>();
	
	/**
	 * Main constructor
	 * @param i		input row ID
	 * @param j		input columns ID
	 * @param val	input cell value
	 * @param dim	dimension of grid
	 */
	public Cell (int i, int j, int val) {
		this.cellId = idcnt++;
		this.rowId = i;
		this.colId = j;
		setSquareId(0);
		this.cellVal = val;
		setPenciled(false);
		setSolved(false);
	}
	
	// Getters
	public int getCellId() {
		return cellId;
	}
	
	public int getRowId() {
		return rowId;
	}
	
	public int getColId() {
		return colId;
	}
	
	public int getSquareId() {
		return squareId;
	}
	
	public int getCellVal() {
		return cellVal;
	}
	
	public boolean getPenciled() {
		return penciled;
	}
	
	public boolean getSolved() {
		return solved;
	}
	
	// Setters	
	public void setSquareId(int squId) {
		this.squareId = squId;
	}
	
	public void setCellVal(int cellVal) {
		this.cellVal = cellVal;
	}
	
	public void setPenciled(boolean val) {
		this.penciled = val;
	}
	
	public void setSolved(boolean val) {
		this.solved = val;
	}

	public void initCellPencVals(int dim) {
		for (int i = 0; i < dim; i++) {
			this.cellPencVals.add(i+1);
		}
	}
}
