# Hellpass

Proyecto académico para una clínica, desarrollado como una API en Java con acceso manual a MariaDB. La aplicación se conectará a Thymeleaf para presentar las vistas. La conexión a la base de datos y las consultas SQL se implementarán explícitamente, sin ORM ni generación automática de sentencias.

> **Estado actual:** el proyecto está en una etapa inicial. Por ahora contiene una clase `Main` de prueba y una clase JDBC para abrir la conexión; todavía no hay endpoints, vistas Thymeleaf, modelo de datos ni consultas de negocio implementadas.

## Materiales del proyecto

- **Enunciado, Mer, UML:** [https://drive.google.com/drive/folders/1eMBn4YL_teQU8MlFmbGw61jfKmCNHbl7?usp=sharing](#)

Reemplaza los enlaces de ejemplo cuando los documentos estén disponibles.

## Tecnologías y herramientas

- Java 17
- Maven
- MariaDB
- JDBC (`DriverManager`) para la conexión y ejecución manual de SQL
- Thymeleaf como motor de plantillas previsto para las vistas

Thymeleaf y el controlador web todavía no están configurados en el `pom.xml`.

## Requisitos previos

1. Instalar un JDK 17.
2. Instalar Maven.
3. Tener una instancia local de MariaDB disponible.
4. Crear la base de datos `db_hells` y las tablas definidas en el MER cuando este esté listo.

La configuración JDBC actual apunta a `localhost:3307`, con usuario `root` y contraseña `root`. Estos valores están escritos directamente en `src/main/java/util/DatabaseConnection.java` solo como configuración local inicial. Antes de compartir o desplegar el proyecto, usa credenciales propias y evita subir contraseñas reales al repositorio.

## Ejecutar el proyecto

Desde la raíz del proyecto:

```bash
mvn clean compile
```

La clase de entrada actual es `com.clinicahellpass.Main`; por el momento solo imprime `Hello world!`. La aplicación todavía no inicia un servidor web ni expone una API.

## Estructura actual

```text
src/
└── main/
    └── java/
        ├── com/clinicahellpass/
        │   └── Main.java
        └── util/
            └── DatabaseConnection.java
pom.xml
```

## Proceso de desarrollo previsto

El desarrollo se hará de forma explícita para que cada parte de la aplicación y su interacción con la base de datos queden visibles:

1. **Revisar el enunciado e identificar requisitos.** Registrar las entidades, atributos, relaciones y operaciones que requiere el sistema.
2. **Definir los modelos.** Representar en Java las entidades identificadas en el enunciado y el MER.
3. **Preparar la base de datos.** Crear manualmente la base y sus tablas en MariaDB, respetando claves primarias, claves foráneas y restricciones del modelo.
4. **Conectar mediante JDBC.** Abrir la conexión con `DriverManager` y comprobar que la base de datos esté disponible.
5. **Escribir SQL manualmente.** Implementar cada operación con sentencias SQL explícitas (`SELECT`, `INSERT`, `UPDATE` y `DELETE`), usando `PreparedStatement` para pasar los valores de entrada.
6. **Implementar la API.** Añadir controladores y rutas para recibir solicitudes, validar datos y coordinar las operaciones con la base de datos.
7. **Integrar Thymeleaf.** Crear las plantillas HTML y conectar los controladores con las vistas que permitan visualizar e interactuar con la información.
8. **Probar el flujo completo.** Verificar las operaciones desde la vista o la solicitud hasta MariaDB y comprobar los resultados y errores.
9. **Mantener la documentación y diagramas.** Actualizar el enunciado enlazado, el MER y el UML a medida que el diseño y la implementación evolucionen.

## Conexión a la base de datos

La conexión inicial está en `src/main/java/util/DatabaseConnection.java`. Utiliza JDBC directamente:

```java
DriverManager.getConnection(url, username, password);
```

La clase conserva la conexión en un campo estático y la crea cuando `getInstance()` se invoca por primera vez. El acceso a datos y el ciclo de vida de las conexiones deberán revisarse al implementar las operaciones de la API; las conexiones y sentencias deben cerrarse adecuadamente después de usarse.

## SQL manual

No se usa JPA, Hibernate ni otro ORM. Las sentencias para crear tablas y consultar o modificar datos se escribirán y mantendrán manualmente. Cuando se defina el esquema, se recomienda guardar los scripts SQL del proyecto en una carpeta como `sql/` y documentar el orden para ejecutarlos.

Las consultas con datos proporcionados por una solicitud deben parametrizarse con `PreparedStatement`; no se deben concatenar valores de entrada directamente en una sentencia SQL.

## Estructura objetivo

La estructura puede evolucionar conforme se incorporen las capas web y de persistencia. Como referencia:

```text
src/main/
├── java/
│   ├── com/clinicahellpass/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── util/
│       └── DatabaseConnection.java
└── resources/
    ├── templates/
    └── static/
```

Esta estructura es una propuesta, no refleja todavía los archivos implementados.

## Pendientes principales

- Incorporar las dependencias y la configuración web necesarias para la API y Thymeleaf.
- Completar el enunciado, las identidades y los enlaces a los entregables del proyecto.
- Terminar y enlazar el MER y el UML en desarrollo.
- Definir y crear el esquema de MariaDB.
- Implementar modelos, consultas SQL manuales y operaciones de la API.
- Crear las plantillas Thymeleaf y verificar la integración completa.
