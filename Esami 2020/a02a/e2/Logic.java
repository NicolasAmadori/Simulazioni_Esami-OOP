package a02a.e2;

public interface Logic {

    enum Piece{
        PEDON("P"),
        ENEMY("*"),
        NONE("");

        String symbol;
        Piece(String s){
            this.symbol = s;
        }

        @Override
        public String toString(){
            return this.symbol;
        }
    }

    Piece getCellPiece(Pair<Integer, Integer> cell);
    void advance();

    boolean isOver();
}
