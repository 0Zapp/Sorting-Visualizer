package controller;

import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBase;
import model.SortingAlgorithm;
import view.MainView;

public class SortBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public SortBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		view.TurnOffButtons();
		SortingAlgorithm sort = view.getSortingAlgorithm();
		SequentialTransition sq = new SequentialTransition();
		sq.getChildren().addAll(sort.startSort(DataBase.getInstance().getItems()));
		sq.setOnFinished(e -> {
			view.TurnOnShuffle();
		});
		sq.play();

	}

}
