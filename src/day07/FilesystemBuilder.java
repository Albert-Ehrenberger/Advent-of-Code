package day07;

import java.util.List;

public class FilesystemBuilder {

    private List<String> consoleLines;
    private int consolePointer;
    private Directory currentDir;
    private boolean endOfConsole = false;
    private int endOfConsolePosition;

    public Directory build(List<String> consoleLines) {
        this.consoleLines = consoleLines;
        this.endOfConsolePosition = consoleLines.size();
        consolePointer = 1;
        Directory root = new Directory("/", null);
        currentDir = root;
        while (!endOfConsole) {
            parseCommand();
        }
        return root;
    }

    private void parseCommand() {
        String line = consoleLines.get(consolePointer);
        if (line.equals("$ ls")) {
            consolePointer++;
            fillDirectory();
        } else {
            changeDir();
            consolePointer++;
        }
        if (consolePointer == endOfConsolePosition) {
            endOfConsole = true;
        }
    }

    private void fillDirectory() {
        boolean endOfDir = false;
        while (!endOfDir) {
            String line = consoleLines.get(consolePointer);
            if (line.charAt(0) == '$') {
                endOfDir = true;
            } else if (line.charAt(0) == 'd') {
                String[] lineParts = line.split(" ");
                currentDir.children.put(lineParts[1], new Directory(lineParts[1], currentDir));
                consolePointer++;
            } else {
                String[] lineParts = line.split(" ");
                currentDir.files.put(lineParts[1], Integer.parseInt(lineParts[0]));
                consolePointer++;
            }
            if (consolePointer == endOfConsolePosition) {
                endOfDir = true;
                endOfConsole = true;
            }
        }
    }

    private void changeDir() {
        String line = consoleLines.get(consolePointer);
        String[] lineParts = line.split(" ");
        String goToDir = lineParts[2];
        if (goToDir.equals("..")) {
            currentDir = currentDir.parent;
        } else {
            currentDir = currentDir.children.get(goToDir);
        }
    }
}
