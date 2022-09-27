package cinema;

import java.util.UUID;

public class Theater {
    private int totalRows = 9;
    private int totalColumns = 9;
    private Seat[] availableSeats;

    public Theater() {
        availableSeats = new Seat[totalRows * totalColumns];
        for (int i = 0; i < availableSeats.length; i++) {
            int column = (i % totalColumns) + 1;
            int row = (i - column + 1) / totalColumns + 1;
            availableSeats[i] = new Seat(row, column);
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Seat[] getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Seat[] availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Seat getSeat(Seat seat) {
        int seatIndex = (seat.getRow() - 1) * totalColumns + seat.getColumn() - 1;
        return availableSeats[seatIndex];
    }

    public Seat getSeat(UUID token) {
        for (Seat seat : availableSeats) {
            if (seat.getToken().equals(token)) {
                return seat;
            }
        }
        return null;
    }

    public void purchaseTicket(Seat seat) {
        getSeat(seat).setPurchased(true);
    }

    public void returnTicket(UUID token) {
        getSeat(token).setPurchased(false);
    }
}
