package View;

import Model.ElementAndBoxAndDirection.Box;
import Model.MapAndCell.Map;

public class View {
    private Map map;


    private void printTruck() {
        System.out.println("Truck: " + " ,Level: " + map.getTruck().getLevel()
                + " ,wallet: " + map.getTruck().getWallet()
                + " ,isInWorking: " + !map.getTruck().isInWareHouse() + "\n"
                + " ,BoxNumbers: " + map.getTruck().getNumOfBoxes() + " ,boxContents: "
                + " ,lastTimeForWork" + map.getTruck().getEndTimeForSellingElements());
        for (Box box : map.getTruck().getBoxes())
            System.out.println("element: " + box.getContent().getClass() + " ,numbers: " +
                    box.getElement().get(box.getContent()));
    }

    private void printHeliCopter() {
        System.out.println("Helicopter: " + " ,Level: " + map.getHelicopter().getLevel() + " ,Cost: "
                + map.getHelicopter().getAllCost() + " ,MapBudget: " + map.getHelicopter().getMapBudget()
                + " ,isInWorking: " + !map.getHelicopter().isInWareHouse() + "\n" + " ,BoxNumbers: "
                + map.getHelicopter().getNumOfBoxes() + " ,LastTimeForWorking: " +
                map.getHelicopter().getEndTimeForBuyingElements() + " ,boxContents: ");
        for (Box box : map.getHelicopter().getBoxes())
            System.out.println("element: " + box.getContent().getClass() + " ,numbers: " +
                    box.getElement().get(box.getContent()));
    }

    private void printWareHouse() {
        System.out.println("wareHouse: \n" + "Level: " + map.getWareHouse().getLevel() + " ,Current: "
                + map.getWareHouse().getCurrent() + " ,Volume: " + map.getWareHouse().getVolume()
                + " ,products: ");
        for (String name: map.getWareHouse().getGoods().keySet())
            System.out.println("productName: " + name + " ,Count: " + map.getWareHouse().getGoods().get(name));
        for (String liveStockName: map.getWareHouse().getLiveStocks().keySet())
            System.out.println("liveStockName: " + liveStockName + " ,Count: " +
                    map.getWareHouse().getLiveStocks().get(liveStockName));
    }

    private void printWell() {
        System.out.println("Well: \n" + "Level: " + map.getWell().getLevel() + " ,Current: "
                + map.getWell().getCurrent() + " ,Volume: " + map.getWell().getVolume()
                + " ,isCharging: " + map.getWell().isInCharging() + " ,price: " + map.getWell().getPrice()
                + "\n" + " ,moneyForUpgrading: " + map.getWell().getMoneyForUpgrading());
    }


    private void printWeavingFactory() {
    }

    private void printSpinnery() {
    }

    private void printSewingFactory() {
    }

    private void printCakeBakery() {
    }

    private void printCookieBakery() {
    }

    private void printEggPowderPlant() {
    }


    public void print(Map map, String string) {//TODO: print map and info
        this.map = map;
        if (string.equals("truck"))
            this.printTruck();
        if (string.equals("helicopter"))
            this.printHeliCopter();
        if (string.equals("wareHouse"))
            this.printWareHouse();
        if (string.equals("well"))
            this.printWell();
        if (string.equals("eggPowderPlant"))
            this.printEggPowderPlant();
        if (string.equals("cookieBakery"))
            this.printCookieBakery();
        if (string.equals("cakeBakery"))
            this.printCakeBakery();
        if (string.equals("SewingFactory"))
            this.printSewingFactory();
        if (string.equals("Spinnery"))
            this.printSpinnery();
        if (string.equals("WeavingFactory"))
            this.printWeavingFactory();
    }

}
