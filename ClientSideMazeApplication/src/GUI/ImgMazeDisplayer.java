package GUI;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import algorithms.mazeGenerators.Cell;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;
/**
 * CommonDisplayer is a class that draws a Maze using Images.
 * CommonDisplayerCommonGameBoard has a:
 * 		Image[][] as a DataMmber for displaying the pictures,
 * 		imgCellDisplay[][] as a reference to the tiles,
 * 		Maze as the original maze that need to be displayed.
 * ImgMazeDisplayer extends CommonDisplayer
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * @see CommonDisplayer
 */
public class ImgMazeDisplayer extends CommonDisplayer {
	
	Image[][] images = null;
	imgCellDisplay[][] maze =null;
	Maze mazeData;
	/**
	 * ImgMazeDisplayer Constructor sets the mazeData and board DataMembers.
	 * @param mazeData
	 * @param board
	 */
	public ImgMazeDisplayer(Maze mazeData,  CommonGameBoard board) {
		super();
		this.mazeData = mazeData;
		this.board = board;
	}
	/**
	 * a method to draw the Maze using a PaintEvent.
	 * @param e - a PaintEvent to draw the Maze.
	 */
	@Override
	public void draw(PaintEvent e) {
		if(board==null)
			return;
		
		
		if(maze==null){//or maze have been changed
			   if(images==null){
					images = new Image[mazeData.getRows()][mazeData.getCols()];
					maze = new imgCellDisplay[mazeData.getRows()][mazeData.getCols()];
			   }
			   
				   for(int i=0;i<mazeData.getRows();i++){
					   for(int j=0;j<mazeData.getCols();j++){
						   if(maze[i][j] == null){
			        			Image im = chooseImage(mazeData.getCell(i, j),i,j,false);
			        			maze[i][j] = new imgCellDisplay(im);
			        		}
					   }
				   }
		   }//setting up the maze
		//board.getShell().layout();
		
		for(int i=0;i<mazeData.getCols();i++){
			for(int j=0;j<mazeData.getRows();j++){
				maze[j][i].paint(e, i*board.w, j*board.h, board.w, board.h); //for some reason it flips the rows with the cols so this fixes it.
			}
	   	}//paints the window
	}
	/**
	 * this method is used to set a tile's image. the calculation is based on position(x,y), cell and a boolean field for knowing if needed to display a solution.
	 * @param cell
	 * @param i
	 * @param j
	 * @param sol
	 * @return
	 */
	public Image chooseImage(Cell cell,int i,int j, boolean sol){
		Image im = null;
		
		String image_path = "squares/square";
		if(cell.getHasRightWall())
			image_path+="_right";
		
		if(cell.getHasBottomWall())
			image_path+="_bottom";
		
		if(cell.getHasLeftWall() && (j == 0))//for some reason it flips the rows with the cols so this fixes it.
			image_path+="_left";
		
		if(cell.getHasTopWall() && (i == 0))//for some reason it flips the rows with the cols so this fixes it.
			image_path+="_top";
		
		if(sol==true)
			image_path+="_sol";
		
		image_path+=".jpg";
		
		im = new Image(board.getDisplay(), new ImageData(image_path));
		
		return im;
	}
	/**
	 * a method to draw the solution of the Maze using a GC.
	 * @param gc - a GC to draw the solution of the Maze.
	 */
	@Override
	public void drawSol(GC gc, Solution sol) {
		if(board==null)
			return;
		
		String[] solMaze = sol.toString().split(System.lineSeparator());
		String[] CellPoint = null;
		
		for(String line : solMaze){
			CellPoint = line.split(" ");
			int x = new Integer(CellPoint[0].split(",")[0]);
			int y = new Integer(CellPoint[0].split(",")[1]);
			Image im = chooseImage(mazeData.getCell(x, y), x, y, true);
			maze[x][y].setI(im);
			maze[x][y].paint(gc, y*board.w, x*board.h, board.w, board.h); //for some reason it flips the rows with the cols so this fixes it.
			board.redraw();
		}
							
	}
	/**
	 * a method to undraw the solution of the Maze using a GC.
	 * @param gc - a GC to undraw the solution of the Maze.
	 */
	@Override
	public void undrawSol(GC gc, Solution sol) {
		if(board==null)
			return;
		
		String[] solMaze = sol.toString().split(System.lineSeparator());
		String[] CellPoint = null;
		
		for(String line : solMaze){
			CellPoint = line.split(" ");
			int x = new Integer(CellPoint[0].split(",")[0]);
			int y = new Integer(CellPoint[0].split(",")[1]);
			Image im = chooseImage(mazeData.getCell(x, y), x, y, false);
			maze[x][y].setI(im);
			maze[x][y].paint(gc, y*board.w, x*board.h, board.w, board.h); //for some reason it flips the rows with the cols so this fixes it.
			board.redraw();
		}
							
	}
	/**
	 * this function returns the Tile in i and j indexes.
	 * @return Object - a tile type object.
	 */
	@Override
	public Object getTile(int i, int j) {
		return mazeData.getCell(i, j);
	}

	public Maze getMazeData() {
		return mazeData;
	}

	public void setMazeData(Maze mazeData) {
		this.mazeData = mazeData;
	}
	/**
	 * returns the rows of the maze.
	 * @return rows - an int
	 */
	@Override
	public int getRows() {
		return mazeData.getRows();
	}
	/**
	 * returns the cols of the maze.
	 * @return cols - an int
	 */
	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return mazeData.getCols();
	}

	@Override
	public void setData(Object arg) {
		mazeData=(Maze) arg;
		
	}

	@Override
	public Object getData() {
		
		return this.mazeData;
	}

	
	
}
