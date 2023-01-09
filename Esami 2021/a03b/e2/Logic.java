package a03b.e2;

import java.util.Optional;

public interface Logic {
    
    Optional<Pair<Integer, Integer>> hit();

    boolean isCellSelected(Pair<Integer, Integer> cell);

    boolean isOver();
}
