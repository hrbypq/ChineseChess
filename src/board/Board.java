package board;

import pieces.*;

public class Board {
	/**
	 * 构造方法 初始化棋盘
	 */
	public Board() {
		initBoard();
	}
	// 最大行数
	public static final int MAX_ROW = 9;
	// 最大列数
	public static final int MAX_COL = 8;
	// 存储棋子
	private ChessPiece[][] pieces;

	/**
	 * 初始化棋盘
	 */
	public void initBoard() {
		pieces = new ChessPiece[MAX_ROW][MAX_COL];
		
	}
}
