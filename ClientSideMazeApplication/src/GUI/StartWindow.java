package GUI;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MazeClient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import presenter.Presenter;
import view.MyView;
import view.RunnabelCLI;
/**
 * StartWindow is the Window presented to a user in the start of the program.
 * StartWindow extends BasicWindow
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see BasicWindow
 */
public class StartWindow extends BasicWindow{
	
	String view = "";
	
	/**
	 * StartWindow Constructor using fields:
	 * @param title - a String
	 * @param width - an int
	 * @param height - an int
	 */
	public StartWindow(String title, int width, int height, Display display) {
		super(title, width, height, display);
	}
	/**
	 * sets all of the widgets and Listeners of the Window.
	 */
	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(2,false));
		Image i=new Image(display, new ImageData("backgrounds/maxresdefault.jpg"));
		shell.setBackgroundImage(i);
		Button open_cli = new Button(shell, SWT.PUSH);
		open_cli.setText("CLI");
		open_cli.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				view = "cli";
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		open_cli.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1, 1));
		
		Button open_gui = new Button(shell, SWT.PUSH);
		open_gui.setText("GUI");
		open_gui.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				view = "gui";
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		open_gui.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 1, 1));
		
	}

	@Override
	void closeMe() {
		if(!shell.isDisposed())
			shell.dispose();
		
		if(view.equals("cli")){
			MazeClient m=new MazeClient();
			MyView v=new MyView();
			Presenter p=new Presenter(m,v);
			m.addObserver(p);
			v.addObserver(p);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(System.out);
			RunnabelCLI cli=new RunnabelCLI(reader, writer,v);
			cli.start();
		}
		if(view.equals("gui")){
			CommonDisplayer md = new ImgMazeDisplayer(null, null);
			MainWindow mw = new MainWindow("maze", 600, 600,null,null,display);
			mw.setDisplayer(md);
			MazeClient m=new MazeClient();
			Presenter p=new Presenter(m,mw);
			m.addObserver(p);
			mw.addObserver(p);
			CommonGameBoard board=new ImgGameBoard(mw.getShell(), SWT.BORDER, md);
			md.setBoard(board);
			
			mw.setGameBoard(board);
			mw.run();
			
		}
	}

}
