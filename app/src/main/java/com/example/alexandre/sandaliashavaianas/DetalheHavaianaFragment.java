package com.example.alexandre.sandaliashavaianas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.sandaliashavaianas.Banco.HavaianaDAO;
import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;


/**
 * Created by Wilma on 23/10/2015.
 */
public class DetalheHavaianaFragment  extends Fragment {

    Havaiana mHavaiana;
    MenuItem mMenuItemFavorito;
    HavaianaDAO mDao;

    public static DetalheHavaianaFragment newInstance(Havaiana c) {

        Bundle args = new Bundle();
        args.putSerializable("havaiana",c);

        DetalheHavaianaFragment fragment = new DetalheHavaianaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDao = new HavaianaDAO(getActivity());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHavaiana = (Havaiana)getArguments().getSerializable("havaiana");

        View layout = inflater.inflate(R.layout.fragment_detalhe_havaiana, null);

        ImageView imgCapa = (ImageView)layout.findViewById(R.id.imgCapa);
        TextView txtNome = (TextView)layout.findViewById(R.id.txtNome);
        TextView txtIngrediente=(TextView)layout.findViewById(R.id.txtGtafabricante);
        TextView txtValor = (TextView)layout.findViewById(R.id.txtValor);
        TextView txtRef = (TextView)layout.findViewById(R.id.txtRef);

        Picasso.with(getActivity()).load(mHavaiana.capa).into(imgCapa);
        txtNome.setText(mHavaiana.nome);
        txtIngrediente.setText(mHavaiana.gtafabricante);
        txtValor.setText(mHavaiana.valor);
        txtRef.setText(mHavaiana.ref);

        // Inflate the layout for this fragment
        return layout;
    }

    @Override
    public  void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        mMenuItemFavorito = menu.findItem(R.id.acao_favorito);
        atualizarItemMenu(mDao.isFavorito(mHavaiana));
    }

    private void atualizarItemMenu(boolean favorito){
        mMenuItemFavorito.setIcon(favorito ?
        android.R.drawable.ic_menu_delete :
        android.R.drawable.ic_menu_save);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.acao_favorito){
            boolean favorito = mDao.isFavorito(mHavaiana);
            if(favorito){
                mDao.excluir(mHavaiana);
            }else{
                mDao.inserir(mHavaiana);
            }
            atualizarItemMenu(!favorito);
            Bus bus = ((HavaianaApp) getActivity().getApplication()).getBus();
            bus.post(mHavaiana);
        }
        return super.onOptionsItemSelected(item);
    }
}
