package adventuregame.game.events.gameplayevents;

import adventuregame.game.AdventurerController;
import adventuregame.game.DisplayIO;
import adventuregame.game.events.Event;
import adventuregame.world.locations.Location;

import java.util.List;

public class MovePeakEvent implements Event {
    private final String TYPE;
    private final String DIRECTION;

    public MovePeakEvent(String type, String direction) {
        TYPE = type;
        DIRECTION = direction;
    }
    @Override
    public void run(AdventurerController controller) {

        if (TYPE.equals("move"))
            runMove(controller);

        if (TYPE.equals("peak"))
            runPeak(controller);
    }

    private void runPeak(AdventurerController controller) {

        if (DIRECTION.equals("")) {
            List<String> validDirections = controller.validDirections();
            List<Location> locationList = controller.peakAll(validDirections);

            for (int i = 0; i < validDirections.size(); i++) {
                DisplayIO.display("In the " + validDirections.get(i) + " direction, ");
                DisplayIO.display("I see a(n) " + locationList.get(i).getName() + "\n");
            }

        } else {
            List<Location> locations = controller.peakAll(List.of(DIRECTION));
            DisplayIO.display("In the " + DIRECTION + " direction, I see a(n) " + locations.get(0).getDescription() + "\n");
        }
    }

    private void runMove(AdventurerController controller) {
        List<String> validDirections = controller.validDirections();

        if (!validDirections.contains(DIRECTION)) {
            DisplayIO.display("The dark miasma in that direction makes it impossible to move in that direction");
        } else {
            controller.move(DIRECTION);
        }
    }
}
