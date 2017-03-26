package Vue;

import Main.MainCasseTete;
import Modele.CasseTete;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CasseTeteController implements EventHandler<ActionEvent>
{
	private CasseTete m_jeu;
	private MainCasseTete m_main;

	public CasseTeteController(CasseTete jeu, MainCasseTete main)
	{
		this.m_jeu = jeu;
		this.m_main = main;
	}

	@Override
	public void handle(ActionEvent e)
	{

	}
}
