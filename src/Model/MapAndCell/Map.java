package Model.MapAndCell;

import java.util.ArrayList;

import Model.Animal.*;
import Model.Animal.LiveStocks.*;
import Model.Animal.WildAnimals.WildAnimal;
import Model.ElementAndBoxAndDirection.Element;
import Model.Places.WareHouse;
import Model.Places.Well;
import Model.Places.WorkShop;
import Model.Products.Product;
import Model.Products.Forage.Forage;
import Model.Transportation.Helicopter;
import Model.Transportation.Truck;

import java.util.HashMap;
import java.util.Iterator;

public class Map {
    private ArrayList<LiveStock> liveStocks = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();
    private ArrayList<Forage> forages = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private Cell[][] cells = new Cell[41][41];
    private WareHouse wareHouse;
    private Well well;
    private Truck truck;
    private Helicopter helicopter;
    private double farmTime = 0;
    private int budget = 1000;
    private ArrayList<WorkShop> workshops = new ArrayList<>();
    private ArrayList<String> workShopName = new ArrayList<>();

    private void budgetDecreament(int amount) {
        budget -= amount;
    }

    public double getFarmTime() {
        return farmTime;
    }

    private boolean isBudgetEnough(int amount) {
        if (budget >= amount) {
            budget -= amount;
            return true;
        }
        return false;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public Well getWell() {
        return well;
    }

    public Truck getTruck() {
        return truck;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public ArrayList<WorkShop> getWorkshops() {
        return workshops;
    }

    private int makeRandomNumbers() {
        return (int) (Math.random() * (35 - 5) + 5);
    }

    //////////////////////////////BUY_ANIMAL_BY_STRING///////////////////
    private void addChicken() {
        if (budget >= 100) {
            Chicken chicken = new Chicken(this.farmTime);
            liveStocks.add(chicken);
            wareHouse.addGoodOrLiveStock(chicken, 1);
            cells[(int) chicken.getX()][(int) chicken.getY()].getLiveStocks().add(chicken);
            budgetDecreament(100);
        }
    }

    private void addOstrich() {
        if (budget >= 1000) {
            Ostrich ostrich = new Ostrich(this.farmTime);
            liveStocks.add(ostrich);
            wareHouse.addGoodOrLiveStock(ostrich, 1);
            cells[(int) ostrich.getX()][(int) ostrich.getY()].getLiveStocks().add(ostrich);
            budgetDecreament(1000);
        }

    }

    private void addCow() {
        if (budget >= 10000) {
            Cow cow = new Cow(this.farmTime);
            liveStocks.add(cow);
            wareHouse.addGoodOrLiveStock(cow, 1);
            cells[(int) cow.getX()][(int) cow.getY()].getLiveStocks().add(cow);
            budgetDecreament(10000);
        }

    }

    public void buyAnimal(String stringName) {
        switch (stringName) {
            case "chicken":
                this.addChicken();
                break;
            case "oistrich":
                this.addOstrich();
                break;
            case "cow":
                this.addCow();
                break;
        }
    }

    //////////////////////////////PLANT_FORAGE//////////////////////////
    public void plantForage(int x, int y, double time) {

        if ((x > 5 && x < 35 && y > 5 && y < 35) && well.canDisChargeWell()) {
            forages.add(new Forage(farmTime));
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    cells[x + i][y + j].addElement(new Forage(farmTime));
        }
    }

    //////////////////////////////MOVE_ANIMAL///////////////////////////
    //TODO: FIND THE BEST BFS
    private void BFS(Element element, double i, double j) {

    }


    private void moveLiveStocks() {
        for (LiveStock liveStock : this.liveStocks) {
            if (this.farmTime - liveStock.getStartTimeForEatingForage() < 2)//if it is eating don't move the liveStock
                continue;
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeElement(liveStock);
            liveStock.checkLiveStock();// checking for weather is hungry or not
            if (liveStock.isMustEatForage() && !forages.isEmpty()) {//liveStock should move wisely
                int closestForageX = 0;
                int closestForageY = 0;
                boolean firstCheck = false;
                for (int i = 5; i < 36; i++)
                    for (int j = 5; j < 36; j++) {
                        if (!cells[i][j].isHaveForage())
                            continue;
                        if (!firstCheck) {
                            closestForageX = i;
                            closestForageY = j;
                            firstCheck = true;
                            continue;
                        }
                        if ((Math.pow(i - liveStock.getX(), 2) +
                                Math.pow(j - liveStock.getY(), 2)) <=
                                (Math.pow(closestForageX - liveStock.getX(), 2) +
                                        Math.pow(closestForageY - liveStock.getY(), 2))) {
                            closestForageX = i;
                            closestForageY = j;
                        }
                    }
                this.BFS(liveStock, closestForageX, closestForageY);
            } else { // liveStock should move randomly
                liveStock.changeDirectionByKnowingCurrentPostition();
                liveStock.moveRandomly();
            }
            cells[(int) liveStock.getX()][(int) liveStock.getY()].getLiveStocks().add(liveStock);
        }

    }

    private void moveWildAnimals() {
        for (WildAnimal wildAnimal : wildAnimals) {
            if (wildAnimal.isCaged())
                continue;
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].removeElement(wildAnimal);
            wildAnimal.changeDirectionByKnowingCurrentPostition();
            wildAnimal.moveRandomly();
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].getWildAnimals().add(wildAnimal);
        }
    }

