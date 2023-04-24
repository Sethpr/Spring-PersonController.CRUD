package io.zipcoder.person;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController{
    List<Person> people;
    
    public void createPerson(Person p){
        people.add(p);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable int id){
        for (Person person : people) {
            if(person.getId() == id){
                return person;
            }
        }
        return null;
    }


    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<Person> getPersonList(){
        return people;
    }

    
    public Person updatPerson(Person p){
        for (int i = 0; i < people.size(); i++) {
            if(people.get(i).getId() == p.getId()){
                people.set(i, p);
            }
        }
        return p;
    }

    public void deletePerson(int id){
        people.remove(id);
    }
}