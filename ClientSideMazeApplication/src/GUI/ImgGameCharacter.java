package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
/**
 * GameCharacter is a class for describing a character on a game board using an Image.
 * GameCharacter is highly recommended because of the 
 * ImgGameCharacter extends GameCharacter
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see GameCharacter
 */
public class ImgGameCharacter extends GameCharacter{

	Image image; 
	/**
	 * GameCharacter Constructor. 
	 * This Constructor sets the characters's start and target coordinates.
	 * This Constructor also sets the characters's image.
	 * @param x
	 * @param y
	 * @param targetI
	 * @param targetJ
	 * @param image
	 */
	public ImgGameCharacter(int y,int x,int targetI,int targetJ, Image image) {
		super(x,y,targetI,targetJ);
		this.image = image;
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
		e.gc.drawOval(x, y, (int) (w*0.6), (int) (h*0.6));
		if(image==null)
			image = new Image(e.gc.getDevice(), "characters/eli.png");
		e.gc.drawImage(image, 0, 0,image.getImageData().width,image.getImageData().height,x,y, (int) (w*0.6), (int) (h*0.6));
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
		gc.drawOval(x, y, (int) (w*0.6), (int) (h*0.6));
		if(image==null)
			image = new Image(gc.getDevice(), "characters/eli.png");
		gc.drawImage(image, 0, 0,image.getImageData().width,image.getImageData().height,x,y, (int) (w*0.6), (int) (h*0.6));
	}
	/**
	 * allows to set ImgGameCharacter's Image
	 * @param image - an Image.
	 * @see Image
	 */
	@Override
	public void setData(Object image){
		if((Image) image ==null)
			return;
		this.image = (Image) image;
	}
	
}
