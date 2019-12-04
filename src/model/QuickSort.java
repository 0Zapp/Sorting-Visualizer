package model;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class QuickSort extends SortingAlgorithm {
	private String name;
	private ArrayList<Transition> transitions;

	public QuickSort(String name) {
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

	private void quickSort(Item[] arr, int lo, int hi) {
		if (lo < hi) {
			int q = partition(arr, lo, hi);
			quickSort(arr, lo, q - 1);
			quickSort(arr, q + 1, hi);
		}
	}

	private int partition(Item[] arr, int lo, int hi) {
		int i = lo;

		transitions.add(colorItem(arr, Color.web(PIVOT_FILL), hi));

		for (int j = lo; j < hi; j++) {
			transitions.add(colorItem(arr, Color.web(SELECTED_FILL), j));
			if (arr[j].getValue() < arr[hi].getValue()) {
				transitions.add(swap(arr, i, j));
				transitions.add(colorItem(arr, Color.web(START_FILL), i));
				i++;
			} else {
				transitions.add(colorItem(arr, Color.web(START_FILL), j));
			}
		}
		transitions.add(swap(arr, i, hi));
		transitions.add(colorItem(arr, Color.web(SORTED_FILL), i));

		return i;
	}

	@Override
	public ArrayList<Transition> startSort(Item[] arr) {
		this.transitions = new ArrayList<>();
		quickSort(arr, 0, arr.length - 1);
		transitions.add(colorItem(Arrays.asList(arr), Color.web(SORTED_FILL)));

		return transitions;
	}

}
