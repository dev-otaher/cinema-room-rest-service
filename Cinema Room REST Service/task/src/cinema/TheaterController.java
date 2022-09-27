package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TheaterController {
    private final Theater theater = new Theater();

    @GetMapping("/seats")
    public ResponseEntity getSeats() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total_rows", theater.getTotalRows());
        response.put("total_columns", theater.getTotalColumns());
        response.put("available_seats", theater.getAvailableSeats());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat seat) {
        if (isInvalidCoordinates(seat)) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }
        Seat chosenSeat = theater.getSeat(seat);
        if (chosenSeat == null || chosenSeat.isPurchased()) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        }
        theater.purchaseSeat(chosenSeat);
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
        Seat seat = theater.getPurchasedSeat(token);
        if (seat == null) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"),
                    HttpStatus.BAD_REQUEST);
        }
        theater.returnSeat(seat);
        Map<String, Object> response = new LinkedHashMap<>(Map.of(
                "returned_ticket", seat
        ));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam(required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            Map<String, Integer> response = new LinkedHashMap<>();
            response.put("current_income", theater.getCurrentIncome());
            response.put("number_of_available_seats", theater.getAvailableSeats().size());
            response.put("number_of_purchased_tickets", theater.getPurchasedSeats().size());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
