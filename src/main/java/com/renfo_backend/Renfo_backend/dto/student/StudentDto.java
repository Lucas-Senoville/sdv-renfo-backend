package com.renfo_backend.Renfo_backend.dto.student;

import com.renfo_backend.Renfo_backend.dto.person.PersonDto;

public class StudentDto extends PersonDto {
    public StudentDto() {
        super();
    }

    public StudentDto(StudentDto studentDto) {
        this(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName());
    }

    public StudentDto(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
