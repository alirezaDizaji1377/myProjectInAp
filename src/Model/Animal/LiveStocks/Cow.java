package Model.Animal.LiveStocks;

import Model.ElementAndBoxAndDirection.Direction;
import Model.Products.LiveStockProducts.Horn;
import Model.Products.Product;

public class Cow extends LiveStock {
    private static final double moneyForSale = 5000;

    {
        price = 10000;
        volume = 10;
        level = 0;
        name = "cow";
    }

    public Cow(double startBeingInMap) {
        super(startBeingInMap);
    }

    @Override
    public Product releaseProduct(double time) {
        return new Horn(time);
    }
}
