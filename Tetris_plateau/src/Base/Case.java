package Base;

import javafx.scene.paint.Color;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Case
{
	protected StringProperty m_couleur;

	protected BooleanProperty m_selected;

	public static final String _colorVide = "TR";

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
		m_selected = new SimpleBooleanProperty(false);
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

	public boolean getSelected()
	{
		return m_selected.get();
	}

	public BooleanProperty getSelectedProperty()
	{
		return m_selected;
	}

	public void setSelected(boolean selected)
	{
		m_selected.set(selected);
	}

	public boolean Vide()
	{
		return m_couleur.get() == _colorVide;
	}
}
