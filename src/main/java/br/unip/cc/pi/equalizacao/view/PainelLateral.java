package br.unip.cc.pi.equalizacao.view;

import br.unip.cc.pi.equalizacao.model.AnalizadorImagem;
import br.unip.cc.pi.equalizacao.model.Histograma;
import br.unip.cc.pi.equalizacao.view.jfreechart.theme.MyChartTheme;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static br.unip.cc.pi.equalizacao.model.Constantes.INTENSIDADE_ESCALA_RGB;

public class PainelLateral extends JPanel {

    private final ChartPanel chartPanelVermelho;
    private final ChartPanel chartPanelAzul;
    private final ChartPanel chartPanelVerde;

    private final JLabel labelVermelha;
    private final JLabel labelAzul;
    private final JLabel labelVerde;

    public PainelLateral() {
        setPreferredSize(new Dimension(400, 774));
        setOpaque(false);

        labelVermelha = new JLabel("");
        labelAzul = new JLabel("");
        labelVerde = new JLabel("");

        labelVermelha.setForeground(new Color(234, 234, 232));
        labelAzul.setForeground(new Color(234, 234, 232));
        labelVerde.setForeground(new Color(234, 234, 232));

        this.chartPanelVermelho = new ChartPanel(null);
        this.chartPanelAzul = new ChartPanel(null);
        this.chartPanelVerde = new ChartPanel(null);

        this.chartPanelVermelho.setPreferredSize(new Dimension(380, 175));
        this.chartPanelAzul.setPreferredSize(new Dimension(380, 175));
        this.chartPanelVerde.setPreferredSize(new Dimension(380, 175));
        this.chartPanelVermelho.setOpaque(false);
        this.chartPanelAzul.setOpaque(false);
        this.chartPanelVerde.setOpaque(false);

        this.chartPanelVermelho.setPopupMenu(null);
        this.chartPanelAzul.setPopupMenu(null);
        this.chartPanelVerde.setPopupMenu(null);

        add(this.chartPanelVermelho);
        add(this.labelVermelha);
        add(this.chartPanelAzul);
        add(this.labelAzul);
        add(this.chartPanelVerde);
        add(this.labelVerde);

        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(77, 77, 77)));
    }

    public void updateChart(BufferedImage bufferedImage) {
        labelVermelha.setText("Carregando....");
        labelAzul.setText("Carregando....");
        labelVerde.setText("Carregando....");

        AnalizadorImagem analizadorImagem = new AnalizadorImagem();
        final Histograma histograma = analizadorImagem.criaHistograma(bufferedImage);

        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        final int totalPixels = width * height;

        List<Double> histogramaRed = new ArrayList<>(totalPixels);
        List<Double> histogramaBlue = new ArrayList<>(totalPixels);
        List<Double> histogramaGreen = new ArrayList<>(totalPixels);

        for (int i = 0; i < INTENSIDADE_ESCALA_RGB; i++) {
            int qtdPixelsVermelhos = histograma.getValorEscalaVermelha(i);
            int qtdPixelsAzuis = histograma.getValorEscalaAzul(i);
            int qtdPixelsVerdes = histograma.getValorEscalaVerde(i);

            for (int j = 0; j < qtdPixelsVermelhos; j++) {
                histogramaRed.add((double) i);
            }
            for (int j = 0; j < qtdPixelsAzuis; j++) {
                histogramaBlue.add((double) i);
            }
            for (int j = 0; j < qtdPixelsVerdes; j++) {
                histogramaGreen.add((double) i);
            }
        }

        final double[] histogramaVermelhoArray = histogramaRed.parallelStream().mapToDouble(i -> i).toArray();
        final double[] histogramaAzulArray = histogramaBlue.parallelStream().mapToDouble(i -> i).toArray();
        final double[] histogramaVerdeArray = histogramaGreen.parallelStream().mapToDouble(i -> i).toArray();

        JFreeChart chartVermelho = createChart(histogramaVermelhoArray);
        EventQueue.invokeLater(() -> {
            chartPanelVermelho.setChart(chartVermelho);
            this.chartPanelVermelho.setOpaque(true);
            labelVermelha.setText("Canal Vermelho");
        });

        JFreeChart chartAzul = createChart(histogramaAzulArray);
        EventQueue.invokeLater(() -> {
            chartPanelAzul.setChart(chartAzul);
            this.chartPanelAzul.setOpaque(true);
            labelAzul.setText("Canal Azul");
        });

        JFreeChart chartVerde = createChart(histogramaVerdeArray);
        EventQueue.invokeLater(() -> {
            chartPanelVerde.setChart(chartVerde);
            this.chartPanelVerde.setOpaque(true);
            labelVerde.setText("Canal Verde");
        });
    }

    private JFreeChart createChart(double[] histogramaVermelhoArray) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Hist", histogramaVermelhoArray, 256, 0, 255);
        String plotTitle = "";
        String xAxis = "";
        String yAxis = "Quantidade";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        ChartFactory.setChartTheme(new MyChartTheme());
        final JFreeChart chart = ChartFactory.createHistogram(plotTitle, xAxis, yAxis,
                dataset, orientation, false, false, false);

        chart.getXYPlot().setBackgroundPaint(new Color(52, 73, 94));
        final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
        renderer.setDefaultFillPaint(new Color(231, 76, 60));

        if (renderer instanceof XYBarRenderer) {
            ((XYBarRenderer) renderer).setBarPainter(new StandardXYBarPainter());
        }
        return chart;
    }
}
