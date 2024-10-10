//import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void idNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void isEmpty() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void gateHorses() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(list);

        Horse horse = new Horse("AAA", 1, 1);
        assertEquals(list, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();

        for (Horse horse : list) {
           verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse1 = new Horse("AAA1", 1, 2.999);
        Horse horse2 = new Horse("AAA2", 1, 2);
        Horse horse3 = new Horse("AAA3", 1, 3);
        Horse horse4 = new Horse("AAA4", 1, 1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertSame(horse3, hippodrome.getWinner());
    }


}