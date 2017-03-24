package Graphique;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import Main.*;

import Base.Plateau;

import java.util.ArrayList;

import Base.Case;

public class PlateauController
{
	protected Main m_main;
	protected int m_h, m_l;
	//protected ObservableList<Case> m_cases = FXCollections.observableArrayList();
	protected SimpleStringProperty[][] m_strProp;
	protected String[][] m_actu;
	protected Rectangle[][] m_rect;

	@FXML
	private GridPane grille;

	public PlateauController()
	{
		//
	}

	@FXML
    private void initialize()
	{
		//
	}

	protected Color NewPaint(String str, int x, int y)
	{
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

	protected void update()
	{
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
					{
						m_rect[i][j].setStroke(Color.GREY);
						m_rect[i][j].setStrokeType(StrokeType.CENTERED);
					}
					else
					{
						m_rect[i][j].setStroke(Color.BLACK);
						m_rect[i][j].setStrokeType(StrokeType.INSIDE);
					}
				}
			}
		}
	}

	public void setMain(Main main)
	{
		this.m_main = main;
		m_h = m_main.getPlateau().getHauteur();
		m_l = m_main.getPlateau().getLargeur();
		int maxi = 30 * Math.max(m_h, m_l);
		grille.setPrefSize(maxi, maxi);
		m_rect = new Rectangle[m_h][m_l];
		m_actu = new String[m_h][m_l];
		m_strProp = new SimpleStringProperty[m_h][m_l];
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				//Couleur par defaut
				m_actu[i][j] = Case._colorVide;
				m_rect[i][j] = new Rectangle();
				m_rect[i][j].setWidth(30);
				m_rect[i][j].setHeight(30);
				if((i < 3) || (((2*i + j) % 7) == 4) || (((2*i + j) % 7) == 1))
				{
					m_rect[i][j].setFill(Color.TRANSPARENT);
					m_rect[i][j].setStroke(Color.GREY);
					m_rect[i][j].setStrokeType(StrokeType.INSIDE);
					m_rect[i][j].setStrokeWidth(1);
				}
				else
				{
					m_rect[i][j].setFill(Color.BLUE);
					m_rect[i][j].setStroke(Color.BLACK);
					m_rect[i][j].setStrokeType(StrokeType.INSIDE);
					m_rect[i][j].setStrokeWidth(1);
				}
				m_strProp[i][j] = new SimpleStringProperty();
				m_strProp[i][j].bind(m_main.getPlateau().getStringProperty(i, j));
				m_strProp[i][j].addListener((ObservableValue<? extends String> obs, String oldV, String newV) -> update());
				//On ajoute le rectangle � la grille
				grille.add(m_rect[i][j], j, i);
			}
		}

	}
}
