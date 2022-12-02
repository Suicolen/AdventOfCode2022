package suic.util.text;

import lombok.experimental.UtilityClass;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;

import java.util.BitSet;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class StringUtils {
    public String[] splitAtComma(String s) {
        return s.split(",");
    }

    public String[] splitAtSpace(String s) {
        return s.split(" ");
    }

    public Stream<Character> toCharStream(String str) {
        return str.chars().mapToObj(c -> (char) c);
    }

    public StreamEx<Character> toCharStreamEx(String str) {
        return IntStreamEx.ofChars(str).mapToObj(c -> (char) c);
    }

    public String[] splitAtEquals(String s) {
        return s.split("=");
    }

    public List<Character> toCharList(String str) {
        return IntStreamEx.ofChars(str).mapToObj(c -> (char) c).toList();
    }
}
