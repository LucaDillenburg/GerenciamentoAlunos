import java.io.*; //bf e println
import java.net.*;

import com.google.gson.Gson; //to Json
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import aluno.*;
import clientews.*;

public class ClienteEscola
{
	private static int ULTIMA_OPCAO = 6;
    private static int OPCAO_SAIR = 99;
    private static String URL = "http://localhost:8080/EscolaAPI/webresources/escola/";

    public static void main(String[] args)
    {
        while(true)
        {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            int opcao;
            System.out.println("Opcoes:");
            System.out.println(" 1. Consultar alunos");
            System.out.println(" 2. Consultar aluno por RA");
            System.out.println(" 3. Consultar aluno por nome");
            System.out.println(" 4. Inserir aluno");
            System.out.println(" 5. Alterar nome do aluno e email");
            System.out.println(" " + ClienteEscola.ULTIMA_OPCAO + ". Excluir aluno");
            System.out.println(ClienteEscola.OPCAO_SAIR + ". SAIR");
            
            while(true)
            {
                System.out.println("");
                System.out.print("Digite a opcao desejada: ");

                try
                {
                	String strOpcao = teclado.readLine();
                	
                    opcao = Integer.parseInt(strOpcao);
                    
                    if (opcao > ClienteEscola.ULTIMA_OPCAO && opcao != ClienteEscola.OPCAO_SAIR)
                    	throw new Exception(""); //para ir para o catch
                    
                    break;
                }catch(Exception e)
                {
                	System.err.println("Opcao invalida!");
                }
            }

            if (opcao == ClienteEscola.OPCAO_SAIR)
                break;

            try
            {
            	String result = null;
            	switch (opcao)
                {
                    case 1: //consultar alunos
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "getAlunos", "GET");
                    	ClienteEscola.printArrayAlunos(result, true);
                    	break;
                    	
                    case 2: //consultar aluno por ra
                    	try {
                    		result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "getAluno/" + 
                        			ClienteEscola.getRAFromTeclado(teclado), "GET");
                        	
                        	JsonObject jsonObj = new JsonParser().parse(result).getAsJsonObject();
                        	System.out.println("Nome: " + jsonObj.get("nome").getAsString());
                        	System.out.println("Email: " + jsonObj.get("email").getAsString());
                    	} catch(Exception err) {
                    		System.out.println("Nao ha nenhum aluno no banco com esse RA!");
                    	}
                    	break;
                    	
                    case 3: //consultar aluno por nome
                    	String nomeAluno = ClienteEscola.getNomeFromTeclado(teclado);
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "getAlunoPorNome/" + 
                    			ClienteWS.encodeString(nomeAluno), "GET");
                    	ClienteEscola.printArrayAlunos(result, false);
                    	break;
                    	
                    case 4: //incluir aluno
                    	try {
                    		result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "incluirAluno", "POST",
                    			true, ClienteEscola.getAlunoFromTeclado(teclado));
                    	
                    		System.out.println("Aluno incluido com sucesso!");
                    	} catch(Exception err) {
                    		System.out.println("Um aluno com o mesmo RA jah foi adicionado!");
                    	}
                    	break;
                    	
                    case 5: //alterar aluno
                    	try {
                    		result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "alterarAluno", "PUT",
                    			true, ClienteEscola.getAlunoFromTeclado(teclado));
                    	
                    		System.out.println("Aluno alterado com sucesso!");
                    	} catch(Exception err) {
                    		System.out.println("Esse aluno nao existe no banco!");
                    	}
                    	break;
                    	
                    case 6: //excluir aluno
                    	try {
                    		result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "excluirAluno/" + 
                    			ClienteEscola.getRAFromTeclado(teclado), "GET");
                    	
                    		System.out.println("Aluno excluido com sucesso!");
                    	} catch(Exception err) {
                    		System.out.println("Esse aluno nao existe no banco!");
                    	}
                    	break;
                }

            }catch(Exception e)
            {
            	System.err.println("Erro! " + e.getMessage());
            	//e.printStackTrace();
            } finally
            {
            	System.out.println();
            	System.out.println();
            	System.out.println();
            }
        }
    }
    
    private static void printArrayAlunos(String result, boolean printarNome)
    {
    	JsonArray alunosArray = new JsonParser().parse(result).getAsJsonArray();
    	
    	if (alunosArray.size() == 0)
    		System.out.println("Nenhum aluno foi encontrado!");
    	else
	    	for (int i=0; i<alunosArray.size(); i++)
	    	{
	    		if (i!=0)
	    			System.out.println();
	    		//System.out.println((i+1)+"o Aluno:");
	    		
	    		JsonObject jsonObj = alunosArray.get(i).getAsJsonObject();
	    		System.out.println("RA: " + jsonObj.get("RA").getAsString());
	    		if (printarNome)
	    			System.out.println("Nome: " + jsonObj.get("nome").getAsString());
	    		System.out.println("Email: " + jsonObj.get("email").getAsString());
	    	}
	}

	private static Aluno getAlunoFromTeclado(BufferedReader teclado)
    {
    	while(true)
    	{
    		try
        	{
    			System.out.print("Digite o RA do aluno: ");
        		String ra = teclado.readLine();

        		System.out.print("Digite o nome do aluno: ");
            	String nome = teclado.readLine();

            	System.out.print("Digite o email do aluno: ");
            	String email = teclado.readLine();

            	return new Aluno(ra, nome, email);
        	}catch(Exception e)
        	{
        		System.err.println("Dado invalido! Digite novamente...");
        		System.err.println();
        	}
    	}
    }
    
    private static String getRAFromTeclado(BufferedReader teclado)
    {
    	while(true)
    	{
    		try
        	{
    			System.out.print("Digite o RA do aluno: ");
        		String ra = teclado.readLine();
        		new Aluno().setRA(ra);
        		return ra;
        	}catch(Exception e)
        	{
        		System.err.println("RA invalido! Digite novamente...");
        		System.err.println();
        	}
    	}
    }
    
    private static String getNomeFromTeclado(BufferedReader teclado)
    {
    	while(true)
    	{
    		try
        	{
    			System.out.print("Digite o Nome do aluno: ");
        		String nome = teclado.readLine();
        		new Aluno().setNome(nome);
        		return nome;
        	}catch(Exception e)
        	{
        		System.err.println("Nome invalido! Digite novamente...");
        		System.err.println();
        	}
    	}
    }
}