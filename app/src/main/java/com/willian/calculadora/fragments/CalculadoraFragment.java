package com.willian.calculadora.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.SharedPreferences;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willian.calculadora.Historico;
import com.willian.calculadora.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class CalculadoraFragment extends Fragment implements View.OnClickListener{

    androidx.appcompat.widget.AppCompatButton numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete,
            numeroOito, numeroNove, ponto, abreParenteses, fechaParenteses, soma, subtracao, multiplicacao, divisao, limpar, apagar, igual;

    androidx.appcompat.widget.AppCompatTextView txtConta, txtResultado;

    Historico historico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculadora, container, false);

        IniciarComponentes(view);

        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        abreParenteses.setOnClickListener(this);
        fechaParenteses.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                txtConta.setText("");
                txtResultado.setText("");
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                TextView conta = view.findViewById(R.id.txt_conta);
                String dados = conta.getText().toString();

                if(!dados.isEmpty()){

                    byte inicio = 0;
                    int tamanho = dados.length() - 1;
                    String txtConta = dados.substring(inicio, tamanho);
                    conta.setText(txtConta);
                }

                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                try{
                    Expression expressao = new ExpressionBuilder(txtConta.getText().toString()).build();
                    double resultado = expressao.evaluate();
                    long longResultado = (long) resultado;

                    if(resultado == (double)longResultado){
                        txtResultado.setText((CharSequence) String.valueOf(longResultado));
                    }
                    else
                    {
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }

                    historico = new Historico();
                    historico.setConta((String) txtConta.getText());

                }catch (Exception error){

                }
            }
        });

        return view;
    }

    private void IniciarComponentes(View view){
        numeroZero = (AppCompatButton) view.findViewById(R.id.btn_zero);
        numeroUm = (AppCompatButton) view.findViewById(R.id.btn_um);
        numeroDois = (AppCompatButton) view.findViewById(R.id.btn_dois);
        numeroTres = (AppCompatButton) view.findViewById(R.id.btn_tres);
        numeroQuatro = (AppCompatButton) view.findViewById(R.id.btn_quatro);
        numeroCinco = (AppCompatButton) view.findViewById(R.id.btn_cinco);
        numeroSeis = (AppCompatButton) view.findViewById(R.id.btn_seis);
        numeroSete = (AppCompatButton) view.findViewById(R.id.btn_sete);
        numeroOito = (AppCompatButton) view.findViewById(R.id.btn_oito);
        numeroNove = (AppCompatButton) view.findViewById(R.id.btn_nove);
        ponto = (AppCompatButton) view.findViewById(R.id.btn_ponto);
        abreParenteses = (AppCompatButton) view.findViewById(R.id.btn_abre_parenteses);
        fechaParenteses = (AppCompatButton) view.findViewById(R.id.btn_fecha_parenteses);
        soma = (AppCompatButton) view.findViewById(R.id.btn_soma);
        subtracao = (AppCompatButton) view.findViewById(R.id.btn_subtracao);
        multiplicacao = (AppCompatButton) view.findViewById(R.id.btn_multiplicacao);
        divisao = (AppCompatButton) view.findViewById(R.id.btn_divisao);
        limpar = (AppCompatButton) view.findViewById(R.id.btn_limpar);
        apagar = (AppCompatButton) view.findViewById(R.id.btn_apagar);
        igual = (AppCompatButton) view.findViewById(R.id.btn_igual);
        txtConta = (AppCompatTextView) view.findViewById(R.id.txt_conta);
        txtResultado = (AppCompatTextView) view.findViewById(R.id.txt_resultado);
    }


    public void DadosVisor(String dado, boolean limpar_dados){

        if(txtResultado.getText().equals("")){
            txtConta.setText(" ");
        }

        if (limpar_dados){
            txtResultado.setText(" ");
            txtConta.append(dado);
        }
        else if (!txtResultado.getText().equals(" "))
        {
            txtConta.setText(" ");
            txtConta.append(txtResultado.getText());
            txtConta.append(dado);
            txtResultado.setText(" ");
        }
        else
        {
            txtConta.append(txtResultado.getText());
            txtConta.append(dado);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_zero:
                DadosVisor("0", true);
                break;

            case R.id.btn_um:
                DadosVisor("1", true);
                break;

            case R.id.btn_dois:
                DadosVisor("2", true);
                break;

            case R.id.btn_tres:
                DadosVisor("3", true);
                break;

            case R.id.btn_quatro:
                DadosVisor("4", true);
                break;

            case R.id.btn_cinco:
                DadosVisor("5", true);
                break;

            case R.id.btn_seis:
                DadosVisor("6", true);
                break;

            case R.id.btn_sete:
                DadosVisor("7", true);
                break;

            case R.id.btn_oito:
                DadosVisor("8", true);
                break;

            case R.id.btn_nove:
                DadosVisor("9", true);
                break;

            case R.id.btn_ponto:
                DadosVisor(".", true);
                break;

            case R.id.btn_abre_parenteses:
                DadosVisor("(", true);
                break;

            case R.id.btn_fecha_parenteses:
                DadosVisor(")", true);
                break;

            case R.id.btn_soma:
                DadosVisor("+", false);
                break;

            case R.id.btn_subtracao:
                DadosVisor("-", false);
                break;

            case R.id.btn_multiplicacao:
                DadosVisor("*", false);
                break;

            case R.id.btn_divisao:
                DadosVisor("/", false);
                break;
        }
    }
}