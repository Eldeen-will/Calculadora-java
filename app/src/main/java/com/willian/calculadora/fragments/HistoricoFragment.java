package com.willian.calculadora.fragments;


import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.willian.calculadora.Historico;
import com.willian.calculadora.R;

public class HistoricoFragment extends Fragment { // fragment historico para segunda aba do aplicativo

    // Declaração de variáveis para manipulação dos componentes e classe
    androidx.appcompat.widget.AppCompatTextView txtHistorico, txtResultado;
    androidx.appcompat.widget.AppCompatButton atualizar_historico;
    Historico historico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historico, container, false);

        iniciarComponente(view);

        // botão para atualizar o histórico, sempre que necessário
        atualizar_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Ao clicar no botão, será usado os metódos da classe para recuparar os dados das contas
                historico = new Historico();
                txtHistorico.append("\n"+historico.getConta());
                txtHistorico.append(historico.getResultado()+"\n");
            }
        });

        return view;
    }

    private void iniciarComponente(View view){
        txtHistorico = (AppCompatTextView) view.findViewById(R.id.txt_historico);
        txtResultado = (AppCompatTextView) view.findViewById(R.id.txt_resultado);
        atualizar_historico = (AppCompatButton) view.findViewById(R.id.atualizar_historico);
    }
}