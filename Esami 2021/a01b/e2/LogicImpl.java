package a01b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.event.ListDataEvent;

public class LogicImpl implements Logic{

    private Optional<Pair<Integer, Integer>> first = Optional.empty();
    private Optional<Pair<Integer, Integer>> second = Optional.empty();
    private Optional<Pair<Integer, Integer>> third = Optional.empty();
    private List<Pair<Integer, Integer>> selectedCell = new ArrayList();
    
    private boolean areHorizontallyAligned(Pair<Integer, Integer> firstCell, Pair<Integer, Integer> secondcell){
        return firstCell.getY().equals(secondcell.getY());
    }

    private boolean areVerticallyAligned(Pair<Integer, Integer> firstCell, Pair<Integer, Integer> secondcell){
        return firstCell.getX().equals(secondcell.getX());
    }

    private boolean canBeSecondCell(Pair<Integer, Integer> cell){
        if(first.isEmpty()) return false;

        return areHorizontallyAligned(first.get(), cell) || areVerticallyAligned(first.get(), cell);
    }

    private boolean canBeThirdCell(Pair<Integer, Integer> cell){
        if(first.isEmpty() || second.isEmpty()) return false;
        
        return (areHorizontallyAligned(first.get(), second.get()) && areVerticallyAligned(first.get(), cell)) ||
                (areVerticallyAligned(first.get(), second.get()) && areHorizontallyAligned(first.get(), cell));
    }

    private void selectCells(){
        if(areHorizontallyAligned(first.get(), second.get())){
            selectHorizzontally(first.get(), second.get());
        }
        else{
            selectVertically(first.get(), second.get());
        }
        if(areHorizontallyAligned(first.get(), third.get())){
            selectHorizzontally(first.get(), third.get());
        }
        else{
            selectVertically(first.get(), third.get());
        }
    }

    private void selectHorizzontally(Pair<Integer, Integer> firstCell, Pair<Integer, Integer> secondCell){
        int startX = firstCell.getX() <= secondCell.getX() ? firstCell.getX() : secondCell.getX(),
                endX = firstCell.getX() <= secondCell.getX() ? secondCell.getX() : firstCell.getX();
        for(int i = startX; i < endX; i++){
            selectedCell.add(new Pair(i, firstCell.getY()));
        }
    }

    private void selectVertically(Pair<Integer, Integer> firstCell, Pair<Integer, Integer> secondCell){
        int startY = firstCell.getY() <= secondCell.getY() ? firstCell.getY() : secondCell.getY(),
                endY = firstCell.getY() > secondCell.getY() ? firstCell.getY() : secondCell.getY();
        for(int i = startY; i < endY; i++){
            selectedCell.add(new Pair(firstCell.getX(), i));
        }
    }

    @Override
    public HitType hit(int x, int y) {
        Pair<Integer, Integer> cell = new Pair<>(x, y);
        if(first.isEmpty()){
            first = Optional.of(cell);
            return HitType.FIRST;
        }else if(second.isEmpty()){
            if(canBeSecondCell(cell)){
                second = Optional.of(cell);
                return HitType.SECOND;
            }
            return HitType.NULL;
        }
        else{
            if(canBeThirdCell(cell)){
                third = Optional.of(cell);
                selectCells();
                return HitType.THIRD;
            }
            else{
                return HitType.NULL;
            }
        }
    }

    @Override
    public boolean isOver() {
        return third.isPresent();
    }

    @Override
    public boolean isCellSelected(int x, int y) {
        return selectedCell.contains(new Pair(x, y));
    }
    
}
