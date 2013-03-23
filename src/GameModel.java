
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameModel extends Observable implements Observer {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer = null;
    private GameType gameType;
    private boolean transferRolls;
    public static final int MAX_PLAYER_COUNT = 10;
    public static final int ROLLS_PER_TURN = 3;

    public DiceModel getDiceModel() {
        return diceModel;
    }

    public class TurnEndNotification {

        public Player player;

        public TurnEndNotification(Player player) {
            this.player = player;
        }
    }

    public class TurnBeginNotification {

        public Player player;

        public TurnBeginNotification(Player player) {
            this.player = player;
        }
    }

    public static class PlayerDescription {

        public String name;
        public PlayerType type;

        public PlayerDescription(String name, PlayerType type) {
            this.name = name;
            this.type = type;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Player.CellMarkedNotification) {
            pickNextPlayer();
        }
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    public class NewGameNotification {
    }

    public class EndGameNotification {
    }

    public List<String> getScoreCellNames() {
        return gameType.createScoreColumn().getCellNames();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void pickNextPlayer() {
        if (players.isEmpty()) {
            currentPlayer = null;
            return;
        }

        int index;

        if (currentPlayer != null) {
            if (transferRolls) {
                currentPlayer.setStoredRolls(diceModel.getRollsLeft());
            }

            setChanged();
            notifyObservers(new TurnEndNotification(currentPlayer));

            index = players.indexOf(currentPlayer);
            index = (index + 1) % players.size();
        } else {
            index = 0;
        }

        currentPlayer = players.get(index);

        boolean found = false;

        for (ScoreCell cell : currentPlayer.getScoreCells()) {
            if (cell instanceof MarkableScoreCell) {
                MarkableScoreCell markable = (MarkableScoreCell) cell;

                if (markable.getScore() == null) {
                    found = true;
                }
            }
        }

        if (found) {
            if (transferRolls) {
                getDiceModel().clear(currentPlayer.acceptsUiInput(),
                        ROLLS_PER_TURN + currentPlayer.getStoredRolls());
            } else {
                getDiceModel().clear(currentPlayer.acceptsUiInput(),
                        ROLLS_PER_TURN);
            }

            setChanged();
            notifyObservers(new TurnBeginNotification(currentPlayer));

            currentPlayer.playTurn(getDiceModel());
        } else {
            setChanged();
            notifyObservers(new EndGameNotification());
        }
    }

    public GameModel(List<PlayerDescription> descriptions, GameType gameType,
            boolean transferRolls) {
        this.diceModel = new DiceModel(gameType.getDieCount());
        this.players = new LinkedList<>();
        this.gameType = gameType;
        this.transferRolls = transferRolls;

        for (PlayerDescription desc : descriptions) {
            ScoreColumn column = gameType.createScoreColumn();
            Player player = desc.type.createPlayer(this, diceModel, column, desc.name);
            player.addObserver(this);
            this.addObserver(player);

            players.add(player);
        }

        pickNextPlayer();
    }

    public Integer getMaximumScore() {
        ScoreColumn column = gameType.createScoreColumn();
        column.setThings(null, diceModel);
        return column.getTotalCell().getMaximumScore();
    }
}
