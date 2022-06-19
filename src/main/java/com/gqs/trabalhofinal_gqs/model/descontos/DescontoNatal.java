package com.gqs.trabalhofinal_gqs.model.descontos;

import com.gqs.trabalhofinal_gqs.model.Produto;

import java.util.Calendar;

public class DescontoNatal implements IDesconto {

    @Override
    public double calcular(Produto produto) {
        Calendar dataHoje = Calendar.getInstance();
        if (dataHoje.get(Calendar.DAY_OF_MONTH) >= 20 && dataHoje.get(Calendar.DAY_OF_MONTH) <= 5 && dataHoje.get(Calendar.MONTH) == 11 && dataHoje.get(Calendar.MONTH) == 0) {
            return 10;
        }
        return 0;
    }
}
