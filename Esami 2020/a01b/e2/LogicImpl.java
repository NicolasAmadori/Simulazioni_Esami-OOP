package a01b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class LogicImpl implements Logic{

    private int size;
    private List<Pair<Integer, Integer>> pedons = new ArrayList<>();
    private Pair<Integer, Integer> kingPosition;

    public LogicImpl(int size) {
        this.size = size;

        Random r = new Random();
        while(pedons.size() < 3){
            Pair<Integer, Integer> newPos = new Pair<>(r.nextInt(size), r.nextInt(size - 2));
            if(!pedons.contains(newPos)){
                pedons.add(newPos);
            }
        }

        kingPosition = new Pair<Integer,Integer>(size - 1, size - 1);
    }

    private boolean isCellAvailable(Pair<Integer, Integer> cell){
        for (Pair<Integer,Integer> pedon : pedons) {
            Pair<Integer,Integer> downLeftCell = new Pair<>(pedon.getX() - 1, pedon.getY() + 1) , downRightCell = new Pair<>(pedon.getX() + 1, pedon.getY() + 1);
            if(cell.equals(downLeftCell) || cell.equals(downRightCell)){
                return false;
            }
        }
        return true;
    }

    private boolean canKingMoveHere(Pair<Integer, Integer> cell){
        return Math.abs(kingPosition.getX() - cell.getX()) <= 1 && Math.abs(kingPosition.getY() - cell.getY()) <= 1;
    }

    @Override
    public void move(Pair<Integer, Integer> cell) {
        if(isCellAvailable(cell) && canKingMoveHere(cell)){
            pedons.remove(cell);
            kingPosition = cell;
        }
    }

    @Override
    public boolean isOver() {
        return pedons.isEmpty();
    }

    @Override
    public Piece getCellPiece(Pair<Integer, Integer> cell) {
        return pedons.contains(cell) ? Piece.PEDON : cell.equals(kingPosition) ? Piece.KING : Piece.NONE;
    }
    
}
