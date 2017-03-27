package Modele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Base.Piece;

public class EventTetris implements KeyListener
{
	protected Piece m_piece;
	protected ThreadTetris m_thread;
	protected ModeleTetris m_modele;
	protected static double m_normal = 600, m_rapide = 300;

	public EventTetris(Piece p, ModeleTetris m)
	{
		m_modele = m;
		setPiece(p);
	}

	public void setPiece(Piece p)
	{
		m_piece = p;
		if(m_piece != null)
			m_thread = new ThreadTetris(m_piece, m_modele, m_normal, m_rapide);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(m_piece != null)
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_KP_DOWN :
					m_thread.Accelerer();
					break;
				case KeyEvent.VK_KP_UP :
					m_modele.Rotate(m_piece, true);
					break;
				case KeyEvent.VK_KP_LEFT :
					m_modele.Move(m_piece, 0, -1);
					break;
				case KeyEvent.VK_KP_RIGHT:
					m_modele.Move(m_piece, 0, 1);
					break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(m_piece != null)
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_KP_DOWN :
					m_thread.Normal();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
