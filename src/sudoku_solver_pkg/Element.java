package sudoku_solver_pkg;
	
import java.util.*;

public class Element {
	// TODO Move constants to be accessible across all classes of the package.
	public static final int ROW_TYPE = 1;
	public static final int COLUMN_TYPE = 2;
	public static final int SQUARE_TYPE = 3;
	
	private static int elIdCnt = 0, rowIdCnt = 0, colIdCnt = 0, squIdCnt = 0; // ID of element
	private int type;
	private int elId = 0, elTypeId = 0; // Individual element IDs
	private boolean solved = false;
	public List<Cell> cells = new ArrayList<Cell>();

	public Element (int type) {
		this.elId = elIdCnt++;
		setElTypeId(type);
		setSolved(false);
	}
	
	// Getters
	public int getType() {
		return type;
	}
	
	public int getElId() {
		return elId;
	}
	
	public int getElTypeId() {
		return elTypeId;
	}
	
	public boolean getSolved() {
		return solved;
	}
	
	// Setters	
	public void setElTypeId(int type) {
		this.type = type;
		if (type == ROW_TYPE) {
			this.elTypeId = rowIdCnt++;
		} else if (type == COLUMN_TYPE ) {
			this.elTypeId = colIdCnt++;
		} else {
			this.elTypeId = squIdCnt++;
		}
	}
	
	public void setSolved (boolean solved) {
		this.solved = solved;
	}

}
