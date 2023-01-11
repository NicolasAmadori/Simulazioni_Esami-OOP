package a01a.e2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LogicImpl implements Logic{
    private int size;
    private List<Pair<Integer, Integer>> firstPlayerCoins = new ArrayList<>();
    private List<Pair<Integer, Integer>> secondPlayerCoins = new ArrayList<>();
    private Player currentPlayer = Player.FIRST;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean place(Pair<Integer, Integer> cell) {
        if(firstPlayerCoins.contains(cell) || secondPlayerCoins.contains(cell)){
            return false;
        }

        switch(currentPlayer){
            case FIRST:
                if(cell.getY() == size - 1 || firstPlayerCoins.contains(new Pair<>(cell.getX(), cell.getY() + 1)) || secondPlayerCoins.contains(new Pair<>(cell.getX(), cell.getY() + 1))){
                    firstPlayerCoins.add(cell);
                    currentPlayer = Player.SECOND;
                    return true;
                }
            break;
            case SECOND:
            if(cell.getY() == size - 1 || firstPlayerCoins.contains(new Pair<>(cell.getX(), cell.getY() + 1)) || secondPlayerCoins.contains(new Pair<>(cell.getX(), cell.getY() + 1))){
                    secondPlayerCoins.add(cell);
                    currentPlayer = Player.FIRST;
                    return true;
                }
            break;
        }
        return false;
    }

    @Override
    public boolean isOver() {
        for(int i = 0; i < size; i++){
            if(checkTripleHorizontally(i) || checkTripleVertically(i)){
                return true;
            }
        }
        return false;
    }

    private boolean checkTripleVertically(int x){
        int firstCounter = 0, secondCounter = 0;
        for(int i = 0; i < size && firstCounter < 3 && secondCounter < 3; i++){
            if(firstPlayerCoins.contains(new Pair<>(x, i))){
                firstCounter++;
            }
            else{
                firstCounter = 0;
            }

            if(secondPlayerCoins.contains(new Pair<>(x, i))){
                secondCounter++;
            }
            else{
                secondCounter = 0;
            }
        }
        return firstCounter == 3 || secondCounter == 3;
    }

    private boolean checkTripleHorizontally(int y){
        int firstCounter = 0, secondCounter = 0;
        for(int i = 0; i < size && firstCounter < 3 && secondCounter < 3; i++){
            if(firstPlayerCoins.contains(new Pair<>(i, y))){
                firstCounter++;
            }
            else{
                firstCounter = 0;
            }

            if(secondPlayerCoins.contains(new Pair<>(i, y))){
                secondCounter++;
            }
            else{
                secondCounter = 0;
            }
        }
        return firstCounter == 3 || secondCounter == 3;
    }

    @Override
    public Player getCellPlayer(Pair<Integer, Integer> cell) {
        if(firstPlayerCoins.contains(cell)){
            return Player.FIRST;
        }
        else if(secondPlayerCoins.contains(cell)){
            return Player.SECOND;
        }
        return Player.NONE;
    }
}
