package Model.Places;

import Model.ElementAndBoxAndDirection.Element;
import Model.Products.Product;

import java.util.ArrayList;

public class WorkShop extends Element {
    private boolean isInWorking = false;
    protected ArrayList<String> nameOfInputProducts = new ArrayList<>();
    private int possibleNumberOfOutputProducts;
    protected int maxNumberOfProducts;
    protected String nameOfOutputProducts;
    protected double timeLastingForWorking = 12;
    protected ArrayList<Product> outputProduct;
    private double endOfTimeForWorking;


    public void setPossibleNumberOfOutputProducts(int num) {
        this.possibleNumberOfOutputProducts = num;
    }

    public int getPossibleNumberOfOutputProducts() {
        return possibleNumberOfOutputProducts;
    }

    public ArrayList<String> getNameOfInputProducts() {
        return nameOfInputProducts;
    }


    public int getMaxNumberOfProducts() {
        return maxNumberOfProducts;
    }

    public void startWorking(double time) {
        isInWorking = true;
        endOfTimeForWorking = time + timeLastingForWorking;
        outputProduct.clear();
    }

    public boolean isInWorking() {
        return isInWorking;
    }

    public boolean checkWorkShopForDistributingOutputs(double time) {// checking it at turn function in map.java
       if (isInWorking && time > endOfTimeForWorking) {
           isInWorking = false;
           return true;
       }
       return false;
    }

    public ArrayList<Product> distributeOutputs(double time) {
      //TODO: we have different products in different workshops so we implement these function in subClasses
        return null;
    }


    @Override
    public void move (double finalX, double finalY) {

    }

    @Override
    public  boolean upgrade() {
      if (level < 3) {
          maxNumberOfProducts++;
          moneyForUpgrading += 100;
          level++;
          return true;
      }
      return false;
    }

    @Override
    public void print() {
        System.out.println("workShopName: " + name + " ,Level: " + level + ",isInWorking: " + isInWorking +
                 "\n" + "InputProducts: \n");
        for (String string: nameOfInputProducts)
            System.out.print(string + " ");
        System.out.println();
        System.out.println("outputProduct: " + outputProduct + "\n");

    }

}
