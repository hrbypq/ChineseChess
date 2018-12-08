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
		if(toRow<0||toCol<0||toRow>MAX_ROW||toCol>MAX_COL)
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
		case "Pawn":{
			break;
		}
		
		//��
		case "Rook":{
			//������������  ����Խ������
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
			//������������   ����Խ������
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
		
		//��
		case "Knight":{
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
		case "Cannon":{
			//ֻ�г���ʱ��Խ������
			//�����ƶ�����
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
			//�����ƶ�����
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
		
		//��
		case "Bishop":{
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
		case "Advisor":{
			break;
		}
		
		//��
		case "King":{
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
		if(toRow>MAX_ROW||toRow<0||toCol>MAX_COL||toCol<0)   //���λ���Ƿ�Ƿ�
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














