package com.example.quizmarveldc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class ActivityTelaQuiz extends AppCompatActivity implements View.OnClickListener{


    public TextView txtNomePlayer;
    public TextView txtPontuacaoPlayer, campoPerguntas, qtdQuestoes;

    public Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    int score = 0, totalPerguntas = PerguntasRespostas.perguntas.length, questaoIndex = 0;
    int percentualAcertos = 0;
    String btnValue = "";
    String respostaSelecionada = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);

        txtNomePlayer = (TextView) findViewById(R.id.txtNomePLayer);
        txtPontuacaoPlayer = (TextView) findViewById(R.id.txtScorePLayer);
        campoPerguntas = (TextView) findViewById(R.id.txtCampoPerguntas);
        qtdQuestoes = (TextView) findViewById(R.id.txtQtdQuestao);
        btnAnswer1 = (Button) findViewById(R.id.btnResposta1);
        btnAnswer2 = (Button) findViewById(R.id.btnResposta2);
        btnAnswer3 = (Button) findViewById(R.id.btnResposta3);
        btnAnswer4 = (Button) findViewById(R.id.btnResposta4);

        nextQuestion();



        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer1.getText().toString();
                verificarResposta(btnValue, btnAnswer1);

            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer2.getText().toString();
                verificarResposta(btnValue, btnAnswer2);
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer3.getText().toString();
                verificarResposta(btnValue, btnAnswer3);
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer4.getText().toString();
                verificarResposta(btnValue, btnAnswer4);
            }
        });


        qtdQuestoes.setText("Perguntas: "+ questaoIndex+"/"+totalPerguntas);


        //String nomePLayer = "Player: " + getIntent().getStringExtra("Chave");
        String nome = "Player: " + getIntent().getStringExtra("Chave");
        //Integer score = 0;

        txtNomePlayer.setText(nome);


    }

    @Override
    public void onClick(View v) {

    }

    public void nextQuestion(){
        if(questaoIndex != totalPerguntas) {
            btnAnswer1.setBackgroundColor(Color.WHITE);
            btnAnswer2.setBackgroundColor(Color.WHITE);
            btnAnswer3.setBackgroundColor(Color.WHITE);
            btnAnswer4.setBackgroundColor(Color.WHITE);

            campoPerguntas.setText(PerguntasRespostas.perguntas[questaoIndex]);
            btnAnswer1.setText(PerguntasRespostas.respostas[questaoIndex][0]);
            btnAnswer2.setText(PerguntasRespostas.respostas[questaoIndex][1]);
            btnAnswer3.setText(PerguntasRespostas.respostas[questaoIndex][2]);
            btnAnswer4.setText(PerguntasRespostas.respostas[questaoIndex][3]);
        }else{
            fimQuiz();
        }
    }

    public void fimQuiz(){
        Intent intentTelaFim = new Intent(this, FimQuiz.class);
        intentTelaFim.putExtra("ChaveScore", String.valueOf(score));
    }

    public void verificarResposta(String clickedBtn, Button btnAnswer) {
        if(clickedBtn.equals(PerguntasRespostas.respostas_corretas[questaoIndex])){
            btnAnswer.setBackgroundColor(Color.GREEN);
            percentualAcertos+=1;
            score += 100;
            questaoIndex++;
            txtPontuacaoPlayer.setText("Score: " + String.valueOf(score)+" pts");
            percentualAcertos++;
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    },
            1000);
        }else{
            btnAnswer.setBackgroundColor(Color.RED);
            questaoIndex++;
            txtPontuacaoPlayer.setText("Score: " + String.valueOf(score)+" pts");
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    },
                    1000);
        }
    }
}