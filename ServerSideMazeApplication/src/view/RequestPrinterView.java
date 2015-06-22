package view;

import java.util.Observable;

/**
 * a class that implements the view, for writing the commands given to the console
 * @author Yehuda Hido Pinto & Adir Ben Avi
 *
 */
public class RequestPrinterView extends Observable implements View {

	/**
	 * prints the command given
	 */
	@Override
	public void displayMSG(String s) {
		System.out.println(s);	
	}

}