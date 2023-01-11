package a04.e2;

public interface Logic {
    
    String getSymbol(Pair<Integer, Integer> cell);

    boolean hit(Pair<Integer, Integer> cell);

    int getResult();

    boolean canStop();
}
