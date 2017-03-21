package Graphique;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Main.*;

import java.util.ArrayList;

import Base.*;

public class PlateauController
{

	private ArrayList<Node> rectListe;
	private Rectangle r;

	private Main _main;

	@FXML
	private GridPane gp;

	public PlateauController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		/*
		for(int i=0;i<rectListe.size()-1;i++)
		{
			gp.getChildren().


			r = (Rectangle)rectListe.get(i);
			r.setFill(Color.WHITE);
			r.setWidth(45);
			r.setHeight(32);

		}
		*/
	}

	public void setMain(Main main)
	{
		this._main = main;
		gp.getChildren().setAll((Node)main.getPlateau());
	}

}
