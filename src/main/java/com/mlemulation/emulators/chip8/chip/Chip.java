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
    public boolean rom_loaded;

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
        this.rom_loaded = false;
    }

    public static Chip getInstance() {
        if (instance == null) {
            instance = new Chip();
        }
        return instance;
    }

    public ChipState getState() {
        return new ChipState(this);
    }

    public void setState(ChipState targetState) {
        this.memory = targetState.memory.clone();
        this.v_reg = targetState.v_reg.clone();
        this.I = targetState.I;
        this.pc = targetState.pc;
        this.opcode = targetState.opcode;
        this.stack = targetState.stack.clone();
        this.stack_ptr = targetState.stack_ptr;
        this.delay_timer = targetState.delay_timer;
        this.sound_timer = targetState.sound_timer;
        this.keys = targetState.keys.clone();
        this.display = targetState.display.clone();
        this.display_width = targetState.display_width;
        this.display_height = targetState.display_height;
        this.rom_loaded = targetState.rom_loaded;
    }
}
