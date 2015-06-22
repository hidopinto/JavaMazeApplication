package GUI;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
/**
 * Ball class is a GameCharacter's shoot.
 * A character can shoot a ball, and this class will handle the drawing things like setting image,and coordinates of the ball.
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see GameCharacter
 */

public class Ball {
	
	char dir;
	int x;
	int y;
	Timer timer;
	TimerTask myTask;
	Composite widget;
	Image image;
	/**
	 * Ball Constructor gets direction to shoot too, coordinates for start, the father widget and a TimerTask injected from outside.
	 * @param dir - the direction the ball will move too.
	 * @param X - the X start point of the ball.
	 * @param Y - the Y start point of the ball.
	 * @param widget - the widget the ball is moving on.
	 * @param task - this TimerTask moves the ball one step a time. it is scheduled via a Timer.
	 */
	public Ball(char dir,int X,int Y,Composite widget,TimerTask task) {
		
		this.x=X;
		this.y=Y;
		this.widget=widget;
		this.myTask = task;
		this.dir=dir;
		
		timer = new Timer();
		timer.scheduleAtFixedRate(myTask, 0, 20);
	}
	/**
	 * This method stop the ball from moving.
	 * It's enough to cancel the TimerTask and the Timer to stop the ball.
	 */
	public void stop(){
		myTask.cancel();
		timer.cancel();
	}
	/**
	 * draws the ball.
	 * @param gc - the GC used to draw the ball.
	 * @param w - the ball's width.
	 * @param h - the ball's height.
	 */
	public void paint(GC  gc,int w,int h){
		if(image ==null)
			image = new Image(gc.getDevice(), "animations/ball.png");
		widget.redraw();
		gc.drawImage(image, 0, 0,image.getImageData().width,image.getImageData().height,x,y, (int) (w*0.4), (int) (h*0.4));
	}
	
}
