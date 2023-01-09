package a02a.e2;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class LogicImpl implements Logic{

    private enum Direction{
        NORD,
        EAST,
        SUD,
        OVEST
    }

    private int size;
    private int lastnumber = 0;
    private Optional<Pair<Integer, Integer>> lastNumberPosition = Optional.empty();
    private Direction dir = Direction.NORD;
    private List<Pair<Integer, Pair<Integer, Integer>>> selectedCells = new ArrayList<>();

    public LogicImpl(int size){
        this.size = size;
    }

    public int getCellnumber(int x, int y){
        if(x < 0 || y < 0 || x > size - 1 || y > size - 1) return -1;

        for (Pair<Integer,Pair<Integer,Integer>> pair : selectedCells) {
            if(pair.getY().equals(new Pair<>(x, y))){
                return pair.getX();
            }
        }
        return -1;
    }

    private boolean isCellAvailable(int x, int y){
        if(x < 0 || y < 0 || x > size - 1 || y > size - 1) return false;

        for (Pair<Integer,Pair<Integer,Integer>> pair : selectedCells) {
            if(pair.getY().equals(new Pair<>(x, y))){
                return false;
            }
        }
        return true;
    }

    @Override
    public void hit() {
        if(isOver()) return;

        if(lastNumberPosition.isEmpty()){
            Random r = new Random();
            lastNumberPosition = Optional.of(new Pair<>(r.nextInt(size), r.nextInt(size)));
            selectedCells.add(new Pair<>(lastnumber++, lastNumberPosition.get()));
        }
        else{
            int x = lastNumberPosition.get().getX(), y = lastNumberPosition.get().getY();
            switch(dir){
                case NORD:
                    if(isCellAvailable(x, y - 1)){
                        lastNumberPosition = Optional.of(new Pair<>(x, y - 1));
                        selectedCells.add(new Pair<>(lastnumber++, lastNumberPosition.get()));
                        System.out.println("aggiunto");
                    }
                    else{
                        dir = Direction.EAST;
                        hit();
                    }
                break;
                case EAST:
                    if(isCellAvailable(x + 1, y)){
                        lastNumberPosition = Optional.of(new Pair<>(x + 1, y));
                        selectedCells.add(new Pair<>(lastnumber++, lastNumberPosition.get()));
                    }
                    else{
                        dir = Direction.SUD;
                        hit();
                    }
                break;
                case SUD:
                    if(isCellAvailable(x, y + 1)){
                        lastNumberPosition = Optional.of(new Pair<>(x, y + 1));
                        selectedCells.add(new Pair<>(lastnumber++, lastNumberPosition.get()));
                    }
                    else{
                        dir = Direction.OVEST;
                        hit();
                    }
                break;
                case OVEST:
                    if(isCellAvailable(x - 1, y)){
                        lastNumberPosition = Optional.of(new Pair<>(x - 1, y));
                        selectedCells.add(new Pair<>(lastnumber++, lastNumberPosition.get()));
                    }
                    else{
                        dir = Direction.NORD;
                        hit();
                    }
                break;
            }
        }
    }

    @Override
    public boolean isOver() {
        return lastNumberPosition.isPresent() && getCellnumber(lastNumberPosition.get().getX(),lastNumberPosition.get().getY() + 1) != -1 
            && getCellnumber(lastNumberPosition.get().getX() + 1,lastNumberPosition.get().getY()) != -1 
            && getCellnumber(lastNumberPosition.get().getX(),lastNumberPosition.get().getY() - 1) != -1 
            && getCellnumber(lastNumberPosition.get().getX() - 1,lastNumberPosition.get().getY()) != -1;
    }
    
}
