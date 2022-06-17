package cinema;

public class Seats {
    public Seats() {
    }

    int row;
    int column;
    int price;

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
