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
	private Stage m_primaryStage;
	private BorderPane m_rootLayout;
	public Plateau m_plateau;

	public Main()
	{
		//On ajoute notre unique plateau
		m_plateau = new Plateau(10,10);
	}

	@Override
	public void start(Stage primaryStage)
	{
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Plateau");

		initRootLayout();

		afficherPlateau();

	}

	public Plateau getPlateau()
	{
		return m_plateau;
	}


	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Graphique/Fond.fxml"));
            m_rootLayout = (BorderPane) loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(m_rootLayout);
            m_primaryStage.setScene(scene);
            m_primaryStage.show();
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
            grille.setPrefSize(30 * m_plateau.getLargeur(), 30 * m_plateau.getHauteur());


            // Donne au controller l'acces au Main.
            PlateauController controller = loader.getController();
            controller.setMain(this);

            // Fixe l'affichage au centre
            m_rootLayout.setCenter(grille);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



	public static void main(String[] args)
	{
		launch(args);

	}
}
