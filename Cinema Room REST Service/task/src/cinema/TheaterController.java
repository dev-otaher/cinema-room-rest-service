package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

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
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }
        Seat chosenSeat = theater.getSeat(seat);
        if (chosenSeat.isPurchased()) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        }
        theater.purchaseTicket(seat);
        Map<String, Object> response = new LinkedHashMap<>(Map.of(
                "token", chosenSeat.getToken(),
                "ticket", chosenSeat
        ));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean isInvalidCoordinates(Seat seat) {
        return seat.getRow() == 0
                || seat.getRow() > theater.getTotalRows()
                || seat.getRow() < 0
                || seat.getColumn() == 0
                || seat.getColumn() > theater.getTotalColumns()
                || seat.getColumn() < 0;
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Map<String, String> map) {
        UUID token = UUID.fromString(map.get("token"));
        Seat seat = theater.getSeat(token);
        if (seat == null) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"),
                    HttpStatus.BAD_REQUEST);
        }
        theater.returnTicket(token);
        Map<String, Object> response = new LinkedHashMap<>(Map.of(
                "returned_ticket", seat
        ));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
