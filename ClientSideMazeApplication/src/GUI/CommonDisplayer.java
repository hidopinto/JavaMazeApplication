package GUI;
/**
 * CommonDisplayer is a class that defines the all the basics and common things to class which implements Displayer.
 * CommonDisplayerCommonGameBoard has a CommonGameBoard as a DataMmber for displaying on it.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see Displayer
 */
public abstract class CommonDisplayer implements Displayer {

	CommonGameBoard board;
	
	/**
	 * set the board DataMember.
	 * @param board - a CommonGameBoard
	 */
	public void setBoard(CommonGameBoard board) {
		this.board = board;
	}
	
	public abstract void setData(Object arg) ;
	public abstract Object getData() ;
	
		
}
