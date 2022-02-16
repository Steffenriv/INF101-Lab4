package datastructure;

import cellular.CellState;

import java.lang.reflect.Array;

public class CellGrid implements IGrid {

    private int rows;
    private int cols;

    private CellState[][] grid;

    public CellGrid(int rows, int columns, CellState initialState) {
		// TODO Auto-generated constructor stub
        this.rows = rows;
        this.cols = columns;
        this.grid = new CellState[rows][columns];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++) {
                grid[row][col] = initialState;
            }
        }
	}

    @Override
    public int numRows() {
        // TODO Auto-generated method stub
        return this.rows;
    }

    @Override
    public int numColumns() {
        // TODO Auto-generated method stub
        return this.cols;
    }

    @Override
    public void set(int row, int column, CellState element) {
        // TODO Auto-generated method stub
        if(row < 0 || row > numRows()) throw new IndexOutOfBoundsException();
        if(column < 0 || column > numColumns()) throw new IndexOutOfBoundsException();

        this.grid[row][column] = element;
    }

    @Override
    public CellState get(int row, int column) {
        // TODO Auto-generated method stub
        if(row < 0 || row > numRows()) throw new IndexOutOfBoundsException();
        if(column < 0 || column > numColumns()) throw new IndexOutOfBoundsException();

        return this.grid[row][column];
    }

    @Override
    public IGrid copy() {
        IGrid copy = new CellGrid(numRows(),numColumns(),CellState.DEAD);
        for(int row = 0; row < numRows(); row++) {
            for(int column= 0; column < numColumns(); column++) {

                copy.set(row,column,grid[row][column]);

            }
        }

        return copy;

    }
    
}
