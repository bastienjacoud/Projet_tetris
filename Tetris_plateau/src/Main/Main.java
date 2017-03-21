package Main;

import java.io.IOException;
import java.awt.Color;
import Base.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import Graphique.*;

public class Main extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	public ObservableList<Plateau> _plateau = FXCollections.observableArrayList();

	public Main()
	{
		_plateau.add(new Plateau(20,10));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Plateau");

		initRootLayout();
		showPersonOverview();


	}

	public ObservableList<Plateau> getPlateau()
	{
		return _plateau;
	}


	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Graphique/Fond.fxml"));
            rootLayout = (BorderPane) loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Graphique/Grille.fxml"));
            AnchorPane grille = (AnchorPane) loader.load();


            // Set person overview into the center of root layout.
            rootLayout.setCenter(grille);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	public static void main(String[] args) {
		launch(args);
	}
}
