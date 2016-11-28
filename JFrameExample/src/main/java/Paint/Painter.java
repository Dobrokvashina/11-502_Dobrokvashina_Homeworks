package Paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class Painter extends JComponent {


    private Image image;
    private Graphics2D g2;
    private Graphics2D g2Component;
    private int size;
    private Color background, brush;
    private int oldX, oldY, curX, curY, rotate, rotate1;

    public Painter() {
        background = Color.white;
        brush = Color.black;
        size = 2;
        rotate = 0;

        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                g2.setColor(brush);
                if (rotate == 0) {
                    oldX = e.getX();
                    oldY = e.getY();
                }else if (rotate == 90){
                    oldX = e.getY();
                    oldY = getSize().height - e.getX();
                }else if (rotate == 180){
                    oldX = getSize().width  - e.getX();
                    oldY = getSize().height - e.getY();
                }else if (rotate == 270){
                    oldX = getSize().width  - e.getY();
                    oldY = e.getX();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (rotate == 0) {
                    curX = e.getX();
                    curY = e.getY();
                } else if (rotate == 90) {
                    curX = e.getY();
                    curY = getSize().height - e.getX();
                } else if (rotate == 180) {
                    curX = getSize().width  - e.getX();
                    curY = getSize().height - e.getY();
                } else if (rotate == 270) {
                    curX = getSize().width  - e.getY();
                    curY = e.getX();
                }

                if (g2 != null) {
                    g2.setStroke(new BasicStroke(size));
                    g2.drawLine(curX, curY, oldX, oldY);
                    repaint();

                    oldX = curX;
                    oldY = curY;
                }
            }}
        );

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2Component = (Graphics2D) g;

        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g2Component.rotate(Math.toRadians(rotate), getSize().width / 2, getSize().height / 2);
        g2Component.drawImage(image, 0, 0, null);

    }

    public void clear() {
        g2.setPaint(background);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(brush);
        repaint();
    }

    public void chooseBrushColor() {
        Color c = JColorChooser.showDialog(null, "Choose a Color", g2.getColor());
        if (c != null) {
            g2.setColor(c);
            brush = c;
        }
    }

    public void chooseBackColor() {
        Color c = JColorChooser.showDialog(null, "Choose Background", g2.getColor());
        if (c != null) {
            g2.setColor(c);
            background = c;
            clear();
        }
    }

    public void setBrushSize(int size) {
        this.size = size;
    }


    public void Srotate() {
        rotate1 += 90;
        rotate = rotate1 % 360;
        repaint();
    }

}
