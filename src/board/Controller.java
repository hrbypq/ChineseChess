package board;

import java.util.*;

/**
 * ������  ������û����
 * @author ypq
 *@version 1.0
 */
public class Controller {
	
	/**
	 * ���췽��
	 */
	public Controller() {
		board=new Board();
	}
	
	private Board board;
	
	/**
	 * �ƶ�����
	 * @param Row
	 * @param Col
	 * @param toRow
	 * @param toCol
	 * @param color
	 * @return  �Ƿ��ƶ��ɹ�
	 */
	public boolean movePiece(int Row,int Col,int toRow,int toCol,boolean color) {
		if()
		boolean state=board.generateMove(Row, Col, toRow, toCol);
		if(!state)
			return false;
		return true;
	}
	
	/**
	 * �������� ����������
	 * @return
	 */
	public Board getBorad() {
		Board obj=(Board)board.clone();
		return obj;
	}
	
	/**
	 * �ж�����Ƿ����
	 * @return
	 */
	public boolean hasWin() {
		return board.getState();
	}
}








