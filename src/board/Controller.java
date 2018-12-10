package board;

import java.util.*;

/**
 * 控制类  不造有没有用
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
	 * @param color
	 * @return  是否移动成功
	 */
	public boolean movePiece(int Row,int Col,int toRow,int toCol,boolean color) {
		if()
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
	
	/**
	 * 判断棋局是否结束
	 * @return
	 */
	public boolean hasWin() {
		return board.getState();
	}
}








