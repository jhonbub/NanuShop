package db;

import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import Entidad.Productos;
import Entidad.Usuarios;

public class Dbproductos extends Dbhelper{

    Context context;
    public Dbproductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto (String NOMBRE_PRODUCTO, int  VALOR_PRODUCTO , String Des_producto , String DISPONIBLE , String IMG){
        long id = 0;
        try {
            Dbhelper dbhelper = new Dbhelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBRE_PRODUCTO",NOMBRE_PRODUCTO);
            values.put("VALOR_PRODUCTO",VALOR_PRODUCTO);
            values.put("Des_producto" , Des_producto);
            values.put("DISPONIBLE" , DISPONIBLE);
            values.put("IMG" , IMG);
            id = db.insert(Table_productos, null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Productos> mostraProductos(){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos Producto = null;
        Cursor cursorProducto;
         cursorProducto = db.rawQuery("SELECT * FROM  Table_productos where DISPONIBLE = 'si' ;", null);


        if (cursorProducto.moveToFirst()){
            do {
                Producto= new Productos();
                Producto.setId(cursorProducto.getInt(0));
                Producto.setNombre_producto(cursorProducto.getString(1));
                Producto.setValor_producto(cursorProducto.getInt(2));
                Producto.setDes_producto(cursorProducto.getString(3));
                Producto.setDisponible(cursorProducto.getString(4));
                Producto.setImg(cursorProducto.getString(5));
                listaProductos.add(Producto);

            }while (cursorProducto.moveToNext());


        }

        cursorProducto.close();
        return listaProductos;
    }


    public ArrayList<Productos> mostraProductostotal(){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos Producto = null;
        Cursor cursorProducto;
        cursorProducto = db.rawQuery("SELECT * FROM  Table_productos  ;", null);


        if (cursorProducto.moveToFirst()){
            do {
                Producto= new Productos();
                Producto.setId(cursorProducto.getInt(0));
                Producto.setNombre_producto(cursorProducto.getString(1));
                Producto.setValor_producto(cursorProducto.getInt(2));
                Producto.setDes_producto(cursorProducto.getString(3));
                Producto.setDisponible(cursorProducto.getString(4));
                Producto.setImg(cursorProducto.getString(5));
                listaProductos.add(Producto);

            }while (cursorProducto.moveToNext());


        }

        cursorProducto.close();
        return listaProductos;
    }

    public Productos VERProducto(int ID){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Productos Producto = null;
        Cursor cursorProducto;
        cursorProducto = db.rawQuery("SELECT * FROM  Table_productos where DISPONIBLE = 'si' AND ID_PRODUCTO ='"+ID+"' LIMIT 1;", null);


        if (cursorProducto.moveToFirst()){

                Producto= new Productos();
                Producto.setId(cursorProducto.getInt(0));
                Producto.setNombre_producto(cursorProducto.getString(1));
                Producto.setValor_producto(cursorProducto.getInt(2));
                Producto.setDes_producto(cursorProducto.getString(3));
                Producto.setDisponible(cursorProducto.getString(4));
                Producto.setImg(cursorProducto.getString(5));

        }

        cursorProducto.close();
        return Producto;
    }



    public boolean nodisponible(int ID){

        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
boolean correcto = false;
try {
    db.execSQL("UPDATE Table_productos SET DISPONIBLE = 'NO' WHERE ID_PRODUCTO = '"+ID+"';" );
    correcto = true;
}catch (Exception ex){
    ex.toString();
    correcto=false;
}
finally {
    db.close();
}

return correcto;


    }
    public boolean disponible(int ID){

        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        boolean correcto = false;
        try {
            db.execSQL("UPDATE Table_productos SET DISPONIBLE = 'si' WHERE ID_PRODUCTO = '"+ID+"';" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }
        finally {
            db.close();
        }

        return correcto;


    }
}
