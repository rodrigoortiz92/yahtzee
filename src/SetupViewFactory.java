
import java.awt.Window;

public class SetupViewFactory {

    private AddPlayerView addPlayerView;
    private SetupModel setupModel;
    private AddPlayerModel addPlayerModel;
    private Window owner;

    public SetupViewFactory(AddPlayerView addPlayerView, SetupModel setupModel,
            AddPlayerModel addPlayerModel) {
        this.addPlayerView = addPlayerView;
        this.setupModel = setupModel;
        this.addPlayerModel = addPlayerModel;
        this.owner = null;
    }

    SetupView createEmpty() {
        setupModel.clear();
        addPlayerModel.clear();
        return new SetupView(setupModel, addPlayerView, getOwner());
    }

    SetupView create() {
        return new SetupView(setupModel, addPlayerView, getOwner());
    }

    public Window getOwner() {
        return owner;
    }

    public void setOwner(Window owner) {
        this.owner = owner;
    }
}
