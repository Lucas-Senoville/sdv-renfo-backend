package com.renfo_backend.Renfo_backend.dto.registration;

import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Registration;

public abstract class RegistrationDtoMapper {
    public static RegistrationDto toDto(Registration registration) {
        if (registration == null) {
            return new RegistrationDto();
        }

        return new RegistrationDto(registration.getId(), registration.getDate());
    }

    public static RegistrationWithLessonDto toDtoWithLesson(Registration registration) {
        if (registration == null) {
            return new RegistrationWithLessonDto();
        }

        return new RegistrationWithLessonDto(toFullDto(registration));
    }

    public static RegistrationWithStudentDto toDtoWithStudent(Registration registration) {
        if (registration == null) {
            return new RegistrationWithStudentDto();
        }

        return new RegistrationWithStudentDto(toFullDto(registration));
    }

    public static FullRegistrationDto toFullDto(Registration registration) {
        if (registration == null) {
            return new FullRegistrationDto();
        }

        FullRegistrationDto fullRegistrationDto = new FullRegistrationDto(toDto(registration));
        fullRegistrationDto.setLesson(registration.getLesson());
        fullRegistrationDto.setStudent(registration.getStudent());

        return fullRegistrationDto;
    }

    public static Registration toRegistration(RegistrationDto registrationDto) {
        if (registrationDto == null) {
            return new Registration();
        }

        Registration registration = toRegistration((RegistrationDto) registrationDto);
        registration.setStudent(StudentDtoMapper.toStudent(registrationDto.getStudent()));
        registration.setLesson(LessonDtoMapper.toLesson(registrationDto.getLesson()));

        return registration;
    }
}
