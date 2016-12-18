/**
 * Created by Саоша on 09.12.2016.
 */
public class Message {

    public String message;
    public String nickname;

    @Override
    public String toString() {
        return nickname + ": " + message;
    }
}
