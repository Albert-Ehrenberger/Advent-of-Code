package day04;

public final class SectionPair {

    private SectionPair() {

    }

    static boolean isOverlapping(String sectionPair) {
        String[] sections = sectionPair.split(",");
        String firstSection = sections[0];
        String secondSection = sections[1];
        String[] firstRange = firstSection.split("-");
        String[] secondRange = secondSection.split("-");
        int firstLower = Integer.parseInt(firstRange[0]);
        int firstHigher = Integer.parseInt(firstRange[1]);
        int secondLower = Integer.parseInt(secondRange[0]);
        int secondHigher = Integer.parseInt(secondRange[1]);
        return firstLower <= secondLower && firstHigher >= secondHigher ||
                firstLower >= secondLower && firstHigher <= secondHigher;
    }
}
