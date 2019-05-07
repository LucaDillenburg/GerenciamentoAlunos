/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola.bd.daos;

import java.sql.*;
import escola.bd.*;
import escola.bd.core.*;
import escola.bd.dbos.*;

/**
 *
 * @author u17188
 */
public class Alunos
{
    public static Aluno[] getAlunos() throws Exception
    {
        try
        {
            String sql = "select * from aluno";
            BDSQLServer.COMANDO.prepareStatement (sql);

            return Alunos.vetorAlunosFromRestult((MeuResultSet)BDSQLServer.COMANDO.executeQuery ());
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }
    }
            
    public static Aluno getAluno (String ra) throws Exception
    {
        if (ra==null)
            throw new Exception ("RA nao fornecido");

        try
        {
            String sql = "select nome, email from aluno where ra = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            //parametros
            BDSQLServer.COMANDO.setString(1, ra);

            MeuResultSet result = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            
            if (!result.first()) //resultado vazio
                return null;
            
            return new Aluno(ra, result.getString("nome"), result.getString("email"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }
    }

    public static Aluno[] getAlunoPorNome (String nome) throws Exception
    {
        if (nome==null)
            throw new Exception ("Nome nao fornecido");

        try
        {
            String sql = "select * from aluno where nome = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            //parametros
            BDSQLServer.COMANDO.setString(1, nome);

            return Alunos.vetorAlunosFromRestult((MeuResultSet)BDSQLServer.COMANDO.executeQuery ());
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }
    }

    public static void inserir (Aluno aluno) throws Exception
    {
        if (aluno==null)
            throw new Exception ("Aluno nao fornecido");

        //try
        {
            String sql = "insert into aluno values(?,?,?)";
            BDSQLServer.COMANDO.prepareStatement (sql);

            //parametros
            BDSQLServer.COMANDO.setString(1, aluno.getRA());
            BDSQLServer.COMANDO.setString(2, aluno.getNome());
            BDSQLServer.COMANDO.setString(3, aluno.getEmail());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit();
        }
        //catch (SQLException erro)
        {
            //throw new Exception ("Erro ao inserir aluno");
        }
    }

    public static void deletar (String ra) throws Exception
    {
        if (ra==null)
            throw new Exception ("RA nao fornecido");

        try
        {
            String sql = "delete from aluno where ra = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            //parametros
            BDSQLServer.COMANDO.setString(1, ra);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir aluno");
        }
    }

    public static void alterar (Aluno aluno) throws Exception
    {
        if (aluno==null)
            throw new Exception ("Aluno nao fornecido");
        if (Alunos.getAluno(aluno.getRA()) == null)
            throw new Exception ("Aluno nao existe");

        try
        {
            String sql = "update aluno set nome = ?, email = ? where ra = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            //parametros
            BDSQLServer.COMANDO.setString(1, aluno.getNome());
            BDSQLServer.COMANDO.setString(2, aluno.getEmail());
            BDSQLServer.COMANDO.setString(3, aluno.getRA());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de usuario");
        }
    }

    protected static Aluno[] vetorAlunosFromRestult(MeuResultSet result)
    {
        try
        {
            result.last();
            int qtdRows = result.getRow();
            Aluno[] alunos = new Aluno[qtdRows];

            result.beforeFirst();
            for (int i=0; result.next(); i++)
                alunos[i] = new Aluno(result.getString("ra"), result.getString("nome"), result.getString("email"));
            
            return alunos;
        }catch(Exception e)
        { return null; /*nao vai chegar aqui*/ }
    }
}