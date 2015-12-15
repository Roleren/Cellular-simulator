package Runner;


import javafx.event.EventHandler;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MouseHandler {
	double mouseAnchorX;
	double mouseAnchorY;
	
	
	DoubleProperty zoom = new SimpleDoubleProperty(400);
	
	public MouseHandler(Painter mainFrame){
		scrollHandler(mainFrame);
		
	}
	public void scrollHandler(Painter mainFrame){
		mainFrame.setOnScroll(new EventHandler<ScrollEvent>(){
			@Override
			public void handle (ScrollEvent event){
				if(event.getDeltaY() > 0)
					mainFrame.scale += 1;
				
				else if(event.getDeltaY() < 0)
					mainFrame.scale -= 1;
				
				
			}
		});
	}
	public void clickHandler(Painter mainFrame){
	mainFrame.setOnMouseClicked(new EventHandler<MouseEvent>(){
		@Override
		public void handle (MouseEvent event){
			if(event.isPrimaryButtonDown())
				mainFrame.scale += 1;
			
			else if(event.isSecondaryButtonDown())
				mainFrame.scale -= 1;
			
			
		}
	});
	}
	
}
