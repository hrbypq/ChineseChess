package board;

import pieces.*;

public class Board implements Cloneable{
	/**
	 * ���췽�� ��ʼ������
	 */
	public Board() {
		initBoard();
	}
	// �������
	public static final int MAX_ROW = 10;
	// �������
	public static final int MAX_COL = 9;
	//���״̬  ��Ϊfalse����ֽ���
	private boolean state=true;
	// �洢����
	private ChessPiece[][] pieces;

	/**
	 * ��ʼ������
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
	 * ��������жϴ˲��Ƿ�Ϸ�
	 * @param piece
	 * @param toRow
	 * @param toCol
	 * @return
	 */
	public boolean isLegalMove(ChessPiece piece,int toRow,int toCol) {
		//����ظ����
		if(toRow==piece.getRow()&&toCol==piece.getCol())     
			return false;
		//���ܳ�������
		if(toRow<0||toCol<0||toRow>MAX_ROW-1||toCol>MAX_COL-1)
			return false;
		//������ӱ����жϴ˲��Ƿ�Ϸ�
		if(!piece.isLegalMove(toRow, toCol))
			return false;
		//���ܳ��Լ�����
		if(pieces[toRow][toCol]!=null&&pieces[toRow][toCol].getColor()==piece.getColor())
			return false;
		//��������жϴ˲��Ƿ�Ϸ�
		switch(piece.getName()) {
		
		//��
		case "RPawn":
		case "BPawn":{
			break;
		}
		
		//��
		case "RRook":
		case "BRook":{
			//������������  ����Խ������
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
			//������������   ����Խ������
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
		
		//��
		case "RKnight":
		case "BKnight":{
			//����������
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
		
		//��
		case "RCannon":
		case "BCannon":{
			//ֻ�г���ʱ��Խ������
			//�����ƶ�����
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
			//�����ƶ�����
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
		
		//��
		case "RBishop":
		case "BBishop":{
			//����λ����������
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
		
		//��
		case "RAdvisor":
		case "BAdvisor":{
			break;
		}
		
		//��
		case "RKing":
		case "BKing":{
			break;
		}
		
		}    //switch end
		return true;
	}
	
	
/**
 * ���ɲ�����������
 * @param Row
 * @param Col
 * @param toRow
 * @param toCol
 * @return �Ƿ��ƶ��ɹ�
 */
	public boolean generateMove(int Row,int Col,int toRow,int toCol) {
		if(toRow>MAX_ROW-1||toRow<0||toCol>MAX_COL-1||toCol<0)   //���λ���Ƿ�Ƿ�
			return false;
		ChessPiece prev=pieces[Row][Col];    //��Ҫ�ƶ�������
		if(prev==null)
			return false;
		if(!prev.isLegalMove(toRow, toCol))
			return false;
		if(!this.isLegalMove(prev, toRow, toCol))
			return false;
		//�ƶ�����
		if(pieces[toRow][toCol]==null) {
			prev.setRow(toRow);
			prev.setCol(toCol);
			pieces[toRow][toCol]=(ChessPiece)prev.clone();
			pieces[Row][Col]=null;
		}
		//�γɳ��ӵ����
		else {
			if(pieces[toRow][toCol].getName()=="RKing"||pieces[toRow][toCol].getName()=="BKing")   //��������
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
	 * ������״̬ ��Ϊfalse����ֽ���
	 * @return
	 */
	public boolean getState() {
		return state;
	}
	
	
	/**
	 * �������
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














