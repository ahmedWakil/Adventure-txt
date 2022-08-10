package adventuregame.game.events.menuevents;

import adventuregame.game.AdventurerController;
import adventuregame.game.DisplayIO;
import adventuregame.game.events.Event;
import adventuregame.game.events.EventFactory;

import java.util.ArrayList;
import java.util.List;

public class MainEvent implements Event {
    private static final List<String> mainMenuOptions = new ArrayList<>(List.of("Start", "Select Level", "Quit"));

    @Override
    public void run(AdventurerController controller) {
        DisplayIO.display(controller.gameIntoInstructions());

        boolean running = true;
        while(running) {
            DisplayIO.display("MAIN MENU");
            int userResponse = DisplayIO.getUserSelectionFromList(mainMenuOptions);
            Event destinationEvent = EventFactory.getEvent(mainMenuOptions.get(userResponse));

            if (destinationEvent != null)
                destinationEvent.run(controller);
            else
                running = false;
        }
    }
}
