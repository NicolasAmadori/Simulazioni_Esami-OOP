package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        logic = new LogicImpl(size);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
            logic.move(cells.get(button));
        	updateButtons();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        updateButtons();
        this.setVisible(true);
    }
    
    private void updateButtons(){
        for (var entry : cells.entrySet()) {
            entry.getKey().setText(logic.getCellPiece(entry.getValue()).toString());
            entry.getKey().setEnabled(!logic.isOver());
        }
    }
}
