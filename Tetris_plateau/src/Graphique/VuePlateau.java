package Graphique;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VuePlateau extends Application {

	private Stage primaryStage;
	private GridPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Plateau");

		initRootLayout();
		//showPersonOverview();


	}

	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VuePlateau.class.getResource("../Graphique/VuePlateau.fxml"));
            rootLayout = (GridPane) loader.load();
            rootLayout.getChildren().get(10)

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
            loader.setLocation(VuePlateau.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
           // rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	public static void main(String[] args) {
		launch(args);
	}
}
