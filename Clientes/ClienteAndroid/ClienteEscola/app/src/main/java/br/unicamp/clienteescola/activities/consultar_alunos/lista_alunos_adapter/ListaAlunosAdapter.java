package br.unicamp.clienteescola.activities.consultar_alunos.lista_alunos_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;

public class ListaAlunosAdapter extends ArrayAdapter<Aluno>
{
    private Context context;
    private List<Aluno> listaAlunos= null;

    public ListaAlunosAdapter(@NonNull Context context, int resource, @NonNull List<Aluno> listaDeCarros) {
        super(context, 0, listaDeCarros);
        this.context = context;
        this.listaAlunos = listaDeCarros;
    }


    public View getView(int position, View view, ViewGroup parent)
    {
        Aluno aluno = this.listaAlunos.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_aluno,null);

        ((EditText)view.findViewById(R.id.edtRA)).setText(aluno.getRA());
        ((EditText)view.findViewById(R.id.edtNome)).setText(aluno.getNome());
        ((EditText)view.findViewById(R.id.edtEmail)).setText(aluno.getEmail());

        return view;
    }
}