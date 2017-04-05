package Graphique;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import Main.*;

import Base.Case;
import Base.Plateau;

public class PlateauController
{
	protected Main m_main;
	protected int m_h, m_l;
	protected String[][] m_actu;
	protected Rectangle[][] m_rect;

	@FXML
	protected GridPane grille;

	public PlateauController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}

	public void updateFin()
	{
		//
	}

	public void updateScore()
	{
		//
	}

	public void updateSuiv()
	{
		//
	}

	public void updateLevel()
	{
		//
	}

	protected Color NewPaint(String str)
	{
		//if(m_main.m_plateau.Contains(x, y).getSelected())
		switch(str)
		{
			case "1" :
				return Color.RED;
			case "2" :
				return Color.GREEN;
			case "3" :
				return Color.BLUE;
			case "4" :
				return Color.YELLOW;
			case "5" :
				return Color.ORANGE;
			case "6" :
				return Color.PURPLE;
			default :
				return Color.TRANSPARENT;
		}

	}

	protected Color NewFinalPaint(String str, int x, int y)
	{
		if(m_main.getPlateau().Contains(x, y).getSelected())
			return NewPaint(str).darker();
		else
			return NewPaint(str);
	}


	public void update()
	{
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				//Pour chaque cas, on regarde si la couleur a change
				String str;
				if(m_actu[i][j] != (str = m_main.getPlateau().getString(i, j)))
				{
					//Si ca a change, on actualise le rectangle
					m_actu[i][j] = str;
					m_rect[i][j].setFill(NewPaint(str));
					if(str == Case._colorVide)
						m_rect[i][j].setStroke(Color.GREY);
					else m_rect[i][j].setStroke(Color.BLACK);
				}
			}
		}
	}

	protected void Observe(Plateau p)
	{
		p.setObserver(this);
	}

	public void setMain(Main main)
	{
		this.m_main = main;
		Observe(m_main.getPlateau());
		m_h = m_main.getPlateau().getHauteur();
		m_l = m_main.getPlateau().getLargeur();
		grille.getChildren().clear();
		grille.getColumnConstraints().clear();
		grille.getRowConstraints().clear();
		grille.setPrefSize(30*m_h, 30*m_l);

		m_rect = new Rectangle[m_h][m_l];
		m_actu = new String[m_h][m_l];
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				//Couleur par defaut
				m_actu[i][j] = Case._colorVide;
				m_rect[i][j] = new Rectangle();
				m_rect[i][j].setWidth(30);
				m_rect[i][j].setHeight(30);
				m_rect[i][j].setFill(Color.TRANSPARENT);
				m_rect[i][j].setStroke(Color.GREY);
				m_rect[i][j].setStrokeType(StrokeType.INSIDE);
				m_rect[i][j].setStrokeWidth(1);
				//On ajoute le rectangle à la grille
				grille.add(m_rect[i][j], j, i);
			}
		}
	}
}
