package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
    EditText telefono, nombre, domicilio, fecha;
    Button modificar, eliminar, regresar;
    Propietario[] vector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        telefono = findViewById(R.id.txtTelefonoP);
        nombre = findViewById(R.id.txtNombreP);
        domicilio = findViewById(R.id.txtDomicilioP);
        fecha = findViewById(R.id.txtFechaP1);
        modificar = findViewById(R.id.btnModificarP);
        eliminar = findViewById(R.id.btnEliminarP);
        regresar = findViewById(R.id.btnRegresarP);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        final EditText peticion = new EditText(this);

        alerta.setTitle("Atención")
                .setMessage("¿Cuál es el nombre o teléfono del propietario que desea modificar/eliminar")
                .setView(peticion)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        extraerDatos(peticion.getText().toString());
                    }
                })
                .show();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main6Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void extraerDatos(String clave){
        Propietario propietario = new Propietario(this);
        vector = propietario.consultar(clave);

        if(vector == null){
            String datos = "No exsite Propietario con el parametro de búsqueda establecido";
            Toast.makeText(this,datos,Toast.LENGTH_SHORT);
            Intent regresar = new Intent(Main6Activity.this, MainActivity.class);
            startActivity(regresar);
        }else{
            telefono.setText(vector[0].getTelefono());
            nombre.setText(vector[0].getNombre());
            domicilio.setText(vector[0].getDomicilio());
            fecha.setText(vector[0].getFecha());
        }
    }

    private void modificar(){

    }

    private void eliminar(){

    }
}
