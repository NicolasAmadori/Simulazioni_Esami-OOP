package a04.e2;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("QUIT");
    private Logic logic;

    public GUI(int size) {
        logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            if(logic.canStop()){
                System.out.println(logic.getResult());
                System.exit(0);
            }
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
            jb.setEnabled(!logic.hit(cells.get(jb)));
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("");
                this.cells.put(jb, new Pair<>(j, i));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }

        for (var entry : cells.entrySet()) {
            entry.getKey().setText(logic.getSymbol(entry.getValue()));
        }
        this.setVisible(true);
    }
}