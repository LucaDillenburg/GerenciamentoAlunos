/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import escola.bd.daos.Alunos;
import escola.bd.dbos.Aluno;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author u17188
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
    public Aluno[] getAlunos() throws WebApplicationException
    {
        try
        {
            return Alunos.getAlunos();
        }catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
    
    //GET , onde o RA do aluno é passado como parâmetro
    @GET
    @Path("/getAluno/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno getAluno(@PathParam("ra")String ra) throws WebApplicationException
    {
        try
        {
            return Alunos.getAluno(ra);
        }catch(IllegalArgumentException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
    
    //GET , onde o nome do aluno é passado como parâmetro 
    @GET
    @Path("/getAlunoPorNome/{nomeAluno}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno[] getAlunoPorNome(@PathParam("nomeAluno")String encodedNomeAluno) throws WebApplicationException
    {
        String nomeAluno = null;
        
        try
        {
            nomeAluno = URLDecoder.decode(encodedNomeAluno, StandardCharsets.UTF_8.name());
        }catch(Exception e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Nome aluno invalido!").build());
        }
        
        try
        {
            return Alunos.getAlunosPorNome(nomeAluno);
        }catch(IllegalArgumentException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
    
    //DELETE, para excluir o aluno do banco de dados.
    @PUT // JAR-RS nao aceita metodo @Delete
    @Path("/excluirAluno/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluirAluno(@PathParam("ra")String ra) throws WebApplicationException
    {
        try
        {
            Alunos.deletar(ra);
        }catch(IllegalArgumentException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
    
    //POST para incluir um novo aluno no banco de dados
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    public void inserirAluno(Aluno aluno) throws WebApplicationException
    {
        try
        {
            Alunos.inserir(aluno);
        }catch(IllegalArgumentException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
  
    //PUT, para alterar nome e email do aluno no banco de dados
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarAluno(Aluno aluno) throws WebApplicationException
    {
        try
        {
            Alunos.alterar(aluno);
        }catch(IllegalArgumentException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        catch(SQLException e)
        {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
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
