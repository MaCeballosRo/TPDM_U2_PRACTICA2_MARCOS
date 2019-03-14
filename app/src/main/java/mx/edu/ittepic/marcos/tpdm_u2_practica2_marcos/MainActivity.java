package mx.edu.ittepic.marcos.tpdm_u2_practica2_marcos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Propietario vectorP[];
    Seguro vectorS[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarDatosSeguro(position);
            }
        });
    }

    private void mostrarDatosSeguro(final int pos){
        Intent consulta = new Intent(this, Main4Activity.class);

        consulta.putExtra("id",vectorS[pos].getIdseguro());
        consulta.putExtra("descripcion",vectorS[pos].getDescripcion());
        consulta.putExtra("fecha",vectorS[pos].getFecha());
        consulta.putExtra("tipo",vectorS[pos].getTipo());
        consulta.putExtra("telefono",vectorS[pos].getTelefono());

        startActivity(consulta);
    }

    protected void onStart(){
        Seguro seguro = new Seguro(this);
        Propietario propietario = new Propietario(this);
        vectorS = seguro.consultar();
        String[] datos = null;

        if(vectorS == null){
            datos = new String[1];
            datos[0] = "No hay seguros capturados";
        }else{
            datos = new String[vectorS.length];
            for(int i =0; i<vectorS.length; i++){
                Seguro temp = vectorS[i];

                vectorP = propietario.consultar(temp.getTelefono());

                datos[i] = temp.getDescripcion()+" -- Tipo: "+temp.getTipo()+"\n"+vectorP[0].getNombre();
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        lista.setAdapter(adaptador);

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nuevopropietario:
                Intent nuevoPropietario = new Intent(this,Main2Activity.class);
                startActivity(nuevoPropietario);
                break;
            case R.id.nuevoseguro:
                Intent nuevoSeguro = new Intent(this, Main3Activity.class);
                startActivity(nuevoSeguro);
                break;
            case R.id.consultaPropietario:
                Intent consulta = new Intent(this, Main5Activity.class);
                startActivity(consulta);
                break;
            case R.id.modicarEliminarProp:
                Intent modificaElimina = new Intent(this, Main6Activity.class);
                startActivity(modificaElimina);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
