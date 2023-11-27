package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Entidad.APPData;
import Entidad.CARRITO;
import db.Dbcarrito;
import db.Dbproductos;

public class SacarActivity extends AppCompatActivity {
    TextView textNombreProducto,textValor,textDescProducto;
    Button Regresar, eliminar_producto;
    ImageView imageView;
    CARRITO carrito;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sacar);
        textNombreProducto=findViewById(R.id.textNombreProducto);
        textValor=findViewById(R.id.textValor);
        textDescProducto=findViewById(R.id.textDescProducto);
        imageView = findViewById(R.id.imageView);
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras ==null){
                id= Integer.parseInt(null);
            }else {
                id= extras.getInt("ID");
            }

        }else {
            id=(int)savedInstanceState.getSerializable("ID");

        }
        Dbcarrito dbcarrito = new Dbcarrito(SacarActivity.this);
        carrito = dbcarrito.VERCarrito(id);

        if(carrito != null) {
            textNombreProducto.setText(carrito.getNombre_producto());
            textValor.setText(String.valueOf(carrito.getValor_producto()));

            textDescProducto.setText(carrito.getDes_producto());
            String nombreImagen = carrito.getImg();
            int resId = getResources().getIdentifier(
                    nombreImagen, "drawable", getPackageName());

            // Establece la imagen dinámicamente
            imageView.setImageResource(resId);

        }
        eliminar_producto = findViewById(R.id.EliminardelCarrito);
        eliminar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    carrito = dbcarrito.VERCarrito(id);
                    dbcarrito.eliminaFila(id);

                    Toast.makeText(SacarActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();

                    Dbproductos dbproductos = new Dbproductos(SacarActivity.this);

                    dbproductos.disponible(id);
                    Intent intent = new Intent(SacarActivity.this, MenuActivity.class);
                    startActivity(intent);


                } catch (NumberFormatException e) {
                    // Manejar la excepción si el valor no puede ser convertido a un entero
                    Toast.makeText(SacarActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}