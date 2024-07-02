package LabManagement.service.LessonService;

import LabManagement.entity.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLessons();
    Lesson getLessonById(int id);
    Lesson createLesson(Lesson lesson);
    Lesson updateLesson(int id, Lesson lesson);
    void deleteLesson(int id);
    List<Lesson> findAllByLabId(int id);
}
