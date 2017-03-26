package Modele;

public class ThreadTetris extends Thread
{
	protected Piece m_piece;

	public ThreadTetris()
	{
		// TODO Auto-generated constructor stub
	}
	public void run()
	{
	    for(int i = 0; i < 10; i++)
	    	System.out.println(this.getName());
	}

}
