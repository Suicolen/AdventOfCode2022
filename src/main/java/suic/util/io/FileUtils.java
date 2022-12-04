package suic.util.io;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import one.util.streamex.StreamEx;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@UtilityClass
public final class FileUtils {

    @SneakyThrows(IOException.class)
    public List<String> read(String path) {
        return Files.readAllLines(getPath(path));
    }

    // a list of strings delimited by an empty line
    public List<List<String>> readGroups(String path) {
        return readAsStreamEx(path).foldLeft(new ArrayList<>(), (list, element) -> {
            if (element.trim().isEmpty() || list.isEmpty()) {
                list.add(new ArrayList<>());
            } else {
                list.get(list.size() - 1).add(element);
            }
            return list;
        });
    }

    @SneakyThrows(IOException.class)
    public Stream<String> readAsStream(String path) {
        return Files.lines(getPath(path));
    }

    @SneakyThrows(IOException.class)
    public StreamEx<String> readAsStreamEx(String path) {
        return StreamEx.ofLines(getPath(path));
    }

    @SneakyThrows(IOException.class)
    public String readString(String path) {
        return Files.readString(getPath(path));
    }

    // int in each line
    public List<Integer> readInts(String path) {
        return readAsStream(path).map(Integer::parseInt).toList();
    }

    // long in each line
    public List<Long> readLongs(String path) {
        return readAsStream(path).map(Long::parseLong).toList();
    }

    // ints in one line separated by separator
    public List<Integer> readInts(String path, String separator) {
        return Arrays.stream(readString(path).split(separator)).map(Integer::parseInt).toList();
    }

    public List<Long> readLongs(String path, String separator) {
        return Arrays.stream(readString(path).split(separator)).map(Long::parseLong).toList();
    }

    private static Path getPath(String path) {
        return Path.of("./inputs/").resolve(path);
    }

}
