package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {
    @JsonIgnore
    private UUID token;
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean purchased;

    public Seat() {
    }

    public Seat(int row, int column) {
        token = UUID.randomUUID();
        this.row = row;
        this.column = column;
        price = generatePrice();
        purchased = false;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "token=" + token +
                ", row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", purchased=" + purchased +
                '}';
    }
}
