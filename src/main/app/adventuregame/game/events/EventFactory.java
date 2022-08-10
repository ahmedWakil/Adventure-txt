package adventuregame.game.events;

import adventuregame.game.events.gameplayevents.GameEvent;
import adventuregame.game.events.gameplayevents.MovePeakEvent;
import adventuregame.game.events.gameplayevents.NextLevelEvent;
import adventuregame.game.events.menuevents.MainEvent;
import adventuregame.game.events.menuevents.SelectLevelEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventFactory {
    public static final List<String> pageList = new ArrayList<>();
    private static final Map<String, Event> pageMap = initialize();

    private static Map<String, Event> initialize() {
        Map<String, Event> mapping = new HashMap<>();
        mapping.put("Main", new MainEvent());
        mapping.put("Start", new GameEvent());
        mapping.put("Select Level", new SelectLevelEvent());
        mapping.put("move north", new MovePeakEvent("move", "NORTH"));
        mapping.put("move south", new MovePeakEvent("move", "SOUTH"));
        mapping.put("move east", new MovePeakEvent("move", "EAST"));
        mapping.put("move west", new MovePeakEvent("move", "WEST"));
        mapping.put("peak", new MovePeakEvent("peak", ""));
        mapping.put("peak north", new MovePeakEvent("peak", "NORTH"));
        mapping.put("peak south", new MovePeakEvent("peak", "SOUTH"));
        mapping.put("peak east", new MovePeakEvent("peak", "EAST"));
        mapping.put("peak west", new MovePeakEvent("peak", "WEST"));
        mapping.put("NextLevel", new NextLevelEvent());

        pageList.addAll(mapping.keySet());

        return mapping;
    }

    public static Event getEvent(String pageName) {
        return pageMap.get(pageName);
    }
}
