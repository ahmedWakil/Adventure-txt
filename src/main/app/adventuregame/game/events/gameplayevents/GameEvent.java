package adventuregame.game.events.gameplayevents;

import adventuregame.game.AdventurerController;
import adventuregame.game.DisplayIO;
import adventuregame.game.events.Event;
import adventuregame.game.events.EventFactory;

import java.util.*;

public class GameEvent implements Event {

    private static final Map<String, List<String>> commandMapping = initialize();

    private static Map<String, List<String>> initialize() {
        Map<String, List<String>> commands = new HashMap<>();
        commands.put("move", List.of("north", "south", "east", "west"));
        commands.put("peak", List.of("north", "south", "east", "west"));
        commands.put("quit", new ArrayList<>());
        commands.put("help", new ArrayList<>());

        return commands;
    }

    @Override
    public void run(AdventurerController controller) {
        DisplayIO.display("Now that we are on our way, I should tell you what exactly what I can do for you");
        helpWithCommands();
        controller.createMap();
        DisplayIO.display(controller.currentLocationIntroduction());

        boolean playing = true;
        while (playing) {
            String command = DisplayIO.getUserCommand();
            playing = !command.equals("quit");
            Event destinationEvent = EventFactory.getEvent(command);

            if (destinationEvent != null)
                destinationEvent.run(controller);
            else if (command.equals("help"))
                helpWithCommands();
            else if (!command.equals("quit"))
                commandChecker(command);

            if (controller.atEnd() && playing)
                EventFactory.getEvent("NextLevel").run(controller);
        }
    }

    private void helpWithCommands() {
        DisplayIO.display("With the way this world works I am currently only restricted to these commands:\n");
        DisplayIO.display("move <direction>");
        DisplayIO.display("-I will move us in the specified direction if I am able\n");
        DisplayIO.display("peak <direction>");
        DisplayIO.display("-I will tell you what I see in the specified direction. If direction is left empty, " +
                "I can tell you all the directions we can travel and also tell you what I see in each of those direction.\n");
        DisplayIO.display("quit");
        DisplayIO.display("-Go back to the main menu\n");
    }

    private void commandChecker(String command) {
        String[] commandSplit = command.split(" ");
        Set<String> commands = commandMapping.keySet();
        List<String> parameterList;

        boolean validCommand = true;
        if (!commands.contains(commandSplit[0])) {
            DisplayIO.display("I do not recognize the command \"" + commandSplit[0] + "\" You can type \"help\" for more details");
            validCommand = false;
        } else {
            parameterList = commandMapping.get(commandSplit[0]);
            int i = 1;
            while (validCommand) {
                if (!parameterList.contains(commandSplit[i])) {
                    DisplayIO.display("I do not recognize the parameter <" + commandSplit[i] + "> for the command\"" +
                            commandSplit[0] + "\". You can type help for more details");
                    validCommand = false;
                }

                i++;
            }
        }
    }
}
