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
import Entidad.Productos;
import Entidad.Usuarios;
import db.DBUsuarios;
import db.Dbcarrito;
import db.Dbproductos;

public class verActivity extends AppCompatActivity {

    TextView textNombreProducto,textValor,textDescProducto;
Button Regresar, Agregar_carrito;
    ImageView imageView;
Productos productos;
    Usuarios usuario;
int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
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
        Dbproductos dbproductos = new Dbproductos(verActivity.this);
        productos = dbproductos.VERProducto(id);

        if(productos != null) {
            textNombreProducto.setText(productos.getNombre_producto());
            textValor.setText(String.valueOf(productos.getValor_producto()));

            textDescProducto.setText(productos.getDes_producto());
            String nombreImagen = productos.getImg();
            int resId = getResources().getIdentifier(
                    nombreImagen, "drawable", getPackageName());

            // Establece la imagen dinámicamente
            imageView.setImageResource(resId);

        }
        Agregar_carrito=findViewById(R.id.AgregarCarrito);
        Agregar_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    productos = dbproductos.VERProducto(id);
                    int valor = productos.getValor_producto();
                    String disponible = "si";
                    String img= productos.getImg();
                    String nombre= productos.getNombre_producto();
                    String producto= productos.getDes_producto();
                     Dbcarrito dbcarrito = new Dbcarrito(verActivity.this);
                    loginActivity loginActivity = new loginActivity();
                    APPData appData = APPData.getInstance();
                    int Id_user = appData.getUserId();

                    Toast.makeText(verActivity.this, Id_user+"numero "+nombre+disponible, Toast.LENGTH_SHORT).show();

                    dbcarrito.agragarproducto(nombre,valor,producto,disponible,img,Id_user,id);


                        Toast.makeText(verActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                        int id_P = productos.getId();
                         dbproductos.nodisponible(id_P);
                        Intent intent = new Intent(verActivity.this, MenuActivity.class);
                        startActivity(intent);


                } catch (NumberFormatException e) {
                    // Manejar la excepción si el valor no puede ser convertido a un entero
                    Toast.makeText(verActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                }
            }



        });
        Regresar=findViewById(R.id.Regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}