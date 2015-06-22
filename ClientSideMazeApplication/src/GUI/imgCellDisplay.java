package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

/**
 * imgCellDisplay is a class to draw a tile using an image.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see Image
 */
public class imgCellDisplay {

	Image image;
	/**
	 * imgCellDisplay Constructor, sets the imgCellDisplay's Image.
	 * @param img - an Image.
	 * @see Image
	 */
	public imgCellDisplay(Image img) {
		this.image = img;
	}
	/**
	 * a method for drawing a tile using a PaintEvent and 4 ints to describe start and finish coordinates.
	 * @param e - a PaintEvent.
	 * @param start - start x coordinate(int).
	 * @param end - start y coordinate(int).
	 * @param w - the character's width(int).
	 * @param h - the character's height(int).
	 * @see PaintEvent
	 */
	public void paint(PaintEvent e,int start,int end,int w,int h){
		e.gc.drawImage(image, 0, 0,image.getImageData().width,image.getImageData().height,start,end,w,h);
	}
	/**
	 * a method for drawing a tile using a GC and 4 ints to describe start and finish coordinates.
	 * @param gc - a GC.
	 * @param start - start x coordinate(int).
	 * @param end - start y coordinate(int).
	 * @param w - the character's width(int).
	 * @param h - the character's height(int).
	 * @see GC
	 */
	public void paint(GC gc,int start,int end,int w,int h){ 
		gc.drawImage(image, 0, 0,image.getImageData().width,image.getImageData().height,start,end,w,h);
	}
	/**
	 * sets the imgCellDisplay's image.
	 * @param i - an Image
	 * @see Image
	 */
	public void setI(Image i) {
		this.image = i;
	}
	
}
