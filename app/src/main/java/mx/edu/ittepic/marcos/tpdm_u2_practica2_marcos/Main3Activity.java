package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText idseguro, descripcion,fecha,telefono;
    Spinner tipo;
    Button insertar, regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        idseguro = findViewById(R.id.txtIdSeguro);
        descripcion = findViewById(R.id.txtDescripcion);
        fecha = findViewById(R.id.txtFechaS);
        telefono = findViewById(R.id.txtTelefonoS);
        tipo = findViewById(R.id.cmbTipo);
        insertar = findViewById(R.id.btnInsertarS);
        regresar = findViewById(R.id.btnRegresarS);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarSeguro();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(principal);
            }
        });
    }

    private void insertarSeguro(){
        String mensaje = "";
        Propietario propietario = new Propietario(this);
        Propietario vector[] = propietario.consultar();

        if(vector == null){
            mensaje = "Error! Antes debe crear un propietario";

        }else{
            Seguro seguro = new Seguro(this);
            vector = propietario.consultar(telefono.getText().toString());

            if(vector != null){
                boolean respuesta = seguro.insertar( new Seguro(idseguro.getText().toString(),descripcion.getText().toString(), fecha.getText().toString(),tipo.getSelectedItem().toString(),telefono.getText().toString()));

                if(respuesta){
                    mensaje = "Se pudo insertar un nuevo seguro";
                    telefono.setText("");
                    descripcion.setText("");
                    fecha.setText("");
                    tipo.setSelection(0);
                    telefono.setText("");
                }
                else {
                    mensaje = "Error! No se pudo insertar el seguro, respuesta de SQLite: "+seguro.error;
                }
            }else {
                mensaje = "Error! No existe ningún propietario con ese número de telefono";
            }
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
