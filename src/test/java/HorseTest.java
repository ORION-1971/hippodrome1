import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

//import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNametext() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n", "\s"})
    public void blankNameExep(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }


    @Test
    public void sppedExep() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("AAA", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void distExep() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("AAA", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void gateNameTest() {            // throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("AAA", 1, 1);
        assertEquals("AAA", horse.getName());

       /* Field name = Horse.class.getDeclaredField("name");                           // РЕФЛЕКСИЯ
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("AAA", nameValue);*/
    }

    @Test
    public void gateSpeedTest() {
        Horse horse = new Horse("AAA", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void gateDistanceTest() {
        Horse horse = new Horse("AAA", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void moveUserGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("AAA", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("AAA", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}
