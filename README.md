# Desarrollo Guiado por Pruebas - Caso práctico

#### CLASES DE EQUIVALENCIA 

---

### CREAR UN PROYECTO CON MAVEN
En el directorio de trabajo ejecutar el comando necesario para crear/generar un proyecto maven basado en un arquetipo:
```yml
Grupo (groupId): edu.unisabana.dyas
Artefacto (artifactId): clasesequivalencia
Paquete (package): edu.unisabana.dyas.tdd
archetypeArtifactId: maven-archetype-quickstart
```

🎓 Si necesitas más ayuda con la creación de proyectos en Maven, revisa el [**Taller de Nivelación**](https://github.com/CesarAVegaF312/DYAS-Taller_nivelacion.git).

---

### ACTUALIZAR Y CREAR DEPENDENCIAS EN EL PROYECTO

Busque en internet el repositorio central de maven.

Busque el artefacto JUnit y entre a la versión más nueva.

![](img/repo.png)

**NOTA** Ingresar directamente a ["2. Junit"](https://mvnrepository.com/artifact/junit/junit).  

Ingrese a la pestaña de Maven y haga click en el texto de la dependencia para copiarlo al portapapeles.

Edite el archivo `pom.xml` y realice las siguientes actualizaciones:
- Agregue/Reemplace la dependencia copiada a la sección de dependencias.
- Cambie la versión del compilador de Java a la versión 8, agregando la sección `properties` antes de la sección de dependencias:

```xml
<properties>
  <maven.compiler.target>1.8</maven.compiler.target>
  <maven.compiler.source>1.8</maven.compiler.source>
</properties>
```

---

### COMPILAR Y EJECUTAR
Ejecute los comandos necesarios de Maven, para compilar el proyecto y verificar que el proyecto se creó correctamente y los cambios realizados al archivo pom no generan inconvenientes.

Busque el comando requerido para ejecutar las pruebas unitarias de un proyecto desde Maven y ejecútelo sobre el proyecto. Se debe ejecutar la clase `AppTest` con resultado exitoso.

---

## EJERCICIO “REGISTRADURÍA”

Se va a crear un proyecto base para un cliente en la registraduría, en el cual se registrarán personas con intención de votar para las próximas elecciones y se generarán los certificados electorales de aquellas personas cuyo voto sea válido.

Se usará la clase *Person* que se describe más adelante. El servicio de la registraduría permitirá registrar personas que sean votantes.

### REQUERIMIENTOS
- Solo se registrarán votantes válidos.
- Solo se permite una inscripción por número de documento.

---

### HACER EL ESQUELETO DE LA APLICACION

Cree el archivo `RegisterResult.java` en el directorio `edu.unisabana.dyas.tdd.registry` con la enumeración:

```java
package edu.unisabana.dyas.tdd.registry;

public enum RegisterResult {
    DEAD, UNDERAGE, INVALID_AGE, VALID, DUPLICATED
}
```

Cree el archivo `Gender.java` en el paquete `edu.unisabana.dyas.tdd.registry` con la enumeración:

```java
package edu.unisabana.dyas.tdd.registry;

public enum Gender {
    MALE, FEMALE, UNIDENTIFIED;
}
```

Cree el archivo `Person.java` en el paquete `edu.unisabana.dyas.tdd.registry` con el siguiente contenido:

```java
package edu.unisabana.dyas.tdd.registry;
/**
 * Person representation Class
 */
public class Person {
    private String name;
    private int id;
    private int age;
    private Gender gender;
    private boolean alive;

    public Person() { super(); }

    public Person(String name, int id, int age, Gender gender, boolean alive) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.alive = alive;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public int getAge() { return age; }
    public Gender getGender() { return gender; }
    public boolean isAlive() { return alive; }

    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }
    public void setAge(int age) { this.age = age; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setAlive(boolean alive) { this.alive = alive; }

    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + ", age=" + age + ", gender=" + gender + ", alive=" + alive + "]";
    }
}
```

Cree el archivo `Registry.java` en el directorio `edu.unisabana.dyas.tdd.registry` con el método `registerVoter`:

```java
package edu.unisabana.dyas.tdd.registry;

public class Registry {
    public RegisterResult registerVoter(Person p) {
        // TODO Validate person and return real result.
        return RegisterResult.VALID;
    }
}
```

Cree la misma estructura de paquetes `edu.unisabana.dyas.tdd.registry` en la ruta `src/test/java`.  
Todos los archivos relacionados específicamente con los temas de pruebas deben ir bajo la carpeta `test`.

Bajo la carpeta de pruebas, cree la clase `RegistryTest.java` en el directorio `edu.unisabana.dyas.tdd.registry`:

```java
package edu.unisabana.dyas.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void validateRegistryResult() {
        Person person = new Person();
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    // TODO Complete with more test cases
}
```

---

### EJECUTAR LAS PRUEBAS

Para correr las pruebas utilice:
```sh
$ mvn package
```

También puede utilizar:
```sh
$ mvn test
```

---

Revise cuál es la diferencia.  
Tip: [Maven Lifecycle Phases](https://www.devopsschool.com/blog/maven-tutorials-maven-lifecycle-phases-goal).

---

### 2️⃣ Nota sobre ubicación del `pom.xml`

⚠️ **Nota importante:**  
Recuerde ejecutar todos los comandos Maven desde la carpeta **raíz del proyecto**, donde se encuentra el archivo `pom.xml`.

---

### FINALIZAR EL EJERCICIO
Piense en los casos de [equivalencia](https://prezi.com/view/LyUYlz5nx2UmnKVMgSve/?referral_token=inUc7klnB3FN) que se pueden generar del ejercicio para la registraduría dadas las condiciones. Deben ser al menos 5.

Complete la implementación de la clase `RegistryTest.java` con (al menos) un método por cada clase de equivalencia, creando diferentes personas y validando que el resultado sea el esperado.

Complete la implementación del método `registerVoter` en la clase `Registry.java` para retornar el resultado esperado según la entrada.

---

## ENTREGAR
- Crear un repositorio para este proyecto y agregar la URL del mismo como entrega del laboratorio.
- Agregar y configurar el archivo `.gitignore` del repositorio para excluir la carpeta `target` y los archivos generados por el IDE usado (`.classpath`, `.idea`, `.settings`, etc.).
- Agregar el nombre de los integrantes que realizaron el laboratorio. Puede ser en un archivo `integrantes.txt` o agregándolos en este `README`.

---

# Guía avanzada de Pruebas Unitarias

Las pruebas unitarias son la base de un plan de pruebas exhaustivo. Para alinearnos con las buenas prácticas internacionales y los resultados de aprendizaje del curso, además de implementar las pruebas básicas, se deben considerar los siguientes aspectos:

---

### 1. Planificación de las pruebas
Define una **matriz de clases de equivalencia y valores límite** para `registerVoter`. Ejemplo:

| Caso | Entrada | Resultado esperado |
|------|---------|---------------------|
| Persona viva, edad 30, id único | (edad=30, vivo=true, id=1) | VALID |
| Persona muerta | (edad=45, vivo=false) | DEAD |
| Edad 17 | (edad=17, vivo=true) | UNDERAGE |
| Edad -1 | (edad=-1, vivo=true) | INVALID_AGE |
| Persona duplicada | (edad=25, id=777 dos veces) | DUPLICATED |

---

### 2. Cobertura de código

Agrega **JaCoCo** para medir cobertura.  
Este plugin debe incluirse dentro de la sección `<build><plugins> ... </plugins></build>` del archivo `pom.xml`.

```xml
<plugin>
  <groupId>org.jacoco</groupId>
  <artifactId>jacoco-maven-plugin</artifactId>
  <version>0.8.11</version>
  <executions>
    <execution>
      <id>prepare-agent</id>
      <goals><goal>prepare-agent</goal></goals>
    </execution>
    <execution>
      <id>report</id>
      <phase>verify</phase>
      <goals><goal>report</goal></goals>
    </execution>
  </executions>
</plugin>
```

Ejecuta:

```sh
mvn clean test
mvn jacoco:report
```

Revisa el archivo `target/site/jacoco/index.html`.

---

### 3. Robustez de las pruebas
Incluye casos adicionales:
- Persona nula (`null`).
- `id <= 0`.
- Valores de borde (`17`, `18`, `120`, `121`).

---

### 4. Gestión de defectos
Crea un archivo `defectos.md` para documentar fallos:

```
### Defecto 01
- Caso: edad -1
- Esperado: INVALID_AGE
- Obtenido: VALID
- Causa probable: falta de validación en límites
- Estado: Abierto
```

---

### 5. Automatización e integración
- Ejecuta las pruebas unitarias en cada commit con CI (GitHub Actions, Jenkins, GitLab CI).  
- Rechaza merges si `mvn test` falla.

---

### 6. Análisis crítico
Reflexiona:
- ¿Qué escenarios no se cubrieron?
- ¿Qué defectos reales detectaron los tests?
- ¿Cómo mejorarías la clase `Registry` para facilitar su prueba?

---

### 7. Recursos recomendados
- *The Art of Software Testing* – Myers, 2011.  
- *Testing Computer Software* – Kaner, 1999.  
- *Effective Unit Testing* – Lasse Koskela, 2013.  

---

Con estas prácticas, tus pruebas unitarias no solo validan la funcionalidad, sino que se convierten en un **instrumento de calidad**, cumpliendo con los indicadores de desempeño del curso.
