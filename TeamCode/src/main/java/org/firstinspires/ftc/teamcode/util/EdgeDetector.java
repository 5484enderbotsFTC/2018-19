package org.firstinspires.ftc.teamcode.util;

/**
 * Created by guinea on 11/12/17.
 */

public class EdgeDetector {
    boolean prevState = false;
    boolean high = false;
    boolean low = false;
    public void update(boolean state) {
        low = prevState && !state;
        high = !prevState && state;
        prevState = state;
    }
    public boolean isHigh() {
        return high;
    }
    public boolean isLow() {
        return low;
    }
}
