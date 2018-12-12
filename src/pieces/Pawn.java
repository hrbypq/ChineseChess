package pieces;

/**
 * 兵
 * @author ypq
 * @version1.0
 */
public class Pawn extends ChessPiece{
	public Pawn(int init_row,int init_col,boolean init_isRed,String aname){
		super(init_row,init_col,init_isRed,aname);
	}

	@Override
	public boolean isLegalMove(int toRow, int toCol){
		 //红棋情形
		if(this.isRed){
			if(toRow-this.row>0||toRow-this.row<-1)
				return false;
			//红兵 未过河
			if(this.row>=5){
				if(this.col!=toCol)
					return false;
			}
			else{
				if(toCol-this.col>1||toCol-this.col<-1)
					return false;
			}
		}
		//黑棋情形
		else{
			if(toRow-this.row<0||toRow-this.row>1)
				return false;
			//黑兵 未过河
			if(this.row<=4){
				if(this.col!=toCol)
					return false;
			}
			else{
				if(toCol-this.col>1||toCol-this.col<-1)
					return false;
			}
		}
		return true;
	}
}
