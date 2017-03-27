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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
	protected Stage m_primaryStage;
	protected BorderPane m_rootLayout;
	protected Plateau m_plateau;
	protected PlateauController m_controller;

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


		afficherPlateau(m_rootLayout, this);

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
    public void afficherPlateau(BorderPane rootLayout, Main m)
    {
        afficherPlateau(rootLayout, m, "../Graphique/Grille.fxml");
    }

    public void afficherPlateau(BorderPane rootLayout, Main m, String str)
    {

		m_primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent event)
			{
				m_plateau.handleKeyPressed(event.getCode());
			}

		});

		m_primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent event)
			{
				m_plateau.handleKeyReleased(event.getCode());
			}

		});
        try
        {
            // Charge l'affichage du plateau.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(str));
            AnchorPane grille = (AnchorPane) loader.load();
            grille.setPrefSize(30 * m_plateau.getLargeur(), 30 * m_plateau.getHauteur());


            // Donne au controller l'acces au Main.
            m_controller = loader.getController();
            m_controller.setMain(m);

            // Fixe l'affichage au centre
            rootLayout.setCenter(grille);
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
