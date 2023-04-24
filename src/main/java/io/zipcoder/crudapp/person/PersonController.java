package io.zipcoder.crudapp.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController{
    @Autowired
    PersonRepository people;
    
    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(people.save(p), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int id){
        return new ResponseEntity<>(people.findOne(id), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>((List<Person>) people.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> updatPerson(@PathVariable("id") int id, @RequestBody Person p){
        Person old = people.findOne(id);
        people.save(p);

        if(old.equals(null)){
            return new ResponseEntity<>(people.save(p), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(people.save(p), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") int id){
        people.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}