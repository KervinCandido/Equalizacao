package br.unip.cc.pi.equalizacao.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnalizadorImagem {

    public Histograma criaHistograma(BufferedImage image) throws IllegalArgumentException {
        if (image == null) {
            throw new IllegalArgumentException("image não pode ser nulo");
        }
        final int width = image.getWidth();
        final int height = image.getHeight();

        Histograma histograma = new Histograma();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Color cor = new Color(image.getRGB(x, y));
                histograma.addValorEscalaVermelha(cor.getRed());
                histograma.addValorEscalaVerde(cor.getGreen());
                histograma.addValorEscalaAzul(cor.getBlue());
            }
        }

        return histograma;
    }
}
