package com.jetbrains;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;

public class Form {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton btnenter;
    private JButton btndelete;
    private JButton btnClear;
    private JPanel tablet;

    private rbt rbtt;
    public Form() {
        btnenter.addActionListener(actionEvent -> btnenter_Click());
        btndelete.addActionListener(actionEvent -> btndelete_Click());
        btnClear.addActionListener(actionEvent -> btnClear_Click());

        rbtt = new rbt();
    }


    private void btnClear_Click() {
        String s= textField1.getText();
        rbtt.Clear();
        drawGraph(rbtt.buildGraph());

    }

    private void btndelete_Click() {
       String s= textField1.getText();
       rbtt.DeleteRBT(Integer.parseInt(s));
        drawGraph(rbtt.buildGraph());
    }

    private void btnenter_Click() {
        String s= textField1.getText();
        rbtt.insert(Integer.parseInt(s));
        drawGraph(rbtt.buildGraph());
    }

    private void drawGraph(Forest<BSTNODE, Integer> graph) {

        Transformer<BSTNODE, Paint> vertexColor = (myrbtNode ->myrbtNode.color==0?Color.RED:Color.BLACK) ;
        Transformer<BSTNODE, String> vertexText = myrbtNode -> String.valueOf(myrbtNode.data);

        Layout<BSTNODE, Integer> layout = new TreeLayout<>(graph);
        VisualizationViewer<BSTNODE, Integer> vv = new VisualizationViewer<>(layout);

        vv.getRenderContext().setVertexLabelTransformer(vertexText);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);

        final DefaultModalGraphMouse<String, Number> graphMouse = new DefaultModalGraphMouse<>();
        vv.setGraphMouse(graphMouse);
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

        tablet.removeAll();
        tablet.add(vv);
        tablet.revalidate();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
