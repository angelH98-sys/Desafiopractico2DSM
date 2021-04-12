package sv.edu.udb.desafiopractico2dsm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sv.edu.udb.desafiopractico2dsm.Datos.Productos;

public class AdaptadorProductos extends ArrayAdapter<Productos> {
    List<Productos> productos;
    private Activity context;

    public AdaptadorProductos(@NonNull Activity context, @NonNull List<Productos> productos) {
        super(context, R.layout.producto, productos);
        this.context = context;
        this.productos = productos;
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
            rowview = layoutInflater.inflate(R.layout.producto,null);
        else rowview = view;

        TextView txtNombre = rowview.findViewById(R.id.txtNombre);
        TextView txtDescripcion = rowview.findViewById(R.id.txtDescripcion);
        TextView txtLaboratorio = rowview.findViewById(R.id.txtLaboratorio);
        TextView txtPrecio = rowview.findViewById(R.id.txtPrecio);
        TextView txtPresentacion = rowview.findViewById(R.id.txtPresentacion);

        txtNombre.setText("Nombre : "+productos.get(position).getNomb());
        txtNombre.setText("Descripcion : "+productos.get(position).getDesc());
        txtNombre.setText("Laboratorio : "+productos.get(position).getLabo());
        txtNombre.setText("Precio : "+productos.get(position).getPrec());
        txtNombre.setText("Presentacion : "+productos.get(position).getPres());



        return rowview;
    }
}
