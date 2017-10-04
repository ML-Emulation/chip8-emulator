package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.opcodes.Opcodes;
import com.mlemulation.emulators.chip8.specs.Specs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
        Opcodes opcodeRepresentation = Opcodes.FromInt(this.state.opcode);

        if (opcodeRepresentation == null) {
            System.out.println(String.format("Unsupported code: 0x%04X\n", this.state.opcode));
            return;
        }

        switch(opcodeRepresentation) {
            case x0NNN:
                // TODO implement this behaviour
                this.state.pc += 2;
                break;
            case x00E0:
                Arrays.fill(this.state.display, (byte)0x0000);
                this.state.pc += 2;
                break;
            case x00EE:
                this.state.pc = this.state.stack[(--this.state.stackPtr) & 0x000F] + 2;
                break;
            case x1NNN:
                this.state.pc = this.state.opcode & 0x0FFF;
                break;
            case x2NNN:
                this.state.stack[(this.state.stackPtr++) & 0x000F] = this.state.pc;
                this.state.pc = this.state.opcode & 0x0FFF;
                break;
            case x3XNN:
                if (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] == (this.state.opcode & 0x00FF)) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case x4XNN:
                if (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] != (this.state.opcode & 0x00FF)) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case x5XY0:
                if (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] == this.state.vReg[(this.state.opcode & 0x00F0) >> 4]) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case x6XNN:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(this.state.opcode & 0x00FF);
                this.state.pc += 2;
                break;
            case x7XNN:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] += (this.state.opcode & 0x00FF);
                this.state.pc += 2;
                break;
            case x8XY0:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = this.state.vReg[(this.state.opcode) >> 4];
                this.state.pc += 2;
                break;
            case x8XY1:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8] | this.state.vReg[(this.state.opcode & 0x00F0) >> 4]);
                this.state.pc += 2;
                break;
            case x8XY2:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8] & this.state.vReg[(this.state.opcode & 0x00F0) >> 4]);
                this.state.pc += 2;
                break;
            case x8XY3:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8] ^ this.state.vReg[(this.state.opcode & 0x00F0) >> 4]);
                this.state.pc += 2;
                break;
            case x8XY4:
                break;
            case x8XY5:
                break;
            case x8XY6:
                break;
            case x8XY7:
                break;
            case x8XYE:
                break;
            case x9XY0:
                break;
            case xANNN:
                break;
            case xBNNN:
                break;
            case xCXNN:
                break;
            case xDXYN:
                break;
            case xEX9E:
                break;
            case xEXA1:
                break;
            case xFX0A:
                break;
            case xFX1E:
                break;
            case xFX07:
                break;
            case xFX15:
                break;
            case xFX18:
                break;
            case xFX29:
                break;
            case xFX33:
                break;
            case xFX55:
                break;
            case xFX65:
                break;
            default:
                System.out.println(String.format("Unsupported code: 0x%04X\n", this.state.opcode));
        }
    }
}
