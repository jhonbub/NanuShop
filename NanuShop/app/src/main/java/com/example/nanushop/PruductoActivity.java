package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DBUsuarios;
import db.Dbproductos;

public class PruductoActivity extends AppCompatActivity {
    EditText txtNombreP,txtValor,txtDes,txtDisponible,txtIGM;
    Button agregarProductos , SALIR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruducto);
        agregarProductos= findViewById(R.id.agregarProductos);
        txtNombreP    = findViewById(R.id.NombreP  );

        txtValor   = findViewById(R.id.ValorP);
        txtDes        = findViewById(R.id.Descricion );
        txtIGM         = findViewById(R.id.IMG);
        agregarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int valor = Integer.parseInt(txtValor.getText().toString());
                    String disponible = "si";
                    Dbproductos dbproductos = new Dbproductos(PruductoActivity.this);

                    long id = dbproductos.insertarProducto(
                            txtNombreP.getText().toString(),
                            valor,
                            txtDes.getText().toString(),
                            disponible,
                            txtIGM.getText().toString()
                    );

                    if (id > 0) {
                        Toast.makeText(PruductoActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(PruductoActivity.this, "Error, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }
                } catch (NumberFormatException e) {
                    // Manejar la excepción si el valor no puede ser convertido a un entero
                    Toast.makeText(PruductoActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SALIR=findViewById(R.id.salir);
        SALIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PruductoActivity.this, menuADActivity.class);
                startActivity(intent);
            }
        });


    }


    private void limpiar (){
        txtNombreP .setText("");
        txtValor.setText("");
        txtDes.setText("");
        txtIGM.setText("");
    }
}