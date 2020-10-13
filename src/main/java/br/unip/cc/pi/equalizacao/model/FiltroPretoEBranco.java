package br.unip.cc.pi.equalizacao.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltroPretoEBranco {

    public void tornaPretoEBranco(BufferedImage bufferedImage) {
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        int[] pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color corPixel = new Color(bufferedImage.getRGB(x, y), true);
                final int red = corPixel.getRed();
                final int blue = corPixel.getBlue();
                final int green = corPixel.getGreen();
                int alpha = corPixel.getAlpha();
                int corMedia = (red + blue + green)/3;

                pixels[y * width + x] = new Color(corMedia, corMedia, corMedia, alpha).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
    }
}
