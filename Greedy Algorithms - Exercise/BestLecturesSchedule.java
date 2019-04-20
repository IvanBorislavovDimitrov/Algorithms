import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BestLecturesSchedule {

    private static class Lecture {
        int startTime;
        int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lecturesCount = Integer.parseInt(bufferedReader.readLine().split("\\s+")[1]);
        Map<String, Lecture> lectures = new LinkedHashMap<>();
        for (int i = 0; i < lecturesCount; i++) {
            String[] lectureAndTime = bufferedReader.readLine().split(": ");
            String[] time = lectureAndTime[1].split(" - ");
            lectures.put(lectureAndTime[0], new Lecture(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        }

        Map.Entry<String, Lecture> firstLecture = getFirstLecture(lectures);
        lectures.remove(firstLecture.getKey());
        List<Map.Entry<String, Lecture>> nextLectures = new ArrayList<>();
        lectures = removeOverlaps(lectures, firstLecture);
        nextLectures.add(firstLecture);
        while (!lectures.isEmpty()) {
            Map.Entry<String, Lecture> next = getNextLecture(lectures, nextLectures.get(nextLectures.size() - 1));
            nextLectures.add(next);
            lectures = removeOverlaps(lectures, next);
        }
        System.out.println(String.format("Lectures (%d):", nextLectures.size()));
        nextLectures.forEach(lecture ->
                System.out.println(String.format("%d-%d -> %s",
                        lecture.getValue().startTime, lecture.getValue().endTime, lecture.getKey())));

    }

    private static Map<String, Lecture> removeOverlaps(Map<String, Lecture> lectures, Map.Entry<String, Lecture> currentLecture) {
        return lectures.entrySet().stream()
                .filter(l -> Math.max(currentLecture.getValue().startTime, l.getValue().startTime) >
                        Math.min(currentLecture.getValue().endTime, l.getValue().endTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map.Entry<String, Lecture> getNextLecture(Map<String, Lecture> lectures, Map.Entry<String, Lecture> currentLecture) {
        Map.Entry<String, Lecture> next = null;
        int minDiff = 0;
        for (Map.Entry<String, Lecture> lectureEntry : lectures.entrySet()) {
            if (next == null) {
                next = lectureEntry;
            } else {
                if (minDiff == 0) {
                    minDiff = lectureEntry.getValue().endTime - currentLecture.getValue().endTime;
                } else if (minDiff > lectureEntry.getValue().endTime - currentLecture.getValue().endTime) {
                    minDiff = lectureEntry.getValue().endTime - currentLecture.getValue().endTime;
                    next = lectureEntry;
                }
            }
        }

        return next;
    }

    private static Map.Entry<String, Lecture> getFirstLecture(Map<String, Lecture> lectures) {
        return lectures.entrySet().stream()
                .min(Comparator.comparingInt(l -> l.getValue().endTime))
                .orElse(null);
    }
}
