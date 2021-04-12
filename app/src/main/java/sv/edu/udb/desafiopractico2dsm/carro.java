package sv.edu.udb.desafiopractico2dsm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class carro extends AppCompatActivity {
    FirebaseFirestore db;
    private Map carrito;
    private ArrayList carroList;
    LinearLayout master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);
        db = FirebaseFirestore.getInstance();
        master = findViewById((R.id.master));

        db.collection("carrito")
        .document("jose")
        .get()
        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        carrito = document.getData();
                          carroList = (ArrayList) carrito.get("carrito");
                          for(Object a:carroList){
                              HashMap data = (HashMap) a;
                              String idDoc = (String) data.get("medicamento");
                              int cantMed =  Integer.parseInt(data.get("cantidad").toString());
                              getMeds(idDoc,cantMed);
                          }
//                        }
                    } else Log.d("get-Firebase", "No such document");

                } else  Log.w("get-Firebase", "Error getting documents.", task.getException());
            }
        });
    }


    private void getMeds(String idDoc, int cant) {
        LinearLayout.LayoutParams layParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);

        LinearLayout lineMain = new LinearLayout(getApplicationContext());
        lineMain.setPadding(5,5,5,5);
        lineMain.setOrientation(LinearLayout.HORIZONTAL);
        lineMain.setLayoutParams(layParams);

        ImageView medImg = new ImageView(getApplicationContext());
        medImg.setPadding(15,15,15,15);
        medImg.setLayoutParams(layParams);

        LinearLayout lineVert1 = new LinearLayout(getApplicationContext());
        lineVert1.setOrientation(LinearLayout.VERTICAL);
        lineVert1.setLayoutParams(layParams);

        LinearLayout lineVert2 = new LinearLayout(getApplicationContext());
        lineVert2.setOrientation(LinearLayout.VERTICAL);
        lineVert2.setLayoutParams(layParams);



        Log.d("get-Firebase","ID => "+idDoc);
        db.collection("medicamentos")
            .document(idDoc)//ZpOSrUN6lsB3xXLarVH7
            .get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()) {
                            Map data = doc.getData();
                            Log.d("get-Firebase", "datos : " + data.get("imgUrl"));//medicamentos.add(doc.getData());

                            Picasso.get().load((String) data.get("imgUrl")).resize(300,300).into(medImg);
                            newTxtView(lineVert1,"Medicamento: " + data.get("nombre"));
                            newTxtView(lineVert1,"Precio Unidad: " + data.get("precio"));
                            newTxtView(lineVert1,"Laboratorio: " + data.get("laboratorio"));
                            newTxtView(lineVert2,"Descripcion: " + data.get("descripcion"));
                            newTxtView(lineVert2,"Cantidad pedida: " + cant);
                            newTxtView(lineVert2,"Costo Total : " + (Double.parseDouble(data.get("precio").toString())*cant));

                        }
                        else Log.d("get-Firebase", "No such document");
                    }
                    else Log.w("get-Firebase", "Error getting documents.", task.getException());
                }
            });

        lineMain.addView(medImg);
        lineMain.addView(lineVert1);
        lineMain.addView(lineVert2);
        master.addView(lineMain);
    }

    private void newTxtView(LinearLayout ll, String txt){
        TextView txtView = new TextView(getApplicationContext());
        LinearLayout.LayoutParams layParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200,1.0f);
        txtView.setLayoutParams(layParams);
        txtView.setText(txt);
        ll.addView(txtView);
    }
}