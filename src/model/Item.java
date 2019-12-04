package model;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Item extends Rectangle {

	private int value;

	public Item(int n) {
		this.value = n;
	}

	public int getValue() {
		return this.value;
	}

	public TranslateTransition moveX(int x) {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(200));
		t.setByX(x);

		return t;
	}

}
