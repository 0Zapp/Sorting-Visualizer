package model;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class SortingAlgorithm {
	final public static String START_FILL = "#CC7000";
	final public static String START_STROKE = "#6A0000";
	final public static String NEW_MIN_FILL = "#FFA700";
	final public static String SELECTED_FILL = "#009FFF";
	final public static String SORTED_FILL = "#2FFF00";
	final public static String MIN_FILL = "#FFFFFF";
	final public static String PIVOT_FILL = "#CD4ACE";
	final public static int DX = 30;
	private String name;

	public abstract String getName();

	@Override
	public abstract String toString();

	public Transition swap(Item[] arr, int i, int j) {

		ParallelTransition pt = new ParallelTransition();
		int dxFactor = j - i;

		pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor));

		Item tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return pt;

	}

	ParallelTransition colorItem(List<Item> list, Color color) {
		ParallelTransition pt = new ParallelTransition();

		for (Item c : list) {
			FillTransition ft = new FillTransition();
			ft.setShape(c);
			ft.setToValue(color);
			ft.setDuration(Duration.millis(100));
			pt.getChildren().add(ft);
		}

		return pt;
	}
	
	

	ParallelTransition colorItem(Item[] arr, Color Color, int... a) {
		ParallelTransition pt = new ParallelTransition();

		for (int i = 0; i < a.length; i++) {
			FillTransition ft = new FillTransition();
			ft.setShape(arr[a[i]]);
			ft.setToValue(Color);
			ft.setDuration(Duration.millis(100));
			pt.getChildren().add(ft);
		}
		return pt;
	}
	
	ParallelTransition colorItemInstant(Item[] arr, Color Color, int... a) {
		ParallelTransition pt = new ParallelTransition();

		for (int i = 0; i < a.length; i++) {
			FillTransition ft = new FillTransition();
			ft.setShape(arr[a[i]]);
			ft.setToValue(Color);
			ft.setDuration(Duration.millis(1));
			pt.getChildren().add(ft);
		}
		return pt;
	}

	public abstract ArrayList<Transition> startSort(Item[] items);

}
