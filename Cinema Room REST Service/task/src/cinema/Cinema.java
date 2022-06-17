package cinema;


import java.util.ArrayList;
import java.util.List;

public class Cinema {
    int total_rows;
    int total_columns;

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

    public Cinema() {

    }

    public List<cinema.Seats> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<cinema.Seats> available_seats) {
        this.available_seats = available_seats;
    }

    List<Seats> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        available_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_rows; j++) {
                if (i <= 4) {
                    Seats now = new Seats(i, j);
                    now.setPrice(10);
                    available_seats.add(now);
                } else {
                    Seats now = new Seats(i, j);
                    now.setPrice(8);
                    available_seats.add(now);
                }
            }
        }
    }
}
