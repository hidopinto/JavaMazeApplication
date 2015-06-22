package GUI;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * The most Basic Window possible. 
 * It handles all the events in the Window.
 * It gives the programmer the ability to define the Window's Widget's.
 * has 2 fields:
 * 		shell - a Shell
 * 		display - a Display
 * BasicWindow extends Observable
 * BasicWindow implements Runnable
 * @author Pinto
 * @see Observable
 * @see Runnable
 */
public abstract class BasicWindow  extends Observable implements Runnable{
	Display display;
	Shell shell;
	/**
	 * BasicWindow Constructor receiving the window's name as String and it's width and height as integers.
	 * @param title
	 * @param width
	 * @param height
	 */
	public BasicWindow(String title, int width, int height, Display display) {
		this.display = display;
		shell = new Shell(display);
		shell.setText(title);
		shell.setSize(width, height);
	}
	/**
	 * returns the shell.
	 * @return shell - the Shell
	 */
	public Shell getShell(){
		return shell;
	}
	/**
	 * returns the display.
	 * @return display - the Display
	 */
	public Display getDisplay(){
		return display;
	}

	/**
	 * abstract method which creates the widgets in the window.
	 */
	abstract void initWidgets();
	abstract void closeMe();
	/**
	 * handles all the events in the Window.
	 */
	@Override
	public void run(){
		initWidgets();
		shell.open();
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed
		 
//		display.dispose(); // dispose OS components
		 
		 closeMe();

	}
	

}

