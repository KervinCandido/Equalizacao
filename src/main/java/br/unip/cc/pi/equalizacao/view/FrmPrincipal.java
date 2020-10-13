package br.unip.cc.pi.equalizacao.view;

import br.unip.cc.pi.equalizacao.model.Equalizador;
import br.unip.cc.pi.equalizacao.model.FiltroPretoEBranco;
import br.unip.cc.pi.equalizacao.model.Imagem;
import br.unip.cc.pi.equalizacao.util.CustomJMenuBarUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrmPrincipal extends JFrame {

    private static final int PREFERRED_WIDTH = 1200;
    private static final int PREFERRED_HEIGHT = 800;
    private PainelImageView painelImageView;
    private Imagem imagem;
    private PainelLateral painelLateral;

    public void createAndShow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        JPanel conteiner = new JPanel(new BorderLayout());
        setContentPane(conteiner);
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        pack();
        setLocationRelativeTo(null);

        conteiner.setBackground(new Color(43, 43, 43));
        conteiner.setLayout(new BorderLayout());
        configuraMenuBar();

        final PainelControlador painelControlador = new PainelControlador();
        painelControlador.setCarregarImagemListener(this::carregarImage);
        painelControlador.setEqualizarImagemListener(this::equalizarImagem);
        painelControlador.setPretoEBrancoListener(this::tornarPretoEBranco);
        painelControlador.setSalvarImagemListener(this::salvarImage);
        conteiner.add(painelControlador, BorderLayout.PAGE_START);

        painelImageView = new PainelImageView();
        conteiner.add(painelImageView, BorderLayout.CENTER);

        painelLateral = new PainelLateral();
        conteiner.add(painelLateral, BorderLayout.LINE_END);

        setVisible(true);
    }

    private void salvarImage() {
        if (imagem.getBufferedImage() == null) return;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));

        int codeReturn = fileChooser.showSaveDialog(this);
        if (codeReturn == JFileChooser.APPROVE_OPTION) {
            try {
                final File selectedFile = fileChooser.getSelectedFile();
                final FileFilter fileFilter = fileChooser.getFileFilter();
                final String extencao = fileFilter.getDescription();

                ImageIO.write(imagem.getBufferedImage(), extencao, new File(selectedFile.getAbsolutePath() + "." + extencao));
                JOptionPane.showMessageDialog(this, "Imagem Salva Com Sucesso!!", "Imagem Salva", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Não foi possível salvar o arquivo",
                        "Falha ao Salvar",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tornarPretoEBranco() {
        FiltroPretoEBranco filtroPretoEBranco = new FiltroPretoEBranco();
        filtroPretoEBranco.tornaPretoEBranco(imagem.getBufferedImage());
        atualizaImagemEHistograma(imagem.getBufferedImage());
    }

    private void atualizaImagemEHistograma(BufferedImage bufferedImage) {
        painelImageView.loadImage(bufferedImage);
        EventQueue.invokeLater(()-> new Thread(() -> painelLateral.updateChart(bufferedImage)).start());
    }

    private void equalizarImagem() {
        if (this.imagem == null) return;

        Equalizador equalizador = new Equalizador();
        equalizador.equalizar(this.imagem.getBufferedImage());
        atualizaImagemEHistograma(imagem.getBufferedImage());
    }

    private void configuraMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu arquivo = new JMenu("Arquivo");
        arquivo.setBackground(new Color(60, 63, 65));

        final JMenuItem carregar = new JMenuItem("Carregar");
        carregar.addActionListener(evt -> carregarImage());

        final JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(evt -> System.exit(0));

        arquivo.add(carregar);
        arquivo.add(sair);

        menuBar.add(arquivo);
        setJMenuBar(menuBar);

        new CustomJMenuBarUtil().customizeMenuBar(menuBar);
    }

    private void carregarImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagem (*.jpeg, *.jpg, *.png)", "jpeg", "jpg", "png"));
        int codeReturn = fileChooser.showOpenDialog(this);
        if (codeReturn == JFileChooser.APPROVE_OPTION) {
            try {
                final BufferedImage bufferedImage = ImageIO.read(fileChooser.getSelectedFile());
                this.imagem = new Imagem(bufferedImage);
                atualizaImagemEHistograma(bufferedImage);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Não foi possível carregar a image",
                        "Falha ao carregar",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
