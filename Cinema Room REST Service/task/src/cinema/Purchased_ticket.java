package cinema;

import java.util.UUID;

public class Purchased_ticket {
    UUID token;
    public Purchased_ticket(Seats ticket) {
        this.ticket = ticket;
        token = UUID.randomUUID();
    }

    public Seats getTicket() {
        return ticket;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
    Seats ticket;

    public Purchased_ticket() {
    }
}
