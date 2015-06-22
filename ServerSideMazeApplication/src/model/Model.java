package model;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * a basic model interface, in the mvp framework
 * @author Yehuda Hido Pinto & Adir Ben Avi
 *
 */
public interface Model {
	void generateMaze(int i,int j,String name);
	Maze getMaze(String name);
	void solveMaze(String name);
	Solution getSolution(String name);
	void stop();
}