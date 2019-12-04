package model;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class InsertionSort extends SortingAlgorithm {

	private String name;
	private ArrayList<Transition> transitions;

	public InsertionSort(String name) {
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

	@Override
	public ArrayList<Transition> startSort(Item[] arr) {
		this.transitions = new ArrayList<>();
		for (int i = 1; i < arr.length; i++) {
			int j = i - 1;
			Item key = arr[i];
			int keyIndx = i;

			ParallelTransition pt = new ParallelTransition();

			transitions.add(colorItem(arr, Color.web(SELECTED_FILL), i));

			while (j >= 0 && arr[j].getValue() > key.getValue()) {
				pt.getChildren().add(arr[j].moveX(DX));
				arr[j + 1] = arr[j];
				j--;
			}

			arr[j + 1] = key;

			pt.getChildren().add(key.moveX(DX * (j + 1 - i)));
			transitions.add(pt);
			transitions.add(colorItem(arr, Color.web(START_FILL), j + 1));

		}

		transitions.add(colorItem(Arrays.asList(arr), Color.web(SORTED_FILL)));

		return transitions;

	}

}
