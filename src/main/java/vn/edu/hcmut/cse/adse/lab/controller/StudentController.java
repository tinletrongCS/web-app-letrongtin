package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            // Can viet them ham searchByName trong Service/Repository
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id)
    {
        return studentService.getById(id);
    }
}
