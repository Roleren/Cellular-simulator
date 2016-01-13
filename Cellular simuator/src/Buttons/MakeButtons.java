package Buttons;

import Atoms.atom;
import Camera.CameraPerspectives;
import Runner.GUI;
import Runner.Simulator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MakeButtons {
	Button restart, pause, rotate, exit;
	GridPane grid = new GridPane();

	public void makeButton(BorderPane border, Simulator simulator, GUI gui) {

		// Buttons
		restart = new Button("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println("Restart");
				gui.restart();
				gui.setPaused(false);
				gui.setStep(0);
			}
		});

		pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (!gui.isPaused()) {
					pause.setText("fortsett");
					gui.getTimer().stop();
					gui.setPaused(true);
				} else {
					pause.setText("Pause");
					gui.getTimer().start();
					gui.setPaused(false);
				}
			}
		});

		rotate = new Button("Rotate");
		rotate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				CameraPerspectives.rotateAroundYAxis(gui, gui.getMainFrame());

			}
		});

		exit = new Button("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println("Exit");
				Platform.exit();
			}
		});
		// creating object buttons
		GridPane grid1 = new GridPane();
		int i = 1;
		Button currentButton;
		for (atom currentObject : PossibleObjects.createListOfObjects()) {
			currentButton = new Button(currentObject.toString());

			currentButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					createObject(currentObject, simulator);
				}

			});
			grid1.add(currentButton, 0, i);
			i++;
		}
		// Add to parrent scene
		grid.add(restart, 1, 0);
		grid.add(pause, 2, 0);
		grid.add(rotate, 3, 0);
		grid.add(exit, 4, 0);
		border.setTop(grid);
		border.setLeft(grid1);
	}

	/**
	 * Creates the buttons on screen for making objects
	 * 
	 * @param currentAtom
	 */
	private void createObject(atom currentObject, Simulator simulator) {
		if (currentObject.type.equals("atom"))
			ButtonsForAtoms.createAtom(currentObject, simulator);
		else if (currentObject.type.equals("molecule"))
			ButtonsForMolecules.createAtom(currentObject, simulator);
		else if (currentObject.type.equals("molecular environment"))
			ButtonsForMolecularEnvironments.createAtom(currentObject, simulator);
	}
}
