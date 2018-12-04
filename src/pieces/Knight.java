package pieces;

/**
 * 马
 * 
 * @author ypq
 * @version 1.0
 */
public class Knight extends ChessPiece {
	public Knight(int init_row, int init_col, boolean init_isRed, String aname) {
		super(init_row, init_col, init_isRed, aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol) {
		int temp = (toRow - this.row) * (toRow - this.row) + (toCol - this.col) * (toCol - this.col);
		if (this.row == toRow || this.col == toCol)
			return false;
		if (temp != 5) // 马走日
			return false;
		return true;
	}
}
