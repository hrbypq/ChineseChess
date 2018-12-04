package pieces;

/**
 * ��
 * 
 * @author ypq
 * @version 1.0
 */
public class Bishop extends ChessPiece {

	public Bishop(int init_row, int init_col, boolean init_isRed, String aname) {
		super(init_row, init_col, init_isRed, aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol) {
		// ��������
		if (this.isRed) {
			if (toRow < 5) // ���ܹ���
				return false;
		} 
		// ����
		else {
			if (toRow > 5)
				return false;
		}
		if (this.row == toRow || this.col == toCol)
			return false;
		int temp = (toRow - this.row) * (toRow - this.row) + (toCol - this.col) * (toCol - this.col);
		if (temp != 8) // ������
			return false;
		return true;
	}
}
