package com.mlemulation.emulators.chip8.chip;

/**
 * This class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} state.
 */
class ChipState {

    final byte[] memory;
    final byte[] vReg;
    final long I;
    final long pc;
    final long opcode;
    final long[] stack;
    final long stackPtr;
    final long delayTimer;
    final long soundTimer;
    final byte[] keys;
    final byte[] display;
    final long displayWidth;
    final long displayHeight;
    final boolean romLoaded;

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
        this.romLoaded = chip.romLoaded;
    }
}
