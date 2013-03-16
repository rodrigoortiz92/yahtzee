import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;

public class Facet extends JComponent {

    private final int SIDE = 64;
    private final int ARC = 8;

    protected void paintComponent(Graphics g){


        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);
        g2.drawRoundRect(0, 0, SIDE, SIDE, ARC, ARC);
        g2.fillRoundRect(0, 0, SIDE, SIDE, ARC, ARC);
    }

    public class Facet1 extends Facet {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Facet2 extends Facet {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE/9, SIDE/9, DIA, DIA);
            g2.fillOval(SIDE/9, SIDE/9, DIA, DIA);
            g2.drawOval(SIDE - SIDE/9 - DIA, SIDE - SIDE/9 - DIA, DIA, DIA);
            g2.fillOval(SIDE - SIDE/9 - DIA, SIDE - SIDE/9 - DIA, DIA, DIA);

        } 
    }

    public class Facet3 extends Facet2 {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Facet4 extends Facet2 {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE - SIDE/9 - DIA, SIDE/9, DIA, DIA);
            g2.fillOval(SIDE - SIDE/9 - DIA, SIDE/9, DIA, DIA);
            g2.drawOval(SIDE/9, SIDE - SIDE/9 - DIA, DIA, DIA);
            g2.fillOval(SIDE/9, SIDE - SIDE/9 - DIA, DIA, DIA);
        }
    }

    public class Facet5 extends Facet4 {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/2 - DIA/2, SIDE/2 - DIA/2, DIA, DIA);
        }
    }

    public class Facet6 extends Facet4 {

        private final int DIA = (int)(SIDE/10);

        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.white);
            g2.drawOval(SIDE/9, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE/9, SIDE/2 - DIA/2, DIA, DIA);
            g2.drawOval(SIDE - SIDE/9 - DIA, SIDE/2 - DIA/2, DIA, DIA);
            g2.fillOval(SIDE - SIDE/9 - DIA, SIDE/2 - DIA/2, DIA, DIA);

        }
    }
}
