package Modele;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import Base.*;

public class CasseTete extends Plateau
{
	private Piece m_piece_sortable;
	private int m_score;

	public CasseTete()
	{
		super(6,6);
		m_piece_sortable = null;
	}

	public int getScore()
	{
		return m_score;
	}

	public void setScore(int score)
	{
		this.m_score = score;
	}

	public Piece getPieceSortable()
	{
		return m_piece_sortable;
	}



	public void setPieceSortable(Piece p)
	{
		m_piece_sortable = p;
	}


	public void handleKeyPressed(KeyCode keyCode)
	{
		if(!finJeu())
		{
			switch(keyCode)
			{
				case DOWN:
					if(m_piece_sortable != null)
						super.Move(m_piece_sortable, 1, 0);
					break;
				case UP:
					if(m_piece_sortable != null)
						super.Move(m_piece_sortable, -1, 0);
					break;
				case RIGHT:
					if(m_piece_sortable != null)
						super.Move(m_piece_sortable, 0, 1);
					break;
				case LEFT:
					if(m_piece_sortable != null)
						super.Move(m_piece_sortable, 0, -1);
					break;
				case C:
					if((m_piece_sortable == null) || (super.getIndex(m_piece_sortable)+1 == super.getPieces().size()) )
					{
						if(m_piece_sortable != null)
							super.deselectionne(m_piece_sortable);
						m_piece_sortable = super.getPieces().get(0);
						m_piece_sortable.setSelected(true);
						super.selectionne(m_piece_sortable);
					}
					else
					{
						super.deselectionne(m_piece_sortable);
						m_piece_sortable = super.getPieces().get(super.getIndex(m_piece_sortable)+1);
						m_piece_sortable.setSelected(true);
						super.selectionne(m_piece_sortable);
					}
					break;
				default :
					break;
			}
		}

	}

	public void initLVL1()
	{
		boolean[][] forme1 = new boolean[][] {{true,true}};
		boolean[][] forme2 = new boolean[][] {{true,true,true}};
		boolean[][] forme3 = new boolean[][] {{true},{true},{true}};
		boolean[][] forme4 = new boolean[][] {{true},{true}};

		Piece sortable = new Piece(forme1, -1,-1, "1");
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

	public void jouer()
	{
		initLVL1();
	}

	public boolean finJeu()
	{
		return super.getPieces().get(0).Contains(2, 5);

	}



}
