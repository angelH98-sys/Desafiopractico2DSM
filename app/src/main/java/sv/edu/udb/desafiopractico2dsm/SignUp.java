package sv.edu.udb.desafiopractico2dsm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText editText;
    private ProgressBar progressBar;
    private Button buttonSave;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        buttonSave = findViewById(R.id.btnLogin);

    }

    public void signUpNewUser(View v){

        progressBar.setVisibility(View.VISIBLE);
        buttonSave.setEnabled(false);

        String mail, password;
        editText = findViewById(R.id.email);
        mail = editText.getText().toString();
        editText = findViewById(R.id.password);
        password = editText.getText().toString();

        if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)){
            showToast("Completa los campos para continuar");
            progressBar.setVisibility(View.GONE);
            buttonSave.setEnabled(true);
            return;
        }

        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showToast("¡Registro exitoso!");
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            showToast("No fue posible registrar el usuario");
                            progressBar.setVisibility(View.GONE);
                            buttonSave.setEnabled(true);
                        }
                    }
                });

    }

    private void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}