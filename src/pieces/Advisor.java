package pieces;

/**
 * ��
 * 
 * @author ypq
 * @version 1.0
 */
public class Advisor extends ChessPiece {

	public Advisor(int init_row, int init_col, boolean init_isRed, String aname) {
		super(init_row, init_col, init_isRed, aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol) {
		if (toCol < 3 || toCol > 5)
			return false;
		// ��������
		if (this.isRed) {
			if (toRow < 7 || toRow > 9)
				return false;
		} 
	    // ����	
		else {    
			if (toRow < 0 || toRow > 2)
				return false;
		} // �����߳��Ź���
		int temp = (toRow - this.row) * (toRow - this.row) + (toCol - this.col) * (toCol - this.col);
		if (temp != 2)
			return false; // ����б��
		return true;
	}
}
