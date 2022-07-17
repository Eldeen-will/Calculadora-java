package com.willian.calculadora;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.willian.calculadora.fragments.CalculadoraFragment;
import com.willian.calculadora.fragments.HistoricoFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new CalculadoraFragment();
           case 1:
               return new HistoricoFragment();
           default:
               return new CalculadoraFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
