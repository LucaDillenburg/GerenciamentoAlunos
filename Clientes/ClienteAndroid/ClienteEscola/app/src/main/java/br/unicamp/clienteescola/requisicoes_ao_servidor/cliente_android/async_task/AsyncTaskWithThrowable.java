package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task;

import android.os.AsyncTask;

public abstract class AsyncTaskWithThrowable<Params, Progress, Result> {
    protected abstract Result doInBackground(Params ... params) throws Throwable;

    public final AsyncTaskWithThrowable<Params, Progress, Result> execute(Params... params) {
        _asyncTask.execute(params);
        return this;
    }

    @SuppressWarnings({"UnusedDeclaration"}) //a classe que extends essa nao precisa necessariamente @Override esse metodo
    protected void onPreExecute() {
    }

    @SuppressWarnings({"UnusedDeclaration"}) //a classe que extends essa nao precisa necessariamente @Override esse metodo
    protected void onPostExecute(ResultHolder<Result> resultHolder) {
    }

    @SuppressWarnings({"UnusedDeclaration"}) //a classe que extends essa nao precisa necessariamente @Override esse metodo
    protected void onProgressUpdate(Progress... values) {
    }

    // ASYNC TASK
    protected AsyncTask<Params, Progress, ResultHolder<Result>> _asyncTask = new AsyncTask<Params, Progress, ResultHolder<Result>>() {
        @Override
        protected ResultHolder<Result> doInBackground(Params... params)  {

            try {
                return new ResultHolder<Result>(AsyncTaskWithThrowable.this.doInBackground(params));
            }
            catch (Throwable e) {
                return new ResultHolder<Result>(e);
            }
        }

        @Override
        protected void onPreExecute() {
            AsyncTaskWithThrowable.this.onPreExecute();
        }

        @Override
        protected void onPostExecute(ResultHolder<Result> result) {
            AsyncTaskWithThrowable.this.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Progress... values) {
            AsyncTaskWithThrowable.this.onProgressUpdate(values);
        }
    };

    // RESULT HOLDER
    public class ResultHolder<Result> {
        protected Throwable throwable = null;
        protected Result result = null;

        private ResultHolder(Result result) {
            this.result = result;
        }
        private ResultHolder(Throwable throwable) {
            this.throwable = throwable;
        }

        public Result getResult() throws Throwable{
            if (this.throwable != null)
                throw this.throwable;
            return this.result;
        }
    }
}
