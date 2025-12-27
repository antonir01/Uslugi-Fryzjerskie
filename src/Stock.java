import java.util.ArrayList;
import java.util.List;

public class Stock {
    String name;
    StockType stockType;
    double price;

    private static final List<Stock> stockList = new ArrayList<>();

    public Stock(String name, StockType stockType, double price) {
        this.name = name;
        this.stockType = stockType;
        this.price = price;
        stockList.add(this);
    }

    public static List<Stock> getStockList() {
        return stockList;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", stockType=" + stockType +
                ", price=" + price +
                '}';
    }
}
