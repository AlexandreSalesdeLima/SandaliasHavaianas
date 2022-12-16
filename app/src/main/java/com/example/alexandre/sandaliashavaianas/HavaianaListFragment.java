package com.example.alexandre.sandaliashavaianas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexandre.sandaliashavaianas.Cless.Categoria;
import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;
import com.example.alexandre.sandaliashavaianas.Cless.Sandalia;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilma on 23/10/2015.
 */
public class HavaianaListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Havaiana> mHavaianas;
    HavaianaAdapter mAdapter;
    SwipeRefreshLayout mSwipe;
    HavaianaTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mHavaianas = new ArrayList<Havaiana>();
        Bus bus = ((HavaianaApp) getActivity().getApplication()).getBus();
        bus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_lista_havaiana, container, false);

        mSwipe = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        mSwipe.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mHavaianas.isEmpty()){
        mAdapter = new HavaianaAdapter(getActivity(),mHavaianas);
        setListAdapter(mAdapter);

        carregarHavaiana();}
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {//É chamado dentro da MaimActivity
        super.onListItemClick(l, v, position, id);
        Havaiana havaiana = mHavaianas.get(position);
        exibirHavaiana(havaiana);
    }

    public void exibirHavaiana(Havaiana havaiana){

        if(getActivity() instanceof AoClicarNaHavaianaListener){

           ((AoClicarNaHavaianaListener)getActivity()).clicouNaHavaiana(havaiana);

        }
    }

    @Override
    public void onRefresh() {
        carregarHavaiana();
    }


    public void carregarHavaiana(){
        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            mSwipe.setRefreshing(true);
            if(mTask == null){
           mTask = new HavaianaTask();
            mTask.execute();}

        } else {
            mSwipe.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.msg_sem_conexao, Toast.LENGTH_SHORT).show();
        }

    }
    class HavaianaTask extends AsyncTask<Void, Void, Sandalia> {
        @Override
        protected Sandalia doInBackground(Void... params) {


            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dl.dropboxusercontent.com/u/108923676/Havaianas.json")
                    .build();

            Response response = null ;
            try {

                Thread.sleep(10000);
                response = client.newCall(request).execute();
                String json = response.body().string();
                Gson gson = new Gson();
                Sandalia sandalia = gson.fromJson(json, Sandalia.class);
                return sandalia;
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Sandalia sandalia) {
            super.onPostExecute(sandalia);

            if (sandalia != null && sandalia.categorias != null) {

                mHavaianas.clear();

                for (Categoria categoria : sandalia.categorias) {
                    mHavaianas.addAll(categoria.havaianas);
                }
                     mAdapter.notifyDataSetChanged();
                if(getResources().getBoolean(R.bool.tablet)) {
                    exibirHavaiana(mHavaianas.get(0));
                }
            } else{
                Toast.makeText(getActivity(), R.string.msg_erro_geral, Toast.LENGTH_LONG).show();
            }
            mSwipe.setRefreshing(false);
        }
    }
}
