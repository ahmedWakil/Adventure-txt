package adventuregame.game;

import adventuregame.json.JsonFileReaderTest;
import adventuregame.json.JsonTest;
import adventuregame.json.PrototypeLoaderTest;
import adventuregame.world.Generators.WfcTest;
import adventuregame.world.grid.CoordinateTest;
import adventuregame.world.grid.WaveFunctionGridTest;
import adventuregame.world.locations.PrototypeTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Build Verification Test")
@SelectClasses({
        JsonFileReaderTest.class,
        JsonTest.class,
        PrototypeLoaderTest.class,
        AdventurerControllerTest.class,
        WfcTest.class,
        CoordinateTest.class,
        WaveFunctionGridTest.class,
        PrototypeTest.class
})

public class MinimumBuildVerificationTests {
}
