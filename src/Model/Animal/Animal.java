package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;
import Model.ElementAndBoxAndDirection.Element;

public abstract class Animal extends Element {
	 double speed;
	 private boolean isKilled = false;

	{
		speed = 1;
		direction = Direction.getRandomDirection();
		x = (int) (Math.random() * (35 - 5 + 1) + 5);
		y = (int) (Math.random() * (35 - 5 + 1) + 5);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setIsKilled(boolean check) {
	    isKilled = check;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void moveRandomly() {

    	switch(this.getDirection()) {
    	case north:
    		this.x -= this.getSpeed();
    		break;
    	case south:
    		this.x += this.getSpeed();
    		break;
    	case east:
    		this.y += this.getSpeed();
    		break;
    	case west:
    		this.y -= this.getSpeed();
    		break;
    	case northEast:
    		this.x -= this.getSpeed();
    		this.y += this.getSpeed();
    		break;
    	case northWest:
    		this.x -= this.getSpeed();
    		this.y -= this.getSpeed();
    		break;
    	case southEast:
    		this.x += this.getSpeed();
    		this.y += this.getSpeed();
    		break;
    	case southWest:
    		this.x += 1;
    		this.y -= 1;
    		break;    
    	}
    }
    
    public void pickUpSuitableDirectionInTheWestBorder() {
    	if(x == 5 && y > 5 && y < 35) {
    		while(direction.equals(Direction.west) || direction.equals(Direction.northWest) 
    				|| direction.equals(Direction.southWest)) 
    			direction = Direction.getRandomDirection();
    	  }	
    }
    
    public void pickUpSuitableDirectionInTheEastBorder() {
    	   if(x == 35 && y > 5 && y < 35) {
       		while(direction.equals(Direction.east) || direction.equals(Direction.northEast) 
       				|| direction.equals(Direction.southEast)) 
       			direction = Direction.getRandomDirection();
       	  }	
       
    }
    
    public void pickUpSuitableDirectionInTheNorthBorder() {
    	if(y == 5 && x > 5 && x < 35) {
    		while(direction.equals(Direction.north) || direction.equals(Direction.northWest) 
    				|| direction.equals(Direction.northEast)) 
    			direction = Direction.getRandomDirection();
    	}
    }
    
    public void pickUpSuitableDirectionInTheSouthBorder() {
    	if(y == 35 && x > 5 && x < 35) {
    		while(direction.equals(Direction.south) || direction.equals(Direction.southEast)
    				|| direction.equals(Direction.southWest))
    			direction = Direction.getRandomDirection();
    	}
    }
    
    public void pickUpSuitableDirectionInTheNorthWestBorder() {
    	if(x == 5 && y == 5) {
    		while(!(direction.equals(Direction.east) || direction.equals(Direction.south)
    				|| direction.equals(Direction.southEast)))
    			direction = Direction.getRandomDirection();
    	}
    		
    }
    
    public void pickUpSuitableDirectionInTheNorthEastBorder() {
     	if(x == 5 && y == 35) {
    		while(!(direction.equals(Direction.west) || direction.equals(Direction.south)
    				|| direction.equals(Direction.southWest)))
    			direction = Direction.getRandomDirection();
    	}
   
    }
    
    public void pickUpSuitableDirectionIntheSouthWestBorder() {
    	if(x == 35 && y == 5) {
    		while(!(direction.equals(Direction.east) || direction.equals(Direction.north)
    				|| direction.equals(Direction.northEast)))
    			direction = Direction.getRandomDirection();
    		
    	}
    }

    public void pickUpSuitableDirectionInTheSouthEastBorder() {
    	if(x == 35 && y == 35) {
    		while(!(direction.equals(Direction.west) || direction.equals(Direction.north)
    				|| direction.equals(Direction.northWest)))
    			direction = Direction.getRandomDirection();
    		
    	}
    }
    
    public void changeDirectionByKnowingCurrentPostition() {
       this.pickUpSuitableDirectionInTheWestBorder();
       this.pickUpSuitableDirectionInTheEastBorder();
       this.pickUpSuitableDirectionInTheNorthBorder();
       this.pickUpSuitableDirectionInTheSouthBorder();
       this.pickUpSuitableDirectionInTheNorthEastBorder();
       this.pickUpSuitableDirectionInTheNorthWestBorder();
       this.pickUpSuitableDirectionInTheSouthEastBorder();
       this.pickUpSuitableDirectionIntheSouthWestBorder();
    }


	@Override
	public boolean upgrade() {
		return false;
	}
}
