package com.mlemulation.emulators.chip8.opcodes;

/**
 * Created by nunomota on 10/3/2017.
 */
public enum Opcodes {

    x00E0("0x00E0", 0x00E0, 0xFFFF, "Clear the screen"),
    x00EE("0x00EE", 0x00EE, 0xFFFF, "Return from a subroutine"),
    x0NNN("0x0NNN", 0x0000, 0xF000, "Calls RCA 1802 program at NNN"),
    x1NNN("0x1NNN", 0x1000, 0xF000, "Jump to address NNN"),
    x2NNN("0x2NNN", 0x2000, 0xF000, "Call subroutine at NNN"),
    x3XNN("0x3XNN", 0x3000, 0xF000, "Skip next instruction if VX equals NN"),
    x4XNN("0x4XNN", 0x4000, 0xF000, "Skip next instruction if VX doesn't equal NN"),
    x5XY0("0x5XY0", 0x5000, 0xF00F, "Skip next instruction if VX equals VY"),
    x6XNN("0x6XNN", 0x6000, 0xF000, "Sets VX to NN"),
    x7XNN("0x7XNN", 0x7000, 0xF000, "Adds NN to VX"),
    x8XY0("0x8XY0", 0x8000, 0xF00F, "Sets VX to the value of VY"),
    x8XY1("0x8XY1", 0x8001, 0xF00F, "Sets VX to the value of 'VX OR VY'"),
    x8XY2("0x8XY2", 0x8002, 0xF00F, "Sets VX to the value of 'VX AND VY'"),
    x8XY3("0x8XY3", 0x8003, 0xF00F, "Sets VX to the value of 'VX XOR VY'"),
    x8XY4("0x8XY4", 0x8004, 0xF00F, "Adds VX to VY and sets VF to 1 if there's a carry (0 otherwise)"),
    x8XY5("0x8XY5", 0x8005, 0xF00F, "Subtracts VY from VX and sets VF to 0 if there's a borrow (1 otherwise)"),
    x8XY6("0x8XY6", 0x8006, 0xF00F, "Right shifts VX by 1 and sets VF to the least significant bit of VX (before the shift)"),
    x8XY7("0x8XY7", 0x8007, 0xF00F, "Sets VX to 'VY - VX' and sets VF to 0 if there's a borrow (1 otherwise)"),
    x8XYE("0x8XYE", 0x800E, 0xF00F, "Left shifts VX by 1 and sets VF to the most significant bit of VX (before the shift)"),
    x9XY0("0x9XY0", 0x9000, 0xF00F, "Skips the next instruction if VX doesn't equal VY"),
    xANNN("0xANNN", 0xA000, 0xF000, "Sets I to NNN"),
    xBNNN("0xBNNN", 0xB000, 0xF000, "Jumps to the address NNN plus V0"),
    xCXNN("0xCXNN", 0xC000, 0xF000, "Sets VX to the result of 'random AND NN'"),
    xDXYN("0xDXYN", 0xD000, 0xF000, "TODO"),    // TODO fill in the description
    xEX9E("0xEX9E", 0xE09E, 0xF0FF, "Skips the next instruction if key stored in VX is pressed"),
    xEXA1("0xEXA1", 0xE0A1, 0xF0FF, "Skips the next instruction if key stored in VX isn't pressed"),
    xFX07("0xFX07", 0xF007, 0xF0FF, "Sets VX to the value of delay timer"),
    xFX0A("0xFX0A", 0xF00A, 0xF0FF, "A key press is awaited and then stored in VX"),
    xFX15("0xFX15", 0xF015, 0xF0FF, "Sets the delay timer to the value of VX"),
    xFX18("0xFX18", 0xF018, 0xF0FF, "Sets the sound timer to the value of VX"),
    xFX1E("0xFX1E", 0xF01E, 0xF0FF, "Adds VX to I"),
    xFX29("0xFX29", 0xF029, 0xF0FF, "TODO"),    // TODO fill in the description
    xFX33("0xFX33", 0xF033, 0xF0FF, "TODO"),    // TODO fill in the description
    xFX55("0xFX55", 0xF055, 0xF0FF, "Writes [V0, VX] to memory, starting at address I"),
    xFX65("0xFX65", 0xF065, 0xF0FF, "Fills [V0, VX] with values from memory, starting at address I");

    public final String label;
    public final int value;
    public final int bitFilter;
    public final String description;

    Opcodes(String label, int value, int bitFilter, String description) {
        this.label = label;
        this.value = value;
        this.bitFilter = bitFilter;
        this.description = description;
    }

    // TODO fill in this method
    public static Opcodes FromInt(int opcode) {
        return null;
    }
}
