package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    void testRoll() {
        Dice dice = new Dice();
        int rollResult = dice.roll();

        // Check if the roll result is within the valid dice range (1 to 6)
        assertTrue(rollResult >= 1 && rollResult <= 6, "Dice roll should return a value between 1 and 6.");
    }

    @Test
    void testRollMultipleTimes() {
        Dice dice = new Dice();
        boolean validRolls = true;

        // Roll the dice multiple times to ensure it generates valid results each time
        for (int i = 0; i < 1000; i++) {
            int rollResult = dice.roll();
            if (rollResult < 1 || rollResult > 6) {
                validRolls = false;
                break;
            }
        }

        assertTrue(validRolls, "Dice roll should always return a value between 1 and 6.");
    }
}
