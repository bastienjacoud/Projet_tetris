package Main;

import java.io.IOException;


import Modele.ModeleTetris;
import Vue.TetrisController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainTetris extends Main
{

	public MainTetris()
	{
		m_plateau = new ModeleTetris();
	}

	@Override
	public void start(Stage primaryStage)
	{
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Tetris");

		initRootLayout();

		afficherPlateau(m_rootLayout, this, "../Vue/VueTetris.fxml");
		((TetrisController)m_controller).Lancer();
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
