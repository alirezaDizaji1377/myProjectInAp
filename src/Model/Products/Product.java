package Model.Products;

import Model.ElementAndBoxAndDirection.Element;

public class Product extends Element {
     private double firstTime;
     private double secondTime;
     private boolean isPickedUp = false;

    public Product(double firstTime) {
       this.firstTime = firstTime;
       this.secondTime = firstTime + 10;
    }


    public double getFirstTime() {
        return firstTime;
    }

    public double getSecondTime() {
        return secondTime;
    }

    public void setIsPickedUp(boolean check) {
        isPickedUp = check;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    @Override
    public void move(double finalX, double finalY) {
        //TODO:nothing
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
