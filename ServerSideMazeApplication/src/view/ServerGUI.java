package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * ServerGUI is a window that displays messages from server to user in a nice and comfortable way.
 * Note that closing ServerGUI won't close the server,  for the convenient of the user. 
 * ServerGUI extends BasicWindow
 * ServerGUI implements View
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see BasicWindow
 * @see View
 */
public class ServerGUI extends BasicWindow implements View{

	Text requestsFromUser;
	Text statusBar;
	MainGUI father;
	
	/**
	 * ServerGUI Constructor using parameters.
	 * This Constructor also sets father to the MainGUI created it.
	 * @param title - a String that represents the title of the window. 
	 * @param width - an int that represents the window's width.
	 * @param height - an int that represents the window's height.
	 * @param display - a Display that the window shown above.
	 * @See Display
	 */
	public ServerGUI(String title, int width, int height, Display display, MainGUI father) {
		super(title, width, height, display);
		this.father = father;
	}

	/**
	 * This function defines all of the Widgets we want in the window.
	 */
	void initWidgets() {
		shell.setLayout(new GridLayout(1, false));
		
		Label lsat_request_title = new Label(shell, SWT.NONE);
		lsat_request_title.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lsat_request_title.setForeground(new Color(display, 0, 255, 0));
		lsat_request_title.setText("Last Requests");
		lsat_request_title.setFont(new Font(display, "David", (int)(shell.getSize().y/20), SWT.BOLD));
		
		statusBar = new Text(shell, SWT.BORDER);
		statusBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		statusBar.setBackground(new Color(display, 255, 255, 255));
		statusBar.setForeground(new Color(display, 0, 255, 0));
		statusBar.setEnabled(false);

		Label requests_title = new Label(shell, SWT.NONE);
		requests_title.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		requests_title.setForeground(new Color(display, 0, 255, 0));
		requests_title.setText("Server Requests");
		requests_title.setFont(new Font(display, "David", (int)(shell.getSize().y/20), SWT.BOLD));
		
		requestsFromUser = new Text(shell, SWT.BORDER | SWT.V_SCROLL);
		requestsFromUser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		requestsFromUser.setBackground(new Color(display, 0, 0, 0));
		requestsFromUser.setForeground(new Color(display, 0, 255, 0));
		requestsFromUser.setEnabled(false);
		
		Button closeGUI = new Button(shell, SWT.PUSH);
		closeGUI.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1, 1));
		closeGUI.setText("close GUI");
		
		closeGUI.addSelectionListener(new SelectionListener() {
			
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
	 * When a message is received, it resets the text to display it, and sets the statusBar to display the first line.
	 * @param s - a String that describes the message.
	 */
	@Override
	public void displayMSG(String s) {
		requestsFromUser.setEnabled(true);
		
		requestsFromUser.setText(s);
		
		requestsFromUser.setEnabled(false);
		
		statusBar.setEnabled(true);
		statusBar.setText(requestsFromUser.getText().split(System.lineSeparator())[0]); //setting the text in the status bar to be the last operation.
		statusBar.setEnabled(false);
	}
	
	/**
	 * This function closes the window down.
	 * Before closing the function closes the server.
	 */
	public void closeMe(){
		getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				//TODO send DATA to main GUI Window
				if(!shell.isDisposed())
					shell.close();
				father.gui = null;
			}
		});
		
	}

}
