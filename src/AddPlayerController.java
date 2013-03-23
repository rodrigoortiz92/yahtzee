
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddPlayerController {

    private AddPlayerView view;
    private AddPlayerModel model;
    private AddPlayerAction addPlayerAction = new AddPlayerAction();
    private NameListener nameListener = new NameListener();
    private TypeOptionAction typeOptionAction = new TypeOptionAction();

    public AddPlayerController(AddPlayerView view, AddPlayerModel model) {
        this.view = view;
        this.model = model;
    }

    public NameListener getNameListener() {
        return nameListener;
    }

    public AddPlayerAction getAddPlayerAction() {
        return addPlayerAction;
    }

    public TypeOptionAction getTypeOptionAction() {
        return typeOptionAction;
    }

    public class AddPlayerAction extends AbstractAction {

        public AddPlayerAction() {
            super("Add player");
        }

        @Override
        public void actionPerformed(ActionEvent a) {
            if (model.getPlayerType() == null) {
                JOptionPane.showMessageDialog(view.getParent(),
                        "Select a player type.",
                        "Select a player type",
                        JOptionPane.INFORMATION_MESSAGE, null);

                return;
            }

            final int MAX_LENGTH = 12;

            if (model.getName().length() > MAX_LENGTH) {
                JOptionPane.showMessageDialog(view.getParent(),
                        "The player name is too long. Maximum length is "
                        + MAX_LENGTH + " characters.",
                        "Name too long",
                        JOptionPane.INFORMATION_MESSAGE, null);

                return;
            }

            if (model.getName().equals("")) {
                JOptionPane.showMessageDialog(view.getParent(),
                        "Player has to have a name.",
                        "Input a name",
                        JOptionPane.INFORMATION_MESSAGE, null);

                return;
            }

            if (model.addPlayer()) {
                model.setName("");
            } else {
                JOptionPane.showMessageDialog(view.getParent(),
                        "The name is already in use.",
                        "Duplicate name",
                        JOptionPane.INFORMATION_MESSAGE, null);

                return;
            }
        }
    }

    public class NameListener implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        private void update() {
            model.setName(view.getName());
        }
    }

    public class TypeOptionAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setPlayerType(view.getType());
        }
    }
}
