package Base;

import javafx.scene.paint.Color;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Case
{
	protected StringProperty m_couleur;

	protected static String _colorVide = "00000000";

	public Case()
	{
		Init(_colorVide);
	}

	public Case(String couleur)
	{
		Init(couleur);
	}

	protected void Init(String c)
	{
		m_couleur = new SimpleStringProperty(c);
	}

	public String getCouleur()
	{
		return m_couleur.get();
	}

	public StringProperty getCouleurProperty()
	{
		return m_couleur;
	}

	public void setCouleur(String couleur)
	{
		this.m_couleur.set(couleur);
	}

	public boolean Vide()
	{
		return m_couleur.get() == _colorVide;
	}
}
