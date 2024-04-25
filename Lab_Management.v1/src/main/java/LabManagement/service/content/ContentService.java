package LabManagement.service.content;

import LabManagement.entity.Content;

import java.util.List;

public interface ContentService {
    Content saveContent(Content content);
    void deleteContent(int id);
    List<Content> getAllContents();
    Content getContentById(int id);
    List<Content> findAllByReservationistId(int id);
}
