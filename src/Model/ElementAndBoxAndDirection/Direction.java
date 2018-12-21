package Model.ElementAndBoxAndDirection;
import java.util.Random;

public enum  Direction {
    south,
    north,
    east,
    west,
    southEast,
    southWest,
    northEast,
    northWest;

    public static Direction getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
