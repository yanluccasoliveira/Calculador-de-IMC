package com.yantestes.calculadordeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // CLASSE ESTÁTICA VIEWHOLDER PARA PROCURAR OS COMPONENTES 1 VEZ SÓ INDEPENDENTE DA QUANTIDADE DE INSTANCIAÇÕES (NOVOS OBJETOS), ASSIM O APLICATIVO NÃO GASTA PROCESSAMENTO PROCURANDO SEMPRE OS COMPONENTES//
    private static class ViewHolder
    {
        EditText edit_text_peso, edit_text_altura;
        TextView text_view_resultado;
        Button button_calcular;
    }

    ViewHolder viewholder = new ViewHolder(); // OBJETO VIEWHOLDER

    // CREATE //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewholder.button_calcular = findViewById(R.id.button_calcular);
        this.viewholder.edit_text_peso = findViewById(R.id.field_peso);
        this.viewholder.edit_text_altura = findViewById(R.id.field_altura);
        this.viewholder.text_view_resultado = findViewById(R.id.label_resultado);

        FieldPesoFormat();
        FieldAlturaFormat();
        Calcular();
    }

    // FUNÇÃO PARA FORMATAR O CAMPO PESO (INSERIR VIRGULA AUTOMATICAMENTE) //
    public void FieldPesoFormat()
    {
        viewholder.edit_text_peso.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence char_sequence, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence char_sequence, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (editable.length() > 3)
                {
                    if (!editable.toString().contains(","))
                    {
                        editable.insert(3, ",");
                    }
                }
            }
        });
    }

    // FUNÇÃO PARA FORMATAR O CAMPO ALTURA (INSERIR VIRGULA AUTOMATICAMENTE) //
    public void FieldAlturaFormat()
    {
        viewholder.edit_text_altura.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence char_sequence, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence char_sequence, int start, int before, int count)
            {
//                viewholder.edit_text_peso.setText(char_sequence + String.valueOf(start));
//                viewholder.text_view_resultado.setText("Sequencia: " + char_sequence + "\nStart: " + start + "\nBefore: " + before + "\nCount: " + count);

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                //viewholder.edit_text_altura.removeTextChangedListener(this);

                //String.format("%,d",  Float.parseFloat(editable.toString()));

                if (editable.length() > 1) //&& (editable.length() < 2))
                {
                    if (!editable.toString().contains(","))
                    {
                        editable.insert(1, ",");
                    }
                }
            }
        });
    }

    // FUNÇÃO QUE IMPLEMENTA O CLICK DO BOTÃO CALCULAR E GERA OS CÁLCULOS //
    public void Calcular()
    {
        viewholder.button_calcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                float peso = Float.parseFloat(viewholder.edit_text_peso.getText().toString().replace(",", "."));
                float altura = Float.parseFloat(viewholder.edit_text_altura.getText().toString().replace(",", "."));
                float imc = Float.parseFloat(String.format("%.2f" , (float) (peso / (float) Math.pow(altura, 2))));
                String estado = "";
                //String resultado_imc = String.valueOf(imc).replace(".", ",");

                if ((viewholder.edit_text_peso.getText().length() < 0) || (viewholder.edit_text_altura.getText().length() < 0))
                {
                    Toast.makeText(MainActivity.this, "Todos os campos devem ser preenchidos para calcular seu IMC.", Toast.LENGTH_LONG).show();
                }

                //if ((viewholder.edit_text_peso.getText().length() >= 1) && (viewholder.edit_text_altura.getText().length() >= 1))
                else
                {
                    if ((peso <= 10) || (altura < 0.70))
                    {
                        estado = "Impossível! Você entrou para o Guiness Book!";
                        viewholder.text_view_resultado.setText(estado);
                    }

                    else
                    {
                        if (imc <= 18.5)
                        {
                            estado = "Seu IMC é " + imc + ", esse valor significa Magreza, você precisa se alimentar!";
                        }

                        else if ((imc > 18.5) && (imc <= 24.9))
                        {
                            estado = "Seu IMC é " + imc + ", esse valor significa que seu peso está Normal. Parabéns, continue assim!";
                        }

                        else if ((imc > 24.9) && (imc <= 29.9))
                        {
                            estado = "Seu IMC é " + imc + ", esse valor significa que você está Sobrepeso. Você precisa começar a melhorar sua alimentação, caso contrário, você pode ficar obeso!";
                        }

                        else if ((imc > 29.9) && (imc <= 39.9))
                        {
                            estado = "Seu IMC é " + imc + ", esse valor significa que você está Obeso. Você precisa começar uma dieta e melhorar sua alimentação, pois, você pode obter uma doença  futuramente!";
                        }

                        else
                        {
                            estado = "Seu IMC é " + imc + ", esse valor significa que você está muito Obeso. Você precisa urgente procurar um(a) nutricionista para começar uma dieta e melhorar sua alimentação. Você está correndo risco de vida e de obter uma doença grave!";

                        }

                        viewholder.text_view_resultado.setText(estado);
                    }
                }
            }
        });
    }

}