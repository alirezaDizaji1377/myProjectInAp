package Controller;

import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;
import Model.Products.Product;
import View.View;

import java.util.ArrayList;

public class Controller {
    private Map map = new Map();
    private View view = new View();
    private String instruction;
    private ArrayList<Element> elements;
    private boolean isIdentified = false;

    {
        elements.add(new LiveStock(0));
        elements.add(new Dog());
        elements.add(new Cat());
        elements.add(new WildAnimal());
        elements.add(new Product(0));
    }

    public void setInstruction(String string) {
        this.instruction = string;
    }

    private String getInstruction() {
        return instruction;
    }

    private boolean isScaleInMap(int x, int y) {
        if (Math.abs(x - 20) <= 15 && Math.abs(y - 20) <= 15) {
            isIdentified = true;
            return true;
        }
        return false;
    }

    public void operateInstruction() {
        isIdentified = false;
        String[] split = getInstruction().split("\\s");
        if (getInstruction().matches("buy\\s(chicken|ostrich|cow)")) {
            this.buyAnimal(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("pickup\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.pickup(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        if (getInstruction().matches("cage\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.pickup(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

        }

        if (getInstruction().matches("plant\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.plant(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        if (getInstruction().matches("well")) {
            this.well();
            isIdentified = true;
        }
        if (getInstruction().matches("start\\s(eggPowderPlant|cakeBakery|cookieBakery|" +
                "sewingFactory|spinnery|weavingFactory)")) {
            this.startWorkshop(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("upgrade\\s(eggPowderPlant|cakeBakery|cookieBakery|" +
                "sewingFactory|spinnery|weavingFactory|cat|well|truck|helicopter|wareHouse)")) {
            this.upgrade(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("turn \\d+")) {
            this.turn(Integer.parseInt(split[1]));
            isIdentified = true;
        }

        if (getInstruction().matches("(truck|helicopter)\\sadd\\s[a-z]+\\s\\d+")) {
            if (split[0].equals("truck"))
                this.addTruck(split[2], Integer.parseInt(split[3]));
            else if (split[0].equals("helicopter"))
                this.addHelicopter(split[2]);
            isIdentified = true;
        }

        if (getInstruction().matches("(truck|helicopter)\\sclear")) {
            if (split[0].equals("truck"))
                this.clearTruck();
            if (split[0].equals("helicopter"))
                this.clearHelicopter();
            isIdentified = true;
        }

        if (getInstruction().matches("(truck|helicopter)\\sgo")) {
            if (split[0].equals("truck"))
                this.goTruck();
            if (split[0].equals("helicopter"))
                this.goHelicopter();
            isIdentified = true;
        }

        if (getInstruction().matches("print\\s(wareHouse|truck|helicopter|well|CakeBakery|CookieBakery|" +
                "EggPowderPlant|SewingFactory|Spinnery|WeavingFactory)")) {//TODO: print map and info
            this.print(split[1]);
            isIdentified = true;
        }

        if (!isIdentified)
            System.out.println("command not found!");
    }

    private void buyAnimal(String animalName) {
        map.buyAnimal(animalName);
    }


    private void pickup(int x, int y) {
        map.pickUpAndPutInWareHouse(x, y);
    }

    private void cage(int x, int y) {
        map.cageWildAnimal(x, y);
    }

    private void plant(int x, int y) {
        map.plantForage((int) x, (int) y, map.getFarmTime());
    }

    private void well() {
        map.chargeWell();
    }

    private void startWorkshop(String workshopName) {
        map.startWorkshop(workshopName);
    }

    private void upgrade(String elementName) {
        map.upgrade(elementName);
    }

    private void print(String place) {
        view.print(map, place);
    }

    private void turn(double increase) {
        map.turnMap(increase);
    }

    private void addTruck(String name, int count) {
        for (Element element : elements)
            if (element.getName().equals(name)) {
                map.addElementToTruck(element, count);
                break;
            }
    }

    private void clearTruck() {
        map.clearTruck();
    }

    private void goTruck() {
        map.goTruck();
    }

    public void addHelicopter(String name) {
        for (Element element : elements)
            if (element.getName().equals(name)) {
                map.addElementToHelicopter(element);
                break;
            }
    }

    public void clearHelicopter() {
        map.clearHelicopter();
    }

    public void goHelicopter() {
        map.goHelicopter();
    }
}
