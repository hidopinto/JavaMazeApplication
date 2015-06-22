package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * MazeClient is the class that talk with the sever in the name of the user.
 * MazeClient is the Model class in this architecture.
 * MazeClient extends Observable
 * MazeClient implements Model
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see Observable
 * @see Model
 */
public class MazeClient extends Observable implements Model {

	Maze last_maze = null;
	Solution last_sol = null;
	
	/**
	 * this method asks the server to generate a new maze.
	 * @param i - the rows
	 * @param j - the cols
	 * @param name - a String - the command
	 */
	@Override
	public void generateMaze(int i, int j, String name) {
		System.out.println("CLIENT SIDE");
		try{
			Socket myServer = new Socket("127.0.0.1", 7000); //127.0.0.1 needed to be changed to the server ip. works here because they are on the same computer.
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
			PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
			String line = "GenerateMaze "+name+":"+j+":"+i;
			
			while(!((line).equals("exit"))){
				outToServer.println(line);
				outToServer.flush();
	
				String serverMSG = "";
				String maze_line=null;
				maze_line = inFromServer.readLine();
				while(maze_line!=null && !((maze_line).equals("end"))){
					serverMSG += maze_line + System.lineSeparator();
					maze_line = inFromServer.readLine();
				}//reads all the maze
				
				last_maze = new Maze(serverMSG);
				line = inFromServer.readLine();
			}
			outToServer.println("exit");
			outToServer.flush();
						
			inFromServer.close();
			outToServer.close();
			myServer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.setChanged();
		this.notifyObservers("maze");
	}

	/**
	 * this method returns the last maze.
	 * @return last_maze - a Maze.
	 */
	@Override
	public Maze getMaze() {
		return last_maze;
	}

	/**
	 * this method asks the server to solve a Maze.
	 * @param name - a String - the command
	 */
	@Override
	public void solveMaze(String name) {
		System.out.println("CLIENT SIDE");
		try{
			Socket myServer = new Socket("127.0.0.1", 7000); //127.0.0.1 needed to be changed to the server ip. works here because they are on the same computer.
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
			PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
			String line = "SolveMaze " + name;
			
			while(!((line).equals("exit"))){
				outToServer.println(line);
				outToServer.flush();
				
				String serverMSG = "";
				String sol_line=null;
				sol_line = inFromServer.readLine();
				while(sol_line!=null && !sol_line.equals("end")){
					serverMSG += sol_line + System.lineSeparator();
					sol_line = inFromServer.readLine();
				}//reads all the solution
				
				last_sol = new Solution(serverMSG);
				line = inFromServer.readLine();
			}
			outToServer.println("exit");
			outToServer.flush();
						
			inFromServer.close();
			outToServer.close();
			myServer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.setChanged();
		this.notifyObservers("solution");
	}

	/**
	 * this method returns the last Solution.
	 * @return last_sol - a Solution.
	 */
	@Override
	public Solution getSolution() {
		return last_sol;
	}

	/**
	 * this method stop the MazeClient
	 */
	@Override
	public void stop() {
		
	}

}
