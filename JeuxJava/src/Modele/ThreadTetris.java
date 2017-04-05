package Modele;

import Base.Piece;

public class ThreadTetris extends Thread
{
	protected Piece m_piece;
	protected ModeleTetris m_modele;
	protected double m_normal, m_accelere, m_actu;

	public ThreadTetris(ModeleTetris m, double t1, double t2)
	{
		m_modele = m;
		m_normal = t1;
		m_accelere = t2;
		m_actu = m_normal;
	}

	protected void Pause()
	{
		try
		{
			ThreadTetris.sleep((int)m_actu);
		}
		catch(Exception e)
		{
			//catch
		}
	}

	public void Accelerer()
	{
		m_actu = m_accelere;
	}

	public void Normal()
	{
		m_actu = m_normal;
	}

	public boolean PieceOK(Piece p)
	{
		return (p == null) || (p.getX() < 0) || (p.getY() < 0);
	}

	public void Action()
	{
		int rep = 0;
		do
		{
			if(m_modele.getActive() != null)
				rep++;
			Pause();
		}while(PieceOK(m_modele.getActive()) || (m_modele.Move(m_modele.getActive(), 1, 0)));
		m_modele.setActive(null);
		Pause();
		if((rep > 1) && m_modele.Suivante())
			Action();
		else m_modele.setFini(true);
	}

	public void run()
	{
		Action();
	}

}
