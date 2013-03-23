
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Die extends JComponent {

    private final int SIDE = (32 * 4) / 3;
    private final int MIDDLE = SIDE / 2;
    private final int ARC = (12 * 4) / 3;
    private final int DIA = (6 * 4) / 3;
    private final int OFFSET = (6 * 4) / 2;
    private final Color BORDER = Color.black;
    private final Color SURFACE = Color.white;
    private final Color DOT = Color.black;

    private int value = DiceModel.DIE_MIN_VALUE;

    public void setValue(int value) {
        this.value = value;

        repaint();
    }

    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(BORDER);
        g2.drawRoundRect(0, 0, SIDE, SIDE, ARC, ARC);
        g2.setColor(SURFACE);
        g2.fillRoundRect(1, 1, SIDE - 1, SIDE - 2, ARC, ARC);

        getDrawer(value).paintDots(g);
    }

    public Dimension getMinimumSize() {
        return new Dimension(SIDE + 1, SIDE + 1);
    }

    public Dimension getPreferredSize() {
        return new Dimension(SIDE + 1, SIDE + 1);
    }

    private DotDrawer getDrawer(int i) {

        switch (i) {
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

    private abstract class DotDrawer {

        public abstract void paintDots(Graphics g);

        protected void drawDot(Graphics g, int x, int y) {
            g.setColor(DOT);
            g.drawOval(x - DIA / 2, y - DIA / 2, DIA, DIA);
            g.fillOval(x - DIA / 2, y - DIA / 2, DIA, DIA);
        }
    }

    public class Face1 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE, MIDDLE);
        }
    }

    public class Face2 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE - OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE + OFFSET);
        }
    }

    public class Face3 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE - OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE, MIDDLE);
            drawDot(g, MIDDLE + OFFSET, MIDDLE + OFFSET);
        }
    }

    public class Face4 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE - OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE + OFFSET);
            drawDot(g, MIDDLE - OFFSET, MIDDLE + OFFSET);
        }
    }

    public class Face5 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE - OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE + OFFSET);
            drawDot(g, MIDDLE - OFFSET, MIDDLE + OFFSET);
            drawDot(g, MIDDLE, MIDDLE);
        }
    }

    public class Face6 extends DotDrawer {

        public void paintDots(Graphics g) {
            drawDot(g, MIDDLE - OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE - OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE + OFFSET);
            drawDot(g, MIDDLE - OFFSET, MIDDLE + OFFSET);
            drawDot(g, MIDDLE + OFFSET, MIDDLE);
            drawDot(g, MIDDLE - OFFSET, MIDDLE);
        }
    }
}
