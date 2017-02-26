package ru.itis.inform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.inform.dao.SubjectsDAO;
import ru.itis.inform.model.Subject;
import ru.itis.inform.service.SubjectService;

import java.util.List;



@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectsDAO subjectsDAO;

    @Override
    public Subject find(Long id) {
        return subjectsDAO.find(id);
    }

    @Override
    public void delete(Long id) {
        subjectsDAO.delete(id);
    }

    @Override
    public Long save(Subject subject) {
        return subjectsDAO.save(subject);
    }

    @Override
    public List<Subject> findAll() {
        return subjectsDAO.findAll();
    }

    @Override
    public void update(Subject subject) {
        subjectsDAO.update(subject);
    }

    @Override
    public boolean isExist(Long id) {
        return subjectsDAO.find(id) != null;
    }
}
