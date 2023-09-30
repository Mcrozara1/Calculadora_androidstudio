package com.example.trabalho_calculadora;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextN1;
    private EditText editTextN2;
    private EditText editTextResposta;
    private EditText editTextMemo;
    private double memoria = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextN1 = findViewById(R.id.editTextN1);
        editTextN2 = findViewById(R.id.editTextN2);
        editTextResposta = findViewById(R.id.editTextResposta);
        editTextMemo = findViewById(R.id.editTextMemo);

        Button btnSomar = findViewById(R.id.btnSomar);
        Button btnSubtrair = findViewById(R.id.btnSubtrair);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);
        Button btnFinalizar = findViewById(R.id.btnFinalizar);
        Button btnAdicaomemo = findViewById(R.id.btnAdicaomemo);
        Button btnSubtracaomemo = findViewById(R.id.btnSubtracaomemo);
        Button btnChamamemo = findViewById(R.id.btnChamamemo);
        Button btnLimpamemo = findViewById(R.id.btnLimpamemo);

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular('+');
            }
        });

        btnSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular('-');
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular('*');
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular('/');
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicaomemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarMemoria();
            }
        });

        btnSubtracaomemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtrairMemoria();
            }
        });

        btnChamamemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarMemoria();
            }
        });

        btnLimpamemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparMemoria();
            }
        });
    }

    private void calcular(char operacao) {
        String valorN1 = editTextN1.getText().toString();
        String valorN2 = editTextN2.getText().toString();

        if (!valorN1.isEmpty() && !valorN2.isEmpty()) {
            double n1 = Double.parseDouble(valorN1);
            double n2 = Double.parseDouble(valorN2);
            double resultado = 0;

            switch (operacao) {
                case '+':
                    resultado = n1 + n2;
                    break;
                case '-':
                    resultado = n1 - n2;
                    break;
                case '*':
                    resultado = n1 * n2;
                    break;
                case '/':
                    if (n2 != 0) {
                        resultado = n1 / n2;
                    } else {
                        Toast.makeText(MainActivity.this, "Divisão por zero não é permitida.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

            editTextResposta.setText(String.valueOf(resultado));

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextResposta.getWindowToken(), 0);
        } else {
            Toast.makeText(MainActivity.this, "Preencha ambos os campos N1 e N2.", Toast.LENGTH_SHORT).show();
        }
    }
    private void adicionarMemoria() {
        String valorResposta = editTextResposta.getText().toString();
        if (!valorResposta.isEmpty()) {
            double resultado = Double.parseDouble(valorResposta);
            memoria += resultado;
            editTextMemo.setText(String.valueOf(memoria));
        } else {
            Toast.makeText(MainActivity.this, "Nenhum resultado para adicionar à memória.", Toast.LENGTH_SHORT).show();
        }
    }

    private void subtrairMemoria() {
        String valorResposta = editTextResposta.getText().toString();
        if (!valorResposta.isEmpty()) {
            double resultado = Double.parseDouble(valorResposta);
            memoria -= resultado;
            editTextMemo.setText(String.valueOf(memoria));
        } else {
            Toast.makeText(MainActivity.this, "Nenhum resultado para subtrair da memória.", Toast.LENGTH_SHORT).show();
        }
    }
    private void chamarMemoria() {
        String valorN1Temporario = editTextN1.getText().toString();


        editTextMemo.setText(String.valueOf(memoria));

        editTextN1.setText(valorN1Temporario);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextN1.getWindowToken(), 0);
    }
    private void limparMemoria() {
        memoria = 0.0;
        editTextMemo.setText(String.valueOf(memoria));
    }
}