package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {
    private final Theater theater = new Theater();

    @GetMapping("/seats")
    public Theater getTheater() {
        return theater;
    }
}
