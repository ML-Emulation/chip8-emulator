package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.opcodes.Opcodes;
import com.mlemulation.emulators.chip8.specs.Specs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This singleton class represents the current Chip's this.state.
 */
public class Chip {

    private static Chip instance = null;

    public ChipState state;

    private Chip() {
        this.state = new ChipState();
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
        this.state.reset();
        try {
            fileByteArray = Files.readAllBytes(romPath);
            if (fileByteArray.length <= Specs.MEMORY) {
                System.arraycopy(fileByteArray, 0, this.state.memory, 0, fileByteArray.length);
                this.state.isRomLoaded = true;
                this.state.isRunning = true;
            } else {
                System.out.println("ROM too big to fit in memory...");
            }
        } catch (IOException e) {
            System.out.println("Exception thrown while reading ROM...");
            e.printStackTrace();
        }
    }

    public ChipState getState() {
        return this.state.copy();
    }

    public void setState(ChipState targetState) {
        this.state = targetState.copy();
    }

    public void execute() {
        this.state.opcode = (this.state.memory[this.state.pc] << 8) | this.state.memory[this.state.pc+1];
        Opcodes opcode = Opcodes.FromInt(this.state.opcode);

        if (opcode != null) {
            System.out.print(String.format("Supported code found: %d (%s)", this.state.opcode, opcode.label));
        } else {
            System.out.println(String.format("Unsupported code: %s", Integer.toHexString(this.state.opcode)));
        }
    }
}
