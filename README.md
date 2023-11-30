# Script Nivel 3

Script para subir a nivel 3 una lista de ciudadanos.

### Resumen
  - Carga los ciudadanos un un CSV enviado por el área de Licencias de Conducir
  - Comprueba por número de documento si existe en miba 1 . 
  - Si no existe en miba 1 lo reporta en el archivo notFoundL1
  - Si existe en miba1 comprueba que existe en L2
  - Si no existe en L2 lo reporta en el archivo notFoundL2 ( POR AHORA , quiza se inserte el citizen)
  - Si existe en L2 le sube el nivel de validación a 3 y lo audita.
  - Para subir el nivel Genera el archivo SQL de insert llamado CITIZEN_LEVELS_X (*)
  - Para auditar genera el archivo SQL de insets en ONSITE_VALIDATION_X (*)
  - Para ** exportar reporte a TAD**  ver la query en  /resource/querys  

(*) X el el numero de archivo ya que lo particiona para limitar la cantidad de sentencia a correr en un solo script.

### Aplicación
  - Apache Maven 3.5.3 [ https://maven.apache.org/ ] para gestión de paquetes
  - Java version [ https://www.java.com/es/ ]: 1.8 como lenguaje servidor
  - Oracle [ https://www.oracle.com/es/index.html ] 12.1.1.0 como motor de Base de Datos
  - Spring Boot [https://spring.io/projects/spring-boot ] como Framework de Aplicación
  - JDBC Template de Spring Boot.

 
## Uso

### Compilar
```
mvn install
```
### Properties 

Se debe tener un archivo de properties llamado **application.properties**  en un PATH_X .

#### El archivo debe contener : 

```
spring.datasource.jdbcUrl= conexión a Login2
spring.datasource.username= conexión a Login2
spring.datasource.password= conexión a Login2
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```
```
spring.second-datasource.jdbcUrl = conexión a miba1
spring.second-datasource.username = conexión a miba1
spring.second-datasource.password = conexión a miba1
spring.second-datasource.driver-class-name= org.postgresql.Driver
```
Para las siguientes properties Ver documentación de Login2 para la tabla ONSITE_VALIDATION , los siguientes son a modo de ejemplo.
```
gcba.id = 132654asdzfas123-asd123
gcba.cuilGcba = 27777777
gcba.siteId = 19
gcba.reasonId = 4
gcba.firstName = script first name
gcba.lastName = script last name
gcba.emailGcba = script@script.com
```

```
file.input = C:\\Users\\User\\eclipse-workspace\\nivel3\\src\\main\\resources\\input\\lic.csv
folder.output = C:\\Users\\User\\eclipse-workspace\\nivel3\\src\\main\\resources\\output\\
max.sql.sentences = 5
```

### Correr el jar 
 
```
java -jar target\nivel3-0.0.1-SNAPSHOT.jar PATH_1 PATH_2 PATH_3 PATH_4 --spring.config.location=file:///PATH_5
```

#### Los PATHs son :
 ```
PATH_1 Archivo con CSV de licencias. 
PATH_2 Archivo donde escribir los citizen no encontrados en L1 (*)
PATH_3 Archivo donde escribir los citizen no encontrados en L2 (*)
PATH_4 Archivo donde escribir los citizen encontrados en L2 (*)
PATH_5 Path al Archivo application.properties (sin el nombre del archivo)
```
(*) si no existe el archivo los crea, si existe entonces le suma filas a lo que tenia.

#### Ejemplos : 
```
PATH_1 C:\\Users\\User\\input\\lic.csv
PATH_2 C:\\Users\\User\\output\\notFoundL1.csv
PATH_3 C:\\Users\\User\\output\\notFoundL2.csv
PATH_4 C:\\Users\\User\\output\\foundL2.csv
PATH_5 file:///Users/User/proyectos/script-nivel3/
```
