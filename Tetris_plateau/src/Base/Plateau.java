package Base;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plateau {

	protected int m_x, m_y;
	protected ListProperty<Piece> m_pieces;
	protected Case[][] m_cases;

	public Plateau()
	{
		Init(1, 1);
	}

	public Plateau(int hauteur, int largeur)
	{
		Init(hauteur, largeur);
	}

	public int getHauteur()
	{
		return m_x;
	}

	public int getLargeur()
	{
		return m_y;
	}

	public ArrayList<Piece> getPieces()
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for(int i=0;i<pieces.size();i++)
			pieces.add(m_pieces.get(i));
		return pieces;
	}


	public Case[][] getCases()
	{
		return m_cases;
	}

	public StringProperty getStringProperty(int ligne, int colonne)
	{
		if((ligne < 0)
			|| (ligne >= m_cases.length)
			|| (colonne < 0)
			|| (colonne >= m_cases[ligne].length))
			return new SimpleStringProperty(Case._colorVide);
		return m_cases[ligne][colonne].getCouleurProperty();
	}


	public void setLargeur(int x)
	{
		m_x = x;
	}

	public void setLongueur(int y)
	{
		m_y = y;
	}

	public void setPieces(ArrayList<Piece> pieces)
	{
		for(int i = 0; i < pieces.size(); i++)
			m_pieces.add(pieces.get(i));
	}

	/*
	public void setCases(Case[][] cases)
	{
		m_cases = cases;
	}
	*/

	protected void Init(int h, int l)
	{
		m_x = (h > 0) ? h : 1;
		m_y = (l > 0) ? l : 1;
		m_pieces = new SimpleListProperty<Piece>();
		setPieces(new ArrayList<Piece>());
		m_cases = new Case[m_x][m_y];
		for(int i = 0; i < m_x; i++)
		{
			for(int j = 0; j < m_y; j++)
				m_cases[i][j] = new Case(Case._colorVide);
		}
	}

	/* Retourne l'index de chaque case ayant changee
	 */

	/*
	public int[][] getChange()
	{
		int[][] tab = new int[m_x.get() * m_y.get()][2];
		int compteur = 0;
		for(int i = 0; i < m_x.get(); i++)
		{
			for(int j = 0; j < m_y.get(); j++)
			{
				if(m_change[i][j])
				{
					tab[compteur][0] = i;
					tab[compteur][1] = j;
					compteur++;
				}
			}
		}
		int[][] res = new int[compteur][2];
		for(int i = 0; i < compteur; i++)
		{
			res[i][0] = tab[i][0];
			res[i][1] = tab[i][1];
		}
		return res;
	}

	*/

	/*
	 * Retourne la case qui correspond aux coordonnees indiquees
	 */
	/*
	public Case getCase(int ligne, int colonne)
	{
		return m_cases[ligne][colonne].get();
	}
	*/

	/* Indique si la case aux coordonnees indiquees est occupee
	 */
	public boolean Occupee(int x, int y)
	{
		for(int i = 0; i < m_pieces.size(); i++)
		{
			if(m_pieces.get(i).Contains(x, y))
				return true;
		}
		return false;
	}

	/* Essaye de poser la piece en parametre aux coordonnees indiquees
	 * Retourne 'true' si la piece a pu etre posee, false sinon
	 */
	public boolean poserPiece(Piece p, int px, int py)
	{
		//Pose une piece aux coordonnees voulues
		if(positionPossible(p, px, py))
		{
			//On ajoute la piece au plateau
			m_pieces.add(p);
			return true;
		}
		return false;
	}

	/* Regarde si une position est valide pour une piece
	 */
	public boolean positionPossible(Piece p, int px, int py)
	{
		//Si la piece sort du plateau
		if((px < 0) || ((px + p.Hauteur()) > m_x))
			return false;
		if((py < 0) || ((py + p.Largeur() > m_y)))
			return false;
		//On verifie qu'il n'y ait pas deux cases occupees superposees
		int tx = p.getX(), ty = p.getY();
		p.setPos(px,  py);
		int[][] id = p.Index();
		//On remet la piece a sa position initiale
		p.setPos(tx, ty);
		for(int i = 0; i < id.length; i++)
		{
			if(Occupee(id[i][0], id[i][1]))
				return false;
		}
		return true;
	}

	/* Retourne l'index d'une piece donnee dans la liste.
	 * Retourne -1 s'il n'y a pas de correspondance.
	 */
	public int getIndex(Piece p)
	{
		return m_pieces.indexOf(p);
	}

	/* Bouge une piece du plateau
	 * p : piece a bouger
	 * x : deplacement vertical (<0 haut, 0 nul, >0 bas)
	 * y : deplacement lateral (<0 droite, 0 nul, >0 gauche)
	 * x ou y doit etre nul
	 * Retourne "true" si le depplacement a eu lieu, "false" sinon
	 */
	public boolean Move(Piece p, int x, int y)
	{
		x = (x == 0) ? 0 : (x > 0) ? 1 : -1;
		y = (y == 0) ? 0 : (y > 0) ?1 : -1;
		int px = p.getX() + x;
		int py = p.getY() + y;
		if(positionPossible(p, px, py))
		{
			p.Move(x, y);
			return true;
		}
		return false;
	}

	//Retourne les index contenus dans a ou dans b mais pas dans les deux
	//On suppose qu'il n'y a jamais deux fois le meme index dans un tableau

	/*
	public static int[][] diff(int[][] a, int[][] b)
	{
		int[][] c = new int[a.length + b.length][2];
		int compteur = 0;
		for(int i = 0; i < a.length; i++)
		{
			boolean test = true;
			for(int j = 0; (j < b.length) && test; j++)
			{
				if((a[i][0] == b[j][0]) && (a[i][1] == b[j][1]))
				{
					b[i][0] = b[i][1] = -1;
					test = false;
				}
			}
			if(test)
			{
				//La valeur est dans a et pas dans b
				c[compteur][0] = a[i][0];
				c[compteur][1] = a[i][1];
				compteur++;
			}
		}
		//On recupere les valeurs dans b qui n'etaient pas dans a
		for(int i = 0; i < b.length; i++)
		{
			if((b[i][0] >= 0) && (b[i][1] >= 0))
			{
				//La valeur est dans b et pas dans a
				c[compteur][0] = b[i][0];
				c[compteur][1] = b[i][1];
				compteur++;
			}
		}
		int[][] res = new int[compteur][2];
		for(int i = 0; i < compteur; i++)
		{
			res[i][0] = c[i][0];
			res[i][1] = c[i][1];
		}
		return res;
	}

	*/
}
