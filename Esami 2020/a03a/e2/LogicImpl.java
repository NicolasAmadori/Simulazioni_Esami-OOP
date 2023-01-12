package a03a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic{

    private int size;
    private List<Pair<Integer,Integer>> enemies = new ArrayList<>();
    private Pair<Integer,Integer> tower;
    private List<Pair<Integer,Integer>> availableCells = new ArrayList<>();

    public LogicImpl(int size) {
        this.size = size;
        Random r = new Random();

        tower = new Pair<Integer,Integer>(r.nextInt(size), r.nextInt(size));

        while(enemies.size() < size){
            
            Pair<Integer, Integer> newPos = new Pair<Integer,Integer>(r.nextInt(size), r.nextInt(size));
            if(!enemies.contains(newPos) && !tower.equals(newPos)){
                enemies.add(newPos);
            }
        }
        updateAvailableCells();
    }

    private void updateRange(int start, int stop, int delta){
        boolean canContinue = true;
        for (int i = start; !canContinue && (start < stop ? i < stop : i >= stop); i = i + (start < stop ? 1 : -1)) {
            Pair<Integer, Integer> pos = new Pair<>(i, tower.getY());
            if(!enemies.contains(pos)){
                availableCells.add(pos);
            }
            else{
                canContinue = false;
            }
        }
    }

    private void updateAvailableCells(){
        boolean canContinue = true;
        for (int i = tower.getX() - 1; i >= 0 && !canContinue; i--) {
            Pair<Integer, Integer> pos = new Pair<>(i, tower.getY());
            if(!enemies.contains(pos)){
                availableCells.add(pos);
            }
            else{
                canContinue = false;
            }
        }

        for (int i = tower.getX() - 1; i >= 0 && !canContinue; i--) {
            Pair<Integer, Integer> pos = new Pair<>(i, tower.getY());
            if(!enemies.contains(pos)){
                availableCells.add(pos);
            }
            else{
                canContinue = false;
            }
        }

        for (int i = tower.getX() - 1; i >= 0 && !canContinue; i--) {
            Pair<Integer, Integer> pos = new Pair<>(i, tower.getY());
            if(!enemies.contains(pos)){
                availableCells.add(pos);
            }
            else{
                canContinue = false;
            }
        }

        for (int i = tower.getX() - 1; i >= 0 && !canContinue; i--) {
            Pair<Integer, Integer> pos = new Pair<>(i, tower.getY());
            if(!enemies.contains(pos)){
                availableCells.add(pos);
            }
            else{
                canContinue = false;
            }
        }
    }

    @Override
    public boolean isCellAvailable(Pair<Integer, Integer> cell) {
        return availableCells.contains(cell);
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
