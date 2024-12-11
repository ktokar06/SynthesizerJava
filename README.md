<h1 align="center">Hi there, I'm<a ></a> Kirill</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center"> Student, Java Developer 🇷🇺 </h3>

# 📜 MIDI Player

This project is a simple Java-based MIDI player that plays predefined musical notes. It demonstrates the use of the javax.sound.midi package to create and send MIDI messages.

## 📦 Installation

1. Clone repositories:
   ```bash
   git clone https://github.com/ktokar06/SynthesizerJava.git
   ```

2. Go to the project directory:
   ```bash
   cd SynthesizerJava
   ```
## Features

- Plays a sequence of musical notes defined in the NOTES array.

- Allows conversion of note names (e.g., "A", "B", "C") to MIDI note numbers.

- Utilizes MIDI synthesizer and receiver to produce sound.

## Requirements

- Java Development Kit (JDK) 8 or later.

- A MIDI-compatible sound system.

## How It Works

1. The program initializes a MIDI synthesizer and receiver.

2. Notes are defined in the NOTES array, where each entry contains:

    - MIDI note number.
    - 
    - Duration of the note.
      
    - Number of times the note should be played.

3. The program sends MIDI messages to play the sequence of notes.

## Code Overview

``NOTES`` Array

```java
static final int[][] NOTES = new int[][]{
    {60, 1, 1}, {62, 1, 1}, {64, 1, 1}, {65, 1, 1}, {67, 2, 2},
    {69, 1, 4}, {67, 4, 1}, {69, 1, 4}, {67, 4, 1}, {65, 1, 4},
    {64, 2, 2}, {62, 1, 4}, {60, 4, 1}
};
```

``Each sub-array contains:``

- MIDI note number (e.g., 60 for C).

- Duration multiplier (e.g., 1 for half a second).

 - Repetition count.

## Conversion of Note Names

The convertToId method converts note names (e.g., "C", "D") to MIDI note numbers.

```java
private static int convertToId(String note) {
    switch (note) {
        case "A": return 69;
        case "B": return 70;
        case "C": return 60;
        case "D": return 62;
        case "E": return 64;
        case "F": return 65;
        case "G": return 67;
        default:
            System.out.println("Entered incorrect note: " + note);
            return 69;
    }
}
```

## Playing Notes

The playNotes method sends MIDI messages to play notes provided by the user.

```java
private static void playNotes(Receiver receiver, String[] notes)
        throws InvalidMidiDataException, InterruptedException {
    for (String note : notes) {
        ShortMessage msg = new ShortMessage();
        msg.setMessage(144, convertToId(note), 100);
        receiver.send(msg, -1L);
        Thread.sleep(500L);
        msg.setMessage(128, convertToId(note), 100);
        receiver.send(msg, -1L);
    }
}
``` 

## Notes

- The program defaults to the note A if an invalid note is entered.

- Predefined notes in the NOTES array are played automatically.

## Example Output

``
Please enter notes:
C D E F G
``

