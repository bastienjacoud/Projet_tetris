package Modele;

import javafx.scene.paint.Color;

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
		boolean[][] forme1 = new boolean[][] {{true,true,true},
											 {false,false,false},
											 {false,false,false}};
		boolean[][] forme = new boolean[][] {{true}};
		Piece p = new Piece(forme1, 1,1,"5");
		_p.poserPiece(p, 1, 1);
		_p.poserPiece(p, 5, 5);
	}



}
