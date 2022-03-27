package edu.sv.udb.realtimedatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;
import edu.sv.udb.realtimedatabase.datos.Persona;

public class AdaptadorPersona extends ArrayAdapter<Persona> {

    List<Persona> personas;
    private Activity context;

    public AdaptadorPersona(@NonNull Activity context, @NonNull List<Persona> personas) {
        super(context, R.layout.persona_layout, personas);
        this.context = context;
        this.personas = personas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        if (view == null)
             rowview = layoutInflater.inflate(R.layout.persona_layout,null);
        else rowview = view;

        TextView tvNombre = rowview.findViewById(R.id.tvNombre);
        TextView tvDUI = rowview.findViewById(R.id.tvDUI);
        TextView tvFecha = rowview.findViewById(R.id.tvFecha);
        TextView tvGenero = rowview.findViewById(R.id.tvGenero);
        TextView tvPeso = rowview.findViewById(R.id.tvPeso);
        TextView tvAltura = rowview.findViewById(R.id.tvAltura);


        tvNombre.setText("Nombre : "+personas.get(position).getNombre());
        tvDUI.setText("DUI : " + personas.get(position).getDui());
        tvFecha.setText("Fecha de nacimiento: " + personas.get(position).getFecha());
        tvPeso.setText("Peso: " + personas.get(position).getPeso() + " lb");
        tvAltura.setText("Altura: " + personas.get(position).getAltura() + " cm");
        tvGenero.setText("Genero: " + personas.get(position).getGenero());

        return rowview;
    }
}
