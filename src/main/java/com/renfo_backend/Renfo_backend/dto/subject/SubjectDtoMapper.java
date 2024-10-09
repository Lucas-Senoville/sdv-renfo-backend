package com.renfo_backend.Renfo_backend.dto.subject;

import com.renfo_backend.Renfo_backend.entity.Subject;

public abstract class SubjectDtoMapper {
    public static SubjectDto toDto(Subject subject) {
        if (subject == null) {
            return new SubjectDto();
        }

        return new SubjectDto(subject.getId(), subject.getName());
    }

    public static Subject toSubject(SubjectDto subjectDto) {
        if (subjectDto == null) {
            return new Subject();
        }

        Subject subject = new Subject(subjectDto.getName());
        subject.setId(subjectDto.getId());

        return subject;
    }
}
