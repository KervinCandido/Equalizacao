package br.unip.cc.pi.equalizacao.view.component;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String texto) {
        super(texto);
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        setForeground(new Color(234, 234, 232));
        setBorder(BorderFactory.createEmptyBorder());
    }
}
