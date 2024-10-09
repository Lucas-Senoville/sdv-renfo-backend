package com.renfo_backend.Renfo_backend.dto.teacher;

import com.renfo_backend.Renfo_backend.dto.person.PersonDto;

public class TeacherDto extends PersonDto {
    public TeacherDto() {
        super();
    }

    public TeacherDto(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
