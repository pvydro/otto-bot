package com.flizzet.chatwindow;

import javafx.geometry.Insets;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Side menu pane for the chat window.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class ChatSideMenu {

	private BorderPane borderPane;
	private InnerShadow sidePaneShadow;

	/** Default constructor */
	public ChatSideMenu() {

	}

	/** Builds side pane */
	public void buildSideMenu() {
		createBorderPane();
		createSidePaneShadow();								// Inner Shadow

		Rectangle menuBg = new Rectangle(0, 0, 190, 500);
		menuBg.setFill(Color.DARKGRAY);						// Menu sidepane
		menuBg.setEffect(sidePaneShadow);

		borderPane.getChildren().add(menuBg);
	}

	/** Sets up shadow */
	private void createSidePaneShadow() {
		sidePaneShadow = new InnerShadow();
		sidePaneShadow.setOffsetX(-1.0f);
		sidePaneShadow.setOffsetY(0f);
		sidePaneShadow.setChoke(0f);
		sidePaneShadow.setBlurType(BlurType.GAUSSIAN);
	}

	/** Creates Border Pane */
	private void createBorderPane() {
		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(0, 0, 0, 0));
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}



}
