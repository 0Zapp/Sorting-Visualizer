package model;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class BubbleSort extends SortingAlgorithm {

	private String name;
	private boolean swapped;
	private ArrayList<Transition> transitions;

	public BubbleSort(String name) {
		this.name = name;
		this.transitions = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	private ArrayList<Transition> compareItem(Item[] arr, int a, int b) {
		ArrayList<Transition> transitions = new ArrayList<>();

		transitions.add(colorItemInstant(arr, Color.web(SELECTED_FILL), a, b));
		transitions.add(colorItem(arr, Color.web(SELECTED_FILL), a, b));

		if (arr[a].getValue() > arr[b].getValue()) {
			transitions.add(swap(arr, a, b));
			swapped = true;
		}

		transitions.add(colorItem(arr, Color.web(START_FILL), a));
		transitions.add(colorItemInstant(arr, Color.web(SORTED_FILL), b));
		return transitions;
	}

	private void bubbleSort(Item[] arr) {
		for (int i = 0; i < arr.length; i++) {
			swapped = false;
			for (int j = 0; j < arr.length - 1 - i; j++) {
				this.transitions.addAll(compareItem(arr, j, j + 1));
			}

			if (!swapped) {
				break;
			}
		}

	}

	@Override
	public ArrayList<Transition> startSort(Item[] arr) {
		this.transitions = new ArrayList<>();
		bubbleSort(arr);
		this.transitions.add(colorItem(Arrays.asList(arr), Color.web(SORTED_FILL)));
		return this.transitions;

	}

}
