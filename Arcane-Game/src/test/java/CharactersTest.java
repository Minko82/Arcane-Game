import characters.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharactersTest
{
    @Test
    public void dieRoll_oneRoll()
    {
        Characters character = new Characters();
        int result = character.rollDie();

        assertTrue(result >= 1 && result <= 6, "Dice roll out of range"); // Tests one roll
    }
    @Test
    public void dieRoll_multipleRolls()
    {
        // Given
        Characters character = new Characters();
        boolean inDieRange = true;

        // When
        for (int i = 0; i < 10; i++) {
            if ((character.rollDie() < 1) || (character.rollDie() > 6)) {
                inDieRange = false;
            }
        }

        // Then
        assertTrue(inDieRange);
    }
    @Test
    public void setName_and_getName_characterNameShouldChange(){
        // Given
        Characters testCharacter = new Characters();

        // When
        testCharacter.setName("Rat");

        // Then
        assertEquals("Rat", testCharacter.getName());
    }
    @Test
    public void setHealth_and_getHealth_characterHealthShouldChange(){
        // Given
        Characters testCharacter = new Characters();

        // When
        testCharacter.setHealth(500);

        // Then
        assertEquals(500, testCharacter.getHealth());
    }
    @Test
    public void constructor_validatingCharacterNameAndHealth(){
        // Given
        Characters testCharacter = new Characters();

        //When

        // Then
        assertEquals(1, testCharacter.getHealth());
        assertEquals("characterConstructor", testCharacter.getName());
    }

    @Test
    public void setLocation_and_getLocation_characterLocationShouldChange(){
        // Given
        Characters testCharacter = new Characters();

        // When
        testCharacter.setLocation(2);

        // Then
        assertEquals(2, testCharacter.getLocation());
    }

}