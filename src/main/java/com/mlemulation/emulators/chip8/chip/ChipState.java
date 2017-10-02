package com.mlemulation.emulators.chip8.chip;

/**
 * This class represents a {@link com.mlemulation.emulators.chip8.chip.Chip} state.
 */
public class ChipState {

    public final byte[] memory;
    public final byte[] v_reg;
    public final long I;
    public final long pc;
    public final long opcode;
    public final long[] stack;
    public final long stack_ptr;
    public final long delay_timer;
    public final long sound_timer;
    public final byte[] keys;
    public final byte[] display;
    public final long display_width;
    public final long display_height;

    public ChipState(Chip chip) {
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
