package Paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Саоша on 17.11.2016.
 */
public class Painter extends JComponent {


    private Image image, file;
    private Graphics2D g2;
    private int size;
    private Color background, brush;
    private int oldX, oldY, curX, curY, rotate;

    public Painter() {
        background = Color.white;
        brush = Color.black;
        size = 2;
        rotate = 0;

        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                oldX = e.getX();
                oldY = e.getY();

                if(g2 != null) {
                    g2.fillRect(oldX, oldY, size, size);
                    repaint();
                }
            }
            }
        );

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {


                curX = e.getX();
                curY = e.getY();

                if (g2 != null) {
                    g2.setStroke(new BasicStroke(size));
                    g2.drawLine(oldX, oldY, curX, curY);
                    repaint();
                    oldX = curX;
                    oldY = curY;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);
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


//    public void rotate(int rotation) {
//        rotate = rotation;
//        repaint();
//    }
}
