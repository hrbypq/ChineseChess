package board;

import pieces.*;

public class Board {
	/**
	 * ���췽�� ��ʼ������
	 */
	public Board() {
		initBoard();
	}
	// �������
	public static final int MAX_ROW = 9;
	// �������
	public static final int MAX_COL = 8;
	// �洢����
	private ChessPiece[][] pieces;

	/**
	 * ��ʼ������
	 */
	public void initBoard() {
		pieces = new ChessPiece[MAX_ROW][MAX_COL];
		
	}
}
