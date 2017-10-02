package com.mlemulation.emulators.chip8.specs;

/**
 * This class is used solely as a constant "container" for
 * all of the specifications of a CHIP-8 system.
 */
public final class Specs {

    public static final int MEMORY = 4096;
    public static final int V_REGISTERS = 16;
    public static final int STACK_SIZE = 16;
    public static final int KEYS = 16;
    public static final int DISPLAY_WIDTH = 64;
    public static final int DISPLAY_HEIGHT = 32;
    public static final int DISPLAY_SIZE = DISPLAY_WIDTH * DISPLAY_HEIGHT;
}
