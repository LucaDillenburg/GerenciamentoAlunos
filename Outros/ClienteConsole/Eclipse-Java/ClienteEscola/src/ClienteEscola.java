import java.io.*; //bf e println
import java.net.*;

import com.google.gson.Gson; //to Json
import aluno.*;
import clientews.*;

public class ClienteEscola
{
    private static int OPCAO_SAIR = 99;
    private static String URL = "http://localhost:8080/Escola/webresources/escola/";

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
            System.out.println(" 6. Excluir aluno");
            System.out.println(ClienteEscola.OPCAO_SAIR + ". SAIR");
            
            while(true)
            {
                System.out.println("");
                System.out.print("Digite a opcao desejada: ");

                try
                {
                	String strOpcao = teclado.readLine();
                
                    opcao = Integer.parseInt(strOpcao);
                    break;
                }catch(Exception e)
                { }
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
                    	break;
                    case 2: //consultar aluno por ra
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "getAluno/" + 
                    			ClienteEscola.getRAFromTeclado(teclado), "GET");
                    	break;
                    case 3: //consultar aluno por nome
                    	String nomeAluno = ClienteEscola.getNomeFromTeclado(teclado);
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "getAlunoPorNome/" + 
                    			ClienteWS.encodeString(nomeAluno), "GET");
                    	break;
                    case 4: //incluir aluno
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "incluirAluno", "POST",
                    			true, ClienteEscola.getAlunoFromTeclado(teclado));
                    	break;
                    case 5: //alterar aluno
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "alterarAluno", "PUT",
                    			true, ClienteEscola.getAlunoFromTeclado(teclado));
                    	break;
                    case 6: //excluir aluno
                    	result = ClienteWS.chamarMetodoServidor(ClienteEscola.URL + "excluirAluno/" + 
                    			ClienteEscola.getRAFromTeclado(teclado), "GET");
                    	break;
                }

            	System.out.println("Resultado: " + result);
            }catch(Exception e)
            {
            	System.err.println("Erro! " + e.getMessage());
            	e.printStackTrace();
            }
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
