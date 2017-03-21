package Graphique;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Main.*;

import java.util.ArrayList;

import Base.*;

public class PlateauController
{

	private ObservableList<Node> m_rectListe;
	private Rectangle m_r;

	private Main _main;

	@FXML
	private GridPane grille;

	public PlateauController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		m_rectListe = grille.getChildren();
		for(int i = 0; i < m_rectListe.size(); i++)
		{
			m_r = (Rectangle)m_rectListe.get(i);
		}
	}

	public void setMain(Main main)
	{
		this._main = main;
		grille.getChildren().setAll((Node)main.getPlateau());
	}
}
