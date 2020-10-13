package br.unip.cc.pi.equalizacao;

import br.unip.cc.pi.equalizacao.view.FrmPrincipal;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            final FrmPrincipal frmPrincipal = new FrmPrincipal();
            EventQueue.invokeLater(frmPrincipal::createAndShow);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.out.println("Não foi possível carregar o gerenciado de layout");
            System.exit(-1);
        }
    }
}
