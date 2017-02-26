package ru.itis.inform.service;

import ru.itis.inform.model.Subject;


public interface SubjectService extends BaseService<Subject> {

    boolean isExist(Long id);
}
