package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.BubbleSort;
import model.DataBase;
import model.HeapSort;
import model.InsertionSort;
import model.MergeSort;
import model.QuickSort;
import model.SelectionSort;
import view.MainView;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		DataBase.getInstance().addAlgorithm(new SelectionSort("Selection Sort"));
		DataBase.getInstance().addAlgorithm(new MergeSort("Merge Sort"));
		DataBase.getInstance().addAlgorithm(new BubbleSort("Bubble Sort"));
		DataBase.getInstance().addAlgorithm(new HeapSort("Heap Sort"));
		DataBase.getInstance().addAlgorithm(new InsertionSort("Insertion Sort"));
		DataBase.getInstance().addAlgorithm(new QuickSort("Quick Sort"));
		MainView.getInstance();
	}

}