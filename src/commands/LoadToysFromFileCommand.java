package commands;
import playroom.Playroom;

/**
 * Команда для завантаження іграшок з файлу
 */
public class LoadToysFromFileCommand implements Command{
    private final Playroom playroom;
    public LoadToysFromFileCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.loadToysFromFile(playroom.toyList);
    }
}