    private void moveDogs() {

        for (Dog dog : dogs) {
            cells[(int) dog.getX()][(int) dog.getY()].removeElement(dog);
            if (wildAnimals.isEmpty()) {
                dog.changeDirectionByKnowingCurrentPostition();
                dog.moveRandomly();
            } else {
                int closestWildAnimalX = 0;
                int closestWildAnimalY = 0;
                boolean checkFirst = false;
                for (WildAnimal wild : wildAnimals) {
                    if (!checkFirst) {
                        closestWildAnimalX = (int) wild.getX();
                        closestWildAnimalY = (int) wild.getY();
                        checkFirst = true;
                        continue;
                    }
                    if ((Math.pow(wild.getX() - dog.getX(), 2) +
                            Math.pow(wild.getY() - dog.getY(), 2)) <=
                            (Math.pow(closestWildAnimalX - dog.getX(), 2) +
                                    Math.pow(closestWildAnimalY - dog.getY(), 2))) {
                        closestWildAnimalX = (int) wild.getX();
                        closestWildAnimalY = (int) wild.getY();
                    }

                }
                this.BFS(dog, closestWildAnimalX, closestWildAnimalY);
            }
            cells[(int) dog.getX()][(int) dog.getY()].getDogs().add(dog);
        }
    }

    private void moveCats() {
        catLoop:
        for (Cat cat : cats) {
            cells[(int) cat.getX()][(int) cat.getY()].removeElement(cat);
            if (products.isEmpty()) {
                cat.changeDirectionByKnowingCurrentPostition();
                cat.moveRandomly();
            } else {
                int closestProductX = 0;
                int closestProductY = 0;
                boolean checkFirst = false;
                for (Product product : products) {
                    if (product.getVolume() + wareHouse.getCurrent() <= wareHouse.getVolume()) {
                        this.BFS(cat, product.getX(), product.getY());
                        continue catLoop;
                    } else if (products.indexOf(product) == products.size() - 1) {
                        cat.changeDirectionByKnowingCurrentPostition();
                        cat.moveRandomly();
                    }
                }
            }
            cells[(int) cat.getX()][(int) cat.getY()].getCats().add(cat);
        }

    }

    public void moveAnimals() {
        this.moveLiveStocks();
        this.moveWildAnimals();
        this.moveCats();
        this.moveDogs();
    }

    /////////////////////////////LIVESTOCK_EAT_FORAGE//////////////////////////
    private void liveStockEatingForage() {

        for (LiveStock liveStock : liveStocks) {
            liveStock.checkLiveStock();
            if (!liveStock.isMustEatForage() ||
                    !cells[(int) liveStock.getX()][(int) liveStock.getY()].isHaveForage() ||
                    farmTime - liveStock.getStartTimeForEatingForage() < 2)
                continue;
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeElement(liveStock);
            liveStock.setStartTimeForEatingForage(this.farmTime);
            liveStock.changeHungerLevel(2);//by eating forage it increases two level of its hunger
            cells[(int) liveStock.getX()][(int) liveStock.getY()].addElement(liveStock);
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeOneForage();
        }


    }

    private void checkLiveStocksForReleasingProduct() {
        for (LiveStock liveStock : liveStocks)
            if ((farmTime - liveStock.getStartTimeBeingInMap()) % 10 == 0) {
                cells[(int) liveStock.getX()][(int) liveStock.getY()].addElement(liveStock.releaseProduct(farmTime));
                products.add(liveStock.releaseProduct(farmTime));
            }
    }

