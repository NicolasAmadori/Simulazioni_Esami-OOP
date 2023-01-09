package a03a.e2;

public interface Logic {
    
    boolean isCellDrawn(Pair<Integer, Integer> cell);

    void hit(Pair<Integer, Integer> cell);

    boolean isOver();
}
