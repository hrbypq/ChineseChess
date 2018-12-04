package pieces;

/**
 * ÅÚ
 * 
 * @author ypq
 * @version 1.0
 */
public class Cannon extends ChessPiece {
	public Cannon(int init_row, int init_col, boolean init_isRed, String aname) {
		super(init_row, init_col, init_isRed, aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol) {
		if (toRow != this.row && toCol != this.col) // ÅÚ²»ÄÜÐ±×ß
			return false;
		return true;
	}
}
