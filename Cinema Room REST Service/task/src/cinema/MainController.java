package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class MainController {
    Cinema free_place = new Cinema(9, 9);
    Statics current_stat = new Statics();
    public MainController(){
        current_stat.setNumber_of_available_seats(free_place.getTotal_columns()*free_place.getTotal_rows());
    }
    List<Purchased_ticket> removed = new ArrayList<>();

    @GetMapping(path = "/seats")
    public Cinema getFree_place() {
        return free_place;
    }

    @PostMapping(path = "/purchase")
    public ResponseEntity<?> checkFree_place(@RequestBody Seats tickets) {
        if ((tickets.getRow() < 1 || free_place.getTotal_rows() < tickets.getRow())
                || (tickets.getColumn() < 1 || tickets.getColumn() > free_place.getTotal_columns())) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error",
                    "The number of a row or a column is out of bounds!"));
        } else {
            for (Seats seats : free_place.available_seats) {
                if (seats.getRow() == tickets.getRow() && seats.getColumn() == tickets.getColumn()) {
                    free_place.available_seats.remove(seats);
                    Purchased_ticket temp = new Purchased_ticket(seats);
                    removed.add(temp);
                    current_stat.setCurrent_income(current_stat.getCurrent_income() + seats.getPrice());
                    current_stat.setNumber_of_purchased_tickets(current_stat.getNumber_of_purchased_tickets() + 1);
                    current_stat.setNumber_of_available_seats(current_stat.getNumber_of_available_seats() - 1);
                    return ResponseEntity.ok(temp);
                }
            }
            return ResponseEntity.badRequest().body(Map.of(
                    "error",
                    "The ticket has been already purchased!"));
        }
    }

    @PostMapping(path = "/return")
    public ResponseEntity<?> return_seats(@RequestBody Token token) {
        for (Purchased_ticket seats : removed) {
            if (Objects.equals(seats.getToken(), token.getToken())) {
                free_place.available_seats.add(seats.getTicket());
                current_stat.setCurrent_income(current_stat.getCurrent_income() - seats.getTicket().getPrice());
                current_stat.setNumber_of_purchased_tickets(current_stat.getNumber_of_purchased_tickets() - 1);
                current_stat.setNumber_of_available_seats(current_stat.getNumber_of_available_seats() + 1);
                removed.remove(seats);
                return ResponseEntity.ok().body(Map.of("returned_ticket", seats.getTicket()));
            }
        }
        return ResponseEntity.badRequest().body(Map.of(
                "error",
                "Wrong token!"));
    }

    @PostMapping(path = "/stats")
    public ResponseEntity<?> return_stat(@RequestParam Optional<String> password) {
        if(password.isPresent()){
            return ResponseEntity.ok(current_stat);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "error",
                "The password is wrong!"));
    }
}
