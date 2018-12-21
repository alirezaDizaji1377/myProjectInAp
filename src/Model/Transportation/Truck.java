package Model.Transportation;


import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Truck extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int wallet = 0;
    private int numOfBoxes = 2;
    private double startTimeForSellingElements;
    private double endTimeForSellingElements;
    private boolean isInWareHouse = true;
    private int countReturnToWareHouse = 0;
    private static int timeDurationForWorking = 20;
    {
        for (int i = 0; i < numOfBoxes; i++)
            boxes.add(new Box());
        moneyForUpgrading = 200;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getWallet() {
        return wallet;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public double getStartTimeForSellingElements() {
        return startTimeForSellingElements;
    }

    public double getEndTimeForSellingElements() {
        return endTimeForSellingElements;
    }

    public static int getTimeDurationForWorking() {
        return timeDurationForWorking;
    }

    @Override
    public void move(double finalX, double finalY) {
        //
    }

    @Override
    public boolean upgrade() {
        if (level < 3) {
            numOfBoxes += 2;
            boxes.add(new Box());
            boxes.add(new Box());
            timeDurationForWorking -= 2;
            moneyForUpgrading += 100;
            level++;
            return true;
        }
        return false;
    }


    private int putOneCountOfAnElementInTrunk(Element element) {
        for (Box box: boxes) {
            if (!(box.getContent().getClass().equals(element.getClass())) ||
                   box.getCurrent() == box.getVolume())
                continue;
            box.addElement(element, 1);
            return 0;
        }
        return 1;
    }

    private int putManyOfAnElementInTrunk(Element element, int count) {
        for (Box box: boxes) {
            if (!box.getContent().getClass().equals(element.getClass()) ||
                  box.getCurrent() == box.getVolume())
                continue;
            int countCapacity = (box.getVolume() - box.getCurrent()) / element.getVolume();
            if (countCapacity < count) {
                box.addElement(element, countCapacity);
                count -= countCapacity;
            } else {
                box.addElement(element, count);
                count = 0;
                break;
            }
        }
        return count;
    }
    public void putElementInTrunk(Element element, int count) {
        if (isInWareHouse)
            if (count == 1) {
                countReturnToWareHouse = this.putOneCountOfAnElementInTrunk(element);
            } else if (count > 1)
                countReturnToWareHouse = this.putManyOfAnElementInTrunk(element, count);

    }

    public int getCountReturnToWareHouse() {
        return countReturnToWareHouse;
    }


    public boolean isTruckContainsAny() {
        for(Box box: boxes)
            if (box.isContainAny())
                return true;
            return false;

    }


    public int startWorking(double time) {
        if(this.isInWareHouse && this.isTruckContainsAny()) {
            startTimeForSellingElements = time;
            endTimeForSellingElements = time + timeDurationForWorking;
            isInWareHouse = false;
            for (Box box : boxes) {
                this.wallet += box.getContent().getPrice() * box.getElement().get(box.getContent());
                box.removeElement();
            }
        }
        return wallet;
    }

    public boolean isInWareHouse() {
        return isInWareHouse;
    }



    public void checkWasTruckCameBackFromBazar(double time) {
        if (time > endTimeForSellingElements && !isInWareHouse) {
            wallet = 0;
            isInWareHouse = true;
            for (Box box : boxes)
                box.removeElement();
        }
    }

    public void clear() {
        if (isInWareHouse && isTruckContainsAny())
            for (Box box: boxes)
                box.removeElement();
    }

}
