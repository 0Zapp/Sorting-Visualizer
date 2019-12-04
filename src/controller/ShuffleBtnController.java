package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

public class ShuffleBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public ShuffleBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		view.populateDisplay();
		view.TurnOnButtons();
	}

}
