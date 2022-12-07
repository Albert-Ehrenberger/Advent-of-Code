package day07;

import java.util.Collection;
import java.util.HashMap;

public class FilesystemOverviewBuilder {

    private final HashMap<String, Integer> overview = new HashMap<>();
    public HashMap<String, Integer> build(Directory fileSystem) {
        probe(fileSystem);
        return overview;
    }

    private int probe(Directory currentDir) {
        int dirSize = 0;
        Collection<Integer> sizes = currentDir.files.values();
        for (int size: sizes) {
            dirSize += size;
        }
        if (currentDir.children.isEmpty()) {
            overview.put(currentDir.name, dirSize);
            return dirSize;
        }
        for (Directory child: currentDir.children.values()) {
            dirSize += probe(child);
        }
        overview.put(currentDir.name, dirSize);
        return dirSize;
    }
}
