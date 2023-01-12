package a02a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic{
    
    private int size;
    private List<Pair<Integer, Integer>> enemies = new ArrayList<>();
    private Pair<Integer, Integer> pedonPosition;

    public LogicImpl(int size) {
        this.size = size;
        Random r = new Random();

        while(enemies.size() < size){
            Pair<Integer, Integer> newPos = new Pair<Integer,Integer>(r.nextInt(size), r.nextInt(size - 1));
            if(!enemies.contains(newPos)){
                enemies.add(newPos);
            }
        }

        pedonPosition = new Pair<Integer,Integer>(r.nextInt(size), size - 1);
    }

    private boolean containEnemy(Pair<Integer, Integer> cell){
        return enemies.contains(cell);
    }

    @Override
    public Piece getCellPiece(Pair<Integer, Integer> cell) {
        return enemies.contains(cell) ? Piece.ENEMY : pedonPosition.equals(cell) ? Piece.PEDON : Piece.NONE;
    }

    @Override
    public void advance() {
        Pair<Integer, Integer> upLeftPos = new Pair<Integer,Integer>(pedonPosition.getX() - 1, pedonPosition.getY() - 1),
                                upRightPos = new Pair<Integer,Integer>(pedonPosition.getX() + 1, pedonPosition.getY() - 1),
                                upPos = new Pair<Integer,Integer>(pedonPosition.getX(), pedonPosition.getY() - 1);
        if(containEnemy(upLeftPos)){
            enemies.remove(upLeftPos);
            pedonPosition = upLeftPos;
        }
        else if(containEnemy(upRightPos)){
            enemies.remove(upRightPos);
            pedonPosition = upRightPos;
        }
        else if (!containEnemy(upPos)){
            pedonPosition = upPos;
        }
    }

    @Override
    public boolean isOver() {
        Pair<Integer, Integer> upLeftPos = new Pair<Integer,Integer>(pedonPosition.getX() - 1, pedonPosition.getY() - 1),
                                upRightPos = new Pair<Integer,Integer>(pedonPosition.getX() + 1, pedonPosition.getY() - 1),
                                upPos = new Pair<Integer,Integer>(pedonPosition.getX(), pedonPosition.getY() - 1);
        return (!containEnemy(upLeftPos) && !containEnemy(upRightPos)) && (containEnemy(upPos) || upPos.getY() < 0);
    }
}
