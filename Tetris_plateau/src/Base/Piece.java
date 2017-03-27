package Base;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Piece
{
	protected boolean[][] m_forme;
	protected StringProperty m_couleur;
	//Les positions valent -1 si la piece n'est pas posee
	protected int m_x, m_y;

	public boolean m_selected;
	//Liste des couleurs disponnibles
	//Format decimal "RRRGGGBBBAAA"
	protected static String[] _col = new String[] {"1", "2", "3", "4", "5", "6"};

	//Retourne une des couleurs disponibles autres que _vide
	static public String Color()
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

	public Piece(boolean[][] forme, String couleur)
	{
		Init(forme, -1, -1, couleur);
	}

	public Piece(boolean[][] forme, int px, int py)
	{
		Init(forme, px, py, Color());
	}

	public Piece(boolean[][] forme, int px, int py, String couleur)
	{
		Init(forme, px, py, couleur);
	}

	protected void Init(boolean[][] forme, int px, int py, String c)
	{
		m_x = px;
		m_y = py;
		m_couleur = new SimpleStringProperty(c);
		this.setForme(forme);
		this.setSelected(false);
	}

	public boolean[][] getForme()
	{

		boolean[][] forme = new boolean[m_forme.length][m_forme[0].length];
		for(int i = 0; i < forme.length; i++)
		{
			for(int j = 0; j < forme[0].length; j++)
				forme[i][j] = m_forme[i][j];
		}
		return forme;
	}

	public String getCouleur()
	{
		return m_couleur.get();
	}

	public void setCouleur(String couleur)
	{
		m_couleur.set(couleur);
	}

	public boolean getSelected()
	{
		return m_selected;
	}

	public void setSelected(boolean selected)
	{
		m_selected = selected;
	}

	public boolean Contains(int ligne)
	{
		return ((ligne >= getX()) && (ligne < (getX() + Hauteur())));
	}

	/* Indique si la piece occupe la case donnee
	 * x : ligne
	 * y : colonne
	 */
	public boolean Contains(int x, int y)
	{
		if((getX() < 0) || (getY() < 0))
			return false;
		//On adapte les index a la piece
		x -= getX();
		y -= getY();
		if((x >= 0)
			&& (x < m_forme.length)
			&& (y >= 0)
			&& (y < m_forme[0].length))
		{
			return m_forme[x][y];
		}
		return false;
	}

	public void setForme(boolean[][] f)
	{
		boolean[][] forme = Ajuster(f);
		m_forme = new boolean[forme.length][forme[0].length];
		for(int i = 0; i < forme.length; i++)
		{
			for(int j = 0; j < forme[0].length; j++)
			{
				m_forme[i][j] = forme[i][j];
			}

		}
	}

	public void setPos(int x, int y)
	{
		if((x < 0) || (y < 0))
		{
			m_x = -1;
			m_y = -1;
		}
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
		if((getX() < 0) || (getY() < 0))
			return new int[0][0];
		int[][] tab = new int[m_forme.length * m_forme[0].length][2];
		int compteur = 0;
		for(int i = 0; i < m_forme.length; i++)
		{
			for(int j = 0; j < m_forme[0].length; j++)
			{
				if(m_forme[i][j])
				{
					tab[compteur][0] = getX() + i;
					tab[compteur][1] = getY() + j;
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
			&& (getX() >= 0)
			&& (getY() >= 0)
			&& ((getX() + x) >= 0)
			&& ((getY() + y) >= 0))
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
			&& (getX() >= 0)
			&& (getY() >= 0)
			&& ((getX() + x) >= 0)
			&& ((getY() + y) >= 0))
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
										tab[compteur][0] = getX() + i + 1;
										tab[compteur][1] = getY() + j;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((i == 0) || !m_forme[i-1][j])
									{
										tab[compteur][0] = getX() + i - 1;
										tab[compteur][1] = getY() + j;
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
										tab[compteur][0] = getX() + i;
										tab[compteur][1] = getY() + j + 1;
										compteur++;
									}
									break;
								case -1:
									//Vers le haut
									if((j == 0) || !m_forme[i][j-1])
									{
										tab[compteur][0] = getX() + i;
										tab[compteur][1] = getY() + j - 1;
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
		this.setForme(forme);
	}

	public void DelLigne(int ligne)
	{
		/*
		ArrayList<Piece> np = new ArrayList<Piece>();
		if((ligne >= getX())
			&& (ligne < (getX() + Hauteur())))
		{
			int id = ligne - getX();
			//On cree la piece superieure
			if(id != 0)
			{
				boolean[][] f = new boolean[id][Largeur()];
				for(int i = 0; i < id; i++)
				{
					for(int j = 0; j < Largeur(); j++)
						f[i][j] = m_forme[i][j];
				}
				Piece p = new Piece(f, getX(), getY(), getCouleur());
				np.add(p);
			}
			if(id != (Hauteur() - 1))
			{
				boolean[][] f = new boolean[Hauteur() - (id + 1)][Largeur()];
				for(int i = id + 1; i < Hauteur(); i++)
				{
					for(int j = 0; j < Largeur(); j++)
						f[i - (id + 1)][j] = m_forme[i][j];
				}
				Piece p = new Piece(f, ligne + 1, getY(), getCouleur());
				np.add(p);
			}
		}
		else np.add(this);
		return np;
		*/
		id = ligne - getX();
		if((ligne >= getX()) && (ligne < (getX() + Hauteur())))
		{
			boolean[][] f = new boolean[Hauteur() - 1][Largeur()];
			for(int i1 = 0; i2 = 0; i1 < Hauteur(); i1++, i2++)
			{
				if(i1 == id)
					i1++;
			}
		}
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

	/* Redessine la piece pour ne pas avoir de colonne/ligne exterieure vide.
	 */
	protected boolean[][] Ajuster(boolean[][] forme)
	{
		//On teste la premiere colonne
		boolean test = false;
		boolean reroll = false;
		for(int i = 0; (i < forme.length) && !test; i++)
			test = forme[i][0];
		if(!test)
		{
			reroll = true;
			//On enleve la colonne vide
			boolean[][] f = new boolean[forme.length][forme[0].length - 1];
			for(int i = 0; i < forme.length; i++)
			{
				for(int j = 1; j < forme[0].length; j++)
					f[i][j - 1] = forme[i][j];
			}
			if(m_y >= 0)
				m_y++;
			return Ajuster(f);
		}

		//On teste la derniere colonne
		test = false;
		for(int i = 0; (i < forme.length) && !test; i++)
			test = forme[i][forme[0].length - 1];
		if(!test)
		{
			//On enleve la colonne vide
			reroll = true;
			boolean[][] f = new boolean[forme.length][forme[0].length - 1];
			for(int i = 0; i < forme.length; i++)
			{
				for(int j = 0; j < (forme[0].length - 1); j++)
					f[i][j] = forme[i][j];
			}
			return Ajuster(f);
		}

		//On teste la premiere ligne
		test = false;
		for(int i = 0; (i < forme[0].length) && !test; i++)
			test = forme[0][i];
		if(!test)
		{
			//On enleve la ligne vide
			reroll = true;
			boolean[][] f = new boolean[forme.length - 1][forme[0].length];
			for(int i = 1; i < forme.length; i++)
			{
				for(int j = 0; j < forme[0].length; j++)
					f[i - 1][j] = forme[i][j];
			}
			if(m_x >= 0)
				m_x++;
			return Ajuster(f);
		}

		//On teste la derniere ligne
		test = false;
		for(int i = 0; (i < forme[0].length) && !test; i++)
			test = forme[forme.length - 1][i];
		if(!test)
		{
			//On enleve la ligne vide
			reroll = true;
			boolean[][] f = new boolean[forme.length - 1][forme[0].length];
			for(int i = 0; i < ((forme.length) - 1); i++)
			{
				for(int j = 0; j < forme[0].length; j++)
					f[i][j] = forme[i][j];
			}
			return Ajuster(f);
		}

		return forme;
	}
}
