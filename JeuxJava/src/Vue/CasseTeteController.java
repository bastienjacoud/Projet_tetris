package Vue;

import Base.Case;
import Graphique.PlateauController;
import Main.Main;
import Main.MainCasseTete;
import Modele.CasseTete;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CasseTeteController extends PlateauController
{

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

	/*
	@Override
	protected void update()
	{
		//System.out.println("Ustart");
		super.update();

		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				//Pour chaque cas, on regarde si la couleur a change
				if(m_actu[i][j] != m_strProp[i][j].get())
				{
					//Si ca a change, on actualise le rectangle
					m_actu[i][j] = m_strProp[i][j].get();
					m_rect[i][j].setFill(NewPaint(m_actu[i][j], i, j));
					if(m_actu[i][j] == Case._colorVide)
						m_rect[i][j].setStroke(Color.GREY);
					else m_rect[i][j].setStroke(Color.BLACK);

					if(((CasseTete)m_main.getPlateau()).getPieceSortable() != null)
					{
						if( ((CasseTete)m_main.getPlateau()).getPieceSortable().Contains(i, j) )
							m_rect[i][j].setFill(Color.BLUE);
					}


				}
			}
		}


		if(((CasseTete)m_main.getPlateau()).finJeu() == true )
		{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Gagné !");
				alert.setHeaderText("Vous avez terminé le niveau.");
				alert.show();

		}

	}
	*/

	public void setMain(Main main)
	{
		super.setMain(main);
	}



}
