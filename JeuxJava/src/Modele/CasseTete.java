package Modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import Base.*;

public class CasseTete extends Plateau
{
	private Piece m_piece_sortable;
	private int m_score;
	private int m_level;

	public CasseTete()
	{
		super(6,6);
		m_piece_sortable = null;
		m_score = -1;
		//quand aucun niveau n'est chargé
		m_level = 0;
	}

	public int getScore()
	{
		return m_score;
	}

	public void setScore(int score)
	{
		m_score = score;
		m_observer.updateScore();
	}

	public int getLevel()
	{
		return m_level;
	}

	public void setLevel(int level)
	{
		m_level = level;
		m_observer.updateLevel();
	}

	public Piece getPieceSortable()
	{
		return m_piece_sortable;
	}

	public void setPieceSortable(Piece p)
	{
		m_piece_sortable = p;
	}

	public boolean estHorizontale(Piece p)
	{
		if(p.getForme().length == 1)
			return true;
		else
			return false;
	}

	public boolean estVerticale(Piece p)
	{
		if(p.getForme()[0].length == 1)
			return true;
		else
			return false;
	}


	public void handleKeyPressed(KeyCode keyCode)
	{
		if(!finJeu())
		{
			switch(keyCode)
			{
				case DOWN:
					if(m_piece_sortable != null)
					{
						if(estVerticale(m_piece_sortable))
						{
							if(super.Move(m_piece_sortable, 1, 0))
								setScore(m_score + 1);
						}
					}
					break;
				case UP:
					if(m_piece_sortable != null)
					{
						if(estVerticale(m_piece_sortable))
						{
							if(super.Move(m_piece_sortable, -1, 0))
								setScore(m_score + 1);
						}
					}
					break;
				case RIGHT:
					if(m_piece_sortable != null)
					{
						if(estHorizontale(m_piece_sortable))
						{
							if(super.Move(m_piece_sortable, 0, 1))
								setScore(m_score + 1);
						}
					}
					break;
				case LEFT:
					if(m_piece_sortable != null)
					{
						if(estHorizontale(m_piece_sortable))
						{
							if(super.Move(m_piece_sortable, 0, -1))
								setScore(m_score + 1);
						}
					}
					break;
				case C:
					if((m_piece_sortable == null) || (super.getIndex(m_piece_sortable)+1 == super.getPieces().size()) )
					{
						if(m_piece_sortable != null)
							super.deselectionne(m_piece_sortable);
						m_piece_sortable = super.getPieces().get(0);
						super.selectionne(m_piece_sortable);
					}
					else
					{
						super.deselectionne(m_piece_sortable);
						m_piece_sortable = super.getPieces().get(super.getIndex(m_piece_sortable)+1);
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
		this.setLevel(1);
		this.setScore(0);
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

	public void initLVL2()
	{
		this.setLevel(2);
		this.setScore(0);

		boolean[][] forme1 = new boolean[][] {{true,true}};
		boolean[][] forme2 = new boolean[][] {{true,true,true}};
		boolean[][] forme3 = new boolean[][] {{true},{true},{true}};
		boolean[][] forme4 = new boolean[][] {{true},{true}};
		Piece sortable = new Piece(forme1, -1,-1, "1");
		Piece p1 = new Piece(forme1, -1, -1);
		Piece p2 = new Piece(forme1, -1, -1);
		Piece p3 = new Piece(forme4, -1, -1);
		Piece p4 = new Piece(forme4, -1, -1);
		Piece p5 = new Piece(forme4, -1, -1);
		Piece p6 = new Piece(forme4, -1, -1);
		Piece p7 = new Piece(forme4, -1, -1);
		Piece p8 = new Piece(forme4, -1, -1);
		Piece p9 = new Piece(forme2, -1, -1);
		Piece p10 = new Piece(forme2, -1, -1);
		Piece p11 = new Piece(forme3, -1, -1);
		poserPiece(sortable, 2, 0);

		poserPiece(p1, 3, 0);
		poserPiece(p2, 4, 4);
		poserPiece(p3, 0, 0);
		poserPiece(p4, 0, 5);
		poserPiece(p5, 2, 5);
		poserPiece(p6, 2, 2);
		poserPiece(p7, 2, 3);
		poserPiece(p8, 4, 3);
		poserPiece(p9, 0, 1);
		poserPiece(p10, 1, 1);
		poserPiece(p11, 0, 4);
	}

	public void reinit()
	{
		m_piece_sortable = null;
		//quand aucun niveau n'est chargé

		int tailleListe = super.getPieces().size();
		for(int i=0;i<tailleListe;i++)
			super.getPieces().remove(0);


		//m_pieces = new ArrayList<Piece>();
		for(int i=0;i<m_cases.length;i++)
			for(int j=0;j<m_cases[0].length;j++)
				m_cases[i][j].setCouleur(Case._colorVide);
	}

	public void reinitialisation()
	{
		switch(m_level)
		{
			case 1:
				reinit();
				initLVL1();
				break;
			case 2:
				reinit();
				initLVL2();
				break;
			default:
				break;
		}
	}

	public boolean levelSuivant()
	{
		int levelActuel = m_level;
		switch(levelActuel)
		{
			case 1:
				reinit();
				initLVL2();
				return true;
			case 2:
				return false;
			default:
				return false;
		}
	}

	public boolean levelPrecedent()
	{
		int levelActuel = m_level;
		switch(levelActuel)
		{
			case 2:
				reinit();
				initLVL1();
				return true;
			case 1:
				return false;
			default:
				return false;
		}
	}

	public void jouer()
	{
		//Par défaut, le jeu se lance sur le niveau 1
		initLVL1();
	}

	public boolean finJeu()
	{
		if(!super.getPieces().isEmpty() )
			return super.getPieces().get(0).Contains(2, 5);
		else
			return false;

	}



}
