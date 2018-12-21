package Model.ElementAndBoxAndDirection;

import java.util.HashMap;

public class Box{
    private int volume;
    private int current;
    private boolean isContainAny = false;
    private HashMap<Element, Integer> element;
    private Element content;

    {
        current = 0;
        volume = 10;
    }

    public void addElement(Element elementEntry, int count) {
        if(this.element.size() == 0 &&
                elementEntry.getVolume() * count <= (volume - current)) {
            isContainAny = true;
            content = elementEntry;
            this.element.put(elementEntry, count);
            current += elementEntry.getVolume() * count;// increase the occupied space (current) in Box
        }
    }

    public void removeElement() {
        current = 0;
        element.clear();
        content = null;
        isContainAny = false;
    }

    public HashMap<Element, Integer> getElement() {
        return element;
    }

    public int getCurrent() {
        return current;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isContainAny() {
        return isContainAny;
    }

    public Element getContent() {
        return content;
    }

    public void setCurrent(int count) {
        this.current = count;
    }
}//TODO: it's completed