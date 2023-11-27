package com.example.nanushop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import db.Dbhelper;

public class MainActivity extends AppCompatActivity {

    Button CrearBace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearBace =findViewById(R.id.CrearBase);
        CrearBace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dbhelper dbhelper =new Dbhelper(MainActivity.this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "No se Creo Base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

}
}