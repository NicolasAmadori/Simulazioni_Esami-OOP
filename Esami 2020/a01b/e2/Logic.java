package a01b.e2;

public interface Logic {

    enum Piece{
        PEDON("P"),
        KING("K"),
        NONE("");

        private String symbol;
        Piece(String symbol){
            this.symbol = symbol;
        }

        @Override
        public String toString(){
            return this.symbol;
        }
    }
    
    Piece getCellPiece(Pair<Integer, Integer> cell);

    void move(Pair<Integer, Integer> cell);

    boolean isOver();
}
