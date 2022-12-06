package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class Main {

    private static final int PACKET_MARKER_LENGTH = 4;
    private static final int MESSAGE_MARKER_LENGTH = 14;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("./src/day06/input.txt"));
        String buffer = input.get(0);
        System.out.println(findMark(buffer));
        System.out.println(findMark2(buffer));
    }

    private static int findMark(String buffer) {
        for (int i = 0; i < buffer.length() - PACKET_MARKER_LENGTH; i++) {
            int markerEndPosition = i + PACKET_MARKER_LENGTH;
            String potentialMarker = buffer.substring(i, markerEndPosition);
            char[] signals = potentialMarker.toCharArray();
            Set<Character> charSet = new HashSet<>();
            for (char signal : signals) {
                charSet.add(signal);
            }
            if (charSet.size() == PACKET_MARKER_LENGTH) {
                return markerEndPosition;
            }
        }
        return -1;
    }

    private static int findMark2(String buffer) {
        for (int i = 0; i < buffer.length() - MESSAGE_MARKER_LENGTH; i++) {
            int markerEndPosition = i + MESSAGE_MARKER_LENGTH;
            String potentialMarker = buffer.substring(i, markerEndPosition);
            char[] signals = potentialMarker.toCharArray();
            Set<Character> charSet = new HashSet<>();
            for (char signal : signals) {
                charSet.add(signal);
            }
            if (charSet.size() == MESSAGE_MARKER_LENGTH) {
                return markerEndPosition;
            }
        }
        return -1;
    }
}
