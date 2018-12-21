package Model.Transportation;


import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Helicopter extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int mapBudget = 0;
    private int allCost = 0;
    private int numOfBoxes = 2;
    private double startTimeForSellingElements;
    private double endTimeForBuyingElements;
    private ArrayList<Element> salesGoods = new ArrayList<>();
    private boolean isInWareHouse = true;
    private static int timeDurationForWorking = 20;
    private boolean isStartedForAddingToBoxes = false;

    {
        for (int i = 0; i < numOfBoxes; i++)
            boxes.add(new Box());
        moneyForUpgrading = 400;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getAllCost() {
        return allCost;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public double getStartTimeForSellingElements() {
        return startTimeForSellingElements;
    }

    public double getEndTimeForBuyingElements() {
        return endTimeForBuyingElements;
    }

    public static int getTimeDurationForWorking() {
        return timeDurationForWorking;
    }

    public boolean isStartedForAddingToBoxes() {
        return isStartedForAddingToBoxes;
    }

    @Override
    public void move(double finalX, double finalY) {

    }

    @Override
    public boolean upgrade() {
      if (level < 3) {
          numOfBoxes += 2;
          boxes.add(new Box());
          boxes.add(new Box());
          moneyForUpgrading += 100;
          level++;
          return true;
      }
     return false;
    }

    public int getMapBudget() {
        return mapBudget;
    }

    public void startForAddingElementToHelicopter(int budget) {
        if (!isStartedForAddingToBoxes) {
            this.mapBudget = budget;
            isStartedForAddingToBoxes = true;
        }
    }

    public void putOneCountOfAnElementInHelicopter(Element element, int budget) {
        this.startForAddingElementToHelicopter(budget);
       if (element.getPrice() <= allCost && isInWareHouse)
        for (Box box: boxes) {
            if (!(box.getContent().getClass().equals(element.getClass())) ||
                    box.getCurrent() == box.getVolume())
                continue;
            box.addElement(element, 1);
            salesGoods.add(element);
            allCost += element.getPrice();
        }

    }

    private boolean isHelicopterContainsAny() {
        for(Box box: boxes)
            if (box.isContainAny())
                return true;
        return false;

    }


    public int startWorking(double time) {
        if(this.isInWareHouse && this.isHelicopterContainsAny()) {
            startTimeForSellingElements = time;
            isStartedForAddingToBoxes = false;
            endTimeForBuyingElements = time + timeDurationForWorking;
            isInWareHouse = false;
            return mapBudget - allCost;
        }
        return mapBudget;
    }

    public boolean isInWareHouse() {
        return isInWareHouse;
    }



    public boolean checkWasHelicopterCameBackFromBazar(double time) {
        if (time > endTimeForBuyingElements && !isInWareHouse) {
            mapBudget = 0;
            allCost = 0;
            salesGoods.clear();
            isInWareHouse = true;
            for (Box box : boxes)
                box.removeElement();
            return true;
        }
        return false;
    }

    public ArrayList<Element> getSalesGoods() {
        return salesGoods;
    }

    public void clear() {
        if (this.isInWareHouse && isHelicopterContainsAny())
            for (Box box: boxes)
                box.removeElement();
    }

}