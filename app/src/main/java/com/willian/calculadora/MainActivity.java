package com.willian.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity{

    // Definindo variáveis para manipular componentes do main e a classe MyViewPagerAdapter
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Conectando XML com o Java
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPagerAdapter = new MyViewPagerAdapter(this); // Criando uma nova instância da classe
        viewPager2.setAdapter(myViewPagerAdapter); // Definindo qual adaptador será usando no viewPager

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){ // Evento na mudança aba

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition()); // Toda vez que mudar de aba, será definido o item selecionado conforme posição informada na classe
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) { // Callback para ao deslizar de aba ao invés de clicar na navbar, realizar a mudança no navbar para a aba correspondente a atual
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}