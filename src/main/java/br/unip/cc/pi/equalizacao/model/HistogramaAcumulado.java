package br.unip.cc.pi.equalizacao.model;

import static br.unip.cc.pi.equalizacao.model.Constantes.INTENSIDADE_ESCALA_RGB;

public class HistogramaAcumulado {

    private final int[] canalVermelho = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalVerde = new int[INTENSIDADE_ESCALA_RGB];
    private final int[] canalAzul = new int[INTENSIDADE_ESCALA_RGB];

    public HistogramaAcumulado(Histograma histograma) {
        criaHistogramaAcumulado(histograma);
    }

    private void criaHistogramaAcumulado(Histograma histograma) {
        canalVermelho[0] = histograma.getValorEscalaVermelha(0);
        canalVerde[0] = histograma.getValorEscalaVerde(0);
        canalAzul[0] = histograma.getValorEscalaAzul(0);

        for (int i = 1; i < INTENSIDADE_ESCALA_RGB; i++) {
            canalVermelho[i] = canalVermelho[i - 1] + histograma.getValorEscalaVermelha(i);
            canalVerde[i] = canalVerde[i - 1] + histograma.getValorEscalaVerde(i);
            canalAzul[i] = canalAzul[i - 1] + histograma.getValorEscalaAzul(i);
        }
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
