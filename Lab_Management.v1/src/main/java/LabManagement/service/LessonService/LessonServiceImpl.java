package LabManagement.service.LessonService;

import LabManagement.dao.LessonRepository;
import LabManagement.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLesson(int id, Lesson lesson) {
        Lesson existingLesson = lessonRepository.findById(id).orElse(null);
        if (existingLesson != null) {
            existingLesson.setLabId(lesson.getLabId());
            existingLesson.setNoLesson(lesson.getNoLesson());
            existingLesson.setName(lesson.getName());
            existingLesson.setModule(lesson.getModule());
            existingLesson.setLevel(lesson.getLevel());
            existingLesson.setWorkTime(lesson.getWorkTime());
            existingLesson.setType(lesson.getType());
            existingLesson.setNote(lesson.getNote());

            return lessonRepository.save(existingLesson);
        }
        return null;
    }

    @Override
    public void deleteLesson(int id) {
        lessonRepository.deleteById(id);
    }
    @Override
    public List<Lesson> findAllByLabId(int id) {
        return lessonRepository.findAllByLabId(id);
    }
}
