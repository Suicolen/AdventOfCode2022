package suic;

import suic.util.io.InputDownloader;

public class AdventCalendar {

    private static final String BASE_PACKAGE = AdventCalendar.class.getPackageName() + ".";


    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.println("-----------------------------------------------------------");
        run(12);
    }

    private static void run(int dayOfMonth) {
        try {
            //InputDownloader inputDownloader = new InputDownloader();
            //inputDownloader.download(dayOfMonth);
            Class<?> clazz = Class.forName(BASE_PACKAGE + pad(dayOfMonth));
            System.out.println("Day " + dayOfMonth + " Solution");
            Puzzle<?> puzzle = (Puzzle<?>) clazz.getDeclaredConstructor().newInstance();
            puzzle.init();
            System.out.println("Part 1 result = " + puzzle.solvePart1());
            System.out.println("Part 2 result = " + puzzle.solvePart2());
            System.out.println("-----------------------------------------------------------");

        } catch (ClassNotFoundException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("-----------------------------------------------------------");
    }

    private static void runAll() {
        for (int i = 1; i <= 25; i++) {
            run(i);
        }
    }

    private static void runRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            run(i);
        }
    }

    public static String pad(int i) {
        return String.format("days.Day%02d", i);
    }
}