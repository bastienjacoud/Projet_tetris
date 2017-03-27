package Vue;

import Base.Case;
import Graphique.PlateauController;
import Main.Main;
import Main.MainCasseTete;
import Modele.CasseTete;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CasseTeteController extends PlateauController
{

	protected SimpleBooleanProperty[][] m_boolProp;
	protected SimpleStringProperty m_lvlProp;
	protected SimpleStringProperty m_scoreProp;

	@FXML
	Label niveau;

	@FXML
	Label score;

	@FXML
	Button reinitialiser;

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
				alert.setTitle("Gagn� !");
				alert.setHeaderText("Vous avez termin� le niveau.");
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

	public void updateLevel()
	{
		niveau.setText( ((CasseTete) (m_main.getPlateau())).getLevelProperty().get() );
	}

	public void updateScore()
	{
		score.setText( ((CasseTete) (m_main.getPlateau())).getScoreProperty().get() );
	}

	public void reinitialise()
	{
		((CasseTete) (m_main.getPlateau())).reinit();
		((CasseTete) (m_main.getPlateau())).initLVL1();
	}

	public void setMain(Main main)
	{
		super.setMain(main);

		//Ajoute un listener sur chaque case pour afficher une s�lection.
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
		reinitialiser.setOnMouseClicked(mouseEvent -> reinitialise());

		m_lvlProp = new SimpleStringProperty();
		m_lvlProp.bind( ((CasseTete) (m_main.getPlateau())).getLevelProperty() );
		m_lvlProp.addListener( (ObservableValue<? extends String> obs, String oldV, String newV) -> updateLevel() );

		m_scoreProp = new SimpleStringProperty();
		m_scoreProp.bind( ((CasseTete) (m_main.getPlateau())).getScoreProperty() );
		m_scoreProp.addListener( (ObservableValue<? extends String> obs, String oldV, String newV) -> updateScore() );


	}



}
