package Model;

public enum CardType {
    HEART(1, "Red", "\u2665"),
    DIAMOND(2, "Red", "\u2666"),
    SPADE(3, "Black", "\u2660"),
    CLUB(4, "Black", "\u2663");

    private final String color;
    private final int i;
    private final String emoji;
    CardType(int i, String color, String emoji) {
        this.i = i;
        this.color = color;
        this.emoji = emoji;
    }
    public int getTypeNum(){
        return i;
    }
    public String cardColor(){
        return color;
    }
    public String getEmoji(){
        return emoji;
    }
}
