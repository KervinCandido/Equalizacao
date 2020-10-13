package br.unip.cc.pi.equalizacao.model;

import java.awt.*;
import java.awt.image.BufferedImage;

import static br.unip.cc.pi.equalizacao.model.Constantes.INTENSIDADE_ESCALA_RGB;

public class Equalizador {

    public void equalizar(BufferedImage bufferedImage) {
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        AnalizadorImagem analizadorImagem = new AnalizadorImagem();

        final Histograma histograma = analizadorImagem.criaHistograma(bufferedImage);
        final HistogramaAcumulado histogramaAcumulado = new HistogramaAcumulado(histograma);

        final ValoresTroca valoresTroca = new ValoresTroca();

        double qtdPixels = width * height;

        for (int i = 0; i < INTENSIDADE_ESCALA_RGB; i++) {
            valoresTroca.addValorEscalaVermelha(i,
                    calculaValorEqualizado(qtdPixels, histogramaAcumulado.getValorEscalaVermelha(i))
            );

            valoresTroca.addValorEscalaVerde(i,
                calculaValorEqualizado(qtdPixels, histogramaAcumulado.getValorEscalaVerde(i))
            );

            valoresTroca.addValorEscalaAzul(i,
                calculaValorEqualizado(qtdPixels, histogramaAcumulado.getValorEscalaAzul(i))
            );
        }

        int[] pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color corPixel = new Color(bufferedImage.getRGB(x, y), true);
                int vermelho = valoresTroca.getValorEscalaVermelha(corPixel.getRed());
                int verde = valoresTroca.getValorEscalaVerde(corPixel.getGreen());
                int azul = valoresTroca.getValorEscalaAzul(corPixel.getBlue());
                int alpha = corPixel.getAlpha();

                pixels[y * width + x] = new Color(vermelho, verde, azul, alpha).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
    }

    private int calculaValorEqualizado(double qtdPixels, int valorHistogramaAcumulado) {
        double pk = valorHistogramaAcumulado / qtdPixels;
        return (int) Math.round(pk * (INTENSIDADE_ESCALA_RGB - 1));
    }
}
