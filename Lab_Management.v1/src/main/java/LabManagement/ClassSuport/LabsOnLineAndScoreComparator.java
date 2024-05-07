package LabManagement.ClassSuport;

import java.util.Comparator;

public class LabsOnLineAndScoreComparator implements Comparator<LabsOnLineAndScore> {
    @Override
    public int compare(LabsOnLineAndScore LabsOnLineAndScore1, LabsOnLineAndScore LabsOnLineAndScore2) {
        return LabsOnLineAndScore2.getScore().compareTo(LabsOnLineAndScore1.getScore());
    }
}
