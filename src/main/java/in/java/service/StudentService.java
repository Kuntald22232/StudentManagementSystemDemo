package in.java.service;

import in.java.entity.Student;
import in.java.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public Student saveStudent(Student student)
    {
        student.setPassword(encoder.encode(student.getPassword()));

        student.getAddress().setStudent(student);

        student.getCollege().setStudent(student);
        student.getCollege().setAddress(student.getAddress());

        return repo.save(student);
    }

    public List<Student> showAllStudents()
    {
        return repo.findAll();
    }

    public Student getStudentByUsername(String username)
    {
        return repo.findByUsername(username);
    }
    public Student getStudetById(Long id)
    {
        return repo.findById(id).get();
    }

    public Student updatStudent(Student update,Long id)
    {
        Student exist=repo.findById(id).orElseThrow(
                ()->new RuntimeException("Student details not found by ID "+id));

        exist.setFirstname(update.getFirstname());
        exist.setLastname(update.getLastname());
        exist.setAge(update.getAge());
        exist.setContact(update.getContact());
        exist.setEmail(update.getEmail());
        exist.setDob(update.getDob());
        exist.setUsername(update.getUsername());
        exist.setPassword(update.getPassword());
        exist.setRole(update.getRole());
        exist.setAuthority(update.getAuthority());
        exist.setAddress(update.getAddress());
        exist.setCollege(update.getCollege());

        return repo.save(exist);
    }

    public void removeStudent(Long id)
    {
        repo.deleteById(id);
        return;
    }

}
