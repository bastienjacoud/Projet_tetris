package Main;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Main.*;
import Modele.CasseTete;

public class MainCasseTete extends Application {

	private Stage m_primaryStage;
	private BorderPane m_rootLayout;
	public Main m;
	public CasseTete c;

	public MainCasseTete()
	{
		m = new Main();
		c = new CasseTete();
	}

	@Override
	public void start(Stage primaryStage) {
		m_primaryStage = primaryStage;
		m_primaryStage.setTitle("Casse Tête");
		m.start(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
