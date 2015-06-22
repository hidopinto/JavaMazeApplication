package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * BasicWindow is a class that describes the basic of a Window.
 * This is an Abstract class with 2 abstract functions:
 * 		initWidgets - this method sets up all the widgets in the window.
 * 		closeMe - this method closes the window.
 * BasicWindow extends Observable
 * BasicWindow implements Runnable
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see Observable
 * @see Runnable
 */
public abstract class BasicWindow extends Observable implements Runnable{
	Display display;
	Shell shell;
	
	/**
	 * BasicWindow Constructor using parameters.
	 * @param title - a String that represents the title of the window. 
	 * @param width - an int that represents the window's width.
	 * @param height - an int that represents the window's height.
	 * @param display - a Display that the window shown above.
	 * @See Display
	 */
	public BasicWindow(String title, int width, int height, Display display) {
		this.display = display;
		shell = new Shell(display);
		shell.setText(title);
		shell.setSize(width, height);
	}
	
	/**
	 * returns the window' shell.
	 * @return shell - a Shell.
	 */
	public Shell getShell(){
		return shell;
	}
	
	/**
	 * returns the window' display.
	 * @return display - a Display.
	 */
	public Display getDisplay(){
		return display;
	}
	
	/**
	 * a function for the classes that extends BasicWindow.
	 * this function sets up all the Widgets in the window.
	 */
	abstract void initWidgets();
	
	/**
	 * a function for the classes that extends BasicWindow.
	 * this function closes the window.
	 */
	abstract public void closeMe();
	
	/**
	 * this is the run function of a window.
	 * this method start with setting up the widgets (initWidgets() ), and waits for events to happen.
	 * when shell is disposed, the function closes the window.
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
		
		closeMe(); 
	}
	

}

