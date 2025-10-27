# Registro de Defectos

Este documento recopila los defectos encontrados durante la ejecución de pruebas unitarias del proyecto **Registraduría**.  
Cada defecto debe documentarse claramente para facilitar su análisis y corrección.

---

## Defectos Encontrados en esta Sesión

### Defecto 01 - RESUELTO ✅
- **Caso de prueba**: Persona con edad 0 (edad inválida).
- **Entrada**: `Person(name="RecienNacido", id=6, age=0, gender=MALE, alive=true)`
- **Resultado esperado**: `INVALID_AGE`
- **Resultado obtenido**: `UNDERAGE`
- **Causa probable**: La lógica validaba `age < 18` antes de validar `age <= 0`, lo que causaba que edad 0 fuera tratada como menor de edad.
- **Solución**: Reordenar las validaciones para verificar rango válido de edad ANTES de validar mayoría de edad.
- **Cambio realizado**: 
  ```java
  // ANTES (incorrecto):
  if (p.getAge() < 0 || p.getAge() > 120) return INVALID_AGE;
  ...
  if (p.getAge() < 18) return UNDERAGE;
  
  // DESPUÉS (correcto):
  if (p.getAge() <= 0 || p.getAge() > 120) return INVALID_AGE;
  ...
  if (p.getAge() < 18) return UNDERAGE;
  ```
- **Estado**: Resuelto

---

## Formato de Defectos Plantilla

### Defecto XX - [ABIERTO/EN PROGRESO/RESUELTO]
- **Caso de prueba**: Descripción breve del escenario fallido
- **Entrada**: Datos/parámetros que causan el defecto
- **Resultado esperado**: Salida correcta según especificación
- **Resultado obtenido**: Salida errónea observada
- **Causa probable**: Análisis de por qué ocurre el error
- **Solución**: Cambios necesarios para corregir
- **Cambio realizado**: Código específico modificado
- **Estado**: Abierto | En progreso | Resuelto

---

## Convenciones de Estado
- **Abierto** → El defecto aún no se corrige.  
- **En progreso** → El defecto está siendo trabajado.  
- **Resuelto** → El defecto fue corregido y validado con pruebas.  

---

## Observaciones
- El ciclo de TDD (Red → Green → Refactor) ayuda a identificar defectos tempranamente.
- Las pruebas unitarias bien diseñadas (con clases de equivalencia y valores límite) detectan estos defectos automáticamente.
- Mantener este archivo actualizado durante todo el ciclo de desarrollo.
