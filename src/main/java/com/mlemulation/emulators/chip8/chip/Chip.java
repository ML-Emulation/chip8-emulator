package com.mlemulation.emulators.chip8.chip;

import com.mlemulation.emulators.chip8.opcodes.Opcodes;
import com.mlemulation.emulators.chip8.specs.Specs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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

        int temp;
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
                temp = (int)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8]) + (int)(this.state.vReg[(this.state.opcode & 0x00F0) >> 4]);
                if (temp < 256) {
                    this.state.vReg[0x000F] = 0x0000;
                } else {
                    this.state.vReg[0x000F] = 0x0001;
                }
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(temp & 0x000F);
                this.state.pc += 2;
                break;
            case x8XY5:
                temp = (int)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8]) - (int)(this.state.vReg[(this.state.opcode & 0x00F0) >> 4]);
                if (temp < 0) {
                    this.state.vReg[0x000F] = 0x0000;
                } else {
                    this.state.vReg[0x000F] = 0x0001;
                }
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(temp & 0x000F);
                this.state.pc += 2;
                break;
            case x8XY6: // TODO fix description of Opcode.x8XY6
                this.state.vReg[0x000F] = (byte)(this.state.vReg[(this.state.opcode & 0x00F0) >> 4] & 0x0007);
                this.state.vReg[(this.state.opcode & 0x00F0) >> 4] = (byte)(this.state.vReg[(this.state.opcode & 0x00F0) >> 4] >> 1);
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = this.state.vReg[(this.state.opcode & 0x00F0) >> 4];
                break;
            case x8XY7:
                temp = (int)(this.state.vReg[(this.state.opcode & 0x00F0) >> 4]) - (int)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8]);
                if (temp < 0) {
                    this.state.vReg[0x000F] = 0x0000;
                } else {
                    this.state.vReg[0x000F] = 0x0001;
                }
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(temp & 0x000F);
                this.state.pc += 2;
                break;
            case x8XYE:
                this.state.vReg[0x000F] = (byte)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8] >> 7);
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(this.state.vReg[(this.state.opcode & 0x0F00) >> 8] << 1);
                this.state.pc += 2;
                break;
            case x9XY0:
                if (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] != this.state.vReg[(this.state.opcode & 0x00F0) >> 4]) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case xANNN:
                this.state.I = this.state.opcode & 0x0FFF;
                this.state.pc += 2;
                break;
            case xBNNN:
                this.state.pc = (this.state.opcode & 0x0FFF) + this.state.vReg[0];
                break;
            case xCXNN:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte)(ThreadLocalRandom.current().nextInt(0x00, 0xFF) & (this.state.opcode & 0x00FF));
                this.state.pc += 2;
                break;
            case xDXYN:
                byte vx = this.state.vReg[(this.state.opcode & 0x0F00) >> 8];
                byte vy = this.state.vReg[(this.state.opcode & 0x00F0) >> 4];
                int width = 8;
                int height = this.state.opcode & 0x000F;

                this.state.vReg[0x000F] = 0x0000;
                for (int y = 0; y < height; y++) {
                    byte pixelValue = this.state.memory[this.state.I + y];
                    for (int x = 0; x < width; x++) {
                        if ((pixelValue & (0x0080 >> x)) != 0) {
                            if (this.state.display[x + vx + (y + vy) * 64] != 0) {
                                this.state.vReg[0x000F] = 1;
                            }
                            this.state.display[x + vx + (y + vy) * 64] ^= 1;
                        }
                    }
                }
                this.state.pc += 2;
                break;
            case xEX9E:
                if (this.state.keys[(this.state.opcode & 0x0F00) >> 8]) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case xEXA1:
                if (!this.state.keys[(this.state.opcode & 0x00F0) >> 4]) {
                    this.state.pc += 4;
                } else {
                    this.state.pc += 2;
                }
                break;
            case xFX0A:
                for (int i = 0; i < this.state.keys.length; i++) {
                    if (this.state.keys[i]) {
                        this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte) i;
                        this.state.pc += 2;
                        break;
                    }
                }
                break;
            case xFX1E:
                this.state.I += this.state.vReg[(this.state.opcode & 0x0F00) >> 8];
                this.state.pc += 2;
                break;
            case xFX07:
                this.state.vReg[(this.state.opcode & 0x0F00) >> 8] = (byte) this.state.delayTimer;
                this.state.pc += 2;
                break;
            case xFX15:
                this.state.delayTimer = this.state.vReg[(this.state.opcode & 0x0F00) >> 8];
                this.state.pc += 2;
                break;
            case xFX18:
                this.state.soundTimer = this.state.vReg[(this.state.opcode & 0x0F00) >> 8];
                this.state.pc += 2;
                break;
            case xFX29:
                this.state.I = this.state.vReg[(this.state.opcode & 0x0F00) >> 8] * 5;
                this.state.pc += 2;
                break;
            case xFX33:
                this.state.memory[this.state.I] = (byte) (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] / 100);
                this.state.memory[this.state.I + 1] = (byte) ((this.state.vReg[(this.state.opcode & 0x0F00) >> 8] / 10) % 10);
                this.state.memory[this.state.I + 2] = (byte) (this.state.vReg[(this.state.opcode & 0x0F00) >> 8] % 10);
                this.state.pc += 2;
                break;
            case xFX55:
                for (int i = 0; i <= (this.state.opcode & 0x0F00) >> 8; i++) {
                    this.state.memory[this.state.I + i] = this.state.vReg[i];
                }
                this.state.pc += 2;
                break;
            case xFX65:
                for (int i = 0; i <= (this.state.opcode & 0x0F00) >> 8; i++) {
                    this.state.vReg[i] = this.state.memory[this.state.I + i];
                }
                break;
            default:
                System.out.println(String.format("Unsupported code: 0x%04X\n", this.state.opcode));
        }
    }
}
