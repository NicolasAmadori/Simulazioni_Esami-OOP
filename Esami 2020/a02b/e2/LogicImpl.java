package a02b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic{
    
    private int size;
    private List<Pair<Integer, Integer>> selectedCell = new ArrayList<>();
    
    public LogicImpl(int size) {
        this.size = size;
    }

    private int getRowSelectedCellsNumber(int x){
        int c = 0;
        for(int i = 0 ; i < size; i++){
            if(selectedCell.contains(new Pair<>(x, i))){
                c++;
            }
        }
        return c;
    }

    private void SelectCells(int x, int nCells, boolean up){
        for(int i = 0; i < size; i++){
            selectedCell.remove(new Pair<>(x, i));
        }

        for(int i = 0; i < nCells; i++){
            selectedCell.add(new Pair<>(x, up ? i : size - 1 - i));
        }
    }

    @Override
    public boolean isCellSelected(Pair<Integer, Integer> cell) {
        return selectedCell.contains(cell);
    }

    @Override
    public void select(Pair<Integer, Integer> cell) {
        if(!selectedCell.contains(cell)){
            selectedCell.add(cell);
        }
    }

    @Override
    public void up() {
        for(int i = 0; i < size; i++){
            SelectCells(i,getRowSelectedCellsNumber(i),true);
        }
    }

    @Override
    public void down() {
        for(int i = 0; i < size; i++){
            SelectCells(i,getRowSelectedCellsNumber(i),false);
        }
    }

}
