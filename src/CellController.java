
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mikko Paukkonen
 */
public class CellController implements MouseListener {

    CellView view;
    ScoreCell scoreCell;

    public CellController(CellView view, ScoreCell scoreCell) {
        this.view = view;
        this.scoreCell = scoreCell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (scoreCell instanceof MarkableScoreCell) {
            MarkableScoreCell markable = (MarkableScoreCell) scoreCell;

            if (markable.getMarkableScore() == null) {
                return;
            }

            if (markable.getMarkableScore() == 0) {
                String yes = "Yes";
                String no = "No";

                int result = JOptionPane.showOptionDialog(view,
                        "Marking this cell will result in a score of zero in "
                        + "the cell. Do you really want to mark this cell?",
                        "Mark zero?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{yes, no}, no);

                if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            markable.markScore();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
