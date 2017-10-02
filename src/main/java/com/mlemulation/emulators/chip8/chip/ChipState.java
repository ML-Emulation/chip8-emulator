package com.mlemulation.emulators.chip8.chip;

public class ChipState {

    private final byte[] memory;
    private final byte[] v_reg;
    private final long I;
    private final long pc;
    private final long opcode;
    private final long[] stack;
    private final long stack_ptr;
    private final long delay_timer;
    private final long sound_timer;
    private final byte[] keys;
    private final byte[] display;
    private final long display_width;
    private final long display_height;

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
