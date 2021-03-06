package Base;

import java.util.ArrayList;

import Graphique.PlateauController;

public class Plateau
{

	protected int m_x, m_y;
	protected ArrayList<Piece> m_pieces;
	protected Case[][] m_cases;
    protected PlateauController m_observer;

	public Plateau()
	{
		Init(1, 1);
	}

	public Plateau(int hauteur, int largeur)
	{
		Init(hauteur, largeur);
	}

    public void setObserver(PlateauController o)
    {
    	m_observer = o;
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
		return m_pieces;
	}

	public Case[][] getCases()
	{
		return m_cases;
	}

	public String getString(int ligne, int colonne)
	{
		if((ligne < 0)
			|| (ligne >= m_cases.length)
			|| (colonne < 0)
			|| (colonne >= m_cases[ligne].length))
			return Case._colorVide;
		return m_cases[ligne][colonne].getCouleur();
	}

	public boolean getSelected(int ligne, int colonne)
	{
		if((ligne < 0)
			|| (ligne >= m_cases.length)
			|| (colonne < 0)
			|| (colonne >= m_cases[ligne].length))
			return false;
		return m_cases[ligne][colonne].getSelected();
	}

	public void setHauteur(int x)

	{
		m_x = x;
	}

	public void setLargeur(int y)
	{
		m_y = y;
	}

	public int getLevel()
	{
		return 0;
	}

	public int getScore()
	{
		return 0;
	}

	public int getNbLignes()
	{
		return 0;
	}

	public String getSuiv(int i, int j)
	{
		return Case._colorVide;
	}

	public void setPieces(ArrayList<Piece> pieces)
	{
		for(int i = 0; i < pieces.size(); i++)
			m_pieces.add(pieces.get(i));
	}

	protected void Init(int h, int l)
	{
		m_x = (h > 0) ? h : 1;
		m_y = (l > 0) ? l : 1;
		m_pieces = new ArrayList<Piece>();
		m_cases = new Case[m_x][m_y];
		for(int i = 0; i < m_x; i++)
		{
			for(int j = 0; j < m_y; j++)
				m_cases[i][j] = new Case(Case._colorVide);
		}
	}

	public Piece Contains(int x, int y)
	{
		for(int i=0;i<this.getPieces().size();i++)
		{
			if(this.getPieces().get(i).Contains(x, y))
				return this.getPieces().get(i);
		}
		return new Piece();
	}

	public void selectionne(Piece piece)
	{
		piece.setSelected(true);
		for(int i=0;i<piece.Index().length;i++)
			for(int j=0;j<piece.Index()[0].length;j++)
				m_cases[i][i].setSelected(true);
		m_observer.update();
	}

	public void deselectionne(Piece piece)
	{
		piece.setSelected(false);
		for(int i=0;i<piece.Index().length;i++)
			for(int j=0;j<piece.Index()[0].length;j++)
				m_cases[i][i].setSelected(false);
		m_observer.update();
	}

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
			p.setPos(px, py);
			int[][] index = p.Index();
			for(int i = 0; i < index.length; i++)
				m_cases[index[i][0]][index[i][1]].setCouleur(p.getCouleur());
			if(m_observer != null)
				m_observer.update();
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
		p.setPos(-1, -1);
		boolean test = false;
		for(int i = 0; (i < id.length) && !test; i++)
			test = Occupee(id[i][0], id[i][1]);
		p.setPos(tx, ty);
		return !test;
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
		int[][] oldp = p.Index();
		if(positionPossible(p, px, py))
		{
			p.Move(x, y);
			int[][] newp = p.Index();
			for(int i = 0; i < oldp.length; i++)
			{
				if(!p.Contains(oldp[i][0], oldp[i][1]))
					m_cases[oldp[i][0]][oldp[i][1]].setCouleur(Case._colorVide);
			}
			for(int i = 0; i < newp.length; i++)
				m_cases[newp[i][0]][newp[i][1]].setCouleur(p.getCouleur());
			if(m_observer != null)
				m_observer.update();
			return true;
		}
		return false;
	}

	/* Rotationne la piece
	 * p : piece a rotater
	 * s : false = gauche (anti-horaire), true = droite
	 */
	public boolean Rotate(Piece p, boolean s)
	{
		int[][] oldp = p.Index();
		p.Rotate(s);
		if(positionPossible(p, p.getX(), p.getY()))
		{
			int[][] newp = p.Index();
			for(int i = 0; i < oldp.length; i++)
			{
				if(p.Contains(oldp[i][0], oldp[i][1]))
					m_cases[oldp[i][0]][oldp[i][1]].setCouleur(p.getCouleur());
				else m_cases[oldp[i][0]][oldp[i][1]].setCouleur(Case._colorVide);
			}
			for(int i = 0; i < newp.length; i++)
			{
				if(p.Contains(newp[i][0], newp[i][1]))
					m_cases[newp[i][0]][newp[i][1]].setCouleur(p.getCouleur());
				else m_cases[newp[i][0]][newp[i][1]].setCouleur(Case._colorVide);
			}
			if(m_observer != null)
				m_observer.update();
			return true;
		}
		p.Rotate(!s);
		return false;
	}

	//Retourne les index contenus dans a et/ou dans b mais pas dans les deux
	//On suppose qu'il n'y a jamais deux fois le meme index dans un tableau
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

	protected String getColor(int x, int y)
	{
		for(int i = 0; i < m_pieces.size(); i++)
		{
			if(m_pieces.get(i).Contains(x, y))
				return m_pieces.get(i).getCouleur();
		}
		return Case._colorVide;
	}

	protected Piece getPiece(int x, int y)
	{
		for(int i = 0; i < m_pieces.size(); i++)
		{
			if(m_pieces.get(i).Contains(x, y))
				return m_pieces.get(i);
		}
		return null;
	}

	public void jouer()
	{
		m_observer.update();
	}

	public void handleKeyPressed(String keyCode)
	{
		//
	}

	public void handleKeyReleased(String keyCode)
	{
		//
	}
}
