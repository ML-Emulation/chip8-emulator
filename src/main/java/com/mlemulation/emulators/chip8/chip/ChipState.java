package com.mlemulation.emulators.chip8.chip;

/**
 * This class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} state.
 */
class ChipState {

    final byte[] memory;
    final byte[] v_reg;
    final long I;
    final long pc;
    final long opcode;
    final long[] stack;
    final long stack_ptr;
    final long delay_timer;
    final long sound_timer;
    final byte[] keys;
    final byte[] display;
    final long display_width;
    final long display_height;

    ChipState(Chip chip) {
        this.memory = chip.memory.clone();
        this.v_reg = chip.v_reg.clone();
        this.I = chip.I;
        this.pc = chip.pc;
        this.opcode = chip.opcode;
        this.stack = chip.stack.clone();
        this.stack_ptr = chip.stack_ptr;
        this.delay_timer = chip.delay_timer;
        this.sound_timer = chip.sound_timer;
        this.keys = chip.keys.clone();
        this.display = chip.display.clone();
        this.display_width = chip.display_width;
        this.display_height = chip.display_height;
    }
}
