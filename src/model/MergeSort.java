package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class MergeSort extends SortingAlgorithm {

	private String name;
	private Item[] tmp;

	@Override
	public String getName() {
		return name;
	}

	public MergeSort(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

	private ArrayList<Transition> merge(Item[] arr, int p, int q, int r) {
		ArrayList<Transition> transitions = new ArrayList<>();

		List<Item> tmpList = new ArrayList<>();

		for (int i = p; i <= r; i++) {
			tmp[i] = arr[i];
			tmpList.add(tmp[i]);
		}

		int i = p;
		int j = q + 1;
		int k = p;

		while (i <= q && j <= r) {
			if (tmp[i].getValue() <= tmp[j].getValue()) {
				arr[k++] = tmp[i++];
			} else {
				arr[k++] = tmp[j++];
			}
		}

		while (i <= q) {
			arr[k++] = tmp[i++];
		}

		while (j <= r) {
			arr[k++] = tmp[j++];
		}

		transitions.add(colorItem(tmpList, Color.web(SortingAlgorithm.SELECTED_FILL)));

		ParallelTransition pt = new ParallelTransition();

		for (int x = p; x <= r; x++) {
			for (int y = p; y <= r; y++) {
				if (tmp[x].equals(arr[y])) {
					pt.getChildren().add(tmp[x].moveX(DX * (y - x)));
				}
			}
		}

		transitions.add(pt);
		transitions.add(colorItem(tmpList, Color.web(SortingAlgorithm.SELECTED_FILL)));

		return transitions;
	}

	private ArrayList<Transition> mergeSort(Item[] arr, int p, int r) {
		ArrayList<Transition> transitions = new ArrayList<>();

		if (p < r) {
			int q = (p + r) / 2;
			transitions.addAll(mergeSort(arr, p, q));
			transitions.addAll(mergeSort(arr, q + 1, r));
			transitions.addAll(merge(arr, p, q, r));
		}

		return transitions;
	}

	@Override
	public ArrayList<Transition> startSort(Item[] arr) {
		ArrayList<Transition> transitions = new ArrayList<>();

		this.tmp = new Item[arr.length];

		transitions.addAll(mergeSort(arr, 0, arr.length - 1));

		transitions.add(colorItem(Arrays.asList(arr), Color.web(SortingAlgorithm.SORTED_FILL)));

		return transitions;
	}

}
