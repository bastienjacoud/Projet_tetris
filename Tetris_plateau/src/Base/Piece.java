package Base;

public class Piece
{

	protected boolean[][] m_forme;
	protected int m_x, m_y;

	public Piece()
	{
		boolean[][] forme = new boolean[][] {{true}};
		Init(forme, 0, 0);
	}

	public Piece(boolean[][] forme)
	{
		Init(forme, 0, 0);
	}

	public Piece(boolean[][] forme, int px, int py)
	{
		Init(forme, px, py);
	}

	protected void Init(boolean[][] forme, int px, int py)
	{
		m_x = Math.max(px, 0);
		m_y = Math.max(py, 0);
		m_forme = forme;
	}

	public boolean[][] getForme()
	{
		return this.m_forme;
	}

	/* Indique si la piece occupe la case donnee
	 * x : ligne
	 * y : colonne
	 */
	public boolean Contains(int x, int y)
	{
		if((x >= m_x)
			&& (x < (m_x + m_forme.length))
			&& (y >= m_y)
			&& (y < (m_y + m_forme[0].length)))
		{
			return m_forme[x][y];
		}
		return false;
	}

	public void setForme(boolean[][] forme)
	{
		this.m_forme = forme;
	}

	public int Hauteur()
	{
		return m_forme.length;
	}

	public int Largeur()
	{
		return m_forme[0].length;
	}

	/* Deplace la piece
	 * x : deplacement vertical
	 * y : deplacement lateral
	 */
	public boolean Move(int x, int y)
	{
		if(((m_x + x) >= 0)
			&& ((m_y + y) >= 0))
		{
			m_x += x;
			m_y += y;
			return true;
		}
		System.out.println("Erreur dans les index");
		return false;
	}

	/* Effectue une rotation a 90� de la piece
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

}
