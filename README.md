# **Entrevista HULK STORE**
El propósito del README es brindar a los desarrolladores involucrados en el proyecto XXX elaborado en JEE  + JSF + JBOSS/WildFly, una guía que permita configurar las herramientas necesarias para su desarrollo, pruebas, despliegue y mantenimiento del sistema.


# Descripción
La empresa ha decidido realizar un emprendimiento, el cual consiste en la creación de una tienda de prductos
para sus empleados, aquí podrás encontrar camisestas, vasos, comics, juguetes y accesorios basads en los superhéroes
de MArvel y DC comics, incluso algunos alternativos creados por la comunidad

Actualmente el contrl es llevado en licros, con un pequeño sistema kardex que contros los productos del inventario,
los cuales se incrementan el ingreso de nuevos productos o disminuyen con la venta de los mismos.
Est ha funcionado, pero se ha visto la necesidad de sistematizar esta información con el fin de hacer un control óptimo y hacer más eficiente el servicio en la tienda.

Por lo anterior tenemos una misión para ti, desarrollar la fase I de este proyecto, la cual consiste en crear un sistema kardex que controle nuestros productos, incremente 
con con el registro registro de nuevos productos y disminuya con la salida de los mismos, además que nuestro vendedor realiza cambios de esas cantidades a través de las 
interfaces correspondientes. Importante aclarar que cuando un producto no tenga stock no permita realizar monivienos con él.
# Requisitos

- [JBOSS 7.2 ] / [WildFly 11 ](https://www.wildfly.org/news/2017/10/24/WildFly11-Final-Released/) 
- [JDK 1.8 ](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
  - __Note__:
    El proyecto esta desarollado en Win7, Se debe configurar las variables de entorno:
      ```
         JAVA_HOME  (path jdk)\jdk1.8.0_181
         Path ....;%JAVA_HOME%\bin;....
     ```
- [Apache Maven 3.6.0 ](https://maven.apache.org/docs/3.6.0/release-notes.html)
  - __Note__:
    El proyecto esta desarollado en Win7, Se debe configurar las variables de entorno:
      ```
         M2_HOME  (path mvn)
         Path ....;%M2_HOME%\bin;....
     ```
- [Archivo inicial ](https://github.com/pvillarruel/Hulk-Store/blob/main/tienda.xml) tienda.xml

# Aplicacion
La aplicacion es un monolito basado en MVN.

## Front End: my-app
### Estructura
  - be-tienda-ejb
  - \_\src\_\_
  - \_\_\adaptadores\_\_\
  - \_\_\dao\_\_\
  - \_\_\entity\_\_\
  - \_\_\enums\_\_\
  - \_\_\utils\_\_\
  - \_\test\_\_
  - be-tienda-web
  - \_\Web Page\_\_
  - \_\_\src\_\_\
  - \_\generic\_\_
  - \_\bean\_\_

### Comandos para construir proyecto sin ID JAVA Win7

- Ingresar a carpeta raiz de proyecto

- Instalar dependencias
  ```
  mvn install
  ```


### Prerequisitos para correr el app 

Para correr la aplicacion se debera tener registradas las siguientes variables de entorno.

- JAVA_HOME (Direccion donde se encuentra el jdk)
- M2_HOME (Direccion donde se encuentra el mvn)
- En la clase Constantes editar la propiedad  `PATHCARGA`
```
public static final String PATHCARGA = "path/tienda.xml";
- __Note__:
    `path` debe ser la ruta donde coloquen el archivo xml 
```

- Usuarios
```
Nombre        Usuario    Clave     

Compador 1   COMPRADOR  COMPRADOR

Vendedor 1   VENDEDOR   VENDEDOR

```

### Testing Backend con selenium

Las pruebas se encontran creadas con la libreria java  `selenium`. Para ejecutar las pruebas se debe de ejecutar `Test File` de las siguientes clases, en el artefacto `be-tienda-ejb`.
```
LecturaArchivoXML
OperacionesTienda
```


## DEMO (WIP)
Si quieres revisar el demo de este proyecto deberas desplegar en Jboss o WildFly y ejecutar en el navegador dependiendo la configuracion del sersvidor(https://xxx.xxx.xxx.xxx:xxxx/tienda/)

## PRUEBAS (PDF)
Si desea tener una guía de uso del sistema por favor acceder al archivo Pruebas.pdf.

## Contribuciones
Si desea agregar contribuciones monetarias aguegue en la cuenta de [paypal-blablo](https://paypal.com) ayudará para tomar el cafecito de las 19:00 :D 

## Autor
* **Villarruel Pablo** - *Documentación y Desarrollo* - [blablo-pvillaruel](https://github.com/pvillarruel)
