package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Entidad.APPData;
import Entidad.Usuarios;
import db.DBUsuarios;
import db.Dbhelper;
public class loginActivity extends AppCompatActivity {
    Button login ,Registrar;
EditText Correo,Clave;
Usuarios usuario;
public String nombre_login;
public int ID_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar los botones después de setContentView
        login = findViewById(R.id.validar);
        Registrar = findViewById(R.id.Resgistrar);
        Correo = findViewById(R.id.Correo);
        Clave = findViewById(R.id.Clave);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ClaveCifrada = cifrarClave(Clave.getText().toString());
                DBUsuarios dbUsuarios = new DBUsuarios(loginActivity.this);
                  usuario = dbUsuarios.Validarsuarios(Correo.getText().toString().toUpperCase(),ClaveCifrada);


                if (usuario == null) {
                    // Mostrar un mensaje de error
                    Toast.makeText(loginActivity.this, "Error en los datos, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                    // Limpiar los campos de entrada
                    limpiar();
                } else {
                    nombre_login = usuario.getNombre();
                    APPData appData = APPData.getInstance();
                    appData.setUserId(usuario.getId());

                    // Mostrar un mensaje de éxito
                    Toast.makeText(loginActivity.this, "¡Bien benido!   "+nombre_login, Toast.LENGTH_SHORT).show();

                    // Redirigir a la actividad AD principal de la aplicación


                    if (usuario.getId()==1|| usuario.getId()==3|| usuario.getId()==4){
                        Intent intent = new Intent(loginActivity.this, menuADActivity.class);

                        startActivity(intent);
                    }else { // Redirigir a la actividad principal de la aplicación
                        Intent intent = new Intent(loginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private String cifrarClave(String contraseña) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(contraseña.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }


    }
    private void limpiar (){
        Correo.setText("");
        Clave.setText("");
    }
}