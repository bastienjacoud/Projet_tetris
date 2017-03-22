package Main;

import java.io.IOException;
import java.awt.Color;
import Base.*;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
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
		//On ajoute notre unique plateau
		_plateau.add(new Plateau(20,10));
	}

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Plateau");

		initRootLayout();
		afficherPlateau();

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
    public void afficherPlateau()
    {
        try
        {
            // Charge l'affichage du plateau.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Graphique/Grille.fxml"));
            AnchorPane grille = (AnchorPane) loader.load();


            // Fixe l'affichage au centre
            rootLayout.setCenter(grille);

            // Donne au controller l'acces au Main.
            PlateauController controller = loader.getController();
            controller.setMain(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



	public static void main(String[] args)
	{
		//launch(args);

		Plateau p = new Plateau(10,5);
		p.getPieces().add(new Piece())
	}
}
