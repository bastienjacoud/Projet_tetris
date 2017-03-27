package Vue;

import Base.Case;
import Graphique.PlateauController;
import Main.Main;
import Modele.ModeleTetris;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrisController extends PlateauController
{

	@FXML
	protected AnchorPane anchor;

	@FXML
	protected Label lScore;

	protected GridPane gpSuiv1;
	protected GridPane gpSuiv2;
	protected SimpleStringProperty m_score;
	protected SimpleStringProperty[][] m_suivProp;
	protected Rectangle[][] m_suivRect;

	public TetrisController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}

	protected void updateSuiv()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				//On actualise le rectangle
				m_suivRect[i][j].setFill(NewPaint(m_suivProp[i][j].get()));
				if(m_suivProp[i][j].get() == Case._colorVide)
				{
					m_suivRect[i][j].setStrokeWidth(0);
				}
				else
				{
					m_suivRect[i][j].setStrokeWidth(1);
				}
			}
		}
	}

	protected void updateScore()
	{
		/*
		String str = "Score : " + ((ModeleTetris)m_main.getPlateau()).getScore();
		lScore.setText(str);
		*/
		lScore.setText( ((ModeleTetris) (m_main.getPlateau())).getScoreProperty().get() );
	}

	public void setMain(Main main)
	{
		super.setMain(main);

		grille.setLayoutX(100);
		/*
		lScore = new Label();
		lScore.setLayoutX(0);
		lScore.setText("0");
		*/
		//lScore.setText("0");
		m_score = new SimpleStringProperty();
		m_score.bind( ((ModeleTetris) (m_main.getPlateau())).getScoreProperty() );
		m_score.addListener( (ObservableValue<? extends String> obs, String oldV, String newV) -> updateScore() );

		gpSuiv1 = new GridPane();
		gpSuiv1.getChildren().clear();
		gpSuiv1.getColumnConstraints().clear();
		gpSuiv1.getRowConstraints().clear();
		gpSuiv1.setPrefSize(60, 120);

		gpSuiv2 = new GridPane();
		gpSuiv2.getChildren().clear();
		gpSuiv2.getColumnConstraints().clear();
		gpSuiv2.getRowConstraints().clear();
		gpSuiv2.setPrefSize(60, 120);

		m_suivRect = new Rectangle[4][4];
		m_suivProp = new SimpleStringProperty[4][4];

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				//Couleur par defaut
				m_suivRect[i][j] = new Rectangle();
				m_suivRect[i][j].setWidth(30);
				m_suivRect[i][j].setHeight(30);
				m_suivRect[i][j].setFill(Color.TRANSPARENT);
				m_suivRect[i][j].setStroke(Color.BLACK);
				m_suivRect[i][j].setStrokeWidth(0);
				m_suivProp[i][j] = new SimpleStringProperty();
				m_suivProp[i][j].bind(((ModeleTetris)m_main.getPlateau()).getSuivProperty(i, j));
				m_suivProp[i][j].addListener((ObservableValue<? extends String> obs, String oldV, String newV) -> updateSuiv());
				//On ajoute le rectangle à la grille
				if(i < 2)
					gpSuiv1.add(m_suivRect[i][j], j, i);
				else gpSuiv2.add(m_suivRect[i][j], j, i - 2);
			}
		}
		gpSuiv1.setLayoutX(450);
		gpSuiv1.setLayoutY(50);
		gpSuiv2.setLayoutX(450);
		gpSuiv2.setLayoutY(150);
		anchor.getChildren().add(gpSuiv1);
		anchor.getChildren().add(gpSuiv2);
		//anchor.getChildren().add(lScore);
	}
}
