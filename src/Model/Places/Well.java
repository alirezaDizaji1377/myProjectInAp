package Model.Places;

import Model.ElementAndBoxAndDirection.Element;

public class Well extends Element {
    private int current = 0;
    private boolean isInCharging = false;
    private double firstTimeForCharge;
    private double lastTimeForCharge;
    private static double timeLastingForCharging = 5;
    private boolean haveOpportunityForExploitingFromWell = true;// make sure that we can use from well

    {
        volume = 5;
        level = 0;
        name = "well";
        price = 19;
        moneyForUpgrading = 250;
    }

    @Override
    public void move(double finalX, double finalY) {

    }

    public boolean isHaveOpportunityForExploitingFromWell() {
        return haveOpportunityForExploitingFromWell;
    }

    @Override
    public boolean upgrade() {
      if (level < 3) {
          level++;
          timeLastingForCharging -= 1;
          volume += 2;
          price -= 2;
          moneyForUpgrading += 50;
          return true;
      }
      return false;
    }

    public double getFirstTimeForCharge() {
        return firstTimeForCharge;
    }

    public double getLastTimeForCharge() {
        return lastTimeForCharge;
    }

    public static double getTimeLastingForCharging() {
        return timeLastingForCharging;
    }

    public boolean isInCharging() {
        return isInCharging;
    }

    public void chargeWell(double time) {
        if (current == 0 && !isInCharging) {
            isInCharging = true;
            firstTimeForCharge = time;
            lastTimeForCharge = time + timeLastingForCharging;
        }
    }

    public boolean canDisChargeWell () {
        if (current != 0 && haveOpportunityForExploitingFromWell && !isInCharging) {
            current--;
            return true;
        } else
            haveOpportunityForExploitingFromWell = false;
        return false;
    }

    public void checkWell(double time) {
        if (time > lastTimeForCharge && isInCharging) {
            haveOpportunityForExploitingFromWell = true;
            isInCharging = false;
        }
    }

    public int getCurrent() {
        return current;
    }

}