    /////////////////////////////CHECK_WILDANIMALS_POSITION_WITH_OTHERS////////////
    public void checkWildAnimalPositionWithOthers() {
        for (WildAnimal wildAnimal : wildAnimals)
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].
                    removeAllElements();
    }

    ////////////////////////////CAGE_WILD_ANIMAL///////////////////////////////////
    public void cageWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            wildAnimal.setIsCaged(true);
    }

    ///////////////////////////PICKUP_ELEMENTS_FROM_MAP/////////////////////////////
    private void pickUpProducts(int x, int y) {
        for (Product product : cells[x][y].getProducts())
            if (wareHouse.getCurrent() + product.getVolume() <= wareHouse.getVolume()) {
                wareHouse.addGoodOrLiveStock(product, 1);
                cells[x][y].removeElement(product);
                product.setIsPickedUp(true);
            }

        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.isPickedUp())
                iterator.remove();
        }
    }

    private void pickUpWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            if (wareHouse.getCurrent() + wildAnimal.getVolume() <= wareHouse.getVolume() &&
                    wildAnimal.isCaged()) {
                wareHouse.addGoodOrLiveStock(wildAnimal, 1);
                cells[x][y].removeElement(wildAnimal);
            }
        Iterator iterator = wildAnimals.iterator();
        while (iterator.hasNext()) {
            WildAnimal wildAnimal = (WildAnimal) iterator.next();
            if (wildAnimal.isCaged() && wildAnimal.getX() == x && wildAnimal.getY() == y)
                iterator.remove();
        }
    }

    public void pickUpAndPutInWareHouse(int x, int y) {
        this.pickUpProducts(x, y);
        this.pickUpWildAnimal(x, y);
    }

    ///////////////////////////PICKUP_BY_CAT///////////////////////////////////////
    public void pickUpByCatAndPutInWareHouse() {
        for (Cat cat : cats)
            this.pickUpProducts((int) cat.getX(), (int) cat.getY());

    }

    ///////////////////////////KILLED_WILD_ANIMALS_BY_DOGS/////////////////////////
    private void killedWildAnimalsByDogs() {
        for (Dog dog : dogs)
            if (!cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().isEmpty()) {
                cells[(int) dog.getX()][(int) dog.getY()].removeElement(dog);
                dog.setIsKilled(true);
                wildAnimals.remove(cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().get(0));
                cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().remove(0);

            }
        Iterator iterator = dogs.iterator();
        while (iterator.hasNext()) {
            Dog dog = (Dog) iterator.next();
            if (dog.getIsKilled())
                iterator.remove();
        }

    }

    ///////////////////////////CHARGE_WELL/////////////////////////////////////////
    public void chargeWell() {
        if (!well.isInCharging() && well.getCurrent() == 0 && isBudgetEnough(well.getPrice())) {
            well.chargeWell(farmTime);
        }

    }

    //////////////////////////START_WORKSHOP//////////////////////////////////////
    public void startWorkshop(String workShopName) {
        for (WorkShop workShop : workshops) {
            if (!workShop.getName().equals(workShopName))
                continue;
            if (!workShop.isInWorking() && wareHouse.isItPossibleForStartingWorkshop(workShop))
                workShop.startWorking(this.farmTime);
        }
    }


    //////////////////////////CHECKING_WORKSHOP_FOR_GETTING_OUTPUT/////////////////
    public void checkWorkshopForGettingOutput(WorkShop workShop) {
        if (workShop.checkWorkShopForDistributingOutputs(farmTime)) {
            ArrayList<Product> goods = workShop.distributeOutputs(this.farmTime);
            this.addProductProducedByWorkshops(workShop, goods);
        }
    }

    private void addProductProducedByWorkshops(WorkShop workShop, ArrayList<Product> goods) {
        if (workShop.getY() < 5)
            for (Product product : goods)
                cells[(int) workShop.getX()][(int) workShop.getY() + 3].addElement(product);
        else if (workShop.getY() > 35)
            for (Product product : goods)
                cells[(int) workShop.getX()][(int) workShop.getY() - 3].addElement(product);
    }

    /////////////////////////ADD_ELEMENT_TO_TRUCK//////////////////////////////////
    private void addOneElementToTruck(Element element) {
        this.truck.putElementInTrunk(this.wareHouse.giveOneNumberFromAnElement(element), 1);
    }

    private void addAllOfAnElementToTruck(Element element) {
        HashMap<Element, Integer> reference = this.wareHouse.giveAllOfAnElement(element);
        this.truck.putElementInTrunk(element, reference.get(element));
    }

    public void addElementToTruck(Element element, int count) {
        if (wareHouse.isHaveThisElement(element) && this.truck.isInWareHouse()) {
            if (count == 1)
                this.addAllOfAnElementToTruck(element);
            else
                this.addOneElementToTruck(element);
            this.wareHouse.addGoodOrLiveStock(element, this.truck.getCountReturnToWareHouse());
        }
    }

    ////////////////////////GO_TRUCK////////////////////////////////////////////////
    public void goTruck() {
        budget += this.truck.startWorking(this.farmTime);
    }

    ///////////////////////CHECK_TRUCK_IS_IN_wareHouse/////////////////////////////
    private void checkIsTruckInWareHouse() {
        this.truck.checkWasTruckCameBackFromBazar(farmTime);
    }

    //////////////////////CLEAR_TRUCK//////////////////////////////////////////////
    public void clearTruck() {
        this.truck.clear();
    }

    ////////////////////////ADD_ELEMENT_IN_HELICOPTER//////////////////////////////
    public void addElementToHelicopter(Element element) {
        this.helicopter.putOneCountOfAnElementInHelicopter(element, budget);
    }

    ////////////////////////GO_HELICOPTER/////////////////////////////////
    public void goHelicopter() {
        budget = this.helicopter.startWorking(farmTime);
    }

    ///////////////////////CHECK_HELICOPTER_COME_BACK_FROM_BAZAR/////////////////////
    private void checkHelicopterCameBackFromBazar() {
        if (this.helicopter.checkWasHelicopterCameBackFromBazar(farmTime)) {
            for (Element element : helicopter.getSalesGoods())
                cells[makeRandomNumbers()][makeRandomNumbers()].addElement(element);
        }
    }

    ////////////////////////CLEAR_HELICOPTER///////////////////////////
    public void clearHelicopter() {
        this.helicopter.clear();
    }

    ///////////////////////LIVE_STOCK_TURN//////////////////////////////
    private void liveStockTurn() {
        this.checkLiveStocksForReleasingProduct();
        this.liveStockEatingForage();
        this.moveLiveStocks();
    }

    //////////////////////WILD_ANIMAL_TURN///////////////////////////////////
    private void wildAnimalTurn() {
        this.moveWildAnimals();
        this.checkWildAnimalPositionWithOthers();
    }

    /////////////////////DOG_TURN////////////////////////////////////////////
    private void dogTurn() {
        this.moveDogs();
        this.killedWildAnimalsByDogs();
    }

    ////////////////////CAT_TURN//////////////////////////////////////////////
    private void catTurn() {
        this.moveCats();
        this.pickUpByCatAndPutInWareHouse();
    }

    public void turnMap(double increase) {
        farmTime += increase;

        this.liveStockTurn();
        this.wildAnimalTurn();
        this.dogTurn();
        this.catTurn();

        for (WorkShop workShop : workshops)
            this.checkWorkshopForGettingOutput(workShop);

        this.checkHelicopterCameBackFromBazar();
        this.checkIsTruckInWareHouse();
    }

    ///////////////////////////UPGRADE_ELEMENT////////////////////
    private void upgradeWareHouse() {
        if (wareHouse.upgrade())
            budgetDecreament((int) wareHouse.getMoneyForUpgrading());
    }

    private void upgradeWorkshop(String workShopName) {
        for (WorkShop workShop: workshops)
            if (workShop.getName().equals(workShopName))
                if (workShop.upgrade())
                    budgetDecreament((int) workShop.getMoneyForUpgrading());
    }

    private void upgradeCat() {
        for (Cat cat: cats)
            if (cat.upgrade())
                budgetDecreament((int) cat.getMoneyForUpgrading());
    }

    private void upgradeWell() {
        if (well.upgrade())
            budgetDecreament((int) well.getMoneyForUpgrading());
    }

    private void upgradeTruck() {
        if (truck.upgrade())
            budgetDecreament((int) truck.getMoneyForUpgrading());
    }

    private void upgradeHelicopter() {
        if (helicopter.upgrade())
            budgetDecreament((int) helicopter.getMoneyForUpgrading());
    }

    public void upgrade(String name) {
        switch (name) {
            case "truck":
                this.upgradeTruck();
                break;
            case "helicopter":
                this.upgradeHelicopter();
                break;
            case "wareHouse":
                this.upgradeWareHouse();
                break;
            case "well":
                this.upgradeWell();
                break;
            case "cat":
                this.upgradeCat();
                break;
            default:
                upgradeWorkshop(name);
                break;

        }
    }
    /////////////////////////PRINT_ELEMENT////////////////////////
    public void printElement(Element element) {
        element.print();
    }
}