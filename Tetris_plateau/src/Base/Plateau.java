package Base;

import java.util.ArrayList;

public class Plateau {

	protected int m_x, m_y;
	protected ArrayList<Piece> m_pieces;

	public Plateau()
	{
		m_x = 0;
		m_y = 0;
	}

	public Plateau(int hauteur, int largeur)
	{
		m_x = hauteur;
		m_y = largeur;
	}

	public Case getCase(int ligne, int colonne)
	{
		for(int i = 0; i < m_pieces.size(); i++)
		{
			if(m_pieces.get(i).Contains(ligne, colonne))
				return new Case(m_pieces.get(i).Couleur());
		}
		return new Case();
	}

	public boolean Occupee(int x, int y)
	{
		for(int i = 0; i < m_pieces.size(); i++)
		{
			if(m_pieces.get(i).Contains(x, y))
				return true;
		}
		return false;
	}

	public void poserPiece(Piece p, int px, int py)
	{
		//Pose une piece aux coordonnees voulues
		if(positionPossible(p, px, py))
			m_pieces.add(p);
	}

	public boolean positionPossible(Piece p, int px, int py)
	{
		//Si la piece sort du plateau
		if((px < 0) || ((px + p.Hauteur()) > m_x))
			return false;
		if((py < 0) || ((py + p.Largeur() > m_y)))
			return false;
		//On verifie qu'il n'y ait pas deux cases occupees superposees
		p.setPos(px,  py);
		int[][] id = p.Index();
		for(int i = 0; i < id.length; i++)
		{
			if(Occupee(id[i][0], id[i][1]))
				return false;
		}
		return true;
	}

}
