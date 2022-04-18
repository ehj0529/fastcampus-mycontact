package com.fastcampus.javaallinone.project3.mycontact.domain;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    private String hobby;

    @NotEmpty
    @NonNull
    @Column(nullable = false)
    private String bloodType;

    private String address;

    //private LocalDate birthday; 날짜객체를 Dto 에 있는 객체로 변경함.
    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    // JPA 의 cascade 옵션 설명
    // CascadeType.PERSIST, 생성을 할때 종속된 테이블도 같이 생성을 한다. 당연히 not Null 은 체워져야 한다.
    // CascadeType.MERGE,   생성후 데이터 변경시 부모 객체의 것과 같이 종속된 객체의 table 도 같이 변경 된다.
    // CascadeType.REMOVE   부모객체에서 삭제를 하는 경우 종속된 객체(Table)에서도 삭제가 된다.
    // CascadeType.ALL   이면 위의 항목이 전체가 적용된다.
    // orphanRemoval 부모객체에서 종속객체는 null 로 갱신 하는 경우 종속객체(Table)에서 같이 삭제가 된다.
    // FetchType.LAZY  FetchType.EAGER 두가지 옵션이 있음. EAGER 은 조인 쿼리 호출
    // LAZY 는 별도 호출 ( 종속 객체가 존재 할때만 별도 종속 테이블 호출
    // @ToString.Exclude 불필요한 쿼리호출을 줄이는것임.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto){

        if(personDto.getAge() != 0){
            this.setAge(personDto.getAge());
        }

        if(StringUtils.hasText(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }

        if(StringUtils.hasText(personDto.getBloodType())){
            this.setBloodType((personDto.getBloodType()));
        }

        if(StringUtils.hasText(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }

        if(StringUtils.hasText(personDto.getJob())){
            this.setJob(personDto.getJob());
        }

        if(StringUtils.hasText(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

    }

}
