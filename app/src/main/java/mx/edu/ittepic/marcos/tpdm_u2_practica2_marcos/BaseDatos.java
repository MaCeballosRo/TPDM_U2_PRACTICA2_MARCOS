package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO (TELEFONO VARCHAR(20) PRIMARY KEY, NOMBRE VARCHAR(200) NOT NULL, DOMICILIO VARCHAR(200),FECHA VARCHAR(200))");
        db.execSQL("CREATE TABLE SEGURO (IDSEGURO VARCHAR(20) PRIMARY KEY, DESCRIPCION VARCHAR(200) NOT NULL, FECHA DATE, TIPO VARCHAR(50),TELEFONO VARCHAR(20) NOT NULL, FOREIGN KEY (TELEFONO) REFERENCES PROPIETARIO(TELEFONO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
