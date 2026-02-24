package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    // TODO: Xem va tim kiem
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // Xem chi tiết
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("sinhVien", student);
        return "student-detail";
    }

    // TODO: Them moi - Hien thi form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // TODO: Them moi - Xu ly luu
    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    // TODO: Chinh sua - Xu ly luu
    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute("student") Student student) {
        student.setId(id);
        studentService.save(student);
        return "redirect:/students";
    }

    // TODO: Xoa
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}