package com.mlemulation.emulators.chip8.chip;

/**
 * This class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} state.
 */
class ChipState {

    final byte[] memory;
    final byte[] vReg;
    final int I;
    final int pc;
    final int opcode;
    final int[] stack;
    final int stackPtr;
    final int delayTimer;
    final int soundTimer;
    final byte[] keys;
    final byte[] display;
    final int displayWidth;
    final int displayHeight;
    final boolean isRunning;
    final boolean isRomLoaded;

    ChipState(Chip chip) {
        this.memory = chip.memory.clone();
        this.vReg = chip.vReg.clone();
        this.I = chip.I;
        this.pc = chip.pc;
        this.opcode = chip.opcode;
        this.stack = chip.stack.clone();
        this.stackPtr = chip.stackPtr;
        this.delayTimer = chip.delayTimer;
        this.soundTimer = chip.soundTimer;
        this.keys = chip.keys.clone();
        this.display = chip.display.clone();
        this.displayWidth = chip.displayWidth;
        this.displayHeight = chip.displayHeight;
        this.isRunning = chip.isRunning;
        this.isRomLoaded = chip.isRomLoaded;
    }
}
