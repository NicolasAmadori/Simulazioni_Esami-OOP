package a03c.e2;

public interface Logic {

    enum Component{
        OBSTACLE,
        BALL,
        NONE
    }

    Component getCellChar(Pair<Integer, Integer> cell);

    void hit();

    boolean isOver();
}
