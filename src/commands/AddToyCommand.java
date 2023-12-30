package commands;

import playroom.Playroom;

/**
 * Команда для додавання іграшки
 */
public class AddToyCommand implements Command {
    private final Playroom playroom;

    public AddToyCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.addToy();
    }
}
