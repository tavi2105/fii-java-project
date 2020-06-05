public class Player {
    private String name;
    private char sign;
    private int tableDimension;

    public Player(String name, char sign, int tableDimension) {
        this.name = name;
        this.sign = sign;
        this.tableDimension = tableDimension;
    }

    public String getName() {
        return name;
    }

    public char getSign() {
        return sign;
    }

    public int getTableDimension() {
        return tableDimension;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", sign=" + sign +
                ", tableDimension=" + tableDimension +
                '}';
    }
}
