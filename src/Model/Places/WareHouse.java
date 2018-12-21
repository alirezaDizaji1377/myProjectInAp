package Model.Places;

import Model.Animal.LiveStocks.LiveStock;
import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;

import java.util.HashMap;

public class WareHouse extends Element {
    private int current = 0; // current is 0 because at first we don't have anything in wareHouse
    private HashMap<String, Integer> goods = new HashMap<>();
    private HashMap<String, Integer> liveStocks = new HashMap<>();

    {
        volume = 40;
        level = 0;
        x = 20;//truly is a constant for specifying the x of wareHouse in map
        y = 40;//truly is a constant for specifying the y of wareHouse in map
        moneyForUpgrading = 150;
    }

    @Override
    public void move(double finalX, double finalY) {
        //nothing
    }

    @Override
    public boolean upgrade() {
        if(level < 3) {// maximum level is 3
            level++;
            volume += 80;
            moneyForUpgrading += 50;
            return true;
        }
        return false;
    }

    public HashMap<String, Integer> getGoods() {
        return goods;
    }

    public HashMap<String, Integer> getLiveStocks() {
        return liveStocks;
    }

    public Element giveOneNumberFromAnElement(Element element) {// for give a thing to truck
        if (element instanceof LiveStock) {// if element is a liveStock
            if (liveStocks.containsKey(element.getName()))
                if (liveStocks.get(element.getName()) == 1) {
                    liveStocks.remove(element.getName());
                    return element;
                } else if (liveStocks.get(element.getName()) > 1) {
                    int previousCount = liveStocks.get(element.getName());
                    liveStocks.put(element.getName(), --previousCount);
                    return element;
                }

        } else {// if element is not a liveStock
            if (goods.get(element.getName()) == 1) {
                goods.remove(element.getName());
                current -= element.getVolume();
                return element;
            } else if (goods.get(element.getName()) > 1) {
                int previousCount = goods.get(element.getName());
                goods.put(element.getName(), --previousCount);
                return element;
            }
        }
        return null;
    }

    public void addGoodOrLiveStock(Element element, int count) {
        if (!(element instanceof LiveStock)) {// if it is not a live stock
            if ((volume - current) >= element.getVolume() * count) {
                if (goods.containsKey(element.getName())) {
                    int previousCount = goods.get(element.getName());
                    goods.put(element.getName(), previousCount + count);
                } else {
                    goods.put(element.getName(), count);
                }
            }

        } else {// if it is a live stock
            if (liveStocks.containsKey(element.getName())) {
                int previousCount = liveStocks.get(element.getName());
                goods.put(element.getName(), previousCount + count);
            } else
                goods.put(element.getName(), count);

        }

    }

    public int getCurrent() {
        return current;
    }

    public boolean isHaveThisElement(Element element) {
        for (String element1 : goods.keySet())
            if (element.getName().equals(element1))
                return true;
        return false;
    }

    public HashMap<Element, Integer> giveAllOfAnElement(Element element) {// for give all of a thing to truck
        HashMap<Element, Integer> stuffs = new HashMap<>();
        if (liveStocks.containsKey(element.getName())) {// if element is a liveStock
            int count = liveStocks.get(element.getName());
            liveStocks.remove(element.getName());
            stuffs.put(element, count);
            return stuffs;
        } else if (goods.containsKey(element.getName())) {// if element is not a liveStock
            int count = goods.get(element.getName());
            current -= element.getVolume() * count;
            goods.remove(element.getName());
            stuffs.put(element, count);
            return stuffs;
        }

        return null;
    }

    public boolean isItPossibleForStartingWorkshop(WorkShop workShop) {
        int min = workShop.getMaxNumberOfProducts();

        for (String string : workShop.getNameOfInputProducts()) {
            if (!this.goods.containsKey(string))
                return false;
            if (this.goods.get(string) < min)
                min = this.goods.get(string);
        }

        for (String string : workShop.getNameOfInputProducts())
            //decrease number of all products by min that used by workshop
            goods.put(string, goods.get(string) - min);

        //it means that we have min numbers for starting the workshop;
        workShop.setPossibleNumberOfOutputProducts(min);
        return true;
    }

}
