package adventuregame.game.events.menuevents;

import adventuregame.game.AdventurerController;
import adventuregame.game.DisplayIO;
import adventuregame.game.events.Event;

import java.util.List;

public class SelectLevelEvent implements Event {
    @Override
    public void run(AdventurerController controller) {
        DisplayIO.display("The level is currently set to: " + controller.getSelectedLevel());
        List<String> levelOptions = controller.levelOptions();
        int userInput = DisplayIO.getUserSelectionFromList(levelOptions);

        int selectionChangeResult = controller.setSelectedLevel(levelOptions.get(userInput));

        if (selectionChangeResult == -1)
            DisplayIO.display("Error: level has not been set to new selection, try again.");
        else
            DisplayIO.display("You have changed the level to: " + controller.getSelectedLevel());
    }
}
