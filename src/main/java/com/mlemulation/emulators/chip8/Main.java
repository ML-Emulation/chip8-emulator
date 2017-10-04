package com.mlemulation.emulators.chip8;

import com.mlemulation.emulators.chip8.chip.Chip;

/**
 * This is a really simple implementation of a Chip8 emulator.
 *
 * I chose to make it an 'interpreting' and not a 'recompiling' emulator for the sake of simplicity (as a static recompiling
 * one has its downfall for self-modifying code, a dynamic one should be chosen instead - which would mean a lot of work)
 * but I might change this in the future.
 *
 * Resources:
 *    http://devernay.free.fr/hacks/chip8/C8TECH10.HTM
 *    https://en.wikipedia.org/wiki/CHIP-8#Opcode_table
 *    http://fms.komkon.org/EMUL8/HOWTO.html
*/
public class Main {
    public static void main(String[] args) {
        Chip chip = Chip.getInstance();

        chip.execute();
    }
}
