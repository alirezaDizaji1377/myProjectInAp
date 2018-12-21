package Model.Places.Workshop;

import Model.Places.WorkShop;
import Model.Products.PowderedEgg;
import Model.Products.Product;

import java.util.ArrayList;

public class EggPowderPlant extends WorkShop {

    {
        name = "EggPowderedPlant";
        nameOfInputProducts.add("egg");
        maxNumberOfProducts = 1;
        nameOfOutputProducts = "powderedEgg";
    }


    @Override
    public ArrayList<Product> distributeOutputs(double time) {
        for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
            this.outputProduct.add(new PowderedEgg(time));
        return outputProduct;
    }



}
