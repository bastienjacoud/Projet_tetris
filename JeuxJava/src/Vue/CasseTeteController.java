package Vue;

import Graphique.PlateauController;
import Main.Main;
import Modele.CasseTete;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CasseTeteController extends PlateauController
{

	protected boolean[][] m_select;
	protected String m_lvl;
	protected String m_score;

	//objets javafx
	@FXML
	Label niveau;

	@FXML
	Label score;

	@FXML
	Button lvlsuivant;

	@FXML
	Button lvlprecedent;

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

	//fonction qui met à jour les modifications du casse tête au niveau du plateau ( en rappelant celle du plateaucontroller)
	@Override
	public void update()
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

	//fonction qui met à jour les pièces sélectionnées(niveau affichage)
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

	//fonction qui met à jour le niveau
	public void updateLevel()
	{
		niveau.setText("" + m_main.getPlateau().getLevel());
	}

	//fonction qui met à jour le score
	public void updateScore()
	{
		score.setText("" + ((CasseTete) (m_main.getPlateau())).getScore() );
	}

	//fonction qui réinitialise le jeu(graphiquement)
	public void reinitialise()
	{
		((CasseTete) (m_main.getPlateau())).reinitialisation();
	}

	//fonction pour passer au niveau suivant
	public void levelsuivant()
	{
		if( !((CasseTete) (m_main.getPlateau())).levelSuivant() )
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Impossible !");
			alert.setHeaderText("Il n'existe pas de niveau suivant.");
			alert.show();
		}
	}

	//fonction pour passer au niveau précédent
	public void levelprecedent()
	{
		if( !((CasseTete) (m_main.getPlateau())).levelPrecedent() )
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Impossible !");
			alert.setHeaderText("Il n'existe pas de niveau precedent.");
			alert.show();
		}
	}

	//fonction qui initialise les objets java fx du jeu et appelle les fonctions pour modifier
	//leurs valeurs quand nécéssaire.
	public void setMain(Main main)
	{
		super.setMain(main);

		//Ajoute un listener sur chaque case pour afficher une sélection.
		m_select = new boolean[m_h][m_l];
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
				m_select[i][j] = m_main.getPlateau().getSelected(i, j);
		}
		reinitialiser.setOnMouseClicked(mouseEvent -> reinitialise());
		lvlsuivant.setOnMouseClicked(mouseEvent -> levelsuivant());
		lvlprecedent.setOnMouseClicked(mouseEvent -> levelprecedent());

		m_lvl = "";

		m_score = "";
	}



}
