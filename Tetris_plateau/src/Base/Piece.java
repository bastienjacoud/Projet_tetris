package Base;

import javafx.scene.paint.Color;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Piece
{
	protected BooleanProperty[][] m_forme;
	protected ObjectProperty<Color> m_couleur;
	//Les positions valent -1 si la piece n'est pas posee
	protected IntegerProperty m_x, m_y;
	//Liste des couleurs disponnibles
	protected static Color[] _col = new Color[] {Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED, Color.ORANGE};

	//Retourne une des couleurs disponibles autres que _vide
	static public Color Color()
	{
		return _col[(int)(Math.random() * _col.length)];
	}

	public Piece()
	{
		boolean[][] forme = new boolean[][] {{true}};
		Init(forme, -1, -1, Color());
	}

	public Piece(boolean[][] forme)
	{
		Init(forme, -1, -1, Color());
	}

	public Piece(boolean[][] forme, Color couleur)
	{
		Init(forme, -1, -1, couleur);
	}

	public Piece(boolean[][] forme, int px, int py)
	{
		Init(forme, px, py, Color());
	}

	public Piece(boolean[][] forme, int px, int py, Color couleur)
	{
		Init(forme, px, py, couleur);
	}

	protected void Init(boolean[][] forme, int px, int py, Color c)
	{
		m_x.set(px);
		m_y.set(py);
		m_couleur.set(c);
		this.setForme(forme);
	}

	public boolean[][] getForme()
	{

		boolean[][] forme = new boolean[m_forme.length][m_forme[0].length];
		for(int i = 0; i < forme.length; i++)
		{
			for(int j = 0; j < forme[0].length; j++)
				forme[i][j] = m_forme[i][j].get();
		}
		return forme;
	}

	public Color Couleur()
	{
		return m_couleur.get();
	}

	/* Indique si la piece occupe la case donnee
	 * x : ligne
	 * y : colonne
	 */
	public boolean Contains(int x, int y)
	{
		if((m_x.get() < 0) || (m_y.get() < 0))
			return false;
		//On adapte les index a la piece
		x -= m_x.get();
		y -= m_y.get();
		if((x >= 0)
			&& (x < m_forme.length)
			&& (y >= 0)
			&& (y < m_forme[0].length))
		{
			return m_forme[x][y].get();
		}
		return false;
	}

	public void setForme(boolean[][] forme)
	{
		m_forme = new SimpleBooleanProperty[forme.length][forme[0].length];
		for(int i = 0; i < forme.length; i++)
		{
			for(int j = 0; j < forme[0].length; j++)
				m_forme[i][j].set(forme[i][j]);
		}
	}

	public void setPos(int x, int y)
	{
		if((x < 0) || (y < 0))
		{
			m_x.set(-1);
			m_y.set(-1);
		}
		else
		{
			m_x.set(x);
			m_y.set(y);
		}
	}

	public int getX()
	{
		return m_x.get();
	}

	public int getY()
	{
		return m_y.get();
	}

	public int Hauteur()
	{
		return m_forme.length;
	}

	public int Largeur()
	{
		return m_forme[0].length;
	}

	/* Retourne la liste des index des cases occupees par la piece a sa position actuelle
	 */
	public int[][] Index()
	{
		if((m_x.get() < 0) || (m_y.get() < 0))
			return new int[0][0];
		int[][] tab = new int[m_forme.length * m_forme[0].length][2];
		int compteur = 0;
		for(int i = 0; i < m_forme.length; i++)
		{
			for(int j = 0; j < m_forme[0].length; j++)
			{
				if(m_forme[i][j].get())
				{
					tab[compteur][0] = m_x.get() + i;
					tab[compteur][1] = m_y.get() + j;
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

	/* Deplace la piece
	 * x : deplacement vertical (<0 haut, 0 nul, >0 bas)
	 * y : deplacement lateral (<0 droite, 0 nul, >0 gauche)
	 * x ou y doit etre nul
	 */
	public boolean Move(int x, int y)
	{
		x = (x == 0) ? 0 : (x > 0) ? 1 : -1;
		y = (y == 0) ? 0 : (y > 0) ?1 : -1;
		if(((x * y) == 0)
			&&(m_x.get() >= 0)
			&& (m_y.get() >= 0)
			&& ((m_x.get() + x) >= 0)
			&& ((m_y.get() + y) >= 0))
		{
			m_x.set(m_x.get() + x);
			m_y.set(m_y.get() + y);
			return true;
		}
		System.out.println("Erreur dans les index");
		return false;
	}

	/* Retourne la liste des cases devant etre libres pour que la piece bouge
	 * x : deplacement vertical (<0 haut, 0 nul, >0 bas)
	 * y : deplacement lateral (<0 droite, 0 nul, >0 gauche)
	 * x ou y doit etre nul
	 */
	public int[][] BesoinDepl(int x, int y)
	{
		x = (x == 0) ? 0 : (x > 0) ? 1 : -1;
		y = (y == 0) ? 0 : (y > 0) ?1 : -1;
		if(((x * y) == 0)
			&& (m_x.get() >= 0)
			&& (m_y.get() >= 0)
			&& ((m_x.get() + x) >= 0)
			&& ((m_y.get() + y) >= 0))
			{
				int[][] tab = new int[m_forme.length * m_forme[0].length][2];
				int compteur = 0;
				//On recupere les index des cases qui seront occupees apres le deplacement
				for(int i = 0; i < m_forme.length; i++)
				{
					for(int j = 0; j < m_forme[0].length; j++)
					{
						if(m_forme[i][j].get())
						{
							switch(x)
							{
								case 1:
									//Vers le bas
									if((i == (m_forme.length - 1)) || !m_forme[i+1][j].get())
									{
										tab[compteur][0] = m_x.get() + i + 1;
										tab[compteur][1] = m_y.get() + j;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((i == 0) || !m_forme[i-1][j].get())
									{
										tab[compteur][0] = m_x.get() + i - 1;
										tab[compteur][1] = m_y.get() + j;
										compteur++;
									}
									break;
								default:
									break;
							}
							switch(y)
							{
								case 1:
									//Vers la droite
									if((j == (m_forme[0].length - 1)) || !m_forme[i][j+1].get())
									{
										tab[compteur][0] = m_x.get() + i;
										tab[compteur][1] = m_y.get() + j + 1;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((j == 0) || !m_forme[i][j-1].get())
									{
										tab[compteur][0] = m_x.get() + i;
										tab[compteur][1] = m_y.get() + j - 1;
										compteur++;
									}
									break;
								default:
									break;
							}
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
			System.out.println("Erreur dans les index");
			return new int[0][2];
	}

	/* Effectue une rotation a 90° de la piece
	 * sens : false = gauche (anti-horaire), true = droite (horaire)
	 */
	public void Rotate(boolean sens)
	{
		boolean[][] forme = new boolean[m_forme[0].length][m_forme.length];
		if(sens)
		{
			//Rotation a droite (horaire)
			for(int i = 0; i < forme.length; i++)
			{
				for(int j = 0; j < forme[0].length; j++)
					forme[i][j] = m_forme[m_forme.length - (j + 1)][i].get();
			}
		}
		else
		{
			//Rotation a gauche (anti-horaire)
			for(int i = 0; i < forme.length; i++)
			{
				for(int j = 0; j < forme[0].length; j++)
					forme[i][j] = m_forme[j][forme.length - (i + 1)].get();
			}
		}
		this.setForme(forme);
	}

	/* Retourne la liste des cases devant etre libres pour effectuer une rotation a 90° de la piece
	 * sens : false = gauche (anti-horaire), true = droite (horaire)
	 */
	public int[][] BesoinRota(boolean sens)
	{
		int[][] tab = new int[m_forme.length * m_forme[0].length][2];
		int compteur = 0;
		Piece p = new Piece(getForme());
		boolean[][] f = p.getForme();
		for(int i = 0; i < f.length; i++)
		{
			for(int j = 0; j < f[0].length; j++)
			{
				if((i >= m_forme.length) || (j >= m_forme[0].length) || !m_forme[i][j].get())
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
}
