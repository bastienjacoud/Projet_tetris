package Modele;


import java.util.ArrayList;

import Base.Case;
import Base.Piece;
import Base.Plateau;

public class ModeleTetris extends Plateau
{
    protected ThreadTetris m_thread;
    protected Piece[] m_suivantes;
    protected Case[][] m_caseSuiv;
    protected int m_score, m_nbLignes;
    protected Piece m_active;
    protected boolean m_fini;

    //Instancie un plateau de Tetris
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

    //Mutateur permettant d'indiquer les prochaines pieces
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
    	if(m_observer != null)
    		m_observer.updateSuiv();
    }

    //Indique la piece qui sera affectée par les événements
    public void  setActive(Piece p)
    {
    	m_active = p;
    }

    public String getSuiv(int ligne, int colonne)
	{
		if((ligne < 0)
			|| (ligne >= m_caseSuiv.length)
			|| (colonne < 0)
			|| (colonne >= m_caseSuiv[ligne].length))
			return Case._colorVide;
		return m_caseSuiv[ligne][colonne].getCouleur();
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
        	m_observer.updateFin();
    }

    public int getNbLignes()
    {
    	return m_nbLignes;
    }

    protected void SetScore(int s)
    {
    	m_score = s;
    	if(m_observer != null)
    		m_observer.updateScore();
    }

    //Lance la partie
    public void jouer()
    {
        SetScore(0);
        m_fini = false;
        m_thread = new ThreadTetris(this, 500, 100);
    	Piece temp = m_suivantes[0];
    	SetSuiv(0, m_suivantes[1]);
    	SetSuiv(1, newPiece());
    	updateAll();
    	m_thread.start();
    	poserPiece(temp, 0, 3);
    }

    //Passe à la piece suivante
    public boolean Suivante()
    {
    	//Gestion des lignes
    	SupprimerLignes();
    	Piece temp = m_suivantes[0];
    	SetSuiv(0, m_suivantes[1]);
    	SetSuiv(1, newPiece());
    	return poserPiece(temp, 0, 3);
    }

    //Pose une piece sur le plateau
    public boolean poserPiece(Piece p, int px, int py)
    {
    	m_active = p;
        boolean bool = super.poserPiece(p, px, py);
        if(!bool)
        	m_active = null;
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

    //Supprime les lignes pleines et redescend les autres
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
    	if(m_observer != null)
    	m_observer.update();
    }

	//Redescend toutes les pieces au dessus de la ligne supprimmee
    protected void Actualiser(int ligne)
    {
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

    //S'assure que l'affichage a suivi la modification
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

    //Retourne une piece parmi les 7 dispos, avec une couleur aleatoire
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

    //Evénement quand une touche est enfoncée
	public void handleKeyPressed(String keyCode)
	{
		switch(keyCode)
		{
			case "Down":
				m_thread.Accelerer();
				break;
			case "Up":
				if(m_active != null)
					Rotate(m_active, true);
				break;
			case "Right":
				if(m_active != null)
					Move(m_active, 0, 1);
				break;
			case "Left":
				if(m_active != null)
					Move(m_active, 0, -1);
				break;
			default :
				break;
		}
	}

	//Evénement quand une touche est relachée
	public void handleKeyReleased(String keyCode)
	{
		switch(keyCode)
		{
			case "Down":
				m_thread.Normal();
				break;
			default :
				break;
		}
	}

	//Met la totalité de l'affichage à jour
	protected void updateAll()
	{
		if(m_observer != null)
		{
			m_observer.update();
			m_observer.updateScore();
			m_observer.updateSuiv();
		}
	}

	public Piece getActive()
	{
		return m_active;
	}
}
