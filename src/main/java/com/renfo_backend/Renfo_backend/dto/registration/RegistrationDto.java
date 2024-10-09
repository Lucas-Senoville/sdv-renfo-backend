package com.renfo_backend.Renfo_backend.dto.registration;

import java.time.LocalDate;

public class RegistrationDto {
    private Long id;
    private LocalDate date;

    public RegistrationDto() {
    }

    public RegistrationDto(RegistrationDto registrationDto) {
        this(registrationDto.id, registrationDto.date);
    }

    public RegistrationDto(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
