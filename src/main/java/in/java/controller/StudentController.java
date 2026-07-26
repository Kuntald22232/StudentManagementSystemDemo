package in.java.controller;

import in.java.entity.Student;
import in.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService service;
    @GetMapping("/")
    public String homePage()
    {
        return "<h1>Welcome to the Student Management System</h1>";
    }
    @PostMapping("/send")
    public Student SaveStudent(@RequestBody Student student)
    {
        return service.saveStudent(student);
    }
    @GetMapping("/get")
    public List<Student> ViewStudent()
    {
        return service.showAllStudents();
    }
    @GetMapping("/get/id/{id}")
    public Student viewStudentById(@PathVariable Long id) {
        return service.getStudetById(id);
    }

    @GetMapping("/get/username/{username}")
    public Student getStudentByUsername(@PathVariable String username) {
        return service.getStudentByUsername(username);
    }
    @PutMapping("/update/{id}")
    public Student UpdateStudent(@RequestBody Student student,@PathVariable Long id)
    {
        return service.updatStudent(student,id);
    }
    @DeleteMapping("/remove/{id}")
    public void DeleteStudent(@PathVariable Long id)
    {
         service.removeStudent(id);
         return;
    }
}
