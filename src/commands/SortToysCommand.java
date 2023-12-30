package commands;

import playroom.Playroom;

/**
 * Команда для сортування іграшок
 */
public class SortToysCommand implements Command {
    private final Playroom playroom;

    public SortToysCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.sortToysBy();
    }
}
