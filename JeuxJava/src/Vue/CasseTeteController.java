package Vue;

import Base.Case;
import Graphique.PlateauController;
import Main.Main;
import Main.MainCasseTete;
import Modele.CasseTete;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CasseTeteController extends PlateauController
{

	protected SimpleBooleanProperty[][] m_boolProp;

	@FXML
	AnchorPane anchor;

	public CasseTeteController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}


	@Override
	protected void update()
	{
		super.update();

		if(((CasseTete)m_main.getPlateau()).finJeu() == true )
		{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Gagné !");
				alert.setHeaderText("Vous avez terminé le niveau.");
				alert.show();

		}

	}


	public void updateSelected()
	{
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				m_rect[i][j].setFill(NewFinalPaint(m_actu[i][j], i, j));
			}
		}
	}

	public void setMain(Main main)
	{
		super.setMain(main);

		//Ajoute un listener sur chaque case pour afficher une sélection.
		m_boolProp = new SimpleBooleanProperty[m_h][m_l];
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				m_boolProp[i][j] = new SimpleBooleanProperty();
				m_boolProp[i][j].bind(m_main.getPlateau().getSelectedProperty(i, j));
				m_boolProp[i][j].addListener((ObservableValue<? extends Boolean> obs, Boolean oldV, Boolean newV) -> updateSelected());
			}
		}
	}



}
