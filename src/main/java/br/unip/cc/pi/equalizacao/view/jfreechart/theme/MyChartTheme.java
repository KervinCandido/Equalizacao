package br.unip.cc.pi.equalizacao.view.jfreechart.theme;

import org.jfree.chart.StandardChartTheme;

import java.awt.*;

public class MyChartTheme extends StandardChartTheme {

    public MyChartTheme() {
        super("My Chart Theme");
        this.setAxisLabelPaint(new Color(142, 68, 173));
        this.setChartBackgroundPaint(new Color(43, 43, 43));
        this.setTickLabelPaint(new Color(255, 255, 255));
    }
}
