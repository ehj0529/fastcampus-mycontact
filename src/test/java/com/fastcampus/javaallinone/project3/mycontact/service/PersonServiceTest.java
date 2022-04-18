package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.domain.Block;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import com.fastcampus.javaallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){
       // givenPeople();
       // givenBlock();
        List<Person> result = personService.getPeopleExcludeBlocks();

        //result.forEach(System.out::println);

        assertThat(result.size()).isEqualTo(19);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
    }

    @Test
    void findByBloodType(){
        givenPerson("martin", 20, "A");
        givenPerson("david", 19, "B");
        givenPerson("dennis", 18, "O");
        givenPerson("sophia", 17, "AB");
        givenPerson("benny", 7, "A");
        givenPerson("john", 37, "A");
        givenPerson("mark", 27, "A");

        List<Person> result = personRepository.findByBloodType("AB");
        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("martin", 20, "A",LocalDate.of(1980,10,1));
        givenPerson("david", 19, "B",LocalDate.of(1983,2,28));
        givenPerson("dennis", 18, "O",LocalDate.of(1991,1,1));
        givenPerson("sophia", 17, "AB",LocalDate.of(1992,3,21));
        givenPerson("benny", 7, "A",LocalDate.of(1999,12,11));
        givenPerson("john", 37, "A",LocalDate.of(2000,8,9));
        givenPerson("mark", 27, "A",LocalDate.of(2002,1,21));

        //List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1991,8,30),LocalDate.of(1999,12,31));
        List<Person> result = personRepository.findByMonthOfBirthday(1, 21);

        result.forEach(System.out::println);
    }

    private void givenPerson(String name, int age, String bloodType,LocalDate birthday) {
        Person person = new Person(name,age,bloodType);
        person.setBirthday(new Birthday( birthday ));
        personRepository.save(person);
    }

    private void givenPerson(String name, int age, String bloodType) {
        Person person = new Person(name,age,bloodType);
        personRepository.save(person);
    }

    @Test
    void getPeopleByName(){
        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }

/*
    @Test
    void cascadeTest(){
        // JPA 의 cascade 옵션 설명
        // CascadeType.PERSIST, 생성을 할때 종속된 테이블도 같이 생성을 한다. 당연히 not Null 은 체워져야 한다.
        // CascadeType.MERGE,   생성후 데이터 변경시 부모 객체의 것과 같이 종속된 객체의 table 도 같이 변경 된다.
        // CascadeType.REMOVE   부모객체에서 삭제를 하는 경우 종속된 객체(Table)에서도 삭제가 된다.
        givenPeople();
        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person); //부모객체에서 update
        personRepository.findAll().forEach(System.out::println);

        personRepository.delete(person); //부모객체에서 delete
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);

    }
*/
    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);
        assertThat(person.getName()).isEqualTo("dennis");
    }


    private void givenPeople() {
        givenPeople("martin", 20, "A");
        givenPeople("david", 21, "B");
        givenBlockPerson("dennis", 22, "O");
        givenBlockPerson("martin", 23, "AB");
    }

    private void givenPeople(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType ));
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
//        blockPerson.setBlock(givenBlock(name));
        blockPerson.setBlock(new Block(name));
        personRepository.save(blockPerson);
    }


//    private void givenBlock() {
//        givenBlock("martin");
//    }
//entity에서 영속성을 부여 하여 별도의 save 메소드가 없어도 자동으로 생성이 된다.
// entity 에서 @OneToOne(cascade = CascadeType.PERSIST) 옵션을 제공하게 되면 자동으로 block 도 생성이 된다.
//    private Block givenBlock(String name) {
//        return blockRepository.save(new Block(name));
//    }
}