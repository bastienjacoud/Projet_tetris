package Modele;

import Base.Case;
import Base.Piece;
import Base.Plateau;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModeleTetris extends Plateau
{
    protected ThreadTetris m_thread;
    protected Piece[] m_suivantes;
    protected Case[][] m_caseSuiv;
    protected int m_score;
    protected SimpleStringProperty m_strScore;

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
        m_strScore = new SimpleStringProperty();
        SetScore(0);
    }

    protected void SetSuiv(int index, Piece p)
    {
    	boolean[][] f = p.getForme();
    	for(int i = 0; i < 2; i++)
    	{
    		for(int j = 0; j < 4; j++)
    		{
    			if((i < f.length) && (j < f[0].length) && f[i][j])
    				m_caseSuiv[(2*index)+i][j].setCouleur(p.Couleur());
    			else m_caseSuiv[(2*index)+i][j].setCouleur(Case._colorVide);
    		}
    	}
    	m_suivantes[index] = p;
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

    public StringProperty getScore()
    {
    	return m_strScore;
    }

    protected void SetScore(int s)
    {
    	m_score = s;
    	m_strScore.set("" + s);
    }

    public void jouer()
    {
        Suivante();
    }

    public boolean Suivante()
    {
    	System.out.println("Suivante");
    	Piece temp = m_suivantes[0];
    	SetSuiv(0, m_suivantes[1]);
    	SetSuiv(1, newPiece());
    	return poserPiece(temp, 0, 3);
    }

    public boolean poserPiece(Piece p, int px, int py)
    {
        boolean bool = super.poserPiece(p, px, py);
        if(bool)
        	m_thread = new ThreadTetris(p, this, 500);
        return bool;
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
}
