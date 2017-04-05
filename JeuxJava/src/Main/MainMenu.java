package Main;

import java.io.IOException;

import Vue.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu extends Application
{
	protected Stage m_primaryStage;
	protected BorderPane m_rootLayout;
	protected MenuController m_controller;

	protected MainCasseTete m_casseTete;
	protected MainTetris m_tetris;

	public MainMenu()
	{
		//
	}

	public MainTetris getTetris()
	{
		return m_tetris;
	}

	public MainCasseTete getCasseTete()
	{
		return m_casseTete;
	}

	@Override
	public void start(Stage primaryStage)
	{
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Menu");

		initRootLayout();

		afficherMenu(m_rootLayout, this);

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

	public void afficherMenu(BorderPane rootLayout, MainMenu m)
    {
        afficherMenu(rootLayout, m, "../Vue/Menu.fxml");
    }

    public void afficherMenu(BorderPane rootLayout, MainMenu m, String str)
    {
        try
        {
            // Charge l'affichage du plateau.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(str));
            AnchorPane menu = (AnchorPane) loader.load();


            // Donne au controller l'acces au Main.
            m_controller = loader.getController();
            m_controller.setMain(m);

            // Fixe l'affichage au centre
            rootLayout.setCenter(menu);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void lancerCasseTete()
    {
    	m_casseTete = new MainCasseTete();
    	m_casseTete.start(m_primaryStage);
    }

    public void lancerTetris()
    {
    	m_tetris = new MainTetris();
    	m_tetris.start(m_primaryStage);
    }

	public static void main(String[] args)
	{
		launch(args);

	}

}
