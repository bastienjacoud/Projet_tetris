package Modele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Base.Piece;

public class ThreadTetris extends Thread
{
	protected Piece m_piece;
	protected ModeleTetris m_modele;
	protected double m_normal, m_accelere, m_actu;

	public ThreadTetris(Piece p, ModeleTetris m, double t1, double t2)
	{
		m_piece = p;
		m_modele = m;
		m_normal = t1;
		m_accelere = t2;
		m_actu = m_normal;
		this.start();
	}

	protected void Pause()
	{
		try
		{
			ThreadTetris.sleep((int)m_normal);
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

	public void run()
	{
		int rep = 0;
		do
		{
			rep++;
			Pause();
		}while(m_modele.Move(m_piece, 1, 0));
		Pause();
		if(rep > 1)
			m_modele.Suivante();
	}

}
