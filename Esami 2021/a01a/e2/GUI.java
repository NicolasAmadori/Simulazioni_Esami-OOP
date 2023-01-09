package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    private int lastIndex = -1;
    private Logic l;

    public GUI(int size) {
        l = new Logic(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
            if(lastIndex == -1){
                lastIndex = cells.indexOf(button);
                button.setText("" + 1);
            }
            else{
                l.selectCell(cells.indexOf(button), lastIndex);
                if(l.isOver()){
                    System.exit(0);
                }
                lastIndex = -1;
            }
        	button.setEnabled(false);
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void Update(boolean[][] cells){

    }
}
