package com.mlemulation.emulators.chip8.display;

import com.mlemulation.emulators.chip8.specs.Specs;

import javax.swing.*;

/**
 * This Singleton class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} display.
 */
public class Display {

    private static Display instance = null;
    private static final String DEFAULT_WINDOW_NAME = "[Chip8] ML Emulator";

    private JFrame frame;

    private Display(String windowTitle, int width, int height) {
        this.frame = new JFrame(windowTitle);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(width, height);
        this.frame.setVisible(true);
    }

    public static Display getInstance() {
        if (instance == null) {
            instance = new Display(DEFAULT_WINDOW_NAME, Specs.DISPLAY_WIDTH, Specs.DISPLAY_HEIGHT);
        }
        return instance;
    }
}
