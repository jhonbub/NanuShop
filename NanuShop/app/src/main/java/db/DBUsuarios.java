package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Entidad.Usuarios;

public class DBUsuarios extends Dbhelper {
Context context;
    public DBUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarContacto (String Nombre, String Correo , String Clave){
        long id = 0;
        try {
            Dbhelper dbhelper = new Dbhelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBRE",Nombre);
            values.put("CORREO",Correo);
            values.put("CLAVE",Clave);
            id = db.insert(Table_user, null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList <Usuarios> mostrarusuarios(){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<Usuarios> listausuarios = new ArrayList<>();
        Usuarios usuario = null;
        Cursor cursorusuarios = null;
        Cursor cursorUsuarios = db.rawQuery("SELECT * FROM " + Dbhelper.Table_user, null);

    if (cursorusuarios.moveToFirst()){
        do {
            usuario= new Usuarios();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setNombre(cursorUsuarios.getString(1));
            usuario.setCorreo(cursorUsuarios.getString(2));
            usuario.setClave(cursorUsuarios.getString(3));
            listausuarios.add(usuario);

        }while (cursorusuarios.moveToNext());
        cursorUsuarios.close();

    }

        return listausuarios;
    }


    public Usuarios Validarsuarios(String correo , String Clave){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Usuarios usuario = null;
        Cursor cursorusuarios ;
        cursorusuarios = db.rawQuery("SELECT * FROM table_user  where CORREO = '"+correo+"' and '"+Clave+"' LIMIT 1;", null);

        if (cursorusuarios.moveToFirst()){

            usuario= new Usuarios();
            usuario.setId(cursorusuarios.getInt(0));
            usuario.setNombre(cursorusuarios.getString(1));
            usuario.setCorreo(cursorusuarios.getString(2));
            usuario.setClave(cursorusuarios.getString(3));


        }
        cursorusuarios.close();
        return usuario;
    }
}
