package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    EditText clave;
    Button consultar, regresar;
    TextView resultado;
    Propietario[] vector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        clave = findViewById(R.id.txtClave);
        consultar = findViewById(R.id.btnBuscar);
        regresar = findViewById(R.id.btnRegresar2);
        resultado = findViewById(R.id.resultado);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main5Activity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

    private void consultar(){
        Propietario propietario = new Propietario(this);
        vector = propietario.consultar(clave.getText().toString());

        String datos = "";
        resultado.setText("");

        if(vector == null){
            datos = "No exsite Propietario con el parámetro de búsqueda introducido";
        } else{
            String tel = "Teléfono: "+vector[0].getTelefono();
            String nom = "Nombre: "+vector[0].getNombre();
            String domicilio = "Domicilio: "+vector[0].getDomicilio();
            String fecha = "Fecha: "+vector[0].getFecha();
            datos = tel +"\n"+nom+"\n"+domicilio+"\n"+fecha;
        }

        resultado.setText(datos);
        vector = null;
    }
}
