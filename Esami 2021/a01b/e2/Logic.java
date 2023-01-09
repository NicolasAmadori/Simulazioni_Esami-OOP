package a01b.e2;

public interface Logic {
    public enum HitType{
        NULL,
        FIRST,
        SECOND,
        THIRD
    }
    
    public HitType hit(int x, int y);

    public boolean isOver();

    public boolean isCellSelected(int x, int y);
}
