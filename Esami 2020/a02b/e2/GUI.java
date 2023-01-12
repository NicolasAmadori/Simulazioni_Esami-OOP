package a02b.e2;

import javax.swing.*;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.TextTable.Cell;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        logic = new LogicImpl(size);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        JButton up = new JButton("UP");
        JButton down = new JButton("DOWN");
        this.getContentPane().add(BorderLayout.NORTH,up);
        this.getContentPane().add(BorderLayout.SOUTH,down);
        
        up.addActionListener(e -> {
            logic.up();
            updateButtons();
        });

        down.addActionListener(e -> {
            logic.down();
            updateButtons();
        });
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	logic.select(cells.get(jb));
            updateButtons();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }

    private void updateButtons() {
        for (var entry : cells.entrySet()) {
            entry.getKey().setText(logic.isCellSelected(entry.getValue()) ? "*" : "");
        }
    }    
}
