package br.com.devdojo.demo.endpoint;

import br.com.devdojo.demo.error.CustomErrorType;
import br.com.devdojo.demo.model.Student;
import br.com.devdojo.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> allList() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"),HttpStatus.NOT_FOUND);
        }return new ResponseEntity<>(student,HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public  ResponseEntity<?> save(@RequestBody Student student) {
        return  new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return  new ResponseEntity<>( HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public  ResponseEntity<?> update(@RequestBody Student student) {
        student = studentRepository.save(student);
        return  new ResponseEntity<>(student, HttpStatus.OK);
    }
}
