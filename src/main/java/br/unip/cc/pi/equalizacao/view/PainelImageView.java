package br.unip.cc.pi.equalizacao.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelImageView extends JPanel {

    private final JLabel lblImage;

    public PainelImageView() {
        setOpaque(false);
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(800, 800));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        lblImage.setHorizontalTextPosition(JLabel.CENTER);
        lblImage.setVerticalTextPosition(JLabel.BOTTOM);
        lblImage.setForeground(new Color(234, 234, 232));
        add(lblImage);
    }

    public void loadImage(BufferedImage bufferedImage) {
        final Image scaledInstance = ajustaTamanho(bufferedImage);
        Icon icon = new ImageIcon(scaledInstance);
        EventQueue.invokeLater(() -> {
            lblImage.setPreferredSize(getSize());
            lblImage.setIcon(icon);
        });
    }

    private Image ajustaTamanho(BufferedImage bufferedImage) {
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();

        int w = 700;
        int h = 500;

        if (width < 700 && height < 500) {
            w = width;
            h = height;
        }

        if (width >= height) {
            h = (height * w) / width;
        } else {
            w = (width * h) / height;
        }

        final Image scaledInstance = bufferedImage
                .getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaledInstance;
    }
}
