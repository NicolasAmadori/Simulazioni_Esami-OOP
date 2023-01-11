package a03c.e2;

import javax.swing.*;
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
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            logic.hit();
        	for (var entry : cells.entrySet()) {
                switch(logic.getCellChar(entry.getValue())){
                    case OBSTACLE:
                        entry.getKey().setText("O");
                    break;
                    case BALL:
                        entry.getKey().setText("*");
                    break;
                    case NONE:
                        entry.getKey().setText("");
                    break;
                }
                entry.getKey().setEnabled(!logic.isOver());
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        for (var entry : cells.entrySet()) {
            switch(logic.getCellChar(entry.getValue())){
                case OBSTACLE:
                    entry.getKey().setText("O");
                break;
                case BALL:
                    entry.getKey().setText("*");
                break;
                case NONE:
                    entry.getKey().setText("");
                break;
            }
            
        }
        this.setVisible(true);
    }
    
}
