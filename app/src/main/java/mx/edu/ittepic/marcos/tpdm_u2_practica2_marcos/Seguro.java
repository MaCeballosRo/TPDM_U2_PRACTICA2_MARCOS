package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {

    private BaseDatos base;
    private String idseguro, descripcion,fecha,telefono, tipo;
    protected String error;

    public Seguro(Activity activity){ base = new BaseDatos(activity,"civil",null,1);}

    public Seguro (String idseguro, String descripcion, String fecha, String tipo,String telefono){
        this.idseguro = idseguro;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.telefono = telefono;
    }

    public Seguro[] consultar(){
        Seguro[] resultado = null;

        try{

            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM SEGURO",null);

            if(c.moveToFirst()){
                resultado = new Seguro[c.getCount()];
                int i=0;
                do{
                    String idseguro = c.getString(0);
                    String descripcion = c.getString(1);
                    String fecha = c.getString(2);
                    String tipo = c.getString(3);
                    String telefono = c.getString(4);
                    resultado[i] = new Seguro(idseguro,descripcion,fecha,tipo,telefono);
                    i++;
                }while(c.moveToNext());

            }else{
                error = "Error! No hay datos que mostrar";
            }
            transaccionConsultar.close();

        }catch(SQLiteException e){
            error = e.getMessage();
            return null;
        }

        return resultado;
    }

    public Seguro[] consultar(String clave){
        Seguro[] resultado = null;

        try{

            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM SEGURO WHERE IDSEGURO = '"+clave+"'",null);

            if(c.moveToFirst()){
                resultado = new Seguro[c.getCount()];
                int i=0;
                do{
                    String idseguro = c.getString(0);
                    String descripcion = c.getString(1);
                    String fecha = c.getString(2);
                    String tipo = c.getString(3);
                    String telefono = c.getString(4);
                    resultado[i] = new Seguro(idseguro,descripcion,fecha,tipo,telefono);
                    i++;
                }while(c.moveToNext());

            }else{
                error = "Error! No hay datos que mostrar";
            }
            transaccionConsultar.close();

        }catch(SQLiteException e){
            error = e.getMessage();
            return null;
        }

        return resultado;
    }

    public boolean insertar(Seguro seguro){

        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("IDSEGURO",seguro.getIdseguro());
            datos.put("DESCRIPCION",seguro.getDescripcion());
            datos.put("FECHA",seguro.getFecha());
            datos.put("TIPO",seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());

            long resultado = transaccionInsertar.insert("SEGURO", null, datos);

            transaccionInsertar.close();

            if(resultado == -1){
                return false;
            }

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean eliminar(Seguro seguro){
        int resultado;

        try{
            String[] vector = {seguro.getIdseguro()};
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();

            resultado = transaccionEliminar.delete("SEGURO", "IDSEGURO = ?",vector);

            transaccionEliminar.close();

            if(resultado == -1){
                return false;
            }
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }

        return resultado>0;
    }

    public boolean modificar (Seguro seguro){

        try{
            String[] vector = {seguro.getIdseguro()};
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION",seguro.getDescripcion());
            datos.put("FECHA",seguro.getFecha());
            datos.put("TIPO",seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());

            SQLiteDatabase transaccionModificar = base.getWritableDatabase();

            long resultado = transaccionModificar.update("SEGURO",datos,"IDSEGURO = ?",vector);

            transaccionModificar.close();

            if(resultado == -1){
                return false;
            }
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getIdseguro() {
        return idseguro;
    }

    public void setIdseguro(String idseguro) {
        this.idseguro = idseguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
