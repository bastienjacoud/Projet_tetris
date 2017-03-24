package Modele;

import Base.*;

public class CasseTete {

	public Plateau _p;

	public CasseTete()
	{
		_p = new Plateau(10,10);
	}

	public Plateau getPlateau()
	{
		return _p;
	}

	public void setPlateau(Plateau p)
	{
		_p = p;
	}

	public void jouer()
	{

	}



}
