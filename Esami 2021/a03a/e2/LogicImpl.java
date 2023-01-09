package a03a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic{

    private int size;
    private List<Pair<Integer, Integer>> drawnCells = new ArrayList<>();
    private boolean isOver = false;
    private Optional<Pair<Integer, Integer>> lastCorner = Optional.empty();

    private void drawRectangle(Pair<Integer, Integer> first, Pair<Integer, Integer> opposite){
        int startX = first.getX() <= opposite.getX() ? first.getX() : opposite.getX(),
            endX = first.getX() > opposite.getX() ? first.getX() : opposite.getX(),
            startY = first.getY() <= opposite.getY() ? first.getY() : opposite.getY(),
            endY = first.getY() > opposite.getY() ? first.getY() : opposite.getY();

        for(int i = startX; i <= endX; i++){
            drawnCells.add(new Pair<>(i, startY));
            drawnCells.add(new Pair<>(i, endY));
        }

        for(int i = startY; i <= endY; i++){
            drawnCells.add(new Pair<>(startX, i));
            drawnCells.add(new Pair<>(endX, i));
        }
    }

    private boolean isInner(Pair<Integer, Integer> current){
        if(lastCorner.isEmpty()){
            return true;
        }

        Pair<Integer, Integer> last = lastCorner.get();
        int lasthorizzontalDistance = last.getX() < size / 2 ? last.getX() : size - 1 - last.getX();
        int lastverticalDistance = last.getY() < size / 2 ? last.getY() : size - 1 - last.getY();

        int currenthorizzontalDistance = current.getX() < size / 2 ? current.getX() : size - 1 - current.getX();
        int currentverticalDistance = current.getY() < size / 2 ? current.getY() : size - 1 - current.getY();
        
        return currenthorizzontalDistance > lasthorizzontalDistance && currentverticalDistance > lastverticalDistance;
    }

    public LogicImpl(int size){
        this.size = size;
    }
    
    @Override
    public boolean isCellDrawn(Pair<Integer, Integer> cell) {
        return drawnCells.contains(cell);
    }

    @Override
    public void hit(Pair<Integer, Integer> cell) {
        if(drawnCells.contains(cell)){
            return;
        }
        
        Pair<Integer, Integer> oppositeCorner = new Pair<>(size - 1 - cell.getX(), size - 1  - cell.getY());
        if(lastCorner.isEmpty() || isInner(cell)){
            lastCorner = Optional.of(cell);
            drawRectangle(cell, oppositeCorner);
            if(cell.getX() - oppositeCorner.getX() == 1 || cell.getX() - oppositeCorner.getX() == -1 || cell.getY() - oppositeCorner.getY() == 1 || cell.getY() - oppositeCorner.getY() == -1){
                isOver = true;
            }
        }
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

}
