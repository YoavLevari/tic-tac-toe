import java.util.Arrays;
import model.BasicModel;
import model.XO;
import org.junit.Test;

public class TestBoard {

  @Test
  public void test() {
    System.out.println(XO.X.name());
    BasicModel model = new BasicModel();

    model.playTurn(1,1);
    System.out.println(Arrays.deepToString(model.getBoard()));

    model.playTurn(2,2);
    System.out.println(Arrays.deepToString(model.getBoard()));
    System.out.println(model.getWinner());

    model.playTurn(1,0);
    model.playTurn(2,0);
    System.out.println(Arrays.deepToString(model.getBoard()));

    model.playTurn(1,2);
    System.out.println(Arrays.deepToString(model.getBoard()));
    //System.out.println(model.getWinner());
    //model.playTurn(0,0);
  }

}
