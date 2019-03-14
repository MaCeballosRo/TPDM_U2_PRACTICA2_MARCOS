package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText telefono, nombre, domicilio, fecha;
    Button insertar, regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        telefono = findViewById(R.id.txtTelefono);
        nombre = findViewById(R.id.txtNombre);
        domicilio = findViewById(R.id.txtDomicilio);
        fecha = findViewById(R.id.txtFecha);
        insertar = findViewById(R.id.btnInsertar);
        regresar = findViewById(R.id.btnRegresar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void insertar(){
        String mensaje = "";
        Propietario propietario = new Propietario(this);
        boolean respuesta = propietario.insertar( new Propietario(telefono.getText().toString(),nombre.getText().toString(), domicilio.getText().toString(),fecha.getText().toString()));

        if(respuesta){
            mensaje = "Se pudo insertar un nuevo propietario";
            telefono.setText("");
            nombre.setText("");
            domicilio.setText("");
            fecha.setText("");
        }
        else {
            mensaje = "Error! No se pudo crear el propietario, respuesta de SQLite: "+propietario.error;
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }
}
