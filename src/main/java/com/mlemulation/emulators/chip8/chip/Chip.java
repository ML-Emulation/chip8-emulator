package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.specs.Specs;

/**
 * This singleton class represents the current Chip's state.
 */
public class Chip {

    private static Chip instance = null;

    public byte[] memory;
    public byte[] vReg;
    public long I;
    public long pc;
    public long opcode;
    public long[] stack;
    public long stackPtr;
    public long delayTimer;
    public long soundTimer;
    public byte[] keys;
    public byte[] display;
    public long displayWidth;
    public long displayHeight;
    public boolean romLoaded;

    private Chip() {
        this.memory = new byte[Specs.MEMORY];
        this.vReg = new byte[Specs.V_REGISTERS];
        this.I = 0L;
        this.pc = 0L;
        this.opcode = 0L;
        this.stack = new long[Specs.STACK_SIZE];
        this.stackPtr = 0L;
        this.delayTimer = 0L;
        this.soundTimer = 0L;
        this.keys = new byte[Specs.KEYS];
        this.display = new byte[Specs.DISPLAY_SIZE];
        this.displayWidth = Specs.DISPLAY_WIDTH;
        this.displayHeight = Specs.DISPLAY_HEIGHT;
        this.romLoaded = false;
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
        this.vReg = targetState.vReg.clone();
        this.I = targetState.I;
        this.pc = targetState.pc;
        this.opcode = targetState.opcode;
        this.stack = targetState.stack.clone();
        this.stackPtr = targetState.stackPtr;
        this.delayTimer = targetState.delayTimer;
        this.soundTimer = targetState.soundTimer;
        this.keys = targetState.keys.clone();
        this.display = targetState.display.clone();
        this.displayWidth = targetState.displayWidth;
        this.displayHeight = targetState.displayHeight;
        this.romLoaded = targetState.romLoaded;
    }
}
