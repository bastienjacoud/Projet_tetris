package Graphique;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Main.*;

import Base.Plateau;

import java.util.ArrayList;

import Base.Case;

public class PlateauController
{
	protected Main m_main;
	protected int m_h, m_l;
	protected ObservableList<Case> m_cases = FXCollections.observableArrayList();
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

	public void setMain(Main main)
	{
		this.m_main = main;
		m_h = m_main.getPlateau().getHauteur();
		m_l = m_main.getPlateau().getLargeur();

	}
}
