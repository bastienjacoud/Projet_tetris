package Modele;

import javafx.scene.paint.Color;

import Base.*;

public class CasseTete extends Plateau
{

	public CasseTete()
	{
		super(10,10);
	}

	/*
	public Plateau getPlateau()
	{
		return _p;
	}
	*/

	/*
	public void setPlateau(Plateau p)
	{
		_p = p;
	}
	*/

	public void jouer()
	{
		boolean[][] forme1 = new boolean[][] {{true,true,true},
											 {false,true,false},
											 {false,true,false}};
		boolean[][] forme = new boolean[][] {{true}};
		Piece p = new Piece(forme1, 1,1,"5");
		System.out.println("1");
		poserPiece(p, 1, 1);
	}



}
