package suic.days;

import one.util.streamex.StreamEx;
import suic.Puzzle;
import suic.util.io.FileUtils;

import java.util.*;

public class Day07 implements Puzzle<Integer> {

    private final Directory root = new Directory("/", null);

    @Override
    public void parse() {
        List<String> input = FileUtils.readAsStream(this.getClass().getSimpleName() + "Input.txt")
                .skip(1)
                .toList();
        Directory current = root;
        for (String line : input) {
            if (line.startsWith("$ cd ..")) {
                current = current.parent();
            } else if (line.startsWith("$ cd")) {
                String name = line.split(" ")[2];
                Directory dir = new Directory(name, current);
                current.children().add(dir);
                current = dir;
            } else if (line.matches("\\d+.*")) {
                String[] data = line.split(" ");
                int size = Integer.parseInt(data[0]);
                String name = data[1];
                File file = new File(name, size);
                current.files().add(file);
            }
        }
    }

    @Override
    public Integer solvePart1() {
        List<Directory> directories = root.flatten();
        return directories.stream()
                .mapToInt(Directory::computeSize)
                .filter(size -> size <= 100_000)
                .sum();
    }

    @Override
    public Integer solvePart2() {
        List<Directory> directories = root.flatten();
        int usedSpace = directories.stream()
                .map(Directory::files)
                .flatMap(List::stream)
                .mapToInt(File::size)
                .sum();
        int availableSpace = 700_000_00;
        int unusedSpace = availableSpace - usedSpace;
        int needToDelete = 300_000_00 - unusedSpace;
        return StreamEx.of(directories).sortedBy(Directory::computeSize)
                .findFirst(dir -> dir.computeSize() > needToDelete)
                .map(Directory::computeSize)
                .orElseThrow();
    }

    // name isn't really necessary as it isn't used but including it nevertheless
    private record Directory(String name, Directory parent, List<Directory> children,
                             List<File> files) {
        public int computeSize() {
            int childTotalSpace = children.stream()
                    .mapToInt(Directory::computeSize)
                    .sum();
            int totalSpace = files.stream().mapToInt(File::size).sum();
            return childTotalSpace + totalSpace;
        }

        public List<Directory> flatten() {
            return StreamEx.of(children)
                    .map(Directory::flatten)
                    .flatMap(List::stream)
                    .append(this)
                    .toList();
        }

        public Directory(String name, Directory parent) {
            this(name, parent, new ArrayList<>(), new ArrayList<>());
        }
    }

    private record File(String name, int size) {
    }

}
