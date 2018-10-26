package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by guinea on 9/26/17.
 */

public class ButtonWatcher {

    public ButtonWatch dpad_up = new ButtonWatch();
    public ButtonWatch dpad_down = new ButtonWatch();
    public ButtonWatch dpad_left = new ButtonWatch();
    public ButtonWatch dpad_right = new ButtonWatch();
    public ButtonWatch a = new ButtonWatch();
    public ButtonWatch b = new ButtonWatch();
    public ButtonWatch x = new ButtonWatch();
    public ButtonWatch y = new ButtonWatch();
    public ButtonWatch guide = new ButtonWatch();
    public ButtonWatch start = new ButtonWatch();
    public ButtonWatch back = new ButtonWatch();
    public ButtonWatch left_bumper = new ButtonWatch();
    public ButtonWatch right_bumper = new ButtonWatch();
    public ButtonWatch left_stick_button = new ButtonWatch();
    public ButtonWatch right_stick_button = new ButtonWatch();

    public class ButtonWatch {
        private boolean pressed = false;
        private boolean released = false;
        public void update(boolean value) {
            released = pressed && !value;
            pressed = value;
        }
        public boolean pressed() { return pressed; }
        public boolean released() { return released; }
    }

    public void update(Gamepad gamepad) {
        dpad_up.update(gamepad.dpad_up);
        dpad_down.update(gamepad.dpad_down);
        dpad_left.update(gamepad.dpad_left);
        dpad_right.update(gamepad.dpad_right);
        a.update(gamepad.a);
        b.update(gamepad.b);
        x.update(gamepad.x);
        y.update(gamepad.y);
        guide.update(gamepad.guide);
        start.update(gamepad.start);
        back.update(gamepad.back);
        left_bumper.update(gamepad.left_bumper);
        right_bumper.update(gamepad.right_bumper);
        left_stick_button.update(gamepad.left_stick_button);
        right_stick_button.update(gamepad.right_stick_button);
    }
}
