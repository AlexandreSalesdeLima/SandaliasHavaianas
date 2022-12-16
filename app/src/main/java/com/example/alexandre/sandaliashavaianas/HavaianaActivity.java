package com.example.alexandre.sandaliashavaianas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;

public class HavaianaActivity extends AppCompatActivity implements AoClicarNaHavaianaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_havaiana);

        //Criando as Abas
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagingasAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void clicouNaHavaiana(Havaiana havaiana) {

        if(getResources().getBoolean(R.bool.fone)){

            Intent it = new Intent(this, DetalheActivity.class);
            it.putExtra("havaiana", havaiana);
            startActivity(it);
        }else{

            DetalheHavaianaFragment fragment = DetalheHavaianaFragment.newInstance(havaiana);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detalhe, fragment, "tagDetalhe");
            ft.commit();
        }
    }

    private class PagingasAdapter extends FragmentPagerAdapter { //Criando os fragment's
        public PagingasAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) { // Se for posiçao 0 ele instacia HavaianaListFragment
            if(position==0){
            return new HavaianaListFragment();}
            else{
                return new HavaianaListFavoritoFragment();// Se não instacia HavaianaListFavoritosFragment
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? "Web" : "Favoritos";
        }
    }
}
