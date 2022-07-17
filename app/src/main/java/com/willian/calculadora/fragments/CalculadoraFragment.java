package com.willian.calculadora.fragments;

import android.os.Bundle;
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

public class CalculadoraFragment extends Fragment implements View.OnClickListener{ // Incluindo fragment para transição de telas e implementando no view para ouvir evento de click

    // Declaração das variáveis, conportamento desde componentes até classe
    androidx.appcompat.widget.AppCompatButton numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete,
            numeroOito, numeroNove, ponto, abreParenteses, fechaParenteses, soma, subtracao, multiplicacao, divisao, limpar, apagar, igual;

    androidx.appcompat.widget.AppCompatTextView txtConta, txtResultado;

    Historico historico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  // Evento disparada assim que o View for instanciado a primeira vez
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculadora, container, false); // Declarando view para uso e por fim retorno

        IniciarComponentes(view); // Metódo para iniciar os componentes acima declarados

        // Setando evento ao clicar em cada tecla da calculadora, para uso no onclick da classe
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

        // Metódo para limpar tanto a conta quanto o resultado nos textview
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                txtConta.setText(""); // setText para definir o texto a ser mostrado no textview, que nesse caso é vazio
                txtResultado.setText("");
            }
        });

        // Metódo para apagar o último caracter escrito pelo usuário
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                TextView conta = view.findViewById(R.id.txt_conta); // Selecionando text view "conta"
                String dados = conta.getText().toString(); // Coletando o que foi digitado

                if(!dados.isEmpty()){ // Se for diferente de vazio o registro anteriormente coletado

                    byte inicio = 0; // Definindo um inicio com byte, para evitar crash
                    int tamanho = dados.length() - 1; // Declarando o tamanho menos um
                    String txtConta = dados.substring(inicio, tamanho); // Definindo em uma variável novamente o valor de conta, agora com o os caracteres originais menos um
                    conta.setText(txtConta); // Definindo no próprio textview
                }

                txtResultado.setText(""); // Como foi alterado a conta, há a necessidade de zerar o resultado
            }
        });
        // Metódo de igual onde é feito a conta e definido o resultado
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                try{ // try catch para evitar crashs caso o metódo evaluate não consiga realizar a conta corretamente
                    Expression expressao = new ExpressionBuilder(txtConta.getText().toString()).build();  // construido a expressão com o que foi digitado na conta
                    double resultado = expressao.evaluate(); // Metódo evaluate é o responsável por resolver a conta
                    long longResultado = (long) resultado; // estendendo o tamanho suportado do resultado

                    if(resultado == (double)longResultado){ // caso o resultado antes da conversão for igual o resultado long, será mostrado o resultado com a capacidade maior, si não, será mostrado o double
                        txtResultado.setText((CharSequence) String.valueOf(longResultado));
                    }
                    else
                    {
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                    // Adicionar o valor para o historico através da classe Historico
                    historico = new Historico();
                    historico.setConta((String) txtConta.getText());
                    historico.setResultado((String) txtResultado.getText());

                }catch (Exception error){

                }
            }
        });

        return view; // Por fim, return no view
    }
    // Iniciando os componentes para realizar a ligação entre o Java e o XML
    private void IniciarComponentes(View view){
        numeroZero = (AppCompatButton) view.findViewById(R.id.btn_zero); // uso do AppCompatButton passando o tipo de componente e findViewById para encontrar o mesmo pelo seu ID declarado no XML
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

    // Metódo que manipula os dados que serão exibidos no textview, com dois parâmetros: dado onde é passado os valores de cada click e limpar_dados para validação
    public void DadosVisor(String dado, boolean limpar_dados){
        // Se o resultado estiver vazio, conta será zerada
        if(txtResultado.getText().equals("")){
            txtConta.setText(" ");
        }
        // Se o parâmetro limpar_dados estiver como true, será limpo o resultado e dado continuidade na conta
        if (limpar_dados){
            txtResultado.setText(" ");
            txtConta.append(dado);
        } // Se o resultado for diferente de vazio, será removido a conta e adicionado o resultado na mesma para continuidade da operação com o resultado anterior
        else if (!txtResultado.getText().equals(" "))
        {
            txtConta.setText(" ");
            txtConta.append(txtResultado.getText());
            txtConta.append(dado);
            txtResultado.setText(" ");
        } // Se não, só será adicionado o resultado a conta junto com o último operador digitado e limpo o resultado
        else
        {
            txtConta.append(txtResultado.getText());
            txtConta.append(dado);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) { // Evento click da class: Switch para chamar o metódo e dado continuidade na conta conforme parâmetros passados
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