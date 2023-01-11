package a06.e2;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.management.LockInfo;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("Advance");
    private Logic logic;

    public GUI(int size) {
        logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            if(logic.isOver()){
                System.exit(0);
            }
            disableButtons();
            logic.advance();
            updateButtons();
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
            logic.select(cells.get(jb));
            updateButtons();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("  ");
                this.cells.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.setVisible(true);
    }

    private void updateButtons(){
        for (var entry : cells.entrySet()) {
            switch(logic.getCellType(entry.getValue())){
                case CENTER:
                entry.getKey().setText("o");
                break;
                case WING:
                entry.getKey().setText("*");
                break;
                case NONE:
                entry.getKey().setText("");
                break;
            }
        }
    }

    private void disableButtons(){
        for (var entry : cells.entrySet()) {
            entry.getKey().setEnabled(false);
        }
    }
}