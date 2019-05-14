/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola.bd.dbos;

/**
 *
 * @author u17188
 */
public class Aluno implements Cloneable
{
    protected String ra;
    protected String nome;
    protected String email;

    public Aluno()
    {
        this.ra = "12345";
        this.nome = "andrezito";
        this.email = "asdfsadf";
    }
    
    public Aluno (String ra, String nome, String email) throws IllegalArgumentException
    {
        this.setRa (ra);
        this.setNome (nome);
        this.setEmail (email);
    }

    public void setRa (String ra) throws IllegalArgumentException
    {
        if (ra.length() != 5)
            throw new IllegalArgumentException ("RA invalido");

        try
        {
            Integer.parseInt(ra);
        }
        catch(Exception ex)
        { throw new IllegalArgumentException ("RA invalido"); }

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

    public String toString ()
    {
        String ret="";

        ret+="RA    : "+this.ra+"\n";
        ret+="Nome  : "+this.nome  +"\n";
        ret+="Email : "+this.email;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Aluno))
            return false;

        Aluno alu = (Aluno)obj;

        if (!this.ra.equals(alu.ra) || !this.nome.equals(alu.nome) || !this.email.equals(alu.email))
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


    public Aluno (Aluno modelo) throws Exception
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