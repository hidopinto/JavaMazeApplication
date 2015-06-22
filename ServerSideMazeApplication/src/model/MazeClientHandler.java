package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Observable;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * a class that handles the client. it can recieve commands from the client and respond to them
 * MazeClientHandler extends Observable
 * MazeClientHandler implements ClientHandler
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see ClientHandler
 * @see Observable
 */
public class MazeClientHandler extends Observable implements ClientHandler{

	Model algorithmlauncher;
	
	/**
	 * general constructor
	 * @param algorithmlauncher -  a Model
	 * @see Model
	 */
	public MazeClientHandler(Model algorithmlauncher) {
		this.algorithmlauncher = algorithmlauncher;
	}
	
	/**
	 * a method that handles a single client, and fulfills it's requests
	 * @param in -  an InputStream
	 * @param out - an OutputStream
	 */
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		BufferedReader inFromClient=new BufferedReader(new InputStreamReader(in));
		PrintWriter outToClient=new PrintWriter(new OutputStreamWriter(out));
		String line;
		
		try {
			while(!((line=inFromClient.readLine()).equals("exit"))){
				
				this.setChanged();
				this.notifyObservers("DisplayMSG" + "," +line);
				
				String[] sp = line.split(" ");
				String command = sp[0];
				String arg = null;
				if (sp.length > 1)
					arg = sp[1];
				
				if(command!=null){
					switch (command) {
					case "GenerateMaze":
						if(arg == null)
							break;
						String[] parameters=arg.split(":");
						algorithmlauncher.generateMaze(Integer.parseInt(parameters[2]),Integer.parseInt(parameters[1]),parameters[0]);
						Maze m = algorithmlauncher.getMaze(parameters[0]);
						//TODO send zip file to client
						
						outToClient.println(m);
						outToClient.println("end");
						outToClient.flush();
						outToClient.println("exit");
						outToClient.flush();
						
						this.setChanged();
						this.notifyObservers("DisplayMSG" + "," +"maze " + parameters[0] + " is ready");
						
						break;
						
					case "SolveMaze":
						if(arg == null)
							break;
						algorithmlauncher.solveMaze(arg);
						Solution sol = algorithmlauncher.getSolution(arg);
						//TODO send zip file to client
						
						outToClient.println(sol);
						outToClient.println("end");
						outToClient.flush();
						outToClient.println("exit");
						outToClient.flush();
						
						this.setChanged();
						this.notifyObservers("DisplayMSG" + "," +"solution for maze " + arg + " is ready");
						
						break;
					}
				}
			}
							
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		outToClient.println("Good Bye");
		outToClient.flush();
	}

}
