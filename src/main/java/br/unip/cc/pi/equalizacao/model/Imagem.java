package br.unip.cc.pi.equalizacao.model;

import java.awt.image.BufferedImage;

public class Imagem {

    private final BufferedImage bufferedImage;

    public Imagem(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
