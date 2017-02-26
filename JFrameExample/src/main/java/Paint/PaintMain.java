package Paint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PaintMain {
    JButton clearBtn, colorBtn, bColorBtn, rotateBtn, scaleBtn;
    JSlider sizeBrush;
    Painter drawArea;


    public static void main(String[] args) {
        new PaintMain().show();
    }



    public void show() {
        JFrame frame = new JFrame("Painter");
        frame.setSize(800, 865);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new Painter();

        content.add(drawArea, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        bColorBtn = new JButton("Fill with color");
        bColorBtn.addActionListener(actionListener);
        colorBtn = new JButton("Choose brush color");
        colorBtn.addActionListener(actionListener);
        sizeBrush = new JSlider(1, 20, 2);
        sizeBrush.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                drawArea.setBrushSize(sizeBrush.getValue());
            }
        });
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);


        rotateBtn = new JButton("Rotate");
        rotateBtn.addActionListener(actionListener);

        scaleBtn = new JButton("Scale");
        scaleBtn.addActionListener(actionListener);

        controls.add(clearBtn);
        controls.add(colorBtn);
        controls.add(sizeBrush);
        controls.add(bColorBtn);
        controls.add(rotateBtn);
        controls.add(scaleBtn);



        content.add(controls, BorderLayout.SOUTH);


        frame.setVisible(true);

    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == colorBtn) {
                drawArea.chooseBrushColor();
            } else if(e.getSource() == bColorBtn) {
                drawArea.chooseBackColor();
            } else if(e.getSource() == rotateBtn) {
                drawArea.Srotate();
            } else if(e.getSource() == scaleBtn) {
                drawArea.scale();
            }
        }
    };

}
