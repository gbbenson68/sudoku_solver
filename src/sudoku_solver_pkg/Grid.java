package sudoku_solver_pkg;

import java.util.*;


// Constructs the grid and assigms all cells to the grid.
public class Grid {
	// TODO Move constants to be accessible across all classes of the package.
	public static final int ROW_TYPE = 1;
	public static final int COLUMN_TYPE = 2;
	public static final int SQUARE_TYPE = 3;
	
	public List<Cell> allCells;
	public List<Element> allElements;
	private boolean solved = false;
	
	// Getters and Setters
	public boolean getSolved() {
		return solved;
	}
	
	public void setSolved(boolean flag) {
		this.solved = flag;
	}
	
	/**
	 * Initial constructor for grids.
	 * @author 	GuyBBenson
	 * @param 	rawGrid - string form of grid used in initial creation.
	 */
	public Grid(String rawGrid) {
		allCells = new ArrayList<Cell>();
		allElements = new ArrayList<Element>();
		createGrid(rawGrid);
	}
	
	/**
	 * Grid constructor used for cloning/copying grids.
	 * @author 	GuyBBenson
	 */
	public Grid() {
		allCells = new ArrayList<Cell>();
		allElements = new ArrayList<Element>();
	}
	
	/**
	 * @author GuyBBenson
	 * @param rawGrid
	 */
	private void createGrid(String rawGrid) {  // BEGIN createGrid(String rawGrid)
		int dim1, dim2;
		String[] rowTokens, colTokens;
		
		// Count number of "rows" of input grid.
		rowTokens = rawGrid.split("\n");
		dim1 = rowTokens.length;

		// Check to see if input grid has an int square root: i.e. grid is 9x9 or 16x16 or 25x25, etc.
		if (isBadDimension(dim1)) {
			System.out.println("***** ERROR: Grid dimension does not have int square root!");
			System.exit(-1);
		}
		
		// Initialize rows
		for (int i = 0; i < dim1; i++) {
			allElements.add(new Element(ROW_TYPE));
		}
		
		// Count number of "columns" of input grid.
		// NOTE: We add the extra " " at the end because split() doesn't take into
		//       account the last spot if the last spot is not occupied.
		dim2 = dim1;
		for (int i = 0; i < rowTokens.length; i++) {
			colTokens = rowTokens[i].split(",");
			if (colTokens.length < dim2) {
				dim2 = colTokens.length;
			}
		}
		
		// Check to see if input grid is square.
		if (dim1 != dim2) {
			System.out.println("***** ERROR: Grid (" + dim1 + "x" + dim2 + ")" + " is not square!");
			System.exit(-1);
		}
		
		// Initialize columns
		for (int j = 0; j < dim2; j++) {
			allElements.add(new Element(COLUMN_TYPE));
		}
		
		// Finally, initialize squares - no extra logic needed here.
		for (int k = 0; k < dim2; k++) {
			allElements.add(new Element(SQUARE_TYPE));
		}		

		// **********
		//  REALLY start grid creation!
		//  
		//  Here, we create all of the cells for the given input grid.
		// **********
		int rowIdx = 0, colIdx = 0, squIdx = 0, val = 0;
		int step = (int) Math.sqrt(dim2);
		Cell thisCell;
		
		// Split grid into row tokens
		rowTokens = rawGrid.split("\n");
		for (int i = 0; i < rowTokens.length; i++) {
			colIdx = 0;
			
			// Split row into column tokens
			colTokens = rowTokens[i].split(",");
			for (int j = 0; j < colTokens.length; j++) {
				try {
					val = Integer.parseInt(colTokens[j]);
				} catch (NumberFormatException e) {
					val = 0;
				}
				thisCell = new Cell(rowIdx, colIdx, val);
				
				// If we have a real value here, it's part of the
				// solution - set cell to solved, otherwise init the
				// penciled values.
				if (val == 0) {
					thisCell.initCellPencVals(rowTokens.length);
				} else {
					thisCell.setSolved(true);
				}
				
				// Add cells to rows and columns.
				(getElement(ROW_TYPE,rowIdx)).cells.add(thisCell);
				(getElement(COLUMN_TYPE,colIdx)).cells.add(thisCell);
				
				// The square to which the cell belongs is based on rowIdx and colIdx.
				// NOTE: Integer divide is used here to truncate the values.
				squIdx = ((int)(rowIdx/step))*step + (int)(colIdx/step);
				(getElement(SQUARE_TYPE,squIdx)).cells.add(thisCell);
				
				// Set square ID after cell is assigned.
				thisCell.setSquareId(squIdx);
				
				colIdx++;
			}
			rowIdx++;
		}

	} // END createGrid(String rawGrid)
	
	/**
	 * @author			GuyBBenson
	 * @param dimension	The dimension (length) of the grid.
	 * @return			<code>boolean</code> True if dimension is bad, false if not.
	 */
	private boolean isBadDimension(int dimension) {
		int dim1 = dimension;
		int dim2 = (int)Math.pow((int)Math.sqrt(dimension), 2);
		
		// Return true if values are NOT equal!
		if (dim1 == dim2) {
			return false;
		} else {
			return true;
		}
	}
	
	public Element getElement (int elType, int elTypeId) {
		int elId = 0, myType = 0, myTypeId = 0;
	
		for (int i = 0; i < allElements.size(); i++) {
			myType = allElements.get(i).getType();
			myTypeId = allElements.get(i).getElTypeId();
			if (myType == elType && myTypeId == elTypeId) {
				elId = i;
				break;
			}
		}
		
		return allElements.get(elId);
	}
	
	/**
	 * 	public void listGridRows() {} - lists grid values by rows
	 */
	public void listGridValues(int type) {
		String[] strType = {"Row", "Column", "Square"};
		int elType, rowIdx, colIdx, id, val;
		List<Element> myElements = new ArrayList<Element>();
		
		for (int i = 0; i < allElements.size(); i++) {			
			elType = allElements.get(i).getType();
			if (type == elType) {
				myElements.add(allElements.get(i));
			}
		}
		
		for (int i = 0; i < myElements.size(); i++) {
			System.out.println("====================");
			System.out.println(strType[type-1] + " ID = " + myElements.get(i).getElTypeId());
			for (int j = 0; j < myElements.get(i).cells.size(); j++) {
				rowIdx = myElements.get(i).cells.get(j).getRowId();
				colIdx = myElements.get(i).cells.get(j).getColId();
				id = myElements.get(i).cells.get(j).getCellId();
				val = myElements.get(i).cells.get(j).getCellVal();
				System.out.println("\tCell (" + rowIdx + "," + colIdx + "): id = " + id + ", val = " + val);
			}
		}
		
	}
	
	/**
	 * public void displayGrid() {} - displays grid in viewable format.
	 */
	public void displayGrid() {
		int elType = 0;
		int step = (int)Math.cbrt(allElements.size());
		List<Element> myElements = new ArrayList<Element>();
		
		for (int i = 0; i < allElements.size(); i++) {			
			elType = allElements.get(i).getType();
			if (elType == ROW_TYPE) {
				myElements.add(allElements.get(i));
			}
		}		
		
		for (int i = 0; i < myElements.size(); i++) {
			for (int j = 0; j < myElements.get(i).cells.size(); j++) {
				System.out.print(myElements.get(i).cells.get(j).getCellVal() + "\t");
				if ( (j+1) < myElements.size() && (j+1)%step == 0) {
					System.out.print("|\t");
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
			if ( (i+1)%step == 0) {
				System.out.println();
			}
		}
		
	}
	
}
