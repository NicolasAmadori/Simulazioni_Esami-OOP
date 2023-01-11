package a01a.e2;

public interface Logic {
    
    enum Player{
        FIRST("O"),
        SECOND("X"),
        NONE("");

        String sign;
        Player(String sign){
            this.sign = sign;
        }

        protected Player next(){
            return Player.values()[(this.ordinal() + 1) % Player.values().length];
        }
    }

    Player getCellPlayer(Pair<Integer, Integer> cell);
    
    boolean place(Pair<Integer, Integer> cell);

    boolean isOver();
}
