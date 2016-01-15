package Buttons;

import Runner.GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;

public class TopMenu {

	public TopMenu(GridPane grid, GUI gui) {
		setMainMenu(grid, gui);

	}

	private void setMainMenu(GridPane grid, GUI gui) {
		// Menus
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuSettings = new Menu("Settings");

		// Add to scene
		menuBar.getMenus().addAll(menuFile, menuSettings);
		grid.getChildren().add(0, menuBar);

		setEventHandlers(gui, menuSettings);
	}

	private void setEventHandlers(GUI gui, Menu menuSettings) {
		// Covalent bonds
		CheckMenuItem menuShowCovalentBonds = new CheckMenuItem("show covalent bonds");
		menuShowCovalentBonds.selectedProperty().set(true);
		
		menuShowCovalentBonds.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				gui.getMainFrame().switchCovalentBondVisibility();
				System.out.println("hei");
			}
		});

		// Affinity bonds
		CheckMenuItem menuShowAffinityBonds = new CheckMenuItem("show affinity bonds");
		menuShowAffinityBonds.selectedProperty().set(true);
		menuShowAffinityBonds.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				gui.getMainFrame().switchAffinityBondVisibility();
				
			}
		});

		// Add to menu
		menuSettings.getItems().addAll(menuShowCovalentBonds, menuShowAffinityBonds);
	}

}
