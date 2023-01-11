package a03c.e2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class LogicImpl implements Logic{

    private enum Direction{
        UP(-1), DOWN(1);

        int yDelta;
        Direction(int yDelta){
            this.yDelta = yDelta;
        }

        Direction opposite(){
            return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
        }
    }
    private int size;
    private List<Pair<Integer, Integer>> obstacles = new ArrayList<Pair<Integer, Integer>>();
    private Map<Pair<Integer, Integer>, Direction> asterisk = new HashMap<>();

    public LogicImpl(int size){
        this.size = size;
        for(int i = 0; i < size; i++){
            asterisk.put(new Pair<>(i, size - 1), Direction.UP);
            Random r = new Random();
            obstacles.add(new Pair<>(i, r.nextInt(size - 3)));
        }
    }

    @Override
    public Component getCellChar(Pair<Integer, Integer> cell) {
        return asterisk.keySet().contains(cell) ? Component.BALL : obstacles.contains(cell) ? Component.OBSTACLE : Component.NONE;
    }

    @Override
    public void hit() {
        if(isOver()) return;

        Map<Pair<Integer, Integer>, Direction> newAsterisk = new HashMap<>();
        for (var entry : asterisk.entrySet()) {
            Pair<Integer, Integer> nextCell = new Pair<>(entry.getKey().getX(), entry.getKey().getY() + entry.getValue().yDelta);
            if(nextCell.getY() == size || obstacles.remove(nextCell)){
                Pair<Integer, Integer> backCell = new Pair<>(entry.getKey().getX(), entry.getKey().getY() + entry.getValue().opposite().yDelta);
                newAsterisk.put(backCell, entry.getValue().opposite());
            }
            else{
                newAsterisk.put(nextCell, entry.getValue());
            }
        }
        asterisk = newAsterisk;
    }

    @Override
    public boolean isOver() {
        for (var entry : asterisk.entrySet()) {
            if(entry.getKey().getY() == 0)
                return true;
        }
        return false;
    }
    
}
