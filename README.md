# Documentación para la Ejecución y Pruebas de la Aplicación

## Requisitos Previos

### 1. Java Development Kit (JDK)
La aplicación está desarrollada en Java, por lo que necesitas instalar el JDK (versión 8 o superior).
* Puedes descargar el JDK desde Oracle o utilizar una distribución abierta como OpenJDK.

### 2. IDE o Entorno de Desarrollo
* Se recomienda utilizar un IDE como IntelliJ IDEA, Eclipse o NetBeans para facilitar la ejecución y prueba del código.

### 3. Base de Datos
* Configura y verifica que tengas acceso a una base de datos compatible con JPA (por ejemplo, MySQL, PostgreSQL o H2 para pruebas).
* Asegúrate de configurar correctamente las credenciales y el esquema en el archivo de configuración `persistence.xml`.

### 4. Librerías Necesarias
* Incluye en tu proyecto las dependencias necesarias para JPA y tu proveedor de base de datos. Ejemplo: Hibernate si usas MySQL. Las dependencias se pueden gestionar con Maven o Gradle.

## Instrucciones para la Ejecución

### 1. Clonar o Importar el Proyecto
* Clona el repositorio o importa los archivos del proyecto en tu IDE favorito.

### 2. Configurar el `persistence.xml`
* Ve al archivo `persistence.xml` y actualiza la configuración de la base de datos:

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">
    <persistence-unit name="miUnidadPersistencia">
        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/miBaseDatos"/>
            <property name="javax.persistence.jdbc.user" value="usuario"/>
            <property name="javax.persistence.jdbc.password" value="contraseña"/>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
        </properties>
    </persistence-unit>
</persistence>
```

### 3. Compilar el Proyecto
* Utiliza las herramientas del IDE o ejecuta el comando:

```bash
javac -d bin src/**/*.java
```

### 4. Ejecutar la Aplicación
* Ejecuta la clase principal `Empleados`:

```bash
java -cp bin com.hackaboss.empleados.Empleados
```

## Pruebas de la Aplicación

### 1. Alta de Empleados
* Selecciona la opción `1` en el menú principal.
* Ingresa los datos solicitados (nombre, apellido, cargo, salario y fecha de inicio).
* Verifica que el mensaje de confirmación se muestre correctamente y que los datos se guarden en la base de datos.

### 2. Baja de Empleados
* Selecciona la opción `2` en el menú principal.
* Ingresa el ID del empleado que deseas eliminar.
* Verifica que el mensaje de confirmación se muestre y que el registro desaparezca de la base de datos.

### 3. Consulta de Empleados
* Selecciona la opción `3` en el menú principal.
* Elige entre las subopciones para listar todos los empleados o buscar por cargo.
* Verifica que la información mostrada sea consistente con los datos en la base de datos.

### 4. Modificación de Empleados
* Selecciona la opción `4` en el menú principal.
* Ingresa el ID del empleado y los nuevos datos.
* Verifica que los cambios se reflejen en la base de datos.

## Supuestos Considerados

### 1. Validaciones de Entrada
* Se supone que el usuario proporcionará datos válidos (e.g., fechas en formato correcto y números para el salario).

### 2. Conexión Activa a la Base de Datos
* Se asume que la base de datos estará en ejecución y accesible durante la operación de la aplicación.

### 3. Estructura Previa de la Base de Datos
* La base de datos tiene las tablas necesarias con las columnas correspondientes al objeto `Empleado`.

### 4. Persistencia Correcta
* Se asume que la configuración de `persistence.xml` y las dependencias de JPA están correctamente definidas.

### 5. Acceso Local
* Se supone que la aplicación se ejecutará localmente y no en un entorno distribuido o en la nube.
