import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stpanda
 * Date: 11/26/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class EZQiangU {

    public static void main(String[] args) {

        OrchSeater seater = new OrchSeater();
        try {
            seater.auth();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);

        }


    }

}
