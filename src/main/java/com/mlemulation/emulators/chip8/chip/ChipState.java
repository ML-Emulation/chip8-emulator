package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.specs.Specs;

/**
 * This class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} state.
 */
class ChipState {

    byte[] memory;
    byte[] vReg;
    int I;
    int pc;
    int opcode;
    int[] stack;
    int stackPtr;
    int delayTimer;
    int soundTimer;
    boolean[] keys;
    byte[] display;
    int displayWidth;
    int displayHeight;
    boolean isRunning;
    boolean isRomLoaded;

    ChipState() {
        this.reset();
    }

    void reset() {
        this.memory = new byte[Specs.MEMORY];
        this.vReg = new byte[Specs.V_REGISTERS];
        this.I = 0;
        this.pc = 0;
        this.opcode = 0;
        this.stack = new int[Specs.STACK_SIZE];
        this.stackPtr = 0;
        this.delayTimer = 0;
        this.soundTimer = 0;
        this.keys = new boolean[Specs.KEYS];
        this.display = new byte[Specs.DISPLAY_SIZE];
        this.displayWidth = Specs.DISPLAY_WIDTH;
        this.displayHeight = Specs.DISPLAY_HEIGHT;
        this.isRunning = false;
        this.isRomLoaded = false;
    }

    ChipState copy() {
        ChipState targetState = new ChipState();

        targetState.memory = this.memory.clone();
        targetState.vReg = this.vReg.clone();
        targetState.I = this.I;
        targetState.pc = this.pc;
        targetState.opcode = this.opcode;
        targetState.stack = this.stack.clone();
        targetState.stackPtr = this.stackPtr;
        targetState.delayTimer = this.delayTimer;
        targetState.soundTimer = this.soundTimer;
        targetState.keys = this.keys.clone();
        targetState.display = this.display.clone();
        targetState.displayWidth = this.displayWidth;
        targetState.displayHeight = this.displayHeight;
        targetState.isRunning = this.isRunning;
        targetState.isRomLoaded = this.isRomLoaded;

        return targetState;
    }
}
