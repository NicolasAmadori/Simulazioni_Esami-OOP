package a02b.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic{

    private enum Direction{
        UP(0,-1),
        LEFT(-1,0),
        RIGHT(1,0);

        int x;
		int y;
		Direction(int x, int y){
			this.x = x;
			this.y = y;
		}
    }

    private Pair<Integer, Integer> cursorPosition;
    private int size;
    private Direction dir = Direction.UP;
    private Map<Pair<Integer, Integer>, String> map = new HashMap<>();

    public LogicImpl(int size){
        this.size = size;
        Random r = new Random();
        cursorPosition = new Pair<>(r.nextInt(size), size - 1);
        for(int i = 0; i < 20; i++){
            Pair<Integer, Integer> newPos = new Pair<Integer,Integer>(r.nextInt(size), r.nextInt(size));
            String s="";

            while(map.containsKey(newPos) || (newPos.getX() == cursorPosition.getX() && newPos.getY() == cursorPosition.getY())){
                newPos = new Pair<Integer,Integer>(r.nextInt(size), r.nextInt(size));
            }
            
            s = r.nextInt(2) == 0 ? "L" : "R";
            map.put(newPos, s);
        }
    }

    public String getCellChar(int x, int y){
        Pair<Integer, Integer> pos = new Pair<>(x, y);
        
        if(pos.getX() == cursorPosition.getX() && pos.getY() == cursorPosition.getY()){
            return "*";
        }
        return map.containsKey(pos) ? map.get(pos) : "";
    }

    @Override
    public void move(){
        if(isOver()) return;
        var nextPos = new Pair<>(cursorPosition.getX() + dir.x, cursorPosition.getY() + dir.y);        
        System.out.println("move cursor to: " + cursorPosition);
        switch(getCellChar(nextPos.getX(), nextPos.getY())){
            case "L":
                dir = Direction.LEFT;
                map.remove(nextPos);
            break;
            case "R":
                dir = Direction.RIGHT;
                map.remove(nextPos);
            break;
        }
        cursorPosition = nextPos;
    }

    @Override
    public boolean isOver() {
        Pair<Integer, Integer> nextPos = new Pair<>(cursorPosition.getX() + dir.x, cursorPosition.getY() + dir.y);
        return nextPos.getX() < 0 || nextPos.getX() == size || nextPos.getY() < 0 || nextPos.getY() > size;
    }
    
}
