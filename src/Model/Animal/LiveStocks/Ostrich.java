package Model.Animal.LiveStocks;

import Model.Products.LiveStockProducts.Feather;
import Model.Products.Product;

public class Ostrich extends LiveStock {
    private static final double priceForSale = 500;

    {
        price = 1000;
        volume = 5;
        level = 0;
        name = "ostrich";
    }

    public Ostrich(double startBeingInMap) {
        super(startBeingInMap);
    }

    public Product releaseProduct(double time) {
        return new Feather(time);
    }
}
