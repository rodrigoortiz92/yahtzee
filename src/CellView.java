
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class CellView extends JTextField implements Observer {

    ScoreCell scoreCell;
    CellController controller;

    public CellView(ScoreCell scoreCell) {
        this.scoreCell = scoreCell;
        controller = new CellController(this, scoreCell);

        this.addMouseListener(controller);

        setColumns(5);
        setEditable(false);
        setBackground(Color.white);
        setFocusable(false);

        scoreCell.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ScoreCell.ScoreChangedNotification) {
            String text = " ";

            if (scoreCell.getScore() != null) {
                text = String.valueOf(scoreCell.getScore());
            }

            setForeground(new Color(0f, 0f, 0f));
                setFont(getFont().deriveFont(Font.PLAIN));


            setText(text);
        }
        if (arg instanceof MarkableScoreCell.MarkableChangeNotification) {
            MarkableScoreCell markableScoreCell = (MarkableScoreCell) scoreCell;

            if (markableScoreCell.isMarkable()) {
                setForeground(new Color(0.5f, 0.5f, 0.5f));
                setFont(getFont().deriveFont(Font.ITALIC));
                setText(String.valueOf(markableScoreCell.getMarkableScore()));
            }
            else
            {
                 setText(" ");
            }
        }
    }
}
