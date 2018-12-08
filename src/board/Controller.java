package board;

/**
 * 控制类
 * @author ypq
 *@version 1.0
 */
public class Controller {
	
	/**
	 * 构造方法
	 */
	public Controller() {
		board=new Board();
	}
	
	
	private Board board;
	
	/**
	 * 移动棋子
	 * @param Row
	 * @param Col
	 * @param toRow
	 * @param toCol
	 * @return  是否移动成功
	 */
	public boolean movePiece(int Row,int Col,int toRow,int toCol) {
		boolean state=board.generateMove(Row, Col, toRow, toCol);
		if(!state)
			return false;
		return true;
	}
	
	/**
	 * 复制棋盘 给界面类用
	 * @return
	 */
	public Board getBorad() {
		Board obj=(Board)board.clone();
		return obj;
	}
	
	
	public void playPVP() {
		
	}

}








