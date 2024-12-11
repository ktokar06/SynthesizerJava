import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class Main {
    static final int[][] NOTES = new int[][]{{60, 1, 1}, {62, 1, 1}, {64, 1, 1}, {65, 1, 1}, {67, 2, 2}, {69, 1, 4}, {67, 4, 1}, {69, 1, 4}, {67, 4, 1}, {65, 1, 4}, {64, 2, 2}, {62, 1, 4}, {60, 4, 1}};
    private static final byte C = 60;
    private static final byte D = 62;
    private static final byte E = 64;
    private static final byte F = 65;
    private static final byte G = 67;
    private static final byte A = 69;
    private static final byte B = 70;

    public Main() {
    }

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        System.out.println("Please enter notes: ");
        new Scanner(System.in);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        Receiver receiver = synthesizer.getReceiver();
        ShortMessage msg = new ShortMessage();
        msg.setMessage(192, 19, -1);
        receiver.send(msg, -1L);

        for(int[] note : NOTES) {
            int count = note[2];
            int duration = note[1];

            for(int i = 0; i < count; ++i) {
                msg.setMessage(144, note[0], 100);
                receiver.send(msg, -1L);
                Thread.sleep(500L * (long)duration);
                msg.setMessage(128, note[0], 100);
                receiver.send(msg, -1L);
            }
        }

    }

    private static void playNotes(Receiver receiver, String[] notes) throws InvalidMidiDataException, InterruptedException {
        for(String note : notes) {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(144, convertToId(note), 100);
            receiver.send(msg, -1L);
            Thread.sleep(500L);
            msg.setMessage(128, convertToId(note), 100);
            receiver.send(msg, -1L);
        }

    }

    private static int convertToId(String note) {
        switch (note) {
            case "A":
                return 69;
            case "B":
                return 70;
            case "C":
                return 60;
            case "D":
                return 62;
            case "E":
                return 64;
            case "F":
                return 65;
            case "G":
                return 67;
            default:
                System.out.println("Entered incorrect note: " + note);
                return 69;
        }
    }
}
