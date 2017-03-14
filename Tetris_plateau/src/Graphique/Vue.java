package Graphique;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Vue extends Application{
	
	Text affichage;
	
	@Override
	public void start(Stage primaryStage)
	{
		BorderPane border = new BorderPane();
		
		GridPane gPane = new GridPane();
		
		int column = 0;
        int row = 0;
        
        /*
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        */
        
        
        
        final Text t = new Text("");
        t.setWrappingWidth(300);
        gPane.add(t, column++, row);
        t.setTextAlignment(TextAlignment.CENTER);
		
		gPane.setGridLinesVisible(true);
		
		border.setCenter(gPane);
		
		Scene scene = new Scene(border, Color.LIGHTBLUE);
		
		primaryStage.setTitle("Plateau");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) 
	{
        launch(args);
    }

}
