package Base;

import java.awt.Color;

public class Piece
{
	protected boolean[][] m_forme;
	protected Color m_couleur;
	//Les positions valent -1 si la piece n'est pas posee
	protected int m_x, m_y;
	//Liste des couleurs disponnibles
	protected static Color[] _col = new Color[] {Color.blue, Color.green, Color.yellow, Color.red, Color.orange};

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
		m_x = px;
		m_y = py;
		m_couleur = c;
		setForme(forme);
	}

	public boolean[][] getForme()
	{
		return this.m_forme;
	}

	public Color Couleur()
	{
		return m_couleur;
	}

	/* Indique si la piece occupe la case donnee
	 * x : ligne
	 * y : colonne
	 */
	public boolean Contains(int x, int y)
	{
		if((m_x < 0) || (m_y < 0))
			return false;
		//On adapte les index a la piece
		x -= m_x;
		y -= m_y;
		if((x >= 0)
			&& (x < m_forme.length)
			&& (y >= 0)
			&& (y < m_forme[0].length))
		{
			return m_forme[x][y];
		}
		return false;
	}

	public void setForme(boolean[][] forme)
	{
		m_forme = new boolean[forme.length][forme[0].length];
		for(int i = 0; i < forme.length; i++)
		{
			for(int j = 0; j < forme[0].length; j++)
				m_forme[i][j] = forme[i][j];
		}
	}

	public void setPos(int x, int y)
	{
		if((x < 0) || (y < 0))
			m_x = m_y = -1;
		else
		{
			m_x = x;
			m_y = y;
		}
	}

	public int getX()
	{
		return m_x;
	}

	public int getY()
	{
		return m_y;
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
		if((m_x < 0) || (m_y < 0))
			return new int[0][0];
		int[][] tab = new int[m_forme.length * m_forme[0].length][2];
		int compteur = 0;
		for(int i = 0; i < m_forme.length; i++)
		{
			for(int j = 0; j < m_forme[0].length; j++)
			{
				if(m_forme[i][j])
				{
					tab[compteur][0] = m_x + i;
					tab[compteur][1] = m_y + j;
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
			&&(m_x >= 0)
			&& (m_y >= 0)
			&& ((m_x + x) >= 0)
			&& ((m_y + y) >= 0))
		{
			m_x += x;
			m_y += y;
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
			&& (m_x >= 0)
			&& (m_y >= 0)
			&& ((m_x + x) >= 0)
			&& ((m_y + y) >= 0))
			{
				int[][] tab = new int[m_forme.length * m_forme[0].length][2];
				int compteur = 0;
				//On recupere les index des cases qui seront occupees apres le deplacement
				for(int i = 0; i < m_forme.length; i++)
				{
					for(int j = 0; j < m_forme[0].length; j++)
					{
						if(m_forme[i][j])
						{
							switch(x)
							{
								case 1:
									//Vers le bas
									if((i == (m_forme.length - 1)) || !m_forme[i+1][j])
									{
										tab[compteur][0] = m_x + i + 1;
										tab[compteur][1] = m_y + j;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((i == 0) || !m_forme[i-1][j])
									{
										tab[compteur][0] = m_x + i - 1;
										tab[compteur][1] = m_y + j;
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
									if((j == (m_forme[0].length - 1)) || !m_forme[i][j+1])
									{
										tab[compteur][0] = m_x + i;
										tab[compteur][1] = m_y + j + 1;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((j == 0) || !m_forme[i][j-1])
									{
										tab[compteur][0] = m_x + i;
										tab[compteur][1] = m_y + j - 1;
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
					forme[i][j] = m_forme[m_forme.length - (j + 1)][i];
			}
		}
		else
		{
			//Rotation a gauche (anti-horaire)
			for(int i = 0; i < forme.length; i++)
			{
				for(int j = 0; j < forme[0].length; j++)
					forme[i][j] = m_forme[j][forme.length - (i + 1)];
			}
		}
		m_forme = forme;
	}

	/* Retourne la liste des cases devant etre libres pour effectuer une rotation a 90° de la piece
	 * sens : false = gauche (anti-horaire), true = droite (horaire)
	 */
	public int[][] BesoinRota(boolean sens)
	{
		int[][] tab = new int[m_forme.length * m_forme[0].length][2];
		int compteur = 0;
		Piece p = new Piece(m_forme);
		boolean[][] f = p.getForme();
		for(int i = 0; i < f.length; i++)
		{
			for(int j = 0; j < f[0].length; j++)
			{
				if((i >= m_forme.length) || (j >= m_forme[0].length) || !m_forme[i][j])
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
