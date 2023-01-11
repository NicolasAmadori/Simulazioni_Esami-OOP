package a03b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

public class LogicImpl implements Logic{

    private enum Direction{
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        int x, y;
        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }

        Direction next(){
            return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
        }
    }

    private int size;
    private Optional<Pair<Integer, Integer>> last = Optional.empty();
    private List<Pair<Integer, Integer>> selectedCells = new ArrayList<>();
    private Direction dir = Direction.UP;

    public LogicImpl(int size){
        this.size = size;
    }

    @Override
    public Optional<Pair<Integer, Integer>> hit(){
        if(isOver()) return Optional.empty();

        if(last.isEmpty()){
            Random r = new Random();
            Pair<Integer, Integer> randomCell = new Pair<>(r.nextInt(size - 4) + 2, r.nextInt(size - 4) + 2);
            last = Optional.of(randomCell);
            selectedCells.add(randomCell);
            return Optional.of(randomCell);
        }
        else{
            Pair<Integer, Integer> nextCell = new Pair<>(last.get().getX() + dir.x, last.get().getY() + dir.y);
            if (isCellSelected(nextCell) || nextCell.getX() < 0 || nextCell.getX() >= size || nextCell.getY() < 0 || nextCell.getY() >= size){
                dir = dir.next();
                nextCell = new Pair<>(last.get().getX() + dir.x, last.get().getY() + dir.y);
            }
            last = Optional.of(nextCell);
            selectedCells.add(nextCell);
            return Optional.of(nextCell);
        }
    }

    @Override
    public boolean isCellSelected(Pair<Integer, Integer> cell) {
        return selectedCells.contains(cell);
    }

    @Override
    public boolean isOver() {
        if(last.isEmpty()) return false;
        
        Pair<Integer, Integer> nextCell = new Pair<>(last.get().getX() + dir.x, last.get().getY() + dir.y);
        
        if (isCellSelected(nextCell) || nextCell.getX() < 0 || nextCell.getX() >= size || nextCell.getY() < 0 || nextCell.getY() >= size){
            nextCell = new Pair<>(last.get().getX() + dir.next().x, last.get().getY() + dir.next().y);
            return isCellSelected(nextCell) || nextCell.getX() < 0 || nextCell.getX() >= size || nextCell.getY() < 0 || nextCell.getY() >= size;
        }
        return false;
    }
}
