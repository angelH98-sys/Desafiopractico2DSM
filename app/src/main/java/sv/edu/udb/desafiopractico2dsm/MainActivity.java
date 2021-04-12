package sv.edu.udb.desafiopractico2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      prueba de actividad de carro
//        Intent carro = new Intent(this, carro.class);
//        startActivity(carro);
//        Fin de prueba
    }

    public void toSignUpActivity(View v){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}