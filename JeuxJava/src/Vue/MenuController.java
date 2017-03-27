package Vue;

import Main.MainCasseTete;
import Main.MainMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

	protected MainMenu m_main;

	@FXML
	Button tetris;

	@FXML
	Button cassetete;

	public MenuController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}

	public void loadTetris()
	{
		m_main.lancerTetris();
	}

	public void loadCasseTete()
	{
		m_main.lancerCasseTete();
	}

	public void setMain(MainMenu main)
	{
		this.m_main = main;
		tetris.setOnMouseClicked(mouseEvent -> loadTetris());
		cassetete.setOnMouseClicked(mouseEvent -> loadCasseTete());
	}

}
