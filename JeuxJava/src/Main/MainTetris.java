package Main;

import java.io.IOException;

import com.sun.glass.events.KeyEvent;

import Modele.ModeleTetris;
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

		//afficherPlateau(m_rootLayout, this);
		afficherPlateau(m_rootLayout, this, "../Vue/VueTetris.fxml");
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
            /*
            scene.setOnKeyPressed(new EventHandler<? super KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:    goNorth = true; break;
                        case DOWN:  goSouth = true; break;
                        case LEFT:  goWest  = true; break;
                        case RIGHT: goEast  = true; break;
                        case SHIFT: running = true; break;
                    }
                }
            });

            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:    goNorth = false; break;
                        case DOWN:  goSouth = false; break;
                        case LEFT:  goWest  = false; break;
                        case RIGHT: goEast  = false; break;
                        case SHIFT: running = false; break;
                    }
                }

            });*/
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
