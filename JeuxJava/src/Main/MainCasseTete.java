package Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import Main.Main;
import Modele.CasseTete;

public class MainCasseTete extends Main
{

	//constructeur
	public MainCasseTete()
	{
		m_plateau = new CasseTete();
	}

	//cr�e le corps du jeu
	@Override
	public void start(Stage primaryStage)
	{
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Casse T�te");

		initRootLayout();

		afficherPlateau(m_rootLayout, this, "../Vue/VueCasseTete.fxml");

		m_plateau.jouer();

	}

	//affiche le fond du jeu
	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../Vue/Fond.fxml"));
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

	//lance le jeu
	public static void main(String[] args)
	{
		launch(args);
	}
}
