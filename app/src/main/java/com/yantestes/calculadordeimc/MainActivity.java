package com.yantestes.calculadordeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // CLASSE ESTÁTICA VIEWHOLDER PARA PROCURAR OS COMPONENTES 1 VEZ SÓ INDEPENDENTE DA QUANTIDADE DE INSTANCIAÇÕES (NOVOS OBJETOS), ASSIM O APLICATIVO NÃO GASTA PROCESSAMENTO PROCURANDO SEMPRE OS COMPONENTES//
    private static class ViewHolder
    {
        EditText edit_text_peso, edit_text_altura;
        TextView text_view_resultado;
        Button button_calcular;
    }

    ViewHolder viewholder = new ViewHolder(); // OBJETO VIEWHOLDER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewholder.button_calcular = findViewById(R.id.button_calcular);
        this.viewholder.edit_text_peso = findViewById(R.id.field_peso);
        this.viewholder.edit_text_altura = findViewById(R.id.field_altura);
        this.viewholder.text_view_resultado = findViewById(R.id.label_resultado);
    }
}