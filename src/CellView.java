
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class CellView extends JTextField implements Observer {

    private ScoreCell scoreCell;
    private CellController controller;
    private boolean darkened;
    private final Color DARK_PRESSABLE = new Color(0.85f, 0.85f, 0.85f);
    private final Color LIGHT_PRESSABLE = new Color(0.9f, 0.9f, 0.9f);
    private final Color DARK = new Color(0.95f, 0.95f, 0.95f);
    private final Color LIGHT = new Color(1f, 1f, 1f);

    public CellView(ScoreCell scoreCell, boolean darkened) {
        this.scoreCell = scoreCell;

        if (scoreCell != null) {
            scoreCell.addObserver(this);
            controller = new CellController(this, scoreCell);
        }

        this.darkened = darkened;
        this.addMouseListener(controller);

        setColumns(5);
        setEditable(false);
        setFocusable(false);
        applyNormalLook();
    }

    private void applyPressableLook() {
        setBackground(darkened ? DARK_PRESSABLE : LIGHT_PRESSABLE);
        setForeground(new Color(0f, 0f, 0f));
        setFont(getFont().deriveFont(Font.ITALIC));

        Border bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border line = BorderFactory.createLineBorder(Color.black);

        setBorder(BorderFactory.createCompoundBorder(line, bevel));
    }

    private void applyNormalLook() {
        setBackground(darkened ? DARK : LIGHT);
        setForeground(new Color(0f, 0f, 0f));
        setFont(getFont().deriveFont(Font.PLAIN));
        Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        Border line = BorderFactory.createLineBorder(Color.black);

        setBorder(BorderFactory.createCompoundBorder(line, empty));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ScoreCell.ScoreChangedNotification) {
            if (scoreCell.getScore() != null) {
                setText(String.valueOf(scoreCell.getScore()));
            }

            applyNormalLook();

        }
        if (arg instanceof MarkableScoreCell.MarkableChangeNotification) {
            MarkableScoreCell markableScoreCell = (MarkableScoreCell) scoreCell;

            if (markableScoreCell.isMarkable()) {
                applyPressableLook();
                setText(String.valueOf(markableScoreCell.getMarkableScore()));
            } else {
                applyNormalLook();
                setText(" ");
            }
        }
    }
}
