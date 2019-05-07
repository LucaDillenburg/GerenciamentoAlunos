/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import escola.bd.dbos.*;
import escola.bd.daos.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import escola.bd.BDSQLServer;
import escola.bd.core.MeuPreparedStatement;
import escola.bd.core.MeuResultSet;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author luca_dillenburg
 */
@Path("escola")
public class ServiceEscola {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceEscola
     */
    public ServiceEscola() {
    }
    
    //GET para consultar todos os alunos do banco de dados
    @GET
    @Path("/getAlunos")
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno[] getAlunos() throws Exception
    {
        return Alunos.getAlunos();
    }
    
    //GET , onde o RA do aluno é passado como parâmetro
    @GET
    @Path("/getAluno/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno getAluno(@PathParam("ra")String ra) throws Exception
    {
        return Alunos.getAluno(ra);
    }
    
    //GET , onde o nome do aluno é passado como parâmetro 
    @GET
    @Path("/getAlunoPorNome/{nomeAluno}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno[] getAlunoPorNome(@PathParam("nomeAluno")String encodedNomeAluno) throws Exception
    {
        String nomeAluno = URLDecoder.decode(encodedNomeAluno, StandardCharsets.UTF_8.name());
        return Alunos.getAlunoPorNome(nomeAluno);
    }
    
    //DELETE, para excluir o aluno do banco de dados.
    @GET
    @Path("/excluirAluno/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean excluirAluno(@PathParam("ra")String ra) throws Exception
    {
        try
        {
            Alunos.deletar(ra);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    //POST para incluir um novo aluno no banco de dados
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean inserirAluno(Aluno aluno)
    {
        try
        {
            Alunos.inserir(aluno);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
  
    //PUT, para alterar nome e email do aluno no banco de dados
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean alterarAluno(Aluno aluno)
    {
        try
        {
            Alunos.alterar(aluno);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    /**
     * Retrieves representation of an instance of escola.ServiceEscola
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ServiceEscola
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
