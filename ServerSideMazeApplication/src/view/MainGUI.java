package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

/**
 * MainGUI is an important window.
 * It's purpose is to save all of the requests from the server, and close it if the user wants to.
 * MainGUI extends BasicWindow
 * MainGUI implements View
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see BasicWindow
 * @see View
 */
public class MainGUI extends BasicWindow implements View {

	ServerGUI gui = null;
	String messages = null;

	/**
	 * MainGUI Constructor using parameters.
	 * This Constructor also sets the messages String to be an empty String.
	 * @param title - a String that represents the title of the window. 
	 * @param width - an int that represents the window's width.
	 * @param height - an int that represents the window's height.
	 * @param display - a Display that the window shown above.
	 * @See Display
	 */
	public MainGUI(String title, int width, int height, Display display) {
		super(title, width, height, display);
		messages = "";
	}

	/**
	 * This function defines all of the Widgets we want in the window.
	 */
	void initWidgets() {
		MainGUI Me = this;
		shell.setLayout(new GridLayout(2, true));
		
		Button openGUI = new Button(shell, SWT.PUSH);
		openGUI.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		openGUI.setText("open Server GUI");
		
		openGUI.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				display.syncExec(new Runnable() {
					
					@Override
					public void run() {
						if(gui==null){
							gui = new ServerGUI("Server Gui", 600, 600, getDisplay(), Me);
							
							
							gui.display.syncExec(new Runnable() {
								
								@Override
								public void run() {
//									if(gui!=null && gui.requestsFromUser!=null && !gui.requestsFromUser.isDisposed())
									gui.run();
									
								}
							});
							
						}
					
					}
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		Button stopServer = new Button(shell, SWT.PUSH);
		stopServer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		stopServer.setText("close server");
		
		stopServer.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
	}
	
	/**
	 * This function is for displaying a message from server.
	 * When a message is received, it first stores all the massages in messages(a String).
	 * Then if the ServerGUI is open it sends it the message to display it.
	 * @param s - a String that describes the message.
	 */
	@Override
	public void displayMSG(String s) {
		
			if(!messages.toString().equals(""))
				messages = s + System.lineSeparator() + messages;
			else
				messages += s;
			
		if(gui!=null)
			gui.display.syncExec(new Runnable() {
				
				@Override
				public void run() {
					gui.displayMSG(messages);
				}
			});
	}
	
	/**
	 * This function closes the window down.
	 * Before closing the function closes the server.
	 */
	@Override
	public void closeMe() {
		setChanged();
		notifyObservers("close");
		if(!shell.isDisposed())
			shell.dispose();
	}

}
