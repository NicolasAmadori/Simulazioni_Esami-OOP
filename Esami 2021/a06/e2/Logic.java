package a06.e2;

public interface Logic {

    enum Type{
        CENTER,
        WING,
        NONE
    }

    Type getCellType(Pair<Integer, Integer> cell);

    Pair<Integer, Integer> advance();

    void select(Pair<Integer, Integer> cell);

    boolean isOver();
}
