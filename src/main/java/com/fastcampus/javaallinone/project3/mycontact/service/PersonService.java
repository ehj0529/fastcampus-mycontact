package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
//        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();

//        //blocks에서 이름을 가져와서 blockNames에 담는다. 릴레이션이 잡혀 있지 않는 경우
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

//        //people 에서 blockNames에 있는 것을 제외 한 것을 필터링 한다. collector를 이용해 list type으로 cate를 한다.
//        //릴레이션이 잡혀있지 않은 경우.
//        return people.stream().filter(person -> !blockNames.contains(person.getName())).collect(Collectors.toList());

        //OnetoOne으로 릴레이션이 잡혀 있는 경우는 아래와 같이 람다식으로 호출하면 된다.
//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
        
        //Query Method 를 이용한 조건문 쿼리 수행
        return personRepository.findByBlockIsNull();
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        //Person person = personRepository.findById(id).get();
//        1차 처리 방법 old 방법임.
//        Optional<Person> person = personRepository.findById(id);
//        if(person.isPresent()){
//            return person.get();
//        }else {
//            return null;
//        }
//        2차 좋은 방법
        Person person = personRepository.findById(id).orElse(null);

        System.out.println("person :: "+ person );
        log.info("person : {}",person);
        return person;

    }


    public List<Person> getPeopleByName(String name) {

        //List<Person> people = personRepository.findAll();
        //return people.stream().filter( person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);

    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto){
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재 하지 않습니다."));
        if(!person.getName().equals(personDto.getName())){
            throw new RuntimeException("이름이 다릅니다. ");
        }
        person.set(personDto);
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow( () ->new RuntimeException("name없데이트 하려는데 아이디가 존재하지 않습니다."));
        person.setName(name);
        personRepository.save(person);
    }

}
