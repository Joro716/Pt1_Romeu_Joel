package com.example.pt1_romeu_joel;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner idioma;
    TextView historial1;
    TextView historial2;
    TextView historial3;
    TextView historial4;
    TextView historial5;
    TextView resultat;
    Button btnnum1;
    Button btnnum2;
    Button btnnum3;
    Button btnnum4;
    Button btnnum5;
    Button btnnum6;
    Button btnnum7;
    Button btnnum8;
    Button btnnum9;
    Button btnnum0;
    Button btnIgual;
    Button btnMes;
    Button btnMenys;
    Button btnX;
    Button btnDivisio;
    Button btnCE;
    Button btnC;
    Button btnBorrar;
    Button btnSigno;
    Button btnComa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idioma = findViewById(R.id.Idioma);
        historial1 = findViewById(R.id.Historial1);
        historial2 = findViewById(R.id.Historial2);
        historial3 = findViewById(R.id.Historial3);
        historial4 = findViewById(R.id.Historial4);
        historial5 = findViewById(R.id.Historial5);
        resultat = findViewById(R.id.Resultat);
        btnnum1 = findViewById(R.id.btnnum1); btnnum1.setOnClickListener(this);
        btnnum2 = findViewById(R.id.btnnum2); btnnum2.setOnClickListener(this);
        btnnum3 = findViewById(R.id.btnnum3); btnnum3.setOnClickListener(this);
        btnnum4 = findViewById(R.id.btnnum4); btnnum4.setOnClickListener(this);
        btnnum5 = findViewById(R.id.btnnum5); btnnum5.setOnClickListener(this);
        btnnum6 = findViewById(R.id.btnnum6); btnnum6.setOnClickListener(this);
        btnnum7 = findViewById(R.id.btnnum7); btnnum7.setOnClickListener(this);
        btnnum8 = findViewById(R.id.btnnum8); btnnum8.setOnClickListener(this);
        btnnum9 = findViewById(R.id.btnnum9); btnnum9.setOnClickListener(this);
        btnnum0 = findViewById(R.id.btnnum0); btnnum0.setOnClickListener(this);
        btnIgual = findViewById(R.id.btnIgual); btnIgual.setOnClickListener(this);
        btnMes = findViewById(R.id.btnMes); btnMes.setOnClickListener(this);
        btnMenys = findViewById(R.id.btnMenys); btnMenys.setOnClickListener(this);
        btnX = findViewById(R.id.btnX); btnX.setOnClickListener(this);
        btnDivisio = findViewById(R.id.btnDividir); btnDivisio.setOnClickListener(this);
        btnCE = findViewById(R.id.btnCE); btnCE.setOnClickListener(this);
        btnC = findViewById(R.id.btnC); btnC.setOnClickListener(this);
        btnBorrar = findViewById(R.id.btnBorrar); btnBorrar.setOnClickListener(this);
        btnSigno = findViewById(R.id.btnSigno); btnSigno.setOnClickListener(this);
        btnComa = findViewById(R.id.btnComa); btnComa.setOnClickListener(this);

        idioma.setOnItemSelectedListener(this);
        idioma.setSelection(posicionSeleccionada, false);
    }

    private static int posicionSeleccionada = 0;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0 || position == posicionSeleccionada) {
            return;
        }

        posicionSeleccionada = position;

        if (position == 1) {
            cambiarIdioma("es"); // Español
        } else if (position == 2) {
            cambiarIdioma("en"); // Inglés
        } else if (position == 3) {
            cambiarIdioma("ca"); // Catalán
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void cambiarIdioma(String codigoIdioma) {
        java.util.Locale locale = new java.util.Locale(codigoIdioma);
        java.util.Locale.setDefault(locale);

        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        recreate();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        // 1. Manejo de botones numéricos agrupados
        if (id == R.id.btnnum0 || id == R.id.btnnum1 || id == R.id.btnnum2 ||
                id == R.id.btnnum3 || id == R.id.btnnum4 || id == R.id.btnnum5 ||
                id == R.id.btnnum6 || id == R.id.btnnum7 || id == R.id.btnnum8 ||
                id == R.id.btnnum9) {

            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());

            // 2. Manejo de acciones y limpieza
        } else if (id == R.id.btnC) {
            resultat.setText("");
            limpiarHistorial();
        } else if (id == R.id.btnCE) {
            resultat.setText("");
        } else if (id == R.id.btnBorrar) {
            String texto = resultat.getText().toString();

            if (!texto.isEmpty()) {
                resultat.setText(texto.substring(0, texto.length() - 1));
            }
        }else if (id == R.id.btnIgual) {
            String operacion = resultat.getText().toString();
            if (!operacion.isEmpty()) {
                // 1. Reemplazamos los símbolos por sus equivalentes en la librería
                String operacionLimpia = operacion.replace("×", "*");
                operacionLimpia = operacionLimpia.replace("÷", "/");
                operacionLimpia = operacionLimpia.replace("＋", "+");
                operacionLimpia = operacionLimpia.replace("−", "-");
                operacionLimpia = operacionLimpia.replace(",", ".");


                // 2. Construimos y evaluamos la expresión respetando la prioridad de operadores
                try{
                    Expression e = new ExpressionBuilder(operacionLimpia).build();
                    double resultado = e.evaluate();
                    String resultadotxt = String.valueOf(resultado).replace(".", ",");

                    // Muestra el resultado: -7.0 (ya que 1+1+3 - 12 = -7)
                    historial1.setTextColor(getResources().getColor(R.color.black));
                    historial5.setText(historial4.getText());
                    historial4.setText(historial3.getText());
                    historial3.setText(historial2.getText());
                    historial2.setText(historial1.getText());
                    historial1.setText(operacion);
                    resultat.setText(String.valueOf(resultadotxt));
                }catch (Exception e) {
                    // Manejo de errores
                    historial5.setText(historial4.getText());
                    historial4.setText(historial3.getText());
                    historial3.setText(historial2.getText());
                    historial2.setText(historial1.getText());
                    historial1.setTextColor(getResources().getColor(R.color.Error));
                    historial1.setText("Error");
                }
            }
        } else if (id == R.id.btnDividir) {
            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());
        } else if (id == R.id.btnMes) {
            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());
        }
        else if (id == R.id.btnMenys) {
            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());
        }else if (id == R.id.btnX) {
            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());
        }else if (id == R.id.btnSigno) {
            if (resultat.getText().toString().charAt(0) == '-') {
                resultat.setText(resultat.getText().toString().substring(1));
            } else {
                resultat.setText("-" + resultat.getText());
            }
        }else if (id == R.id.btnComa) {
            Button btnPulsado = (Button) v;
            resultat.append(btnPulsado.getText());
        }
    }

    public void limpiarHistorial() {
        historial1.setText("");
        historial2.setText("");
        historial3.setText("");
        historial4.setText("");
        historial5.setText("");
    }
}