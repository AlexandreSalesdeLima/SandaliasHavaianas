package com.example.alexandre.sandaliashavaianas;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        if(savedInstanceState == null) {
            Havaiana havaiana = (Havaiana) getIntent().getSerializableExtra("havaiana");

            DetalheHavaianaFragment fragment = DetalheHavaianaFragment.newInstance(havaiana);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detalhe, fragment, "tagDetalhe");
            ft.commit();
        }
    }

}
