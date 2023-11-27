package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    private static final int Database_version = 4;
    private static final String nombre_Database = "NanuShop";
    public static final String Table_user = "table_user", Table_Ventas = "table_ventas", Table_productos = "table_productos" ,
            Table_Carrito = "Table_Carrito";

    public Dbhelper(@Nullable Context context) {
        super(context, nombre_Database, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + Table_user + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT NOT NULL," +
                "CORREO TEXT NOT NULL," +
                "CLAVE TEXT NOT NULL) ");

        sqLiteDatabase.execSQL("CREATE TABLE " + Table_Ventas + " (" +
                "ID_VENTA INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE_PRODUCTO TEXT NOT NULL," +
                "TEL TEXT NOT NULL, " +
                "DIRECCION TEXT NOT NULL," +
                "VALOR INTEGER NOT NULL," +
                "ID_usuario INTERGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + Table_productos + " (" +
                "ID_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE_PRODUCTO TEXT NOT NULL," +
                "VALOR_PRODUCTO INTEGER NOT NULL," +
                "Des_producto TEXT NOT NULL,"+
                "DISPONIBLE TEXT NOT NULL," +
                "IMG TEXT NOT NULL) ");

        sqLiteDatabase.execSQL("CREATE TABLE " + Table_Carrito + " (" +

                "NOMBRE_PRODUCTO TEXT NOT NULL," +
                "VALOR_PRODUCTO INTEGER NOT NULL," +
                "Des_producto TEXT NOT NULL,"+
                "DISPONIBLE TEXT NOT NULL," +
                "IMG TEXT NOT NULL," +
                "ID_usuario INTERGER NOT NULL," +
                "ID_Producto INTERGER NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_productos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Ventas);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_user);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Carrito);
        onCreate(sqLiteDatabase);
    }
}