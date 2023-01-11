package a06.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.w3c.dom.ranges.RangeException;

public class LogicImpl implements Logic{
    
    private int size;
    private List<Pair<Integer, Integer>> selectedCells = new ArrayList<>();
    private List<Pair<Integer, Integer>> placedWings = new ArrayList<>();
    
    public LogicImpl(int size){
        this.size = size;
    }

    private boolean areAdjiacent(Pair<Integer, Integer> c1, Pair<Integer, Integer> c2){
        if(c1.getX() == c2.getX()){
            return c1.getY() == c2.getY() - 1 || c1.getY() == c2.getY() + 1;
        }
        else if (c1.getY() == c2.getY()){
            return c1.getX() == c2.getX() - 1 || c1.getX() == c2.getX() + 1;
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> advance() {
        if(isOver()){
            throw new NoSuchElementException();
        }

        Pair<Integer, Integer> centerPosition = selectedCells.get(placedWings.size() / 2);
        placedWings.add(new Pair<Integer,Integer>(centerPosition.getX(), placedWings.size() % 2 == 0 ? centerPosition.getY() - 1 : centerPosition.getY() + 1));
        return placedWings.get(placedWings.size() - 1);
    }

    @Override
    public void select(Pair<Integer, Integer> cell) {
        if(selectedCells.contains(cell)){
            return;
        }

        for (var c : selectedCells) {
            if(areAdjiacent(cell, c)){
                return;
            }
        }
        selectedCells.add(cell);
    }

    @Override
    public boolean isOver() {
        return placedWings.size() == selectedCells.size() * 2;
    }

    @Override
    public Type getCellType(Pair<Integer, Integer> cell) {
        if(selectedCells.contains(cell)){
            return Type.CENTER;
        }
        else if (placedWings.contains(cell)){
            return Type.WING;
        }
        return Type.NONE;
    }

}
