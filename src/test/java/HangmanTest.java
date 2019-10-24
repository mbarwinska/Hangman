import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
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
        List<String> expectedWordLetterByLetter = Arrays.asList("b","u","n","n","y");

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
        List<String> expectedBlankWord = Arrays.asList("_","_","_","_","_");

        //when
        List<String> blankWord = hangman.createBlankWord();

        //then
        assertThat(blankWord).isEqualTo(expectedBlankWord);
    }

    @Mock
    private Player player;

    @Test
    public void shouldReturnFalseWhenPlayersLetterIsNotPresentInWord() { //nie działa z mockiem -.-
        //given
        player = new Player();
//        Mockito.when(player.insertLetter()).thenReturn("a");
        player.setPlayerLetter("a");
        hangman.setWord("bunny");

        //when
        boolean isLetterInWord = hangman.isLetterInWord(player.getPlayerLetter());

        //then
        assertThat(isLetterInWord).isFalse();

    }

    @Test
    public void shouldReturnTrueWhenPlayersLetterIsPresentInWord() {
        //given
        player = new Player();
        player.setPlayerLetter("u");
        hangman.setWord("bunny");
        hangman.convertWordToStringList();

        //when
           boolean isLetterInWord = hangman.isLetterInWord(player.getPlayerLetter());

        //then
        assertThat(isLetterInWord).isTrue();

    }

    @Test
    public void shouldInsertPlayerLetterToWordInProperPlaceWhenThereIsOneMatch() {
        //given
        player = new Player();
        player.setPlayerLetter("u");
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        hangman.createBlankWord();
        List<String> expectedFilledWord = Arrays.asList("_", "u", "_", "_", "_");

        //when
        List<String> filledWord = hangman.insertPlayerLetterInBlankWord(player.getPlayerLetter());

        //then
        assertThat(filledWord).isEqualTo(expectedFilledWord);

    }

    @Test
    public void shouldInsertPlayerLetterToWordInProperPlaceWhenThereIsMoreThanOneMatch() {
        //given
        player = new Player();
        player.setPlayerLetter("n");
        hangman.setWord("bunny");
        hangman.convertWordToStringList();
        hangman.createBlankWord();
        List<String> expectedFilledWord = Arrays.asList("_", "_", "n", "n", "_");

        //when
        List<String> filledWord = hangman.insertPlayerLetterInBlankWord(player.getPlayerLetter());

        //then
        assertThat(filledWord).isEqualTo(expectedFilledWord);

    }

    @Test
    public void shouldIncrementNumberOfPenaltyPointsBy1WhenThereIsNoMatch() {
        //given
        player = new Player();
        player.setPlayerLetter("a");
        hangman.setWord("bunny");
        int expectedPenaltyPoints = 1;

        //when
        hangman.insertPlayerLetterInBlankWord(player.getPlayerLetter());

        //then
        assertThat(hangman.getPenaltyPoints()).isEqualTo(expectedPenaltyPoints);
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
