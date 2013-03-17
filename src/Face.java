import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;

public class Face extends JComponent {

    private final int SIDE = 32;
    private final int ARC = 12;
    private final int DIA = 6;
    private final int OFFSET = 6;
    private final Color BORDER = Color.black;
    private final Color SURFACE = Color.white;
    private final Color DOT = Color.black;

    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(BORDER);
        g2.drawRoundRect(0, 0, SIDE, SIDE, ARC, ARC);
        g2.setColor(SURFACE);
        g2.fillRoundRect(1, 1, SIDE-1, SIDE-2, ARC, ARC);
    }

    public Dimension getPreferredSize(){
        return new Dimension(SIDE,SIDE+1);
    }

    public Face getFace(int i){

        switch (i){
            case 1:
                return new Face1();
            case 2:
                return new Face2();
            case 3:
                return new Face3();
            case 4:
                return new Face4();
            case 5:
                return new Face5();
            case 6:
                return new Face6();
        }
        return null;
    }

    public class Face1 extends Face {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Face2 extends Face {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE/OFFSET, SIDE/OFFSET, DIA, DIA);
            g2.fillOval(SIDE/OFFSET, SIDE/OFFSET, DIA, DIA);
            g2.drawOval(SIDE - SIDE/OFFSET - DIA, SIDE - SIDE/OFFSET - DIA, DIA, DIA);
            g2.fillOval(SIDE - SIDE/OFFSET - DIA, SIDE - SIDE/OFFSET - DIA, DIA, DIA);

        }
    }

    public class Face3 extends Face2 {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Face4 extends Face2 {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE - SIDE/OFFSET - DIA, SIDE/OFFSET, DIA, DIA);
            g2.fillOval(SIDE - SIDE/OFFSET - DIA, SIDE/OFFSET, DIA, DIA);
            g2.drawOval(SIDE/OFFSET, SIDE - SIDE/OFFSET - DIA, DIA, DIA);
            g2.fillOval(SIDE/OFFSET, SIDE - SIDE/OFFSET - DIA, DIA, DIA);
        }
    }

    public class Face5 extends Face4 {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Face6 extends Face4 {

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(DOT);
            g2.drawOval(SIDE/OFFSET, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/OFFSET, SIDE/2 - DIA/2, DIA, DIA);
            g2.drawOval(SIDE - SIDE/OFFSET - DIA, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE - SIDE/OFFSET - DIA, SIDE/2 - DIA/2, DIA, DIA);

        }
    }
}
