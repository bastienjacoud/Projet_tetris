package Base;

import java.awt.Color;

public class Case
{
	protected Color m_couleur;

	//Liste des couleurs disponnibles
	protected static Color[] _col = new Color[] {Color.blue, Color.green, Color.yellow, Color.red, Color.orange};
	protected static Color _vide = new Color(0, 0, 0, 0);

	public Case()
	{
		Init(_vide);
	}

	public Case(Color couleur)
	{
		Init(couleur);
	}

	protected void Init(Color c)
	{
		m_couleur = c;
	}

	public Color getCouleur()
	{
		return m_couleur;
	}

	public void setCouleur(Color couleur)
	{
		this.m_couleur = couleur;
	}

	public boolean Vide()
	{
		return m_couleur == _vide;
	}

	//Retourne une des couleurs disponibles autres que _vide
	static public Color Color()
	{
		return _col[(int)(Math.random() * _col.length)];
	}
}
