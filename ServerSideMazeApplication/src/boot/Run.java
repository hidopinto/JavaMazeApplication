package boot;

import org.eclipse.swt.widgets.Display;

import view.PropertiesWindow;



public class Run {

	public static void main(String[] args) {
		
		/*
		 * commiters:
		 * yehuda hido pinto id-318865805
		 * adir ben avi id-318509627
		 * 
		*/
	
		PropertiesWindow PW = new PropertiesWindow("set properties", 400, 400, new Display());
		PW.run();
		
	}

}
