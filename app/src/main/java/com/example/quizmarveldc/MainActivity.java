package com.example.quizmarveldc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btnJogar;
    public  EditText nomePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogar = (Button) findViewById(R.id.btnJogar);
        nomePlayer = (EditText) findViewById(R.id.inputNomePlayer);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaQuiz();
            }
        });

    }

    public void abrirTelaQuiz(){
        Intent intentTelaQuiz = new Intent(this, ActivityTelaQuiz.class);
        intentTelaQuiz.putExtra("Chave", nomePlayer.getText().toString());

        startActivity(intentTelaQuiz);
        finish();
    }

}