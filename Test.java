import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.MouseInfo;
import java.util.concurrent.TimeUnit;
import java.awt.Point;

public class Test {
    public static final int FIVE_SECONDS = 15000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();
        int flag = 0;
        while (true) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            int mouseX = p.x;
            if (mouseX > 1 && flag == 0) {
                mouseX -= 1;
                flag = 1;
            } else {
                mouseX += 1;
                flag = 0;
            }
            // robot.mouseMove(mouseX, mouseX);

            // robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            // robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            robot.keyPress(0x11);
            robot.keyRelease(0x11);

            Thread.sleep(FIVE_SECONDS);
        }
    }
}