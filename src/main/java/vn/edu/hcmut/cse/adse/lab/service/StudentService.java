package vn.edu.hcmut.cse.adse.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll()
    {
        return studentRepository.findAll();
    }

    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword)
    {
        return studentRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Student save(Student student)
    {
        return studentRepository.save(student);
    }

    public void deleteById(String id)
    {
        studentRepository.deleteById(id);
    }
}
