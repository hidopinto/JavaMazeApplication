package boot;


import org.eclipse.swt.widgets.Display;

import GUI.StartWindow;

public class Run {

	public static void main(String[] args) {
		
		/*
		 * commiters:
		 * yehuda hido pinto id-318865805
		 * adir ben avi id-318509627
		 * 
		*/
		
		StartWindow sw = new StartWindow("Welcome", 600, 600, new Display());
		sw.run();
		
	}

}
