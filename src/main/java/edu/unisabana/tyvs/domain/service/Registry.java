package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import java.util.HashSet;
import java.util.Set;

public class Registry {

    private static final int MIN_VALID_AGE = 18;
    private static final int MAX_VALID_AGE = 120;
    private static final int MIN_VALID_ID = 1;

    private Set<Integer> registeredIds = new HashSet<>();

    public RegisterResult registerVoter(Person p) {
        // Validación defensiva: null
        if (p == null) {
            return RegisterResult.INVALID;
        }

        // Validación: id debe ser mayor a 0
        if (p.getId() < MIN_VALID_ID) {
            return RegisterResult.INVALID;
        }

        // Validación: edad debe estar dentro del rango válido [1, 120]
        // Edad 0 y negativas son inválidas
        if (p.getAge() <= 0 || p.getAge() > MAX_VALID_AGE) {
            return RegisterResult.INVALID_AGE;
        }

        // Validación: persona debe estar viva
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }

        // Validación: debe ser mayor de edad
        if (p.getAge() < MIN_VALID_AGE) {
            return RegisterResult.UNDERAGE;
        }

        // Validación: no debe estar duplicado
        if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }

        // Si todas las validaciones pasan, registrar la persona
        registeredIds.add(p.getId());
        return RegisterResult.VALID;
    }
}
