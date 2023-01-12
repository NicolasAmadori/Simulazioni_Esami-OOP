package a02b.e2;

public interface Logic {
    
    boolean isCellSelected(Pair<Integer, Integer> cell);

    void select(Pair<Integer, Integer> cell);

    void up();

    void down();
}
