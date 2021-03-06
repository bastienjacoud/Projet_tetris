package Vue;

import Base.Case;
import Graphique.PlateauController;
import Main.Main;
import Modele.ModeleTetris;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	protected Rectangle[][] m_suivRect;
	protected String m_score;

	public TetrisController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}

	public void updateSuiv()
	{
		Platform.runLater(() -> {
			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					String str = m_main.getPlateau().getSuiv(i, j);
					//On actualise le rectangle
					m_suivRect[i][j].setFill(NewPaint(str));
					if(str == Case._colorVide)
						m_suivRect[i][j].setStrokeWidth(0);
					else m_suivRect[i][j].setStrokeWidth(1);
				}
			}
		});
	}

	public void updateFin()
	{
		Platform.runLater(() -> {
			if(((ModeleTetris)m_main.getPlateau()).Fini())
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Fin du jeu !");
				int score = m_main.getPlateau().getScore();
				int ligne = m_main.getPlateau().getNbLignes();
				alert.setHeaderText("Vous avez perdu avec un score de " + score + ".\nVous avez complete " + ligne + " lignes.");
				alert.show();
			}
		});
	}

	public void updateScore()
	{
		Platform.runLater(() -> {
			lScore.setText("Score : " + m_main.getPlateau().getScore());
		});
	}

	public void setMain(Main main)
	{
		super.setMain(main);

		grille.setLayoutX(20);

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
				//On ajoute le rectangle � la grille
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
	}

	public void Lancer()
	{
		m_main.getPlateau().jouer();
	}
}
