package adventuregame.game;

import adventuregame.game.events.Event;
import adventuregame.game.events.EventFactory;

public class Main {

    public static void main(String[] args) {
        AdventurerController playerController = new AdventurerController();

        DisplayIO.display("Welcome to Adventure! Build version: 0.1.0.\n");

        Event event = EventFactory.getEvent("Main");
        event.run(playerController);
    }
}
