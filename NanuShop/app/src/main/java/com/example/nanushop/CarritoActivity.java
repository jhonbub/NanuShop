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
import android.widget.Toast;

import java.util.ArrayList;

import Entidad.APPData;
import Entidad.CARRITO;
import Entidad.Productos;
import adaptadores.ListaProdutosAdacter;
import db.Dbcarrito;
import db.Dbproductos;
import adaptadores.listaCarritoAdacter;
public class CarritoActivity extends AppCompatActivity {
    ArrayList<CARRITO> listaArrayProductos;
    ArrayList<CARRITO> producto ;

    RecyclerView lista;

    listaCarritoAdacter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        Dbcarrito dbcarrito = new Dbcarrito(CarritoActivity.this);
        APPData appData = APPData.getInstance();
        int Id_user = appData.getUserId();

        producto = dbcarrito.mortrarCanasta(Id_user);
        if (producto != null && !producto.isEmpty()) {
            Log.d("DEBUG", "Cantidad de elementos: " + producto.size());
            Toast.makeText(CarritoActivity.this, "numero "+producto.size(), Toast.LENGTH_SHORT).show();
            lista = findViewById(R.id.lista);
            lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            listaArrayProductos = new ArrayList<>();
            adapter = new listaCarritoAdacter(dbcarrito.mortrarCanasta(Id_user));
            lista.setAdapter(adapter);


        }else {
            Toast.makeText(CarritoActivity.this, "La lista de productos está vacía", Toast.LENGTH_SHORT).show();
        }





    }






}