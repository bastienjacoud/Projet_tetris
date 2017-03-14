import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Graphique.*;

public class Main extends Application
{
	private Vue v;

	
	public Main() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		v.start(primaryStage);
	}

	public static void main(String[] args) 
	{
		launch(args);
		// TODO Auto-generated method stub
		//System.out.println(".");
	}
}
