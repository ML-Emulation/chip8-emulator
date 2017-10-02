package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.specs.Specs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public boolean isRunning;
    public boolean isRomLoaded;

    private Chip() {}

    private void reset() {
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
        this.isRunning = false;
        this.isRomLoaded = false;
    }

    public static Chip getInstance() {
        if (instance == null) {
            instance = new Chip();
        }
        return instance;
    }

    public void loadRom(String romPathString) {
        Path romPath = Paths.get(romPathString);
        byte[] fileByteArray;
        this.reset();
        try {
            fileByteArray = Files.readAllBytes(romPath);
            if (fileByteArray.length <= this.memory.length) {
                System.arraycopy(fileByteArray, 0, this.memory, 0, fileByteArray.length);
                this.isRomLoaded = true;
                this.isRunning = true;
            } else {
                System.out.println("ROM too big to fit in memory...");
            }
        } catch (IOException e) {
            System.out.println("Exception thrown while reading ROM...");
            e.printStackTrace();
        }
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
        this.isRunning = targetState.isRunning;
        this.isRomLoaded = targetState.isRomLoaded;
    }
}
