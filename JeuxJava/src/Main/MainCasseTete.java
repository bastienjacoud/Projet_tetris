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

	//private Stage m_primaryStage;
	//private BorderPane m_rootLayout;
	//public Main m;
	//public CasseTete c;

	public MainCasseTete()
	{
		super();
		//m = new Main();
		m_plateau = new CasseTete();
	}

	@Override
	public void start(Stage primaryStage)
	{
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Casse Tête");

		initRootLayout();

		afficherPlateau(m_rootLayout, this);
		m_plateau.jouer();
	}

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

	public static void main(String[] args)
	{
		launch(args);
	}
}
