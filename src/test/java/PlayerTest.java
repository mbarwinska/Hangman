import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
    @Mock
    private Player player;

    @Test
    public void shouldReturnTrueWhenPlayerDidNotInsertAnything() {
        //given
        Mockito.when(player.insertLetter()).thenReturn("");
        //when
        String playerLetter = player.insertLetter();
        //then
        assertThat(playerLetter.isEmpty()).isTrue();
    }

    @Test
    public void shouldCheckIfPlayerInsertedAnyLetter() {
        //given
        Mockito.when(player.insertLetter()).thenReturn("b");
        //when
        String playerLetter = player.insertLetter();
        //then
        assertThat(playerLetter).isNotEmpty();

    }

}