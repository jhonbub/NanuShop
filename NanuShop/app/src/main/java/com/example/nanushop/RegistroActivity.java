package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import db.DBUsuarios;

public class RegistroActivity extends AppCompatActivity {
EditText txtNombre,txtCorreo,txtClave;
Button Validar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Validar = findViewById(R.id.validar);
        txtNombre = findViewById(R.id.Nombre);
        txtClave  = findViewById(R.id.Clave);
        txtCorreo = findViewById(R.id.Correo);

        Validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ClaveCifrada = cifrarClave(txtClave.getText().toString());
                DBUsuarios dbUsuarios = new DBUsuarios(RegistroActivity.this);
               long id = dbUsuarios.insertarContacto(txtNombre.getText().toString(),txtCorreo.getText().toString().toUpperCase(),ClaveCifrada);

                if (id > 0) {
                    Toast.makeText(RegistroActivity.this, "Regitrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, loginActivity.class);
                    startActivity(intent);

                }
                else {

                    Toast.makeText(RegistroActivity.this, "Erro intente de nuevo", Toast.LENGTH_SHORT).show();
                    limpiar();
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
        txtCorreo.setText("");
        txtNombre.setText("");
        txtClave.setText("");
}
}