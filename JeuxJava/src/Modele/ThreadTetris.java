package Modele;

import Base.Piece;

public class ThreadTetris extends Thread
{
	protected Piece m_piece;
	protected ModeleTetris m_modele;
	protected double m_tempo;

	public ThreadTetris(Piece p, ModeleTetris m, double t)
	{
		m_piece = p;
		m_modele = m;
		m_tempo = t;
		this.start();
	}

	public void run()
	{
		int rep = 0;
		do
		{
			rep++;
			try
			{
				ThreadTetris.sleep((int)m_tempo);
			}
			catch(Exception e)
			{
				//catch
			}
		}while(m_modele.Move(m_piece, 1, 0));
		try
		{
			ThreadTetris.sleep((int)m_tempo);
		}
		catch(Exception e)
		{
			System.out.println("Ereur ThreadTetris");
		}
		System.out.println(m_piece.getX() + ", " + m_piece.getY());
		if(rep > 1)
			m_modele.Suivante();
	}

}
