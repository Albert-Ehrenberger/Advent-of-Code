package day07;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;

public class FilesystemOverviewBuilder {

    private final Multimap<String, Integer> overview = ArrayListMultimap.create();

    public Multimap<String, Integer> build(Directory fileSystem) {
        probe(fileSystem);
        return overview;
    }

    private int probe(Directory currentDir) {
        int dirSize = 0;
        Collection<Integer> sizes = currentDir.files.values();
        for (int size : sizes) {
            dirSize += size;
        }
        if (!currentDir.children.isEmpty()) {
            for (Directory child : currentDir.children.values()) {
                dirSize += probe(child);
            }
        }
        overview.put(currentDir.name, dirSize);
        return dirSize;
    }
}
