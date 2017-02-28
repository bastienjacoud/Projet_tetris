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
		if(positionPossible(p, px, py))
		{
			//On peut poser la piece
			for()
		}
	}
	
	public boolean positionPossible(Piece p, int px, int py)
	{
		//Si la piece sort du plateau
		if((px < 0) || ((px + p.Hauteur()) > m_tab.length))
			return false;
		if((py < 0) || ((py + p.Largeur() > m_tab[0].length)))
			return false;
		//On verifie qu'il n'y ait pas deux cases occupees superposees
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
