package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Entidad.Productos;
import adaptadores.ListaProdutosAdacter;
import db.Dbproductos;

public class InventarioActivity extends AppCompatActivity {
    ArrayList<Productos> listaArrayProductos;
    FloatingActionButton exit ;
    ArrayList<Productos> producto ;
    ListaProdutosAdacter adapter;
    RecyclerView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        Dbproductos dbproductos = new Dbproductos(InventarioActivity.this);
        producto = dbproductos.mostraProductostotal();


        if (producto != null && !producto.isEmpty()) {
            Log.d("DEBUG", "Cantidad de elementos: " + producto.size());
            lista = findViewById(R.id.lista);
            lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            listaArrayProductos = new ArrayList<>();
            adapter = new ListaProdutosAdacter(dbproductos.mostraProductostotal());
            lista.setAdapter(adapter);


        }else {
            Toast.makeText(InventarioActivity.this, "La lista de productos está vacía", Toast.LENGTH_SHORT).show();
        }

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventarioActivity.this, menuADActivity.class);
                startActivity(intent);
            }
        });

    }
}