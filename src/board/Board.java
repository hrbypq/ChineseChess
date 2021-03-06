package board;

import pieces.*;

public class Board implements Cloneable{
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
	//棋局状态  若为false则棋局结束
	private boolean state=true;
	// 存储棋子
	private ChessPiece[][] pieces;

	/**
	 * 初始化棋盘
	 */
	public void initBoard() {
		pieces = new ChessPiece[MAX_ROW][MAX_COL];
		pieces[0][0]=new Rook(0,0,false,"BRook");
		pieces[0][8]=new Rook(0,8,false,"BRook");
		pieces[0][1]=new Knight(0,1,false,"BKnight");
		pieces[0][7]=new Knight(0,7,false,"BKnight");
		pieces[0][2]=new Bishop(0,2,false,"BBishop");
		pieces[0][6]=new Bishop(0,6,false,"BBishop");
		pieces[0][3]=new Advisor(0,3,false,"BAdvisor");
		pieces[0][5]=new Advisor(0,5,false,"BAdvisor");
		pieces[0][4]=new King(0,4,false,"BKing");
		pieces[2][1]=new Cannon(2,1,false,"BCannon");
		pieces[2][7]=new Cannon(2,7,false,"BCannon");
		
		pieces[9][0]=new Rook(9,0,true,"RRook");
		pieces[9][8]=new Rook(9,8,true,"RRook");
		pieces[9][1]=new Knight(9,1,true,"RKnight");
		pieces[9][7]=new Knight(9,7,true,"RKnight");
		pieces[9][2]=new Bishop(9,2,true,"RBishop");
		pieces[9][6]=new Bishop(9,6,true,"RBishop");
		pieces[9][3]=new Advisor(9,3,true,"RAdvisor");
		pieces[9][5]=new Advisor(9,5,true,"RAdvisor");
		pieces[9][4]=new King(9,4,true,"RKing");
		pieces[7][1]=new Cannon(7,1,true,"RCannon");
		pieces[7][7]=new Cannon(7,7,true,"RCannon");
		
		for(int i=0;i<=8;i+=2) {
			pieces[3][i]=new Pawn(3,i,false,"BPawn");
			pieces[6][i]=new Pawn(6,i,true,"RPawn");
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
		if(toRow<0||toCol<0||toRow>MAX_ROW-1||toCol>MAX_COL-1)
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
		case "RPawn":
		case "BPawn":{
			break;
		}
		
		//车
		case "RRook":
		case "BRook":{
			//横向行走情形  不能越过棋子
			if(toRow==piece.getRow()) {
				if(toCol>piece.getCol()) {
					for(int i=piece.getCol()+1;i<toCol;i++) {
						if(pieces[toRow][i]!=null) 
							return false;
					}
				}
				else {
					for(int i=toCol+1;i<piece.getCol();i++) {
						if(pieces[toRow][i]!=null) 
							return false;
					}
				}
			}
			//纵向行走情形   不能越过棋子
			if(toCol==piece.getCol()) {
				if(toRow>piece.getRow()) {
					for(int i=piece.getRow()+1;i<toRow;i++) {
						if(pieces[i][toCol]!=null)
							return false;
					}
				}
				else {
					for(int i=toRow+1;i<piece.getRow();i++) {
						if(pieces[i][toCol]!=null)
							return false;
					}
				}
			}
		}
		
		//马
		case "RKnight":
		case "BKnight":{
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
		case "RCannon":
		case "BCannon":{
			//只有吃子时能越过棋子
			//横向移动情形
			if(toRow==piece.getRow()) {
				if(toCol>piece.getCol()) {
					for(int i=piece.getCol()+1;i<toCol;i++) {
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol]==null)
							return false;
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
							return false;
					}
				}
				if(toCol<piece.getCol()) {
					for(int i=toCol+1;i<piece.getCol();i++) {
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol]==null)
							return false;
						if(pieces[toRow][i]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
							return false;
					}
				}
			}
			//纵向移动情形
			if(toCol==piece.getCol()) {
				if(toRow>piece.getRow()) {
					for(int i=piece.getRow()+1;i<toRow;i++) {
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol]==null)
							return false;
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
							return false;
					}
				}
				if(toRow<piece.getRow()) {
					for(int i=toRow+1;i<piece.getRow();i++) {
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol]==null)
							return false;
						if(pieces[i][toCol]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
							return false;
					}
				}
			}
		}
		
		//象
		case "RBishop":
		case "BBishop":{
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
		case "RAdvisor":
		case "BAdvisor":{
			break;
		}
		
		//将
		case "RKing":
		case "BKing":{
			break;
		}
		
		}    //switch end
		return true;
	}
	
	
/**
 * 生成步并更新棋盘
 * @param Row
 * @param Col
 * @param toRow
 * @param toCol
 * @return 是否移动成功
 */
	public boolean generateMove(int Row,int Col,int toRow,int toCol) {
		if(toRow>MAX_ROW-1||toRow<0||toCol>MAX_COL-1||toCol<0)   //检查位置是否非法
			return false;
		ChessPiece prev=pieces[Row][Col];    //将要移动的棋子
		if(prev==null)
			return false;
		if(!prev.isLegalMove(toRow, toCol))
			return false;
		if(!this.isLegalMove(prev, toRow, toCol))
			return false;
		//移动棋子
		if(pieces[toRow][toCol]==null) {
			prev.setRow(toRow);
			prev.setCol(toCol);
			pieces[toRow][toCol]=(ChessPiece)prev.clone();
			pieces[Row][Col]=null;
		}
		//形成吃子的情况
		else {
			if(pieces[toRow][toCol].getName()=="RKing"||pieces[toRow][toCol].getName()=="BKing")   //主将被吃
				this.state=false;
			pieces[toRow][toCol]=null;
			prev.setRow(toRow);
			prev.setCol(toCol);
			pieces[toRow][toCol]=(ChessPiece)prev.clone();
			pieces[Row][Col]=null;
		}
		return true;
	}
	
	/**
	 * 检查棋局状态 若为false则棋局结束
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	
	/**
	 * 获得棋盘
	 * @return
	 */
	public ChessPiece[][] getPieces(){
		return pieces;
	}
	
	
	public Object clone() {
		Board obj=null;
		try {
			obj=(Board)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
}














