package Base;

public class Case
{
	protected String m_couleur;

	protected boolean m_selected;

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
		m_couleur = c;
		m_selected = false;
	}

	public String getCouleur()
	{
		return m_couleur;
	}

	public void setCouleur(String couleur)
	{
		this.m_couleur = couleur;
	}

	public boolean getSelected()
	{
		return m_selected;
	}

	public void setSelected(boolean selected)
	{
		m_selected = selected;
	}

	public boolean Vide()
	{
		return (m_couleur == _colorVide);
	}
}
