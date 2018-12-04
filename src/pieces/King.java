package pieces;

/**
 * ��/˧
 * 
 * @author ypq
 * @version 1.0
 */
public class King extends ChessPiece {
	public King(int init_row, int init_col, boolean init_isRed, String aname) {
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
		if (toRow != this.row && toCol != this.col)    // ����б��
			return false;
		if(toRow-this.row>1||toRow-this.row<-1||toCol-this.col>1||toCol-this.col<-1)     //ֻ����һ��
			return false;
		return true;
	}
}