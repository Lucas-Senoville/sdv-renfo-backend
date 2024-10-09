package com.renfo_backend.Renfo_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renfo_backend.Renfo_backend.dto.lesson.LessonDtoMapper;
import com.renfo_backend.Renfo_backend.dto.registration.FullRegistrationDto;
import com.renfo_backend.Renfo_backend.dto.registration.RegistrationDtoMapper;
import com.renfo_backend.Renfo_backend.dto.student.StudentDtoMapper;
import com.renfo_backend.Renfo_backend.entity.Registration;
import com.renfo_backend.Renfo_backend.exceptions.RegistrationNotFoundException;
import com.renfo_backend.Renfo_backend.repository.RegistrationRepository;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationRepository repository;

    public RegistrationController(RegistrationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<FullRegistrationDto> getRegistrations() {
        return repository.findAll().stream().map(RegistrationDtoMapper::toFullDto).toList();
    }

    @PostMapping
    public FullRegistrationDto newRegistration(@RequestBody FullRegistrationDto fullRegistrationDto) {
        Registration registration = RegistrationDtoMapper.toRegistration(fullRegistrationDto);

        Registration newRegistration = repository.save(registration);

        return RegistrationDtoMapper.toFullDto(newRegistration);
    }

    @GetMapping("/{id}")
    public FullRegistrationDto getRegistration(@PathVariable Long id) {
        return repository.findById(id).map(RegistrationDtoMapper::toFullDto)
                .orElseThrow(() -> new RegistrationNotFoundException(id));
    }

    @PutMapping("/{id}")
    FullRegistrationDto replaceRegistration(@RequestBody FullRegistrationDto newRegistration, @PathVariable Long id) {
        return RegistrationDtoMapper.toFullDto(repository.findById(id)
                .map(registration -> {
                    registration.setDate(newRegistration.getDate());
                    registration.setLesson(LessonDtoMapper.toLesson(newRegistration.getLesson()));
                    registration.setStudent(StudentDtoMapper.toStudent(newRegistration.getStudent()));

                    return repository.save(registration);
                })
                .orElseGet(() -> {
                    return repository.save(RegistrationDtoMapper.toRegistration(newRegistration));
                }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRegistration(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
