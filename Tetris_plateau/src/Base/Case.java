package Base;

import javafx.scene.paint.Color;

import javafx.beans.property.ObjectProperty;

public class Case
{
	protected ObjectProperty<Color> m_couleur;

	protected static Color _colorVide = new Color(0, 0, 0, 0);

	public Case()
	{
		Init(_colorVide);
	}

	public Case(Color couleur)
	{
		Init(couleur);
	}

	protected void Init(Color c)
	{
		m_couleur.set(c);
	}

	public Color getCouleur()
	{
		return m_couleur.get();
	}

	public ObjectProperty<Color> getCouleurProperty()
	{
		return m_couleur;
	}

	public void setCouleur(Color couleur)
	{
		this.m_couleur.set(couleur);
	}

	public boolean Vide()
	{
		return m_couleur.get() == _colorVide;
	}
}
