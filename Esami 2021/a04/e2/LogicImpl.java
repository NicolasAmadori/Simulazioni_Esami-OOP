package a04.e2;

import java.lang.StackWalker.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class LogicImpl implements Logic{

    private int size;
    private int result = 0;
    private Optional<Pair<Integer, Integer>> lastPressed = Optional.empty();
    private Map<Pair<Integer, Integer>, Integer> numbers = new HashMap<>();
    private Map<Pair<Integer, Integer>, String> operators = new HashMap<>();
    
    public LogicImpl(int size){
        this.size = size;
        Random r = new Random();
        while(numbers.size() + operators.size() < size * size){
            Pair<Integer, Integer> newPos = new Pair<>(r.nextInt(size), r.nextInt(size));
            while(numbers.containsKey(newPos) || operators.containsKey(newPos)){
                newPos = new Pair<>(r.nextInt(size), r.nextInt(size));
            }

            if(r.nextBoolean()){
                numbers.put(newPos, r.nextInt(10));
            }
            else{
                int n = r.nextInt(3);
                operators.put(newPos, n == 1 ? "+" : n == 2 ? "-" : "*" );
            }
        }
    }

    private boolean areAdjiacent(Pair<Integer, Integer> c1, Pair<Integer, Integer> c2){
        if(c1.getX().equals(c2.getX())){
            return c1.getY() == c2.getY() - 1 || c1.getY() == c2.getY() + 1;
        }
        else if (c1.getY().equals(c2.getY())){
            return c1.getX() == c2.getX() - 1 || c1.getX() == c2.getX() + 1;
        }
        return  false;
    }

    @Override
    public boolean hit(Pair<Integer, Integer> cell) {
        if(lastPressed.isEmpty() && numbers.containsKey(cell)){
            result = numbers.get(cell);
            lastPressed = Optional.of(cell);
            return true;
        }
        else if (areAdjiacent(lastPressed.get(), cell)){
            if(operators.containsKey(cell) && numbers.containsKey(lastPressed.get())){
                lastPressed = Optional.of(cell);
                return true;
            }
            else if (numbers.containsKey(cell) && operators.containsKey(lastPressed.get())){
                switch(operators.get(lastPressed.get())){
                    case "+": result+= numbers.get(cell);
                    break;
                    case "-": result-= numbers.get(cell);
                    break;
                    case "*": result*= numbers.get(cell);
                    break;
                }
                lastPressed = Optional.of(cell);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canStop() {
        return lastPressed.isPresent() && numbers.containsKey(lastPressed.get());
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public String getSymbol(Pair<Integer, Integer> cell) {
        return numbers.containsKey(cell) ? numbers.get(cell).toString() : operators.containsKey(cell) ? operators.get(cell) : "";
    }
    
}
