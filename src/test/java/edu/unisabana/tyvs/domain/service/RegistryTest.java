package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {

    // ===== PRUEBAS DE CAMINO FELIZ (VALID) =====

    @Test
    public void shouldRegisterValidPerson() {
        // Arrange: Given - persona viva, edad 30, id único
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser VALID
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAcceptAdultAt18() {
        // Arrange: Given - persona tiene 18 años (límite inferior válido), está viva
        Registry registry = new Registry();
        Person person = new Person("Juan", 2, 18, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser VALID
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAcceptMaxAge120() {
        // Arrange: Given - persona tiene 120 años (límite superior válido), está viva
        Registry registry = new Registry();
        Person person = new Person("Anciano", 3, 120, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser VALID
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    // ===== PRUEBAS DE NULIDAD =====

    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        // Arrange: Given - la persona es null
        Registry registry = new Registry();

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(null);

        // Assert: Then - el resultado debe ser INVALID
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    // ===== PRUEBAS DE ID INVÁLIDO =====

    @Test
    public void shouldRejectWhenIdIsZero() {
        // Arrange: Given - persona tiene id = 0, edad 25 y está viva
        Registry registry = new Registry();
        Person person = new Person("Carlos", 0, 25, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser INVALID
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectWhenIdIsNegative() {
        // Arrange: Given - persona tiene id = -5, edad 25 y está viva
        Registry registry = new Registry();
        Person person = new Person("Diana", -5, 25, Gender.FEMALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser INVALID
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    // ===== PRUEBAS DE EDAD INVÁLIDA =====

    @Test
    public void shouldRejectInvalidAgeNegative() {
        // Arrange: Given - persona tiene edad = -1 (límite inferior inválido)
        Registry registry = new Registry();
        Person person = new Person("Bebé", 4, -1, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser INVALID_AGE
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectInvalidAgeOver120() {
        // Arrange: Given - persona tiene edad = 121 (límite superior inválido)
        Registry registry = new Registry();
        Person person = new Person("Imposible", 5, 121, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser INVALID_AGE
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectInvalidAgeZero() {
        // Arrange: Given - persona tiene edad = 0
        Registry registry = new Registry();
        Person person = new Person("Recién Nacido", 6, 0, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser INVALID_AGE
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    // ===== PRUEBAS DE MENORES DE EDAD =====

    @Test
    public void shouldRejectUnderageAt17() {
        // Arrange: Given - persona tiene 17 años (menor de edad), está viva, id válido
        Registry registry = new Registry();
        Person person = new Person("Adolescente", 7, 17, Gender.FEMALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser UNDERAGE
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldRejectUnderageAt10() {
        // Arrange: Given - persona tiene 10 años (menor de edad), está viva, id válido
        Registry registry = new Registry();
        Person person = new Person("Niño", 8, 10, Gender.MALE, true);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(person);

        // Assert: Then - el resultado debe ser UNDERAGE
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    // ===== PRUEBAS DE PERSONA MUERTA =====

    @Test
    public void shouldRejectDeadPerson() {
        // Arrange: Given - persona está muerta (alive = false), edad 40, id válido
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 9, 40, Gender.MALE, false);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(dead);

        // Assert: Then - el resultado debe ser DEAD
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldRejectDeadYoungPerson() {
        // Arrange: Given - persona está muerta y es mayor de edad
        Registry registry = new Registry();
        Person dead = new Person("Eva", 10, 45, Gender.FEMALE, false);

        // Act: When - intento registrarla
        RegisterResult result = registry.registerVoter(dead);

        // Assert: Then - el resultado debe ser DEAD (DEAD se valida antes que edad)
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    // ===== PRUEBAS DE DUPLICADOS =====

    @Test
    public void shouldRejectDuplicateRegistration() {
        // Arrange: Given - registrar una persona y luego intentar registrar otra con mismo id
        Registry registry = new Registry();
        Person person1 = new Person("Pedro", 100, 30, Gender.MALE, true);
        Person person2 = new Person("Pablo", 100, 35, Gender.MALE, true);

        // Act: When - registro la primera persona
        RegisterResult result1 = registry.registerVoter(person1);
        // And - intento registrar la segunda con id duplicado
        RegisterResult result2 = registry.registerVoter(person2);

        // Assert: Then - primera debe ser VALID, segunda DUPLICATED
        Assert.assertEquals(RegisterResult.VALID, result1);
        Assert.assertEquals(RegisterResult.DUPLICATED, result2);
    }

    @Test
    public void shouldAllowDifferentIds() {
        // Arrange: Given - dos personas con ids diferentes
        Registry registry = new Registry();
        Person person1 = new Person("Ana", 200, 28, Gender.FEMALE, true);
        Person person2 = new Person("Benito", 201, 32, Gender.MALE, true);

        // Act: When - registro ambas personas
        RegisterResult result1 = registry.registerVoter(person1);
        RegisterResult result2 = registry.registerVoter(person2);

        // Assert: Then - ambas deben ser VALID
        Assert.assertEquals(RegisterResult.VALID, result1);
        Assert.assertEquals(RegisterResult.VALID, result2);
    }
}
