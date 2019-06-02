package br.unicamp.clienteescola.requisicoes_ao_servidor.aluno;

public class Aluno implements Cloneable
{
    protected String ra;
    protected String nome;
    protected String email;

    public Aluno ()
    { }
    
    public Aluno (String ra, String nome, String email) throws IllegalArgumentException
    {
        this.setRA (ra);
        this.setNome (nome);
        this.setEmail (email);
    }

    public void setRA (String ra) throws IllegalArgumentException
    {
        if (ra.length() != 5)
            throw new IllegalArgumentException ("RA invalido");

        try
        {
            Integer.parseInt(ra);
        }
        catch(Exception ex)
        {throw new IllegalArgumentException ("RA invalido");}

        this.ra = ra;

    }

    public void setNome (String nome) throws IllegalArgumentException
    {
        if (nome == null || nome.equals(""))
            throw new IllegalArgumentException ("Nome invalido");

        this.nome = nome;
    }

    public void setEmail (String email) throws IllegalArgumentException
    {
        if (email == null || email.equals(""))
            throw new IllegalArgumentException ("Email invalido");

        this.email = email;
    }

    public String getRA ()
    {
        return this.ra;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getEmail ()
    {
        return this.email;
    }

    public String toString()
    {
        return "RA: " + this.ra + "; Nome: " + this.nome  + "; Email : " + this.email;
    }

    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Aluno))
            return false;

    	Aluno other = (Aluno) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.ra.hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.email.hashCode();

        return ret;
    }


    public Aluno (Aluno modelo) throws IllegalArgumentException
    {
        this.ra = modelo.ra;
        this.nome = modelo.nome;
        this.email = modelo.email;
    }

    public Object clone ()
    {
        Aluno ret = null;

        try
        {
            ret = new Aluno (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }

}