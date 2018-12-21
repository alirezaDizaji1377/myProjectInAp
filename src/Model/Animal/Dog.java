package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;

public class Dog extends Animal {
    private double speed = 3;//3 cell per 0.33 second
    private boolean isKilled = false;

    {
            price = 0;
            volume = 0;
            level = 0;
            name = "dog";
    }

    public boolean getIsKilled() {
        return isKilled;
    }

    @Override
    public void move(double finalX, double finalY) {
       //nothing
    }

    public void move(double finalX, double finalY, String condition) {
        if (condition.equals("killWildAnimal")) {
            //TODO: BFS from x,y to finalX, finalY
        }

        if (condition.equals("randomMove")) {
            //TODO:move randomly
        }
    }

    @Override
    public void upgrade() {
        //we have not any upgrade for dog
    }
}
