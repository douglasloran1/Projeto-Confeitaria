package com.projetoconfeitaria.project.model;

import java.util.List;

public class EmpresaCompradora extends Empresa {
    private double limiteCredito;
    private List<Pedidos> historicoCompras;

    public EmpresaCompradora() {
    }


    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }


    public List<Pedidos> getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(List<Pedidos> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    

}
