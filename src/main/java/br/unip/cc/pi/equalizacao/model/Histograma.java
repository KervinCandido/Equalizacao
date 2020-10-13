package br.unip.cc.pi.equalizacao.model;

import static br.unip.cc.pi.equalizacao.model.Constantes.INTENSIDADE_ESCALA_RGB;

public class Histograma {

    private final int[] canalVermelho = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalVerde = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalAzul = new int[INTENSIDADE_ESCALA_RGB];


    public void addValorEscalaVermelha(int valorEscalaVermelho) {
        canalVermelho[valorEscalaVermelho]++;
    }

    public void addValorEscalaVerde(int valorEscalaVerde) {
        canalVerde[valorEscalaVerde]++;
    }

    public void addValorEscalaAzul(int valorEscalaAzul) {
        canalAzul[valorEscalaAzul]++;
    }

    public int getValorEscalaVermelha(int indexValor) {
        return canalVermelho[indexValor];
    }

    public int getValorEscalaVerde(int indexValor) {
        return canalVerde[indexValor];
    }

    public int getValorEscalaAzul(int indexValor) {
        return canalAzul[indexValor];
    }
}
