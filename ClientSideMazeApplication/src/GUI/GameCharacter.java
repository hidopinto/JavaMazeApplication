package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

/**
 * GameCharacter is a class that describes a character in a game.
 * This character is the most Basic one  - a circle filled with color(default color is red).
 * More advanced GameCharacters are extending this class like {@link GameCharacter}.
 * This class defines the basics of any GameCharacter like:
 * Last_direction - used for ball direction,
 * start(x,y) and target(targetI,targetJ) coordinates.
 * ball - a {@link Ball}.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 */
public class GameCharacter {
	int x;
	int y;
	char Last_direction;
	Ball ball = null;
	int targetI,targetJ;
	/**
	 * GameCharacter Constructor. 
	 * This Constructor sets the characters's start and target coordinates.
	 * @param x
	 * @param y
	 * @param targetI
	 * @param targetJ
	 */	   
	public GameCharacter(int x,int y,int targetI,int targetJ) {
		this.x=x;this.y=y;
		this.targetI=targetI;this.targetJ=targetJ;
	}
	/**
	 * draws the character with PaintEvent, and width and height.
	 * @param e - PaintEvent
	 * @param w - the cahracter's width
	 * @param h - the cahracter's height
	 */
	public void paint(PaintEvent e,int w,int h){
		e.gc.setForeground(new Color(null,255,0,0));
		e.gc.setBackground(new Color(null,255,0,0));
		e.gc.fillOval(x,y, (int) (w*0.6), (int) (h*0.6));
	}
	/**
	 * draws the character with GC, and width and height.
	 * @param e - GC
	 * @param w - the cahracter's width
	 * @param h - the cahracter's height
	 */
	public void paint(GC gc,int w,int h){
		gc.setForeground(new Color(null,255,0,0));
		gc.setBackground(new Color(null,255,0,0));
		gc.fillOval(x,y, (int) (w*0.6), (int) (h*0.6));
	}
	/**
	 * this method is here for other classes using king of objects in order to draw the character.
	 * @param o - an Object
	 */
	public void setData(Object o){} // this function is here for the classes that extends it, so they could change the sprite, image, etc in the class. Putting this function here makes the programming much more flexable and generic.
	/**
	 * setting a Ball.
	 * @param b - a Ball
	 * @see Ball
	 */
	public void setBall(Ball b){
		this.ball = b;
	}
	/**
	 * setting targetI coordinate.
	 * @param targetI - an int
	 */
	public void setTargetI(int targetI) {
		this.targetI = targetI;
	}
	/**
	 * setting targetJ coordinate.
	 * @param targetJ - an int
	 */
	public void setTargetJ(int targetJ) {
		this.targetJ = targetJ;
	}
}
