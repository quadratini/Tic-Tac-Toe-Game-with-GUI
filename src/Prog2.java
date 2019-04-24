public class Prog2 {

    public static void main(String[] args) {

        // to do
        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.setPreviousClosingPrice(34.5);

        stock.setCurrentPrice(34.35);

    }

}

class Stock {
    // data fields declaration
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock() {

    }

    public Stock(String newSymbol, String newName) {
        // to do
        symbol = newSymbol;
        name = newName;

    }

    public double getChangePercent() {

        // to do

        return (currentPrice - previousClosingPrice) / previousClosingPrice;

    }

    public double getPreviousClosingPrice() {

        // to do

        return previousClosingPrice;

    }

    public double getCurrentPrice() {

        // to do

        return currentPrice;

    }

    public void setCurrentPrice(double newCurrentPrice) {

        // to do
        currentPrice = newCurrentPrice;

    }

    public void setPreviousClosingPrice(double newPreviousClosingPrice) {

        // to do

    }

}