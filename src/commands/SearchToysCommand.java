package commands;

import playroom.Playroom;

/**
 * Команда для пошуку іграшок за параметрами
 */
public class SearchToysCommand implements Command {
    private final Playroom playroom;

    public SearchToysCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.searchToys();
    }
}
