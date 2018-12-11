package view;

import board.*;
import pieces.*;

/**
 * 界面 暂时为命令行界面
 * @author ypq
 *@version 1.0
 */
public class ChessInterface {

	public ChessInterface() {
		controller=new Controller();
	}
	
	private Controller controller;
	
	/**
	 * 打印棋盘
	 */
	public void printBoard() {
		Board board=controller.getBorad();
		ChessPiece[][] pieces=board.getPieces();
		for(int i=0;i<10;i++) {
			for(int j=0;j<9;j++) {
				if(pieces[i][j]!=null)
					System.out.printf("%10s",pieces[i][j].getName());
				else
					System.out.printf("%10s", "0");
			}
			for(int k=0;k<3;k++)
				System.out.println();
		}
	}
	
	/**
	 * pvp模式
	 */
	public void playPVP() {
		
	}
}
