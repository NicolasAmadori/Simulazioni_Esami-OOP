package a01a.e2;

import org.w3c.dom.ranges.RangeException;

import a01a.e2.Pair;

public class Logic {
    
    private boolean[][] cells;

    public Logic(int size){
        cells = new boolean[size][size];
        for (boolean[] row : cells) {
            row = new boolean[size];
        }
    }

    private int getX(int index){
        return index / cells.length;
    }

    private int getY(int index){
        return index % cells.length;
    }

    public void selectCell(int firstCellIndex, int secondCellIndex){
        int startX = getX(firstCellIndex),
            startY = getY(firstCellIndex),
            endX = getX(secondCellIndex),
            endY = getY(secondCellIndex);

        // if(startX < 0 || secondCellIndex > cells.length){
        //     throw new RangeException("Indici fuori range del vettore", null);
        // }
        for(int i = startX; i < endX; i++){
            for(int j = startY; i < endY; i++){
                cells[i][j] = true;
            }
        }
    }

    public boolean isOver(){
        for (boolean b : cells) {
            if(!b){
                return false;
            }
        }
        return true;
    }
}
