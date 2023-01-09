package a02b.e2;

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
            if(logic.isOver()){
                System.exit(0);
            }
            else{
                logic.move();
                for (var cell : cells.entrySet()) {
                    cell.getKey().setText(logic.getCellChar(cell.getValue().getX(), cell.getValue().getY()));
                }
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

        for (var cell : cells.entrySet()) {
            cell.getKey().setText(logic.getCellChar(cell.getValue().getX(), cell.getValue().getY()));
        }
        this.setVisible(true);
    }
    
}
