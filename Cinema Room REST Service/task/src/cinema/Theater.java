package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Theater {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
    private List<Seat> purchasedSeats;
    private int currentIncome;

    public Theater() {
        totalRows = 9;
        totalColumns = 9;
        initAvailableSeats();
        purchasedSeats = new ArrayList<>();
        currentIncome = 0;
    }

    private void initAvailableSeats() {
        availableSeats = new ArrayList<>();
        for (int i = 0; i < totalRows * totalColumns; i++) {
            int column = (i % totalColumns) + 1;
            int row = (i - column + 1) / totalColumns + 1;
            availableSeats.add(new Seat(row, column));
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

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getPurchasedSeats() {
        return purchasedSeats;
    }

    public void setPurchasedSeats(List<Seat> purchasedSeats) {
        this.purchasedSeats = purchasedSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public Seat getSeat(Seat seat) {
        for (Seat s : availableSeats) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                return s;
            }
        }
        return null;
    }

    public Seat getSeat(UUID token) {
        for (Seat seat : availableSeats) {
            if (seat.getToken().equals(token)) {
                return seat;
            }
        }
        return null;
    }

    public Seat getPurchasedSeat(UUID token) {
        for (Seat seat : purchasedSeats) {
            if (seat.getToken().equals(token)) {
                return seat;
            }
        }
        return null;
    }

    public void purchaseSeat(Seat seat) {
        seat.setPurchased(true);
        availableSeats.remove(seat);
        purchasedSeats.add(seat);
        currentIncome += seat.getPrice();
    }

    public void returnSeat(Seat seat) {
        seat.setPurchased(false);
        availableSeats.add(seat);
        purchasedSeats.remove(seat);
        currentIncome -= seat.getPrice();
    }
}
