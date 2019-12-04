package view;

import java.util.Arrays;
import controller.ShuffleBtnController;
import controller.SortBtnController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DataBase;
import model.Item;
import model.SortingAlgorithm;

public class MainView extends Stage {

	private static MainView instance = null;
	private Scene scene;
	private VBox mainVbox;
	private Pane display;
	private HBox optionsHbox;
	private Button shuffleBtn;
	private Button sortBtn;
	private ComboBox<SortingAlgorithm> sortingAlgorithmCbox;

	public MainView() {

		display = new Pane();
		populateDisplay();

		shuffleBtn = new Button("Shuffle");
		shuffleBtn.setOnAction(new ShuffleBtnController(this));

		sortingAlgorithmCbox = new ComboBox<>();
		populateAlgorithmCbox();

		sortBtn = new Button("Sort");
		sortBtn.setOnAction(new SortBtnController(this));

		optionsHbox = new HBox();
		optionsHbox.setSpacing(10);
		optionsHbox.setAlignment(Pos.CENTER);
		optionsHbox.setPadding(new Insets(10, 10, 10, 10));
		optionsHbox.getChildren().addAll(shuffleBtn, sortingAlgorithmCbox, sortBtn);

		mainVbox = new VBox();
		mainVbox.getChildren().addAll(display, optionsHbox);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(500, 600);

		holder.getChildren().addAll(canvas, mainVbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Sorting Visualizer");
		show();

	}

	public void populateDisplay() {
		Item[] Items = DataBase.getInstance().generateItems();
		display.getChildren().clear();
		for (Item Item : Items) {
			display.getChildren().add(Item);
		}

	}

	private void populateAlgorithmCbox() {
		sortingAlgorithmCbox.getItems().clear();
		sortingAlgorithmCbox.getItems().addAll(DataBase.getInstance().getAlgorithms());
		sortingAlgorithmCbox.getSelectionModel().selectFirst();

	}

	public void populateHbox(ObservableList<Item> Items) {

		display.getChildren().clear();
		display.getChildren().addAll((Node) Arrays.asList(Items));

	}

	public static MainView getInstance() {

		if (instance == null) {
			instance = new MainView();
		}
		return instance;

	}

	public SortingAlgorithm getSortingAlgorithm() {
		return sortingAlgorithmCbox.getSelectionModel().getSelectedItem();
	}

	public Pane getListHbox() {
		return display;
	}

	public void TurnOffButtons() {
		shuffleBtn.setDisable(true);
		sortBtn.setDisable(true);
		
	}

	public void TurnOnButtons() {
		shuffleBtn.setDisable(false);
		sortBtn.setDisable(false);
	}

	public void TurnOnShuffle() {
		shuffleBtn.setDisable(false);

	}
}
