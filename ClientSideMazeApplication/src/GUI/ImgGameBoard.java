/**
 * 
 */
package GUI;


import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
/**
 * ImgGameBoard is a class for creating a game board, which it's cells are images.
 * This board looks really nice, and it's highly recommended.
 * ImgGameBoard extends CommonGameBoard.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see CommonGameBoard
 */

public class ImgGameBoard extends CommonGameBoard {
	/**
	 * ImgGameBoard Constructor.
	 * In this Constructor a PaintListener is added to ImgGameBoard in order to draw the board.
	 * @param parent - the parent  widget, which the maze is drown on.
	 * @param style - SWT style.
	 * @param md - a Displayer.
	 */
	public ImgGameBoard(Composite parent,int style, CommonDisplayer md){
        super(parent, style | SWT.DOUBLE_BUFFERED);
        this.md = md;
        
        //gameCharecters.add(new ImgGameCharacter(0,0,((md.getRows()-1)) , ((md.getCols()-1)),null));
        
        h = 1;
        w = 1;
        
        addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				
				if(md==null || md.getData()==null)
					  return;
				   int width=getSize().x;
				   int height=getSize().y;
				   
				   int Last_w = w;
				   int Last_h = h;
				   
				   w=width/md.getCols();
				   h=height/md.getRows();
				   
				   Iterator<GameCharacter> ite = gameCharecters.iterator();
				   
				   while(ite.hasNext()){
					   GameCharacter gc = ite.next();
					   int x = (int) (gc.x / Last_w);
					   int y = (int) (gc.y / Last_h);
					   
					   x *= w;
					   y *= h;
					   
					   gc.x = x + (int)(w*0.2);
					   gc.y = y + (int)(h*0.2);
					   
					   //no need to check on the ball because the w&h settings are only for reSizeing events, which are not likely to happen when a player shoots.
					   
				   }//sets the characters to be in the center of the cell, even if the cell's sizes have been changed.
				   
				   
				   
				   md.draw(e);//draws the maze
				   
				   ite=gameCharecters.iterator();
				   
				   while(ite.hasNext()){
					   GameCharacter gc = ite.next();
					   gc.paint(e, w, h);
				   }//draws all of the characters
			}
		});
	}
	/**
	 * a method that redraws the board.
	 */
	public void generateBoard(){
		redraw();
	}
	
	public void stop(){
		//TODO
	}
	

}
