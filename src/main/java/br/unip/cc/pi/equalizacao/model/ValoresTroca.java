package br.unip.cc.pi.equalizacao.model;

import static br.unip.cc.pi.equalizacao.model.Constantes.INTENSIDADE_ESCALA_RGB;

public class ValoresTroca {

    private final int[] canalVermelho = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalVerde = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalAzul = new int[INTENSIDADE_ESCALA_RGB];


    public void addValorEscalaVermelha(int valorOriginal, int novoValor) {
        canalVermelho[valorOriginal] = novoValor;
    }

    public void addValorEscalaVerde(int valorOriginal, int novoValor) {
        canalVerde[valorOriginal] = novoValor;
    }

    public void addValorEscalaAzul(int valorOriginal, int novoValor) {
        canalAzul[valorOriginal] = novoValor;
    }

    public int getValorEscalaVermelha(int valorOriginal) {
        return canalVermelho[valorOriginal];
    }

    public int getValorEscalaVerde(int valorOriginal) {
        return canalVerde[valorOriginal];
    }

    public int getValorEscalaAzul(int valorOriginal) {
        return canalAzul[valorOriginal];
    }
}
