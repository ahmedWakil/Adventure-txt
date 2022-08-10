package adventuregame.game.events.gameplayevents;

import adventuregame.game.AdventurerController;
import adventuregame.game.DisplayIO;
import adventuregame.game.events.Event;

import java.util.List;

public class NextLevelEvent implements Event {

    @Override
    public void run(AdventurerController controller) {
        DisplayIO.display("We found the portal that will lead us to the next world\n");
        DisplayIO.display("Would like to step through? or remain here.");
        int userResponse = DisplayIO.getUserSelectionFromList(List.of("Step through", "Remain here"));

        if (userResponse == 1) {
            controller.setNextLevelSize();
            controller.createMap();
            DisplayIO.display(controller.currentLocationIntroduction());
        }
    }
}
