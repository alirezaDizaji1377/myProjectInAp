package Model.MapAndCell;

import Model.ElementAndBoxAndDirection.Element;
import Model.Products.Product;
import Model.Products.Forage.Forage;

import java.util.ArrayList;

import Model.Animal.*;
import Model.Animal.LiveStocks.*;
import Model.Animal.WildAnimals.WildAnimal;

public class Cell extends Element {
	private ArrayList<LiveStock> liveStocks = new ArrayList<>();
	private ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
	private ArrayList<Cat> cats = new ArrayList<>();
	private ArrayList<Dog> dogs = new ArrayList<>();
	private ArrayList<Forage> forages = new ArrayList<>();
	private ArrayList<Product> products = new ArrayList<>();
	private double farmTime = 0;

	{
		name = "cell";
	}

	public void addElement(Element element) {
		if (element instanceof LiveStock)
			liveStocks.add((LiveStock) element);
		if (element instanceof WildAnimal)
			wildAnimals.add((WildAnimal) element);
		if (element instanceof Cat)
			cats.add((Cat) element);
		if (element instanceof Dog)
			dogs.add((Dog) element);
		if (element instanceof Product)
			products.add((Product) element);
		if (element instanceof Forage)
			forages.add((Forage) element);

	}

	public void removeElement(Element element) {
		if (element instanceof WildAnimal)
			wildAnimals.remove((WildAnimal) element);
		if (element instanceof Cat)
			cats.remove((Cat) element);
		if (element instanceof Dog)
			dogs.remove((Dog) element);
		if (element instanceof Product)
			products.remove((Product) element);
		if (element instanceof Forage)
			forages.remove((Forage) element);

	}

	public ArrayList<LiveStock> getLiveStocks() {
		return this.liveStocks;
	}

	public ArrayList<WildAnimal> getWildAnimals() {
		return this.wildAnimals;
	}

	public ArrayList<Dog> getDogs() {
		return this.dogs;
	}

	public ArrayList<Cat> getCats() {
		return this.cats;
	}

	public ArrayList<Product> getProducts() {
		return this.products;
	}

	public ArrayList<Forage> getForages() {
		return this.forages;
	}

	public boolean isHaveForage() {
		if (forages.isEmpty())
			return false;
		return true;
	}

	public void removeAllLiveStocks() {
		this.liveStocks.clear();
	}

	public void removeAllCats() {
		this.cats.clear();
	}

	public void removeAllDogs() {
		this.dogs.clear();
	}

	public void removeAllProducts() {
		this.products.clear();
	}

	public void removeAllWildAnimals() {
		this.wildAnimals.clear();
	}

	@Override
	public void move(double finalX, double finalY) {

	}

	public void removeOneForage() {
		forages.remove(0);
	}

	public void removeAllElements() {
		this.products.clear();
		this.cats.clear();
		this.liveStocks.clear();
	}

	@Override
	public boolean upgrade() {
		return false;

	}


}
