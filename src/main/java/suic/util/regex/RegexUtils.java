package suic.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {

    // commonly used regex
    public static final Pattern DIGITS = Pattern.compile("\\d+");
    public static final Pattern LETTERS = Pattern.compile("[a-zA-Z]+");
    public static final Pattern DIGITS_AND_LETTERS = Pattern.compile("[a-zA-Z\\d]+");
    public static final Pattern RANGE_SEPARATED_BY_COMMA = Pattern.compile("(\\d+),(\\d+)(?:,)?(\\d+)?");
    public static final Pattern RANGE_SEPARATED_BY_SPACE_COMMA = Pattern.compile("(\\d+), (\\d+)(?:, )?(\\d+)?");
    public static final Pattern RANGE_SEPARATED_BY_SPACE = Pattern.compile("(\\d+) (\\d+)(?: )?(\\d+)?");

    public static MatchWrapper parseMatch(String regex, String input) {
        return parseMatch(Pattern.compile(regex).matcher(input));
    }

    public static MatchWrapper parseMatch(Pattern pattern, String input) {
        return parseMatch(pattern.matcher(input));
    }

    public static MatchWrapper parseMatch(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalStateException("Matcher does not match the full input string");
        }
        return new MatchWrapper(matcher.toMatchResult());
    }

    private RegexUtils() {

    }
}
