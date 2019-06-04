package br.unicamp.clienteescola.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityConsomeServico extends AppCompatActivity {

    //protected GifImageView imageViewLoading;
    protected TextView tvResultado;

    //mensagens de resultado ou erro
    public void setResultado(String resultado)
    {
        this.tvResultado.setText(resultado);
    }

    public void setMsgErro(String msgErro)
    {
        this.tvResultado.setText(msgErro);
    }


    //process events
    public void startedAsyncTask()
    {
        //set imageViewLoading visible=true
    }

    public void progressUpdate()
    {
        //mudar imagem de imageViewLoading
    }

    public void finishedAsyncTask()
    {
        //set imageViewLoading visible=false
    }

}
