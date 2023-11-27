package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class menuADActivity extends AppCompatActivity {
Button agregarProducto , Recibos,Cuentas,Salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adactivity);
        agregarProducto=findViewById(R.id.agregarProductos);
        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuADActivity.this, PruductoActivity.class);
                startActivity(intent);
            }
        });
        Recibos =findViewById(R.id.Recibos);
        Recibos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuADActivity.this,InventarioActivity.class);
                startActivity(intent);
            }
        });
        Cuentas =findViewById(R.id.Cuentas);
        Salir =findViewById(R.id.salir);
        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuADActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

    }
}