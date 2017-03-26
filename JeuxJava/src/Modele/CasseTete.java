package Modele;

import javafx.scene.paint.Color;

import Base.*;

public class CasseTete extends Plateau
{
	boolean[][] forme1 = new boolean[][] {{true,true}};
	public Piece sortable = new Piece(forme1, -1,-1, "1");

	public CasseTete()
	{
		super(6,6);
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
		boolean[][] forme1 = new boolean[][] {{true,true}};
		boolean[][] forme2 = new boolean[][] {{true,true,true}};
		boolean[][] forme3 = new boolean[][] {{true},{true},{true}};
		boolean[][] forme4 = new boolean[][] {{true},{true}};


		Piece p1 = new Piece(forme1, -1,-1);
		Piece p2 = new Piece(forme3, -1,-1);
		Piece p3 = new Piece(forme4, -1,-1);
		Piece p4 = new Piece(forme3, -1,-1);
		Piece p5 = new Piece(forme3, -1,-1);
		Piece p6 = new Piece(forme1, -1,-1);
		Piece p7 = new Piece(forme2, -1,-1);


		poserPiece(sortable, 2, 1);
		poserPiece(p1, 0, 0);
		poserPiece(p2, 1, 0);
		poserPiece(p3, 4, 0);
		poserPiece(p4, 1, 3);
		poserPiece(p5, 0, 5);
		poserPiece(p6, 4, 4);
		poserPiece(p7, 5, 2);

	}



}
