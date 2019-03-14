package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText idseguro, descripcion, fecha, telefono;
    Spinner tipo;
    Button eliminar, modificar, regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        idseguro = findViewById(R.id.idSeguroM);
        descripcion = findViewById(R.id.descripcionM);
        fecha = findViewById(R.id.fechaM);
        telefono = findViewById(R.id.telefonoM);
        tipo = findViewById(R.id.cmbTipoM);
        eliminar = findViewById(R.id.btnEliminarM);
        modificar = findViewById(R.id.btnModificarM);
        regresar = findViewById(R.id.btnRegresarM);

        Bundle parametros = getIntent().getExtras();

        idseguro.setText(parametros.getString("id"));
        descripcion.setText(parametros.getString("descripcion"));
        fecha.setText(parametros.getString("fecha"));
        String f = parametros.getString("tipo");
        switch (f){
            case "Casa":
                tipo.setSelection(0);
                break;
            case "Auto":
                tipo.setSelection(1);
                break;
            case "Médico":
                tipo.setSelection(2);
                break;
        }
        telefono.setText(parametros.getString("telefono"));

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void eliminar(){
        Seguro seguro = new Seguro(this);
        String mensaje = "";

        boolean respuesta = seguro.eliminar(new Seguro(idseguro.getText().toString(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getSelectedItem().toString(),telefono.getText().toString()));

        if(respuesta){
            mensaje = "Se eliminó correctamente el Seguro "+idseguro.getText().toString();
        }else{
            mensaje = "Error! Algo salió mal: "+seguro.error;
        }

        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        Intent salir = new Intent(this, MainActivity.class);
        startActivity(salir);
    }

    private void modificar(){
        String mensaje = "";
        Seguro seguro = new Seguro(this);
        Propietario propietario = new Propietario(this);
        Propietario vector[] = propietario.consultar(telefono.getText().toString());

        if(vector == null){
            mensaje = "Error! No existe propietario con el número de telefono establecido";
        }else{
            boolean respuesta = seguro.modificar(new Seguro(idseguro.getText().toString(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getSelectedItem().toString(),telefono.getText().toString()));

            if(respuesta){
                mensaje = "Se actualizó correctamente el Seguro "+idseguro.getText().toString();
            }else{
                mensaje = "Error! Algo salió mal: "+seguro.error;
            }
        }
        vector = null;
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
}
