package pieces;

/**
 * 车
 * 
 * @author ypq
 * @version 1.0
 */
public class Rook extends ChessPiece {

	public Rook(int init_row, int init_col, boolean init_isRed, String aname) {
		super(init_row, init_col, init_isRed, aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol) {
		if (toRow != this.row && toCol != this.col) // 车不能斜走
			return false;
		return true;
	}
}
