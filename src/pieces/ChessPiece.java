package pieces;

/**
 * 棋子抽象类
 * 
 * @version 1.0
 * @author ypq
 */
public abstract class ChessPiece {
	// 棋子颜色 红色为真
	protected boolean isRed = true;
	// 当前所处行数
	protected int row = 0;
	// 当前所处列数
	protected int col = 0;
	// 棋子名字
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
	 * 构造方法
	 * 
	 * @param init_row   初始行
	 * @param init_col   初始列
	 * @param init_isRed 颜色 红色为true
	 * @param aname      名字 例如BRook RPawn
	 */
	public ChessPiece(int init_row, int init_col, boolean init_isRed, String aname) {
		this.row = init_row;
		this.col = init_col;
		this.isRed = init_isRed;
		this.name = aname;
	}

/**
 * 针对棋子本身判断此步是否合法
 * @param toRow
 * @param toCol
 * @return
 */
	abstract public boolean isLegalMove(int toRow, int toCol);
}
