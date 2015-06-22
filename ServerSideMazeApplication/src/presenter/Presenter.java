package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;





import model.Model;
import model.MyTCPIPServer;
import view.View;

/**
 * a presenter class, from the mvp framework. connects between the model and the view.
 * Presenter implements Observer.
 * @author Adir
 * @see Observer
 */
public class Presenter implements Observer{
	
	Model m;
	View v;
	MyTCPIPServer serv;
	HashMap<String, presenter.Presenter.Command> commands=new HashMap<String, presenter.Presenter.Command>();
	
	/**
	 * the update function implemented from observer, starts when observed classes are notifying.
	 * @param arg0 - an Observable(View/Model).
	 * @param arg1 - a command.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0==m)
			return;
		if( ((String)arg1).equals("close")){
			Command c = (Command) commands.get("Close");
			c.doCommand("");
			return;
		}
		String[] parameters=((String) arg1).split(",");
		Command c=(Command) commands.get(parameters[0]);
		if(parameters.length >1)
			c.doCommand(parameters[1]);
	}

	/**
	 * a general constructor, Initializing the commands hashmap, and putting the data from DataBase to mazes HashMap.
	 * @param m - a Model.
	 * @param v - a View.
	 */
	public Presenter(Model m, View v, MyTCPIPServer serv) {
		super();
		this.m = m;
		this.v = v;
		this.serv = serv;
		commands.put("DisplayMSG", new DisplayMSGCommand());
		commands.put("Close", new CloseCommand());
	}
	
	public interface Command{
		public void doCommand(String s);
	}
	/**
	 * a command type class, for the generate maze method
	 * @author Adir
	 * @see Command
	 */
	class DisplayMSGCommand implements Command
	{
				@Override
		public void doCommand(String s) {
			v.displayMSG(s);
		}		
	}
	
	class CloseCommand implements Command{

		@Override
		public void doCommand(String s) {
			m.stop();
			serv.stop();
		}
		
	}

	public void setM(Model m) {
		this.m = m;
	}

	public void setV(View v) {
		this.v = v;
	}

	public Model getM() {
		return m;
	}

	public View getV() {
		return v;
	}

}
