package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = generatePrice();
    }

    private int generatePrice() {
        if (row <= 4) {
            return 10;
        } else {
            return 8;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
