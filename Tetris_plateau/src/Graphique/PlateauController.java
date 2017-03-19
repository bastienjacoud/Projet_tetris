package Graphique;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauController
{

	private ObservableList<Node> rectListe;
	private Rectangle r;

	@FXML
	private GridPane gp;

	@FXML
    private void initialize()
	{

		rectListe = gp.getChildren();

		for(int i=0;i<rectListe.size()-1;i++)
		{
			r = (Rectangle)rectListe.get(i);
			r.setFill(Color.WHITE);
			r.autosize();
		}

	}

}
