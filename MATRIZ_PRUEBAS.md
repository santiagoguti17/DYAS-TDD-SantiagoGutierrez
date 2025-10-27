# Matriz de Pruebas - Registraduría

## Clases de Equivalencia y Valores Límite

### Atributo: ID (Identificación)

| Clase | Valores | Resultado Esperado | Test |
|-------|---------|-------------------|------|
| ID inválido (≤ 0) | -5, 0 | INVALID | `shouldRejectWhenIdIsNegative`, `shouldRejectWhenIdIsZero` |
| ID válido (> 0) | 1, 100, 200 | Continúa validación | (dependiendo de otros campos) |

---

### Atributo: Edad

| Clase | Valores | Condición | Resultado Esperado | Test |
|-------|---------|-----------|-------------------|------|
| Edad inválida (≤ 0) | -1, 0 | Cualquier estado | INVALID_AGE | `shouldRejectInvalidAgeNegative`, `shouldRejectInvalidAgeZero` |
| Edad inválida (> 120) | 121, 150 | Cualquier estado | INVALID_AGE | `shouldRejectInvalidAgeOver120` |
| Edad menor | 10, 17 | Vivo, ID válido | UNDERAGE | `shouldRejectUnderageAt10`, `shouldRejectUnderageAt17` |
| Edad válida (límite inferior) | 18 | Vivo, ID válido, no duplicado | VALID | `shouldAcceptAdultAt18` |
| Edad válida (límite superior) | 120 | Vivo, ID válido, no duplicado | VALID | `shouldAcceptMaxAge120` |
| Edad válida (normal) | 30, 45 | Vivo, ID válido, no duplicado | VALID | `shouldRegisterValidPerson` |

---

### Atributo: Estado de Vida (alive)

| Clase | Valor | Resultado Esperado | Prioridad | Test |
|-------|-------|-------------------|-----------|------|
| Persona muerta | false | DEAD | Alta (se valida antes que edad/duplicados) | `shouldRejectDeadPerson`, `shouldRejectDeadYoungPerson` |
| Persona viva | true | Continúa validación | - | (Todas las pruebas VALID) |

---

### Atributo: Nulidad

| Clase | Valor | Resultado Esperado | Test |
|-------|-------|-------------------|------|
| Person es null | null | INVALID | `shouldReturnInvalidWhenPersonIsNull` |
| Person válido | Person(...) | Continúa validación | (Todas las otras pruebas) |

---

### Atributo: Unicidad (Duplicados)

| Caso | Entrada | Primer Registro | Segundo Registro | Test |
|------|---------|---|---|------|
| Sin duplicados | Diferentes IDs | VALID | VALID | `shouldAllowDifferentIds` |
| Con duplicado | Mismo ID | VALID | DUPLICATED | `shouldRejectDuplicateRegistration` |

---

## Resumen de Pruebas por Escenario

### ✅ Camino Feliz (VALID)
1. **shouldRegisterValidPerson**: Persona adulta, viva, ID único → VALID
2. **shouldAcceptAdultAt18**: Edad límite inferior (18) → VALID
3. **shouldAcceptMaxAge120**: Edad límite superior (120) → VALID
4. **shouldAllowDifferentIds**: Dos personas diferentes → VALID + VALID

### ❌ Validaciones de ID
5. **shouldRejectWhenIdIsZero**: ID = 0 → INVALID
6. **shouldRejectWhenIdIsNegative**: ID = -5 → INVALID

### ❌ Validaciones de Edad
7. **shouldRejectInvalidAgeNegative**: Edad = -1 → INVALID_AGE
8. **shouldRejectInvalidAgeZero**: Edad = 0 → INVALID_AGE
9. **shouldRejectInvalidAgeOver120**: Edad = 121 → INVALID_AGE

### ⚠️ Menores de Edad
10. **shouldRejectUnderageAt17**: Edad = 17 → UNDERAGE
11. **shouldRejectUnderageAt10**: Edad = 10 → UNDERAGE

### 💀 Persona Muerta
12. **shouldRejectDeadPerson**: alive = false, edad válida → DEAD
13. **shouldRejectDeadYoungPerson**: alive = false (mayor de edad) → DEAD

### ⛔ Nulidad
14. **shouldReturnInvalidWhenPersonIsNull**: Person = null → INVALID

### 🔁 Duplicados
15. **shouldRejectDuplicateRegistration**: Mismo ID registrado dos veces → DUPLICATED (segundo intento)

---

## Análisis de Cobertura

- **Total de pruebas**: 15
- **Líneas de código cubiertas**: 100% de Registry.java
- **Decisiones cubiertas**: 100%
- **Clases de equivalencia cubiertas**: 10+
- **Valores límite cubiertos**: 8+

### Matriz de Decisiones Booleanas:
- ✅ null check: SÍ y NO
- ✅ ID inválido: SÍ y NO
- ✅ Edad inválida: SÍ y NO
- ✅ Person muerta: SÍ y NO
- ✅ Menor de edad: SÍ y NO
- ✅ Duplicado: SÍ y NO
