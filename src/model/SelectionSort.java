package model;

import java.util.ArrayList;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class SelectionSort extends SortingAlgorithm {

	private String name;

	public SelectionSort(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public ArrayList<Transition> startSort(Item[] arr) {
		ArrayList<Transition> transitions = new ArrayList<>();
		int minIndx;
		int i;
		for (i = 0; i < arr.length - 1; i++) {
			minIndx = i;
			transitions.add(colorItem(arr, Color.web(SortingAlgorithm.NEW_MIN_FILL), minIndx));

			for (int j = i + 1; j < arr.length; j++) {
				transitions.add(colorItem(arr, Color.web(SELECTED_FILL), j));

				if (arr[j].getValue() < arr[minIndx].getValue()) {
					if (minIndx == i) {
						ParallelTransition pt = new ParallelTransition();

						pt.getChildren().addAll(colorItem(arr, Color.web(SortingAlgorithm.MIN_FILL), minIndx),
								colorItem(arr, Color.web(SortingAlgorithm.NEW_MIN_FILL), j));

						transitions.add(pt);
					} else {
						ParallelTransition pt = new ParallelTransition();

						pt.getChildren().addAll(colorItem(arr, Color.web(SortingAlgorithm.START_FILL), minIndx),
								colorItem(arr, Color.web(SortingAlgorithm.NEW_MIN_FILL), j));

						transitions.add(pt);
					}
					minIndx = j;
				} else {
					transitions.add(colorItem(arr, Color.web(SortingAlgorithm.START_FILL), j));
				}
			}

			if (minIndx != i) {
				transitions.add(swap(arr, i, minIndx));
			}

			transitions.add(colorItem(arr, Color.web(SortingAlgorithm.START_FILL), minIndx));
			transitions.add(colorItem(arr, Color.web(SortingAlgorithm.SORTED_FILL), i));
		}

		transitions.add(colorItem(arr, Color.web(SortingAlgorithm.SORTED_FILL), i));

		return transitions;
	}

}
