package Model.Animal.LiveStocks;

import Model.Animal.Animal;
import Model.Products.Product;

public class LiveStock extends Animal {
    private double speed = 1; // 1 cell per 0.33 second
    private double startTimeForEatingForage = -5;//just for denying some requests from the start of game
    private double startTimeBeingInMap;
    private static final double maxHungerLevel = 8;
    private double hungerLevel = maxHungerLevel;
    private boolean mustEatForage = false;


    public double getStartTimeForEatingForage() {
    	return this.startTimeForEatingForage;
    }


    public void setStartTimeForEatingForage(double time) {
        this.startTimeForEatingForage = time;
    }

    public LiveStock(double startBeingInMap) {
        this.startTimeBeingInMap = startBeingInMap;
    }



    @Override
    public void move(double finalX, double finalY) {
    	
    }
    

    public void mustEatForage(boolean check) {
    	this.mustEatForage = check;
    }
    
    public boolean isMustEatForage() {
    	return this.mustEatForage;
    }
    
    public void changeHungerLevel(double number) {
        hungerLevel += number;
    }
   
    public double getHungerLevel() {
    	return hungerLevel;
    }

    public void checkLiveStock() {
      if(this.hungerLevel <= 3)
    	  this.mustEatForage(true);
      if(this.hungerLevel >= 7)
    	  this.mustEatForage(false);
    }


    public double getStartTimeBeingInMap() {
        return startTimeBeingInMap;
    }

    public Product releaseProduct(double time) {
        return new Product(time);
    }


}
