package cinema;

public class Theater {
    private int total_rows = 9;
    private int total_columns = 9;
    private Seat[] available_seats;

    public Theater() {
        available_seats = new Seat[total_rows * total_columns];
        int columnIndex = 0;
        for (int i = 0; i < available_seats.length; i++) {
            if (i % 9 == 0) {
                columnIndex++;
            }
            available_seats[i] = new Seat(i % 9 + 1, columnIndex);
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public Seat[] getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Seat[] available_seats) {
        this.available_seats = available_seats;
    }
}
