package sv.edu.udb.desafiopractico2dsm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.udb.desafiopractico2dsm.Datos.Productos;

public class AddProductosActivity extends AppCompatActivity {
    EditText ENomb,EPres,ELabo,EDesc,EPrec ;
    String key="",Nomb="",Pres="",Labo="",Desc="",Prec="",accion="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_producto);
        inicializar();

    }

    private void inicializar() {
        ENomb= findViewById(R.id.txtNomb);
        EPres= findViewById(R.id.txtPres);
        ELabo= findViewById(R.id.txtLab);
        EDesc= findViewById(R.id.txtDesc);
        EPrec= findViewById(R.id.txtPrec);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        Nomb = datos.getString("nombre");
        Pres=datos.getString("presentacion");
        Labo = datos.getString("laboratorio");
        Desc=datos.getString("descripcion");
        Prec = datos.getString("precio");
        accion=datos.getString("accion");
        ENomb.setText(Nomb);
        EPres.setText(Pres);
        EPrec.setText(Prec);
        ELabo.setText(Labo);
        EDesc.setText(Desc);

    }

    public void guardar(View v){
        String nombre = ENomb.getText().toString();
        String presentacion = EPres.getText().toString();
        String precio = EPrec.getText().toString();
        String laboratorio = ELabo.getText().toString();
        String descripcion = EDesc.getText().toString();
        // Se forma objeto persona
        Productos producto = new Productos(nombre,presentacion,laboratorio,descripcion,precio);

        if (accion.equals("a")) { //Agregar usando push()
            ProductosActivity.refProductos.push().setValue(producto);
        }
        else // Editar usando setValue
        {
            ProductosActivity.refProductos.child(key).setValue(producto);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }

}
