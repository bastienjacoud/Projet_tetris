package Graphique;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
		double r = ((100 * ((int)(str.charAt(0) - '0'))) + (10 * ((int)(str.charAt(1) - '0'))) + (int)(str.charAt(2) - '0')) / 255.0;
		double g = ((100 * ((int)(str.charAt(3) - '0'))) + (10 * ((int)(str.charAt(4) - '0'))) + (int)(str.charAt(5) - '0')) / 255.0;
		double b = ((100 * ((int)(str.charAt(6) - '0'))) + (10 * ((int)(str.charAt(7) - '0'))) + (int)(str.charAt(8) - '0')) / 255.0;
		double a = ((100 * ((int)(str.charAt(9) - '0'))) + (10 * ((int)(str.charAt(10) - '0'))) + (int)(str.charAt(11) - '0')) / 255.0;
		return new Color(r, g, b, a);
	}

	protected void update()
	{
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				if(m_actu[i][j] != m_strProp[i][j].get())
				{
					m_actu[i][j] = m_strProp[i][j].get();
					m_rect[i][j].setFill(NewPaint(m_actu[i][j], i, j));
				}
			}
		}
	}

	public void setMain(Main main)
	{
		this.m_main = main;
		m_h = m_main.getPlateau().getHauteur();
		m_l = m_main.getPlateau().getLargeur();
		m_rect = new Rectangle[m_h][m_l];
		m_actu = new String[m_h][m_l];
		m_strProp = new SimpleStringProperty[m_h][m_l];
		for(int i = 0; i < m_h; i++)
		{
			for(int j = 0; j < m_l; j++)
			{
				m_actu[i][j] = "000000000000";
				m_strProp[i][j] = new SimpleStringProperty();
				m_strProp[i][j].bind(m_main.getPlateau().getStringProperty(i, j));
				m_strProp[i][j].addListener((ObservableValue<? extends String> obs, String oldV, String newV) -> update());
				m_rect[i][j] = new Rectangle();
			}
		}
	}
}
