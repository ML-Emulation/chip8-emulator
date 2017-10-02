package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.specs.Specs;

/**
 * This singleton class represents the current Chip's state.
 */
public class Chip {

    private static Chip instance = null;

    public byte[] memory;
    public byte[] v_reg;
    public long I;
    public long pc;
    public long opcode;
    public long[] stack;
    public long stack_ptr;
    public long delay_timer;
    public long sound_timer;
    public byte[] keys;
    public byte[] display;
    public long display_width;
    public long display_height;

    private Chip() {
        this.memory = new byte[Specs.MEMORY];
        this.v_reg = new byte[Specs.V_REGISTERS];
        this.I = 0L;
        this.pc = 0L;
        this.opcode = 0L;
        this.stack = new long[Specs.STACK_SIZE];
        this.stack_ptr = 0L;
        this.delay_timer = 0L;
        this.sound_timer = 0L;
        this.keys = new byte[Specs.KEYS];
        this.display = new byte[Specs.DISPLAY_SIZE];
        this.display_width = Specs.DISPLAY_WIDTH;
        this.display_height = Specs.DISPLAY_HEIGHT;
    }

    public static Chip getInstance() {
        if (instance == null) {
            instance = new Chip();
        }
        return instance;
    }
}
