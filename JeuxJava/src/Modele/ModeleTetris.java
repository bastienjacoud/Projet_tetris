package Modele;


import java.util.ArrayList;

import Base.Case;
import Base.Piece;
import Base.Plateau;
import Vue.TetrisController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

public class ModeleTetris extends Plateau
{
    protected ThreadTetris m_thread;
    protected Piece[] m_suivantes;
    protected Case[][] m_caseSuiv;
    protected int m_score, m_nbLignes;
    protected Piece m_active;
    protected boolean m_fini;
    protected TetrisController m_observer;

    public ModeleTetris()
    {
        super(20, 10);
        m_caseSuiv = new Case[4][4];
        for(int i = 0; i < 4; i++)
        	for(int j = 0; j < 4; j++)
        		m_caseSuiv[i][j] = new Case();
        m_suivantes = new Piece[2];
        SetSuiv(0, newPiece());
        SetSuiv(1, newPiece());
        m_nbLignes = 0;
        m_fini = false;
    }

    public void setObserver(TetrisController o)
    {
    	m_observer = o;
    }

    protected void SetSuiv(int index, Piece p)
    {
    	boolean[][] f = p.getForme();
    	for(int i = 0; i < 2; i++)
    	{
    		for(int j = 0; j < 4; j++)
    		{
    			if((i < f.length) && (j < f[0].length) && f[i][j])
    				m_caseSuiv[(2*index)+i][j].setCouleur(p.getCouleur());
    			else m_caseSuiv[(2*index)+i][j].setCouleur(Case._colorVide);
    		}
    	}
    	m_suivantes[index] = p;
    }

    public void  setActive(Piece p)
    {
    	m_active = p;
    }

    public void ActuScore()
    {
    	SetScore(m_score);
    }

    public StringProperty getSuivProperty(int ligne, int colonne)
	{
		if((ligne < 0)
			|| (ligne >= m_caseSuiv.length)
			|| (colonne < 0)
			|| (colonne >= m_caseSuiv[ligne].length))
			return new SimpleStringProperty(Case._colorVide);
		return m_caseSuiv[ligne][colonne].getCouleurProperty();
	}

    public int getScore()
    {
    	return m_score;
    }

    public boolean Fini()
    {
    	return m_fini;
    }

    public void setFini(boolean b)
    {
    	m_fini = b;
    	if(b)
    	{
        	System.out.println("Fin");
        	m_observer.updateFin();
    	}
    }

    public int getNbLignes()
    {
    	return m_nbLignes;
    }

    protected void SetScore(int s)
    {
    	m_score = s;
		m_observer.updateScore();
    }

    protected void updateScore()
    {
    	m_observer.updateScore();
    }

    public void jouer()
    {
        SetScore(0);
        m_fini = false;
        m_thread = new ThreadTetris(this, 500, 100);
    	Piece temp = m_suivantes[0];
    	SetSuiv(0, m_suivantes[1]);
    	SetSuiv(1, newPiece());
    	m_thread.start();
    	poserPiece(temp, 0, 3);
    }

    public boolean Suivante()
    {
    	//Gestion des lignes
    	SupprimerLignes();
    	Piece temp = m_suivantes[0];
    	SetSuiv(0, m_suivantes[1]);
    	SetSuiv(1, newPiece());
    	return poserPiece(temp, 0, 3);
    }

    public boolean poserPiece(Piece p, int px, int py)
    {
    	m_active = p;
        boolean bool = super.poserPiece(p, px, py);
        if(!bool)
        {
        	m_active = null;
        }
        return bool;
    }

    /* Retourne la liste des index des lignes pleines
     */
    protected ArrayList<Integer> LignesPleines()
    {
    	ArrayList<Integer> tab = new ArrayList<Integer>();
    	for(int i = 0; i < getHauteur(); i++)
    	{
    		boolean test = true;
    		for(int j = 0; (j < getLargeur()) && test; j++)
    			test = (m_cases[i][j].getCouleur() != Case._colorVide);
    		if(test)
    			tab.add(new Integer(i));
    	}
    	return tab;
    }

    protected void SupprimerLignes()
    {
    	ArrayList<Integer> tab = LignesPleines();
    	int modifScore = 1;
    	for(int i = 0; i < tab.size(); i++)
    	{
    		for(int j = 0; j < m_pieces.size(); j++)
    		{
    			m_pieces.get(j).DelLigne(tab.get(i));
    			//Si la derniere ligne de la piece est detruite, on elimine la piece
    			if(m_pieces.get(j).Hauteur() == 0)
    			{
    				m_pieces.remove(m_pieces.get(j));
    				j--;
    			}
    		}
			m_nbLignes++;
    		Actualiser(tab.get(i));
			SetScore(m_score + modifScore);
			modifScore *= 2;
    	}
    	Refresh();
    }

    protected void Actualiser(int ligne)
    {
    	//Redescend toutes les pieces au dessus de la ligne supprimmee
    	boolean test;
    	do
    	{
    		test = false;
    		for(int i = 0; i < m_pieces.size(); i++)
    		{
    			if(m_pieces.get(i).getX() < ligne)
    				test |= Move(m_pieces.get(i), 1, 0);
    		}
    	}while(test);
    }

    protected void Refresh()
    {
    	for(int i = 0; i < getHauteur(); i++)
    	{
    		for(int j = 0; j < getLargeur(); j++)
    		{
    			Piece p = getPiece(i, j);
    			if(p == null)
    				m_cases[i][j].setCouleur(Case._colorVide);
    			else m_cases[i][j].setCouleur(p.getCouleur());
    		}
    	}
    }

    protected static Piece newPiece()
    {
        int r = (int)(Math.random() * 7);
        boolean[][] forme;
        switch(r)
        {
            case 0 :
                //Ligne
                forme = new boolean[][] {{true, true, true, true}};
                break;
            case 1 :
                //Carre
                forme = new boolean[][] {{true, true}, {true, true}};
                break;
            case 2 :
                //L
                forme = new boolean[][] {{true, true, true}, {true, false, false}};
                break;
            case 3 :
            	//L inverse
            	forme = new boolean[][] {{true, true, true}, {false, false, true}};
            	break;
            case 4 :
            	//T
            	forme = new boolean[][] {{false, true, false}, {true, true, true}};
            	break;
            case 5 :
            	//Z
            	forme = new boolean[][] {{true, true, false}, {false, true, true}};
            	break;
            default :
            	//S
            	forme = new boolean[][] {{false, true, true}, {true, true, false}};
            	break;
        }
        return new Piece(forme);
    }

	public void handleKeyPressed(KeyCode keyCode)
	{
		switch(keyCode)
		{
			case DOWN:
				m_thread.Accelerer();
				break;
			case UP:
				if(m_active != null)
					Rotate(m_active, true);
				break;
			case RIGHT:
				if(m_active != null)
					Move(m_active, 0, 1);
				break;
			case LEFT:
				if(m_active != null)
					Move(m_active, 0, -1);
				break;
			default :
				break;
		}
	}

	public Piece getActive()
	{
		return m_active;
	}

	public void handleKeyReleased(KeyCode keyCode)
	{
		switch(keyCode)
		{
			case DOWN:
				m_thread.Normal();
				break;
			default :
				break;
		}
	}
}
