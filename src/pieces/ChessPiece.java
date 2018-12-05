package pieces;

/**
 * ���ӳ�����
 * 
 * @version 1.0
 * @author ypq
 */
public abstract class ChessPiece {
	// ������ɫ ��ɫΪ��
	protected boolean isRed = true;
	// ��ǰ��������
	protected int row = 0;
	// ��ǰ��������
	protected int col = 0;
	// ��������
	protected String name;

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public boolean getColor() {
		return this.isRed;
	}

	public String getName() {
		return this.name;
	}

	public void setRow(int new_row) {
		this.row = new_row;
	}

	public void setCol(int new_col) {
		this.col = new_col;
	}

	/**
	 * ���췽��
	 * 
	 * @param init_row   ��ʼ��
	 * @param init_col   ��ʼ��
	 * @param init_isRed ��ɫ ��ɫΪtrue
	 * @param aname      ���� ����BRook RPawn
	 */
	public ChessPiece(int init_row, int init_col, boolean init_isRed, String aname) {
		this.row = init_row;
		this.col = init_col;
		this.isRed = init_isRed;
		this.name = aname;
	}

/**
 * ������ӱ����жϴ˲��Ƿ�Ϸ�
 * @param toRow
 * @param toCol
 * @return
 */
	abstract public boolean isLegalMove(int toRow, int toCol);
}
