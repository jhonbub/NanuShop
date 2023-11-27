package com.example.nanushop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Entidad.Productos;
import adaptadores.ListaProdutosAdacter;
import db.Dbproductos;

public class MenuActivity extends AppCompatActivity {
RecyclerView lista;
    FloatingActionButton Factura ;
    FloatingActionButton  Carrito;

ArrayList<Productos> listaArrayProductos;
ArrayList<Productos> producto ;
    ListaProdutosAdacter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Dbproductos dbproductos = new Dbproductos(MenuActivity.this);

        Factura = findViewById(R.id.factura);
        Carrito = findViewById(R.id.carrito);
        producto = dbproductos.mostraProductos();
      if (producto != null && !producto.isEmpty()) {
            Log.d("DEBUG", "Cantidad de elementos: " + producto.size());
          lista = findViewById(R.id.lista);
          lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listaArrayProductos = new ArrayList<>();
         adapter = new ListaProdutosAdacter(dbproductos.mostraProductos());
          lista.setAdapter(adapter);


        }else {
          Toast.makeText(MenuActivity.this, "La lista de productos está vacía", Toast.LENGTH_SHORT).show();
      }

      Factura.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

      Carrito.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MenuActivity.this, CarritoActivity.class);
              startActivity(intent);
          }
      });



    }





}