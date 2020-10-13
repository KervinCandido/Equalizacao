package br.unip.cc.pi.equalizacao.view;

import br.unip.cc.pi.equalizacao.view.component.Button;
import br.unip.cc.pi.equalizacao.view.listener.CarregarImagemListener;
import br.unip.cc.pi.equalizacao.view.listener.EqualizarImagemListener;
import br.unip.cc.pi.equalizacao.view.listener.PretoEBrancoListener;
import br.unip.cc.pi.equalizacao.view.listener.SalvarImagemListener;

import javax.swing.*;
import java.awt.*;

public class PainelControlador extends JPanel {

    private CarregarImagemListener carregarImagemListener;
    private EqualizarImagemListener equalizarImagemListener;
    private SalvarImagemListener salvarImagemListener;
    private PretoEBrancoListener pretoEBrancoListener;
    private Button btnCarregar;
    private Button btnEqualizar;
    private Button btnPretoEBranco;
    private Button btnSalvar;

    public PainelControlador() {
        super(new FlowLayout(FlowLayout.LEFT, 20, 5));
        setOpaque(false);
        setBorder(BorderFactory
                .createMatteBorder(0, 0, 1, 0, new Color(30, 30, 30)));
        setPreferredSize(new Dimension(800, 26));

        createAndConfigureButtons();

        add(btnCarregar);
        add(btnEqualizar);
        add(btnPretoEBranco);
        add(btnSalvar);
    }

    private void createAndConfigureButtons() {
        createAndConfigureBtnCarregar();
        createAndConfigureBtnEqualizar();
        createAndConfigureBtnPretoEBranco();
        createAndConfigureBtnSalvar();
    }

    private void createAndConfigureBtnSalvar() {
        btnSalvar = new Button("Salvar");
        btnSalvar.addActionListener(evt -> {
            if (btnSalvar != null) {
                salvarImagemListener.onSalverImagem();
            }
        });
    }

    private void createAndConfigureBtnPretoEBranco() {
        btnPretoEBranco = new Button("Preto & Branco");
        btnPretoEBranco.addActionListener(evt -> {
            if (pretoEBrancoListener != null) {
                pretoEBrancoListener.onPretoEBranco();
            }
        });
    }

    private void createAndConfigureBtnEqualizar() {
        btnEqualizar = new Button("Equalizar");
        btnEqualizar.addActionListener(evt -> {
            if (equalizarImagemListener != null) {
                equalizarImagemListener.onEqualizarImagem();
            }
        });
    }

    private void createAndConfigureBtnCarregar() {
        btnCarregar = new Button("Carregar");
        btnCarregar.addActionListener(evt -> {
            if (carregarImagemListener != null) {
                carregarImagemListener.onCarregarImagem();
            }
        });
    }

    public void setCarregarImagemListener(CarregarImagemListener carregarImagemListener) {
        this.carregarImagemListener = carregarImagemListener;
    }

    public void setEqualizarImagemListener(EqualizarImagemListener equalizarImagemListener) {
        this.equalizarImagemListener = equalizarImagemListener;
    }

    public void setSalvarImagemListener(SalvarImagemListener salvarImagemListener) {
        this.salvarImagemListener = salvarImagemListener;
    }

    public void setPretoEBrancoListener(PretoEBrancoListener pretoEBrancoListener) {
        this.pretoEBrancoListener = pretoEBrancoListener;
    }
}
