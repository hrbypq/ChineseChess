package view;

import board.*;
import pieces.*;
import java.util.*;

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
			System.out.printf("%d", i+1);
			for(int j=0;j<9;j++) {
				if(pieces[i][j]!=null)
					System.out.printf("%10s",pieces[i][j].getName());
				else
					System.out.printf("%10s", "0");
			}
			for(int k=0;k<3;k++)
				System.out.println();
		}
		for(int i=0;i<9;i++)
			System.out.printf("%10d", i+1);
		for(int i=0;i<2;i++)
			System.out.println();
	}
	
	/**
	 * pvp模式
	 */
	public void playPVP() {
		//轮到哪方行棋 红方为真
		boolean isRedTurn=true;
		int row=0,col=0,toRow=0,toCol=0;
		controller=new Controller();
		Scanner scanner=new Scanner(System.in);
		while(!controller.hasWin()) {
			printBoard();
			System.out.printf("当前%s方行棋\n",(isRedTurn==true)?"红":"黑");
			//输入要移动的棋子的坐标
			while(true) {
				System.out.println("输入要移动的子的坐标(行、列)");
				while(true) {
					try{
						row=scanner.nextInt();
						col=scanner.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("输入错误，请重新输入");
						continue;
					}
					break;
				}
				if(controller.getBorad().getPieces()[row-1][col-1]==null) {
					System.out.println("该位置无棋子,请重新输入");
					continue;
				}
				if(controller.getBorad().getPieces()[row-1][col-1].getColor()!=isRedTurn) {
					System.out.println("不能移动对方棋子,请重新输入");
					continue;
				}
				break;
			}
			//输入要移动到的坐标
			while(true) {
				System.out.println("输入要移动到的坐标");
				try{
					toRow=scanner.nextInt();
					toCol=scanner.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("输入错误,请重新输入");
					continue;
				}
				break;
			}
			boolean flag=controller.movePiece(row-1, col-1, toRow-1, toCol-1, isRedTurn);   //移动棋子
			if(!flag) {
				System.out.println("行棋不合法！请重新输入");
				continue;
			}
			isRedTurn=!isRedTurn;
		}
		System.out.printf("%s方获胜!\n",(isRedTurn==true)?"黑":"红");
		System.out.println("游戏结束");
		scanner.close();
	}
	
	
	
}









