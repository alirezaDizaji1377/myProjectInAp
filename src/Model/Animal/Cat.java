package Model.Animal;

import Model.Products.Product;

import java.util.ArrayList;

public class Cat extends Animal {
    private static final double speedForCollecting = 4;
    private static final double speedNormally = 2;
    private ArrayList<Product> collectProducts;

    {
      speed = speedNormally;
      name = "cat";
      moneyForUpgrading = 100;
    }

    @Override
    public void move(double finalX, double finalY)  {
        //nothing
    }

    //TODO: for cat the move function differs from other elements

    public int getLevel() {
    	return level;
    }
    @Override
    public boolean upgrade() {
        if (level == 0) {
            speed += 2;
            level++;
            return true;
        }
        return false;
    }

   
    public void setCollectProducts(ArrayList<Product> products) {
        this.collectProducts = products;
    }

    public ArrayList<Product> giveProductToWareHouse() {
        return collectProducts;
    }
}
