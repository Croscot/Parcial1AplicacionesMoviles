package com.example.parcial1aplicacionesmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText inputNum;
    Spinner spinner_operaciones;
    TextView resultado;
    Button calcular, generar;
    ArrayAdapter<CharSequence> adapter;
    int posicionItemSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNum = (EditText) findViewById(R.id.edtnumeros);
        spinner_operaciones = (Spinner) findViewById(R.id.spinner2);
        resultado = (TextView) findViewById(R.id.txtresultado);
        calcular = (Button) findViewById(R.id.btncalcular);
        generar = (Button) findViewById(R.id.btngenerar);

        adapter = ArrayAdapter.createFromResource(
                this,
                R.array.selectoperation,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_operaciones.setAdapter(adapter);

        spinner_operaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicionItemSeleccionado = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = inputNum.getText().toString().trim();
                if(input.isEmpty()) {
                    resultado.setText("Por favor ingresa números.");
                    return;
                }

                String[] numeros = input.split(",");
                for (String numero : numeros) {
                    try {
                        Double.parseDouble(numero);
                    } catch (NumberFormatException e) {
                        resultado.setText("Por favor, asegúrate de que todos los elementos sean números.");
                        return;
                    }
                }

                switch (posicionItemSeleccionado) {
                    case 0:
                        resultado.setText("El promedio de los numeros es: " + calcularPromedio(numeros));
                    break;

                    case 1:
                        resultado.setText(esParOImpar(numeros));
                    break;

                    case 2:
                        resultado.setText(ordenarNumeros(numeros));
                    break;
                }
            }
        });

        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNum.setText(generarNumeroAleatorio());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_opcion) {
            Intent i = new Intent(getApplicationContext(), CodificacionTexto.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String generarNumeroAleatorio() {
        Random rnd = new Random();
        StringBuilder mensaje = new StringBuilder();
        int cantidadNumeros = rnd.nextInt(10) + 1;

        for (int i = 0; i < cantidadNumeros; i++) {
            int numeroAleatorio = rnd.nextInt(100);
            mensaje.append(numeroAleatorio);
            if (i < cantidadNumeros - 1) {
                mensaje.append(",");
            }
        }

        return mensaje.toString();
    }


    public String esParOImpar(String[] numeros) {
        String pares = "", impares = "", mensaje = "";
        for (int i = 0; i < numeros.length; i++) {
            int num = Integer.parseInt(numeros[i]);
            if (num % 2 == 0) {
                pares += num + " ";
            } else {
                impares += num + " ";
            }
        }
        return "Numeros pares: \n" + pares + "\nNumeros impares: \n" + impares;
    }

    public static String ordenarNumeros(String[] numeros) {
        String numerosOrdenados = "";
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length - 1; j++) {
                double numero1 = Double.parseDouble(numeros[j]),
                        numero2 = Double.parseDouble(numeros[j + 1]);
                if (numero1 > numero2) {
                    numeros[j] = String.valueOf(numero2);
                    numeros[j + 1] = String.valueOf(numero1);
                }
            }
        }

        for (int i = 0; i < numeros.length; i++) {
            int numero = (int) Double.parseDouble(numeros[i]);
            numerosOrdenados += Integer.toString(numero);
            if (i < numeros.length - 1) {
                numerosOrdenados += ", ";
            }
        }

        return numerosOrdenados;
    }

    public double calcularPromedio(String[] numeros) {
        double suma = 0;

        for (int i = 0; i < numeros.length; i++) {
            suma += Double.parseDouble(numeros[i]);
        }

        return suma / numeros.length;
    }
}