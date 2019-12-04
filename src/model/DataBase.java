package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Paint;

public class DataBase {

	private static DataBase instance = null;
	private Item[] Items;
	private ArrayList<SortingAlgorithm> algorithms;

	public DataBase() {
		Items = new Item[20];
		algorithms = new ArrayList<>();
	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}

		return instance;

	}

	public void addAlgorithm(SortingAlgorithm s) {
		algorithms.add(s);
	}

	public ArrayList<SortingAlgorithm> getAlgorithms() {
		return algorithms;
	}

	public Item[] getItems() {
		return Items;
	}

	public Item[] generateItems() {
		Items = new Item[20];

		for (int i = 1; i <= 20; i++) {
			Item Item = new Item(i * 25);
			Item.setWidth(25);
			Item.setHeight(25 * i);
			Item.setStroke(Paint.valueOf(SortingAlgorithm.START_STROKE));

			Item.setFill(Paint.valueOf(SortingAlgorithm.START_FILL));
			Items[i - 1] = Item;
		}
		shuffleArray(Items);

		for (int i = 0; i < 20; i++) {
			Items[i].setX(i * 30);
		}

		return Items;
	}

	static void shuffleArray(Item[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Item a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

}
