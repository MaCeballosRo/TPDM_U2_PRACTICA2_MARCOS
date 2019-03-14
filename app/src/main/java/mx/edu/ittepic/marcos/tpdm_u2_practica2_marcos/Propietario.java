package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Propietario {

    private BaseDatos base;
    private String telefono, nombre, domicilio,fecha;
    protected String error;

    public Propietario(Activity activity){ base = new BaseDatos( activity, "aseguradora",null,1);}

    public Propietario(String telefono, String nombre, String domicilio, String fecha){
        this.telefono = telefono;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fecha = fecha;
    }

    public Propietario[] consultar(){
        Propietario[] resultado = null;

        try{

            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM PROPIETARIO",null);

            if(c.moveToFirst()){
                resultado = new Propietario[c.getCount()];
                int i = 0;
                do{
                    String telefono = c.getString(0);
                    String nombre = c.getString(1);
                    String domicilio = c.getString(2);
                    String fecha = c.getString(3);
                    resultado[i] = new Propietario(telefono,nombre,domicilio,fecha);
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

    public Propietario[] consultar(String clave){
        Propietario[] resultado = null;

        try{

            SQLiteDatabase transaccionConsultar = base.getReadableDatabase();
            Cursor c = transaccionConsultar.rawQuery("SELECT * FROM PROPIETARIO WHERE telefono = '"+clave+"' OR nombre = '"+clave+"'",null);

            if(c.moveToFirst()){
                resultado = new Propietario[c.getCount()];
                int i = 0;
                do{
                    String telefono = c.getString(0);
                    String nombre = c.getString(1);
                    String domicilio = c.getString(2);
                    String fecha = c.getString(3);
                    resultado[i] = new Propietario(telefono,nombre,domicilio,fecha);
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

    public boolean insertar (Propietario propietario){

        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO",propietario.getTelefono());
            datos.put("NOMBRE",propietario.getNombre());
            datos.put("DOMICILIO",propietario.getDomicilio());
            datos.put("FECHA",propietario.getFecha());

            long resultado = transaccionInsertar.insert("PROPIETARIO",null,datos);

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

    public boolean eliminar (Propietario propietario){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String[] vector = {propietario.getTelefono()};

            resultado = transaccionEliminar.delete("PROPIETARIO", "TELEFONO = ?",vector);

            transaccionEliminar.close();

            if(resultado == -1){
                return false;
            }

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado >1;
    }

    public boolean modificar (Propietario propietario){

        try{
            String[] vector = {propietario.getTelefono()};
            ContentValues datos = new ContentValues();

            datos.put("NOMBRE",propietario.getNombre());
            datos.put("DOMICILIO",propietario.getDomicilio());
            datos.put("FECHA",propietario.getFecha());

            SQLiteDatabase transaccionModificar = base.getWritableDatabase();

            long resultado = transaccionModificar.update("PROPIETARIO",datos,"TELEFONO = ?",vector);

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
