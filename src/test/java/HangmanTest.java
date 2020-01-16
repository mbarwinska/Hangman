import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {
    private Hangman hangman;

    @Before
    public void setUp() {
        hangman = new Hangman();
    }

    @Test
    public void shouldCheckIfGivenWordIsNotNullWhenWordIsSet() {

        //when
        hangman.setWord("bunny");

        //then
        assertThat(hangman.getWord()).isNotNull();
    }

    @Test
    public void shouldConvertSetWordToListOfStrings() {
        //given
        hangman.setWord("bunny");
        List<String> expectedWordLetterByLetter = Arrays.asList("b", "u", "n", "n", "y");

        //when
        List<String> wordLetterByLetter = hangman.convertWordToStringList();

        //then
        assertThat(wordLetterByLetter).isEqualTo(expectedWordLetterByLetter);

    }

    @Test
    public void shouldCreateListOfStringsOfTheSameSizeAsSizeOfListWithWord() {
        //given
        hangman.setWord("bunny");
        List<String> wordLetterByLetter = hangman.convertWordToStringList();

        //when
        List<String> blankWord = hangman.createBlankWord();

        //then
        assertThat(wordLetterByLetter.size()).isEqualTo(blankWord.size());
    }

    @Test
    public void shouldCreateListOfStringsFilledWithUnderscores() {
        //given
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        List<String> expectedBlankWord = Arrays.asList("_", "_", "_", "_", "_");

        //when
        List<String> blankWord = hangman.createBlankWord();

        //then
        assertThat(blankWord).isEqualTo(expectedBlankWord);
    }


    @Test
    public void shouldReturnTrueWhenThereIsWin() {
        //given
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        hangman.createBlankWord();
        hangman.insertPlayerLetterInBlankWord("b");
        hangman.insertPlayerLetterInBlankWord("u");
        hangman.insertPlayerLetterInBlankWord("n");
        hangman.insertPlayerLetterInBlankWord("y");

        //when
        boolean isWin = hangman.isWin();

        //then
        assertThat(isWin).isTrue();

    }

    @Test
    public void shouldReturnFalseWhenThereIsNoWin() {
        //given
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        hangman.createBlankWord();
        hangman.insertPlayerLetterInBlankWord("b");
        hangman.insertPlayerLetterInBlankWord("u");
        hangman.insertPlayerLetterInBlankWord("n");
        hangman.insertPlayerLetterInBlankWord("a");

        //when
        boolean isWin = hangman.isWin();

        //then
        assertThat(isWin).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoWinBecauseOfPenaltyPoints() {
        //given
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        hangman.createBlankWord();
        int counter = 0;
        int maxOfPenaltyPoints = 12;
        while (counter < maxOfPenaltyPoints + 1) {
            hangman.insertPlayerLetterInBlankWord("a");
            counter++;
        }
        //when
        boolean isWin = hangman.isWin();

        //then
        assertThat(isWin).isFalse();
    }
}
