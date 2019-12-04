package model;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class HeapSort extends SortingAlgorithm {

	private String name;
	private ArrayList<Transition> transitions;

	public HeapSort(String name) {
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

	private void heapSort(Item[] arr) {

		transitions.add(colorItem(selectSubTree(arr, arr.length), Color.web(SELECTED_FILL)));
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		transitions.add(colorItem(selectSubTree(arr, arr.length), Color.web(START_FILL)));

		for (int i = arr.length - 1; i > 0; i--) {
			transitions.add(colorItem(arr, Color.web(SORTED_FILL), 0));
			transitions.add(swap(arr, 0, i));
			transitions.add(colorItem(arr, Color.web(SORTED_FILL), i));
			transitions.add(colorItem(selectSubTree(arr, i), Color.web(SELECTED_FILL)));
			heapify(arr, 0, i);
			transitions.add(colorItem(selectSubTree(arr, i), Color.web(START_FILL)));
		}
	}

	private ArrayList<Item> selectSubTree(Item[] arr, int n) {
		ArrayList<Item> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(arr[i]);
		}

		return list;
	}

	private void heapify(Item[] arr, int i, int n) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max = i;

		if (left < n && arr[max].getValue() < arr[left].getValue()) {
			max = left;
		}

		if (right < n && arr[max].getValue() < arr[right].getValue()) {
			max = right;
		}

		if (max != i) {
			transitions.add(swap(arr, i, max));
			heapify(arr, max, n);
		}

	}

	@Override
	public ArrayList<Transition> startSort(Item[] items) {
		this.transitions = new ArrayList<>();

		heapSort(items);

		transitions.add(colorItem(Arrays.asList(items), Color.web(SORTED_FILL)));

		return transitions;
	}

}
