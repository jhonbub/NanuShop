package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Entidad.CARRITO;
import Entidad.Productos;

public class Dbcarrito extends Dbhelper {
    Context context;
    public Dbcarrito(@Nullable Context context) {
        super(context);
        this.context = context;
    }



    public long agragarproducto (String NOMBRE_PRODUCTO, int  VALOR_PRODUCTO , String Des_producto , String DISPONIBLE , String IMG , int ID_usuario , int ID_Producto){
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
            values.put("ID_usuario",ID_usuario);
            values.put("ID_Producto",ID_Producto);
            id = db.insert(Table_Carrito, null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public CARRITO VERCarrito(int ID){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        CARRITO Producto = null;
        Cursor cursorProducto;
        cursorProducto = db.rawQuery("SELECT * FROM  Table_Carrito where DISPONIBLE = 'si' AND ID_PRODUCTO ='"+ID+"' LIMIT 1;", null);


        if (cursorProducto.moveToFirst()){

            Producto= new CARRITO();

            Producto.setNombre_producto(cursorProducto.getString(0));
            Producto.setValor_producto(cursorProducto.getInt(1));
            Producto.setDes_producto(cursorProducto.getString(2));
            Producto.setDisponible(cursorProducto.getString(3));
            Producto.setImg(cursorProducto.getString(4));
            Producto.setID_USUARIO(cursorProducto.getInt(5));
            Producto.setID_producto(cursorProducto.getInt(6));
        }

        cursorProducto.close();
        return Producto;
    }


    public ArrayList<CARRITO> mortrarCanasta(int id_U){
        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<CARRITO> listaProductos = new ArrayList<>();
        CARRITO Producto = null;
        Cursor cursorProducto;
        cursorProducto = db.rawQuery("SELECT * FROM  Table_Carrito where DISPONIBLE = 'si' and ID_usuario ='"+id_U+"' ;", null);


        if (cursorProducto.moveToFirst()){
            do {
                Producto= new CARRITO();
                Producto.setNombre_producto(cursorProducto.getString(0));
                Producto.setValor_producto(cursorProducto.getInt(1));
                Producto.setDes_producto(cursorProducto.getString(2));
                Producto.setDisponible(cursorProducto.getString(3));
                Producto.setImg(cursorProducto.getString(4));
                Producto.setID_USUARIO(cursorProducto.getInt(5));
                Producto.setID_producto(cursorProducto.getInt(6));
                listaProductos.add(Producto);

            }while (cursorProducto.moveToNext());


        }

        cursorProducto.close();
        return listaProductos;
    }

    public boolean eliminaFila(int ID){

        Dbhelper dbhelper = new Dbhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        boolean correcto = false;

        try {
            // Usar la sentencia DELETE para eliminar una fila específica
            String[] args = {String.valueOf(ID)};
            db.delete("Table_Carrito", "ID_PRODUCTO = ?", args);

            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
