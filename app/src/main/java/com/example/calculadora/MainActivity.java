package com.example.calculadora;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private TextView pantalla;
    private TextView subpantalla;

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;
    private Button bdec;
    private Button bmas;
    private Button bmenos;
    private Button bmult;
    private Button bdiv;
    private Button bretr;
    private Button bclr;
    private Button bres;
    private Button bsin;
    private Button bcos;
    private Button btan;

    private ToggleButton tUnidAng;

    private double acum; //Guarda el valor que lleva el carro de operaciones hasta el momento
    private boolean primeraop; //Indica si es la primera operacion
    private char op; //Ultima operacion a hacer

    public void setupCalc(){

        this.b0 = findViewById(R.id.button0);
        this.b1 = findViewById(R.id.button1);
        this.b2 = findViewById(R.id.button2);
        this.b3 = findViewById(R.id.button3);
        this.b4 = findViewById(R.id.button4);
        this.b5 = findViewById(R.id.button5);
        this.b6 = findViewById(R.id.button6);
        this.b7 = findViewById(R.id.button7);
        this.b8 = findViewById(R.id.button8);
        this.b9 = findViewById(R.id.button9);
        this.bdec = findViewById(R.id.buttonDecimal);
        this.bmas = findViewById(R.id.buttonSuma);
        this.bmenos = findViewById(R.id.buttonResta);
        this.bmult = findViewById(R.id.buttonMultipl);
        this.bdiv = findViewById(R.id.buttonDiv);
        this.bretr = findViewById(R.id.buttonRetr);
        this.bclr = findViewById(R.id.buttonClear);
        this.bres = findViewById(R.id.buttonRes);
        this.bsin = findViewById(R.id.buttonSin);
        this.bcos = findViewById(R.id.buttonCos);
        this.btan = findViewById(R.id.buttonTan);

        this.tUnidAng = findViewById(R.id.toggleAng);

        this.pantalla = findViewById(R.id.pantallaTextView);
        this.subpantalla = findViewById(R.id.subPantallaTextView);

        pantalla.setText("0");
        subpantalla.setText("");

        primeraop = true;
    }

    public void Operar(char ope){
        if(primeraop) {
            acum = Double.parseDouble(pantalla.getText().toString());
            primeraop = false;

            switch (ope){
                case '+': op = '+';
                    subpantalla.append(pantalla.getText().toString() + " + ");
                    pantalla.setText("0");
                    break;
                case '-': op = '-';
                    subpantalla.append(pantalla.getText().toString() + " - ");
                    pantalla.setText("0");
                    break;
                case '×': op = '×';
                    subpantalla.append(pantalla.getText().toString() + " × ");
                    pantalla.setText("0");
                    break;
                case '÷': op = '÷';
                    subpantalla.append(pantalla.getText().toString() + " ÷ ");
                    pantalla.setText("0");
                    break;
                case 's': primeraop = true; //Solo hace una operacion
                    subpantalla.setText("sin(" + acum + ") = ");
                    if(!tUnidAng.isChecked()){ //DEG
                        pantalla.setText(String.valueOf(Math.sin(Math.toDegrees(acum))));
                    }
                    else{ //RAD
                        pantalla.setText(String.valueOf(Math.sin(Math.toRadians(acum))));
                    }
                    acum = 0;
                    break;
                case 'c': primeraop = true; //Solo hace una operacion
                    subpantalla.setText("cos(" + acum + ") = ");
                    if(!tUnidAng.isChecked()){ //DEG
                        pantalla.setText(String.valueOf(Math.cos(Math.toDegrees(acum))));
                    }
                    else{ //RAD
                        pantalla.setText(String.valueOf(Math.cos(Math.toRadians(acum))));
                    }
                    acum = 0;
                    break;
                case 't': primeraop = true; //Solo hace una operacion
                    subpantalla.setText("tan(" + acum + ") = ");
                    if(!tUnidAng.isChecked()){ //DEG
                        pantalla.setText(String.valueOf(Math.tan(Math.toDegrees(acum))));
                    }
                    else{ //RAD
                        pantalla.setText(String.valueOf(Math.tan(Math.toRadians(acum))));
                    }
                    acum = 0;
                    break;
                case '=':  primeraop = true;
                    subpantalla.append(pantalla.getText().toString() + " = ");
                    pantalla.setText(String.valueOf(acum));
                    acum = 0;
            }
        }
        else {
            double numaux = Double.parseDouble(pantalla.getText().toString());
            switch (ope){
                case '+': op = '+';
                    subpantalla.append(pantalla.getText().toString() + " + ");
                    acum = acum + numaux;
                    break;
                case '-': op = '-';
                    subpantalla.append(pantalla.getText().toString() + " - ");
                    acum = acum - numaux;
                    break;
                case '×': op = '×';
                    subpantalla.append(pantalla.getText().toString() + " × ");
                    acum = acum * numaux;
                    break;
                case '÷': op = '÷';
                    subpantalla.append(pantalla.getText().toString() + " ÷ ");
                    acum = acum / numaux;
                    break;
                case '=':
                    primeraop = true;
                    subpantalla.append(pantalla.getText().toString() + " = ");
                    double res = 0;
                    switch (op) { //La ultima operacion hecha
                        case '+': res = acum + numaux;
                            break;
                        case '-': res = acum - numaux;
                            break;
                        case '×': res = acum * numaux;
                            break;
                        case '÷': res = acum / numaux;
                            break;
                    }
                    pantalla.setText(String.valueOf(res));
                    acum = 0;
            }
        }
    }

    public void clearCalc(){
        this.pantalla.setText("0");
        this.subpantalla.setText("");
        this.acum = 0;
        this.primeraop = true;
        this.op = ' ';
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupCalc();

        //EVENTOS
        b0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("0");
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("1");
                else
                pantalla.setText("1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("2");
                else
                    pantalla.setText("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("3");
                else
                    pantalla.setText("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("4");
                else
                    pantalla.setText("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("5");
                else
                    pantalla.setText("5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("6");
                else
                    pantalla.setText("6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("7");
                else
                    pantalla.setText("7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("8");
                else
                    pantalla.setText("8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString() != "0")
                    pantalla.append("9");
                else
                    pantalla.setText("9");
            }
        });

        bdec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pantalla.append(".");
            }
        });

        bclr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearCalc();
            }
        });

        bretr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().toString().length() == 1)
                    pantalla.setText("0");
                else
                    pantalla.setText(pantalla.getText().toString().substring(0, pantalla.getText().toString().length() - 1));
            }
        });

        //OPERACIONES
        bmas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Operar('+');
            }
        });

        bmenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Operar('-');
            }
        });

        bmult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Operar('×');
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Operar('÷');
            }
        });

        bsin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Operar('s');
            }
        });

        bcos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Operar('c');
            }
        });

        btan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Operar('t');
            }
        });

        bres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Operar('=');
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
}
