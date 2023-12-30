package commands;

import playroom.Playroom;

/**
 * Команда для видалення іграшки
 */
public class RemoveToyCommand implements Command {
    private final Playroom playroom;

    public RemoveToyCommand(Playroom playroom) {
        this.playroom = playroom;
    }

    @Override
    public void execute() {
        playroom.removeToy();
    }
}
