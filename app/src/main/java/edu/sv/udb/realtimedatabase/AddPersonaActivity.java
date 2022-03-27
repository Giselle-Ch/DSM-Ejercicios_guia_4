package edu.sv.udb.realtimedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import edu.sv.udb.realtimedatabase.datos.Persona;


public class AddPersonaActivity extends AppCompatActivity {
    EditText edtDUI, edtNombre, edtPeso, edtAltura, edtFecha;
    Spinner edtGenero;
    String key="",nombre="",dui="",accion="", peso="", altura="", fecha="", genero="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        edtGenero = (Spinner) findViewById(R.id.edtGenero);

        //  Creando las opciones en los spinner
        String []opciones = {"Masculino", "Femenino"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        //  Haciendo referencia al objeto adapter
        edtGenero.setAdapter(adapter);

        inicializar();

    }

    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDUI = findViewById(R.id.edtDUI);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        edtFecha = findViewById(R.id.edtFechaNacimiento);


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        nombre=datos.getString("nombre");
        accion=datos.getString("accion");
        fecha = datos.getString("fechaNacimiento");
        genero = datos.getString("genero");
        peso = datos.getString("peso");
        altura = datos.getString("altura");


        edtDUI.setText(dui);
        edtNombre.setText(nombre);
        edtFecha.setText(fecha);
        edtPeso.setText(peso);
        edtAltura.setText(altura);

    }

    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String dui = edtDUI.getText().toString();
        String fecha = edtFecha.getText().toString();
        String genero = edtGenero.getSelectedItem().toString();
        String peso = edtPeso.getText().toString();
        String altura = edtAltura.getText().toString();

        // Se forma objeto persona
        Persona persona = new Persona(dui,nombre,fecha,genero,peso,altura);

        if (accion.equals("a")) { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }

}
