package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TheaterController {
    private final Theater theater = new Theater();

    @GetMapping("/seats")
    public Theater getTheater() {
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat seat) {
        if (isInvalidCoordinates(seat)) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        Seat chosenSeat = theater.purchaseSeat(seat);
        if (chosenSeat == null) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(chosenSeat, HttpStatus.OK);
    }

    private boolean isInvalidCoordinates(Seat seat) {
        return seat.getRow() == 0
                || seat.getRow() > theater.getTotalRows()
                || seat.getRow() < 0
                || seat.getColumn() == 0
                || seat.getColumn() > theater.getTotalColumns()
                || seat.getColumn() < 0;
    }
}
