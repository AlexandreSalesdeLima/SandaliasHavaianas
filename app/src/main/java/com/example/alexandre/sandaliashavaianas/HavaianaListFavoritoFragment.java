package com.example.alexandre.sandaliashavaianas;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.alexandre.sandaliashavaianas.Banco.HavaianaDAO;
import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilma on 23/10/2015.
 */
public class HavaianaListFavoritoFragment extends ListFragment  {

    List<Havaiana> mHavaianas;
    HavaianaAdapter mAdapter;
    Bus mBus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mHavaianas = new ArrayList<Havaiana>();
        mBus = ((HavaianaApp)getActivity().getApplication()).getBus();
        mBus.register(this);
    }

    @Override
    public void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mHavaianas.isEmpty()){
        mAdapter = new HavaianaAdapter(getActivity(),mHavaianas);
        setListAdapter(mAdapter);
        carregarHavaiana();
       }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Havaiana havaiana = mHavaianas.get(position);
        if(getActivity() instanceof AoClicarNaHavaianaListener){
            ((AoClicarNaHavaianaListener)getActivity()).clicouNaHavaiana(havaiana);

                    }
    }

    @Subscribe
    public void ListaDeFavoritosAtualizado(Havaiana havaiana){
        carregarHavaiana();
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarHavaiana();
    }

    public void carregarHavaiana(){
        HavaianaDAO dao = new HavaianaDAO(getActivity());
        mHavaianas.clear();
        mHavaianas.addAll(dao.listar());
        mAdapter.notifyDataSetChanged();
    }

}
