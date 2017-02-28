package Base;

public class Plateau {
	
	protected Case[][] m_tab;

	public Plateau() 
	{
		m_tab = new Case[0][0];
	}
	
	public Plateau(int hauteur, int largeur)
	{
		m_tab = new Case[hauteur][largeur];
		for(int i = 0; i < hauteur; i++)
		{
			for(int j = 0; j < largeur; j++)
				m_tab[i][j] = null;
		}
	}
	
	public Case[][] getTab()
	{
		return this.m_tab;
	}
	
	public void setTab(Case[][] tab)
	{
		this.m_tab = tab;
	}
	
	public void poserPiece(Piece p, int px, int py)
	{
		//Pose une piece aux coordonnees voulues
	}
	
	public boolean positionPossible(Piece p, int px, int py)
	{
		for(int i = 0; (i < p.Hauteur()) && (i < m_tab.length); i++)
		{
			for(int j = 0; (j < p.Largeur()) && (j < m_tab[0].length); j++)
			{
				if((m_tab[i][j] != null) && p.Plein(i, j))
					return false;
			}
		}
		return true;
	}

}
