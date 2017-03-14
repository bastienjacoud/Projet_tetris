package Base;

public class Piece {
	
	protected Case[][] m_forme;

	public Piece() 
	{
		// TODO Auto-generated constructor stub
		this.m_forme = null;
	}
	
	public Piece(Case[][] forme)
	{
		this.m_forme = forme;
	}
	
	public Case[][] getForme()
	{
		return this.m_forme;
	}
	
	public boolean Plein(int x, int y)
	{
		return (m_forme[x][y] != null);
	}
	
	public void setForme(Case[][] forme)
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
	
}
