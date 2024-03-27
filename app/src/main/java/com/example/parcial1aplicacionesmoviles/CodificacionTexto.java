package com.example.parcial1aplicacionesmoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CodificacionTexto extends AppCompatActivity {

    EditText edtTextoMultilinea;
    Button btncodificar, btndecodificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codificartexto);

        edtTextoMultilinea = findViewById(R.id.edtTextoMultilinea);
        btncodificar = findViewById(R.id.btncodificar);
        btndecodificar = findViewById(R.id.btndecodificar);

        btncodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtTextoMultilinea.getText().toString();
                if(texto.isEmpty()) {
                    edtTextoMultilinea.setError("Por favor ingresa texto para codificar.");
                    return;
                }
                String textoCodificado = codificarTexto(texto);
                edtTextoMultilinea.setText(textoCodificado);
            }
        });

        btndecodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtTextoMultilinea.getText().toString();
                if(texto.isEmpty()) {
                    edtTextoMultilinea.setError("Por favor ingresa texto para decodificar.");
                    return;
                }
                String textoDecodificado = decodificarTexto(texto);
                edtTextoMultilinea.setText(textoDecodificado);
            }
        });
    }

    public String codificarTexto(String texto) {
        StringBuilder textoCodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            switch (c) {
                case 'a':
                    textoCodificado.append("@");
                    break;
                case 'e':
                    textoCodificado.append("3");
                    break;
                case 'i':
                    textoCodificado.append("1");
                    break;
                case 'o':
                    textoCodificado.append("8");
                    break;
                case 'u':
                    textoCodificado.append("5");
                    break;
                case 'm':
                    textoCodificado.append("&");
                    break;
                case 'n':
                    textoCodificado.append("(");
                    break;
                case 'p':
                    textoCodificado.append(")");
                    break;
                case 'r':
                    textoCodificado.append("#");
                    break;
                default:
                    textoCodificado.append(c);
            }
        }
        return textoCodificado.toString();
    }

    public String decodificarTexto(String texto) {
        StringBuilder textoDecodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            switch (c) {
                case '@':
                    textoDecodificado.append("a");
                    break;
                case '3':
                    textoDecodificado.append("e");
                    break;
                case '1':
                    textoDecodificado.append("i");
                    break;
                case '8':
                    textoDecodificado.append("o");
                    break;
                case '5':
                    textoDecodificado.append("u");
                    break;
                case '&':
                    textoDecodificado.append("m");
                    break;
                case '(':
                    textoDecodificado.append("n");
                    break;
                case ')':
                    textoDecodificado.append("p");
                    break;
                case '#':
                    textoDecodificado.append("r");
                    break;
                default:
                    textoDecodificado.append(c);
            }
        }
        return textoDecodificado.toString();
    }

}