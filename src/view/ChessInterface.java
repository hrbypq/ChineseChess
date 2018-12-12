package view;

import board.*;
import pieces.*;
import java.util.*;

/**
 * ���� ��ʱΪ�����н���
 * @author ypq
 *@version 1.0
 */
public class ChessInterface {

	public ChessInterface() {
		controller=new Controller();
	}
	
	private Controller controller;
	
	/**
	 * ��ӡ����
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
	 * pvpģʽ
	 */
	public void playPVP() {
		//�ֵ��ķ����� �췽Ϊ��
		boolean isRedTurn=true;
		int row=0,col=0,toRow=0,toCol=0;
		controller=new Controller();
		Scanner scanner=new Scanner(System.in);
		while(!controller.hasWin()) {
			printBoard();
			System.out.printf("��ǰ%s������\n",(isRedTurn==true)?"��":"��");
			//����Ҫ�ƶ������ӵ�����
			while(true) {
				System.out.println("����Ҫ�ƶ����ӵ�����(�С���)");
				while(true) {
					try{
						row=scanner.nextInt();
						col=scanner.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("�����������������");
						continue;
					}
					break;
				}
				if(controller.getBorad().getPieces()[row-1][col-1]==null) {
					System.out.println("��λ��������,����������");
					continue;
				}
				if(controller.getBorad().getPieces()[row-1][col-1].getColor()!=isRedTurn) {
					System.out.println("�����ƶ��Է�����,����������");
					continue;
				}
				break;
			}
			//����Ҫ�ƶ���������
			while(true) {
				System.out.println("����Ҫ�ƶ���������");
				try{
					toRow=scanner.nextInt();
					toCol=scanner.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("�������,����������");
					continue;
				}
				break;
			}
			boolean flag=controller.movePiece(row-1, col-1, toRow-1, toCol-1, isRedTurn);   //�ƶ�����
			if(!flag) {
				System.out.println("���岻�Ϸ�������������");
				continue;
			}
			isRedTurn=!isRedTurn;
		}
		System.out.printf("%s����ʤ!\n",(isRedTurn==true)?"��":"��");
		System.out.println("��Ϸ����");
		scanner.close();
	}
	
	
	
}









