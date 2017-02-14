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
	
	public void setForme(Case[][] forme)
	{
		this.m_forme = forme;
	}
	
}
