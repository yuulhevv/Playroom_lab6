package commands;

import playroom.Playroom;

/**
 * Команда для відображення списку іграшок
 */
public class DisplayToyCommand implements Command {
    private final Playroom playroom;

    public DisplayToyCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.displayToys(playroom.toyList);
    }
}
