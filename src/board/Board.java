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
	public static final int MAX_ROW = 10;
	// 最大列数
	public static final int MAX_COL = 9;
	// 存储棋子
	private ChessPiece[][] pieces;

	/**
	 * 初始化棋盘
	 */
	public void initBoard() {
		pieces = new ChessPiece[MAX_ROW][MAX_COL];
		pieces[0][0]=new Rook(0,0,false,"Rook");
		pieces[0][8]=new Rook(0,8,false,"Rook");
		pieces[0][1]=new Knight(0,1,false,"Knight");
		pieces[0][7]=new Knight(0,7,false,"Knight");
		pieces[0][2]=new Bishop(0,2,false,"Bishop");
		pieces[0][6]=new Bishop(0,6,false,"Bishop");
		pieces[0][3]=new Advisor(0,3,false,"Advisor");
		pieces[0][5]=new Advisor(0,5,false,"Advisor");
		pieces[0][4]=new King(0,4,false,"King");
		pieces[2][1]=new Cannon(2,1,false,"Cannon");
		pieces[2][7]=new Cannon(2,7,false,"Cannon");
		
		pieces[9][0]=new Rook(9,0,true,"Rook");
		pieces[9][8]=new Rook(9,8,true,"Rook");
		pieces[9][1]=new Knight(9,1,true,"Knight");
		pieces[9][7]=new Knight(9,7,true,"Knight");
		pieces[9][2]=new Bishop(9,2,true,"Bishop");
		pieces[9][6]=new Bishop(9,6,true,"Bishop");
		pieces[9][3]=new Advisor(9,3,true,"Advisor");
		pieces[9][5]=new Advisor(9,5,true,"Advisor");
		pieces[9][4]=new King(9,4,true,"King");
		pieces[7][1]=new Cannon(7,1,true,"Cannon");
		pieces[7][7]=new Cannon(7,7,true,"Cannon");
		
		for(int i=0;i<=8;i+=2) {
			pieces[3][i]=new Pawn(3,i,false,"Pawn");
			pieces[6][i]=new Pawn(6,i,true,"Pawn");
		}
	}
	
	/**
	 * 根据棋局判断此步是否合法
	 * @param piece
	 * @param toRow
	 * @param toCol
	 * @return
	 */
	public boolean isLegalMove(ChessPiece piece,int toRow,int toCol) {
		//检测重复落点
		if(toRow==piece.getRow()&&toCol==piece.getCol())     
			return false;
		//不能超出棋盘
		if(toRow<0||toCol<0||toRow>MAX_ROW||toCol>MAX_COL)
			return false;
		//针对棋子本身判断此步是否合法
		if(!piece.isLegalMove(toRow, toCol))
			return false;
		//不能吃自己的子
		if(pieces[toRow][toCol]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
			return false;
		//根据棋局判断此步是否合法
		switch(piece.getName()) {
		
		//兵
		case "Pawn":{
			break;
		}
		
		//车
		case "Rook":{
			//横向行走情形  不能越过棋子
			if(toRow==piece.getRow()) {
				if(toCol>piece.getCol()) {
					for(int i=piece.getCol();i<toCol;i++) {
						if(pieces[toRow][i]!=null) 
							return false;
					}
				}
				else {
					for(int i=toCol;i<piece.getCol();i++) {
						if(pieces[toRow][i]!=null) 
							return false;
					}
				}
			}
			//纵向行走情形   不能越过棋子
			if(toCol==piece.getCol()) {
				if(toRow>piece.getRow()) {
					for(int i=piece.getRow();i<toRow;i++) {
						if(pieces[i][toCol]!=null)
							return false;
					}
				}
				else {
					for(int i=toRow;i<piece.getRow();i++) {
						if(pieces[i][toCol]!=null)
							return false;
					}
				}
			}
		}
		
		//马
		case "Knight":{
			//不能蹩马腿
			if(toCol==piece.getCol()-2) {
				if(pieces[piece.getRow()][piece.getCol()-1]!=null) 
					return false;
			}
			if(toCol==piece.getCol()+2) {
				if(pieces[piece.getRow()][piece.getCol()+1]!=null) 
					return false;
			}
			if(toRow==piece.getRow()-2) {
				if(pieces[piece.getRow()-1][piece.getCol()]!=null) 
					return false;
			}
			if(toRow==piece.getRow()+2) {
				if(pieces[piece.getRow()+1][piece.getCol()]!=null) 
					return false;
			}
		}
		
		//炮
		case "Cannon":{
			//只有吃子时能越过棋子
			//横向移动情形
			if(toRow==piece.getRow()) {
				if(toCol>piece.getCol()) {
					for(int i=piece.getCol();i<toCol;i++) {
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol].getColor()!=piece.getColor())
							return false;
					}
				}
				if(toCol<piece.getCol()) {
					for(int i=toCol;i<piece.getCol();i++) {
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol].getColor()!=piece.getColor())
							return false;
					}
				}
			}
			//纵向移动情形
			if(toCol==piece.getCol()) {
				if(toRow>piece.getRow()) {
					for(int i=piece.getRow();i<toRow;i++) {
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol].getColor()!=piece.getColor())
							return false;
					}
				}
				if(toRow<piece.getRow()) {
					for(int i=toRow;i<piece.getRow();i++) {
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol].getColor()!=piece.getColor())
							return false;
					}
				}
			}
		}
		
		//象
		case "Bishop":{
			//象眼位不能有棋子
			if(toRow==piece.getRow()-2&&toCol==piece.getCol()-2) {
				if(pieces[toRow+1][toCol+1]!=null)
					return false;
			}
			if(toRow==piece.getRow()-2&&toCol==piece.getCol()+2) {
				if(pieces[toRow+1][toCol-1]!=null)
					return false;
			}
			if(toRow==piece.getRow()+2&&toCol==piece.getCol()-2) {
				if(pieces[toRow-1][toCol+1]!=null)
					return false;
			}
			if(toRow==piece.getRow()+2&&toCol==piece.getCol()+2) {
				if(pieces[toRow-1][toCol-1]!=null)
					return false;
			}
		}
		
		//仕
		case "Advisor":{
			break;
		}
		
		//将
		case "King":{
			break;
		}
		
		}    //switch end
		return true;
	}
	
	
	/**
	 * 生成步并更新棋盘
	 * @param toRow
	 * @param toCol
	 */
	public void generateMove(int toRow,int toCol) {
		
	}
}














