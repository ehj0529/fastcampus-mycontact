package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findByBlockIsNull();

    List<Person> findByName(String name);

    List<Person> findByBloodType(String bloodType);

    // birthday 를 int 객체형식으로 변경을 해서 나는 오류입
    //List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
    // 들어오는 값으로 적용하는 방식
    //@Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1 and person.birthday.dayOfBirthday = ?2")
    //List<Person> findByMonthOfBirthday(int monthOfBirthday, int dayOfBirthday);
    //param 으로 쿼리 적용하는 방법
    @Query(value = "select person from Person person " +
                               " where person.birthday.monthOfBirthday = :monthOfBirthday " +
                               "   and person.birthday.dayOfBirthday = :dayOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday, @Param("dayOfBirthday") int dayOfBirthday);

    //param 으로 쿼리 적용하는 방법 nativeQuery 방식을 추가하는방법
    //@Query(value = "select * from  person " +
    //        " where month_of_birthday = :monthOfBirthday " +
    //        "   and day_of_birthday = :dayOfBirthday" , nativeQuery = true)
    //List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday, @Param("dayOfBirthday") int dayOfBirthday);


}
