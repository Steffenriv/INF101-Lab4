package cellular;

import java.util.Random;

import datastructure.CellGrid;
import datastructure.IGrid;

/**
 * 
 * A CellAutomata that implements Conways game of life.
 * 
 * @see CellAutomaton
 * 
 *      Every cell has two states: Alive or Dead. Each step the state of each
 *      cell is decided from its neighbors (diagonal, horizontal and lateral).
 *      If the cell has less than two alive Neighbors or more than three
 *      neighbors the cell dies. If a dead cell has exactly three neighbors it
 *      will become alive.
 * 
 * @author eivind
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 * @author Sondre Bolland - sondre.bolland@uib.no
 */
public class GameOfLife implements CellAutomaton {

	/**
	 * The grid of cells
	 */
	IGrid currentGeneration;

	/**
	 * 
	 * Construct a Game Of Life Cell Automaton that holds cells in a grid of the
	 * provided size
	 * 
	 * @param height The height of the grid of cells
	 * @param width  The width of the grid of cells
	 */
	public GameOfLife(int rows, int columns) {
		currentGeneration = new CellGrid(rows, columns, CellState.DEAD);
		initializeCells();
	}

	@Override
	public void initializeCells() {
		Random random = new Random();
		for (int row = 0; row < currentGeneration.numRows(); row++) {
			for (int col = 0; col < currentGeneration.numColumns(); col++) {
				if (random.nextBoolean()) {
					currentGeneration.set(row, col, CellState.ALIVE);
				} else {
					currentGeneration.set(row, col, CellState.DEAD);
				}
			}
		}
	}

	@Override
	public int numberOfRows() {
		// TODO
		return currentGeneration.numRows();
	}

	@Override
	public int numberOfColumns() {
		// TODO
		return currentGeneration.numColumns();
	}

	@Override
	public CellState getCellState(int row, int col) {
		// TODO
		return currentGeneration.get(row,col);
	}

	@Override
	public void step() {
		IGrid nextGeneration = currentGeneration.copy();
		// TODO
		for(int row = 0; row < numberOfRows(); row++) {
			for(int col = 0; col < numberOfColumns(); col++) {
				nextGeneration.set(row,col,getNextCell(row,col));
			}
		}
	}

	@Override
	public CellState getNextCell(int row, int col) {
		// TODO
		CellState status = getCellState(row, col);
		int neighborsStatus = countNeighbors(row,col, CellState.ALIVE);
		if (neighborsStatus < 2) status = CellState.DEAD;
		else if(neighborsStatus == 2 || neighborsStatus == 3) status = CellState.ALIVE;
		else if(neighborsStatus > 3) status = CellState.DEAD;
		else if(status.equals(CellState.DEAD) && neighborsStatus == 3) status = CellState.ALIVE;
		return status;
	}

	/**
	 * Calculates the number of neighbors having a given CellState of a cell on
	 * position (row, col) on the board
	 * 
	 * Note that a cell has 8 neighbors in total, of which any number between 0 and
	 * 8 can be the given CellState. The exception are cells along the boarders of
	 * the board: these cells have anywhere between 3 neighbors (in the case of a
	 * corner-cell) and 5 neighbors in total.
	 * 
	 * @param x     the x-position of the cell
	 * @param y     the y-position of the cell
	 * @param state the Cellstate we want to count occurences of.
	 * @return the number of neighbors with given state
	 */
	private int countNeighbors(int row, int col, CellState state) {
		// TODO
		int count = -1; //Starting at -1, due to countNeighbors
						// should not count row/col value which we are at
		for(int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++){
				if(getCellState(row+i,col+j).equals(state)) {
					count++;
				}
			}
		}
		if (!getCellState(row,col).equals(state)) count++;
		// If CellState which we are at is equal to DEAD, we to add to count due to it starting at -1
		return count;
	}

	@Override
	public IGrid getGrid() {
		return currentGeneration;
	}
}
