package view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import model.MazeClientHandler;
import model.MyModel;
import model.MyTCPIPServer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter;
import presenter.Properties;
import algorithms.mazeGenerators.MazeGenerator;
import algorithms.mazeGenerators.MazeGeneratorDFS;
import algorithms.mazeGenerators.RandomMazeGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;

public class PropertiesWindow extends BasicWindow{

	CommonSearcher searchAlg;
	MazeGenerator mazeGeneratorAlg;
	int port = 7000;
	
	public PropertiesWindow(String title, int width, int height, Display display) {
		super(title, width, height,display);
		// TODO Auto-generated constructor stub
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1, false));
		Label l1=new Label(shell, SWT.NONE);
		l1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1) );
		l1.setText("Generating Algorithm");
				
		Combo generatingAlgo=new Combo(shell, 0);
		generatingAlgo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		generatingAlgo.add("Random");
		generatingAlgo.add("DFS");
		generatingAlgo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				switch (generatingAlgo.getText()) {
				case "Random":
					mazeGeneratorAlg = new RandomMazeGenerator();
					break;

				case "DFS":
					mazeGeneratorAlg = new MazeGeneratorDFS();
					break;
				}
			}
		});
		
		Label l2=new Label(shell, SWT.NONE);
		l2.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1) );
		l2.setText("Solving Algorithm");
		
		Combo solvingAlgo=new Combo(shell, 0);
		solvingAlgo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		solvingAlgo.add("BFS");
		solvingAlgo.add("Astar- Manhattan Distance");
		solvingAlgo.add("Astar- Air Distance");
		solvingAlgo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				switch (solvingAlgo.getText()) {
				case "BFS":
					searchAlg = new BFS();
					break;

				case "Astar- Manhattan Distance":
					searchAlg = new Astar(new MazeManhattanDistance());
					break;
					
				case "Astar- Air Distance":
					searchAlg = new Astar(new MazeAirDistance());
					break;
				}
			}
		});
		
		Label l3=new Label(shell, SWT.NONE);
		l3.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1) );
		l3.setText("Port");
		
		Text port_txt = new Text(shell, SWT.BORDER);
		port_txt.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		port_txt.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if(!isInteger(port_txt.getText()))
					return;
				port = Integer.parseInt(port_txt.getText());
			}
		});
		
		Button startMaze = new Button(shell, SWT.PUSH);
		startMaze.setText("Set Properties");
		startMaze.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		startMaze.addSelectionListener(new SelectionListener() {
					
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
					
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
						
			}
		});
				
		
	}

	@Override
	public void closeMe() {
		
		if(searchAlg==null || mazeGeneratorAlg==null)
			return;
		
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
			
			Properties properties = new Properties(3,searchAlg,mazeGeneratorAlg,port);
			encoder.writeObject(properties);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		XMLDecoder decoder;
		Properties pro = new Properties();
		try {
			decoder = new XMLDecoder(new FileInputStream("properties.xml"));
			pro = (Properties) decoder.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!shell.isDisposed())
			shell.dispose();
		
		MyModel m=new MyModel(pro);
		MazeClientHandler handler=new MazeClientHandler(m);
		MyTCPIPServer serv=new MyTCPIPServer(pro, handler);
		final MainGUI v = new MainGUI("Main Window", 275, 100 ,display);
//		RequestPrinterView v = new RequestPrinterView();
		Presenter p=new Presenter(m,v,serv);
		handler.addObserver(p);
		m.addObserver(p);
		v.addObserver(p);
			
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				serv.start();
			}
		}).start();
		
		v.getDisplay().syncExec(new Runnable() {
			
			@Override
			public void run() {
				v.run();
			}
		});
		
	}
	
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

}
