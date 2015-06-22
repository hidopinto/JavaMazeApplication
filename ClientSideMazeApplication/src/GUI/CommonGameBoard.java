package GUI;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * CommonGameBoard is a class that defines the all the basics and common things to class which implements GameBoard.
 * CommonGameBoard has a Displayer as a DataMmber for displaying the board
 * , two integers: w and h which describes a cell's size in the maze.
 * , LinkedList of GameCharacters which contains all of the GameCharacters on the board.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see Composite
 * @see GameBoard
 * @see GameCharacter
 */
public abstract class CommonGameBoard extends Composite implements GameBoard {

	Displayer md;
	int h;
	int w;
	LinkedList<GameCharacter> gameCharecters;
	
	/**
	 * CommonGameBoard Constructor.
	 * @param parent - the parent  widget, which the maze is drown on.
	 * @param style - SWT style.
	 */
	public CommonGameBoard(Composite parent, int style) {
		super(parent, style | SWT.DOUBLE_BUFFERED);
		gameCharecters = new LinkedList<GameCharacter>();
	}
	/**
	 * sets md DataMember.
	 * @param md - a Displayer.
	 * @see Displayer
	 */
	public void setMD(Displayer md){
		this.md = md;
	}
	
	public void setGameCharecter(GameCharacter gameCharecter) {
		//this.gameCharecter = gameCharecter;
	}
	/**
	 * adds a GameCharacter to the LinkedList - gameCharecters.
	 * @param gameCharecter - a GameCharacter
	 */
	public void addGameCharecter(GameCharacter gameCharecter) {
		this.gameCharecters.add(gameCharecter);
	}
	/**
	 * removes a GameCharacter from the LinkedList - gameCharecters.
	 * @param gameCharecter - a GameCharacter
	 */
	public void removeGameCharecter(GameCharacter gameCharecter) {
		this.gameCharecters.remove(gameCharecter);
	}
	
	public void SetMD(Displayer MD){
		this.md = MD;
	}
}
