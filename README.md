Se presenta un proyecto de Microservicios utilizando SpringBoot, en el cual se implementaron pruebas unitarias y de integracion.
Se realizaron 3 etapas conformadas por: Toma de requisitos y Modelado, Desarrollo y finalmente Testing, esto esta documentado en los archivos subidos en Exp1, Exp2, Exp 3 respectivamente dento de la carpeta MiProyecto.


A continuacion se presentan los diagramas de la etapa de modelado: 

UML:

Diagrama Administrador: https://app.diagrams.net/#G1GLvC1tDz6Xb5K49j2EDjwxZytfpPOm8e#%7B%22pageId%22%3A%22kb6ZePDwfS2-X3zEc-At%22%7D

Diagrama Casos de uso: https://app.diagrams.net/#G1hH6rFKSQ_yMEIBNS8sUGWmVzbEax35yo#%7B%22pageId%22%3A%22B-_QwJC5fJAibyvrJhdR%22%7D

Diagrama Cliente: https://app.diagrams.net/#G1V9jYvV-w9qj3lHbH1fdvYx_5Ooyu-nJa#%7B%22pageId%22%3A%22B1Jo4yszvs32Bjyr3TSQ%22%7D)

Diagrama Empleado: https://app.diagrams.net/#G1zKbWCcuwnECDyKfMm_nWiMFadrp9et2Z#%7B%22pageId%22%3A%22keIqFoi1jQGkwDnpmBmy%22%7D

Diagrama Gerente: https://app.diagrams.net/#G1JPDd7Kc8mlG-Pvt3Ta9xogVdOow2Yj5U#%7B%22pageId%22%3A%221KN0IobEHLXAVtjekWCF%22%7D

Diagrama de Clases: https://app.diagrams.net/#G1gtGFLHiCMuTkM6GmU99b8rBOdEc5Vz1H#%7B%22pageId%22%3A%22oYXu4q9bmDiaMgMdBmnN%22%7D

Diagrama Logistica: https://app.diagrams.net/#G1GxhK2SXJmngrIjnDaFeOr4tvuGjvVOxo#%7B%22pageId%22%3A%22UQyInc_Nd8kPoLNxdH2F%22%7D

Diagrama Microservicios: https://app.diagrams.net/#G1-h0m6serXC1OTQd2snybM_QOp27iFves#%7B%22pageId%22%3A%22XaMV_nspE7f4hmI7BC1B%22%7D

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

Aca se muestran los inicios del proyecto, en la etapa de desarrollo. El cual luego fue optimizado:

Hola, en la carpeta MiProyecto se encuentran las 3 experiencias, en la Experiencia 2, el archivo que dice perfulandia es el microservicio USUARIOS, y los otros 2 tienen los nombres bien asignados y en el archivo scripts-sql estan los endpoints.

README

//ENDPOINTS USUARIO (1ER MICROSERVICIO)

-Pagina web GET: http://localhost:8080/cliente

-GET CLIENTE (ID): http://localhost:8080/clientes/101

-POST ADMINISTRADOR (ID): http://localhost:8080/administradores { "nombre": "Matías", "apellido": "González", "email": "matias.gonzalez@example.com" }

-PUT ENCARGADO DE LOGISTICA: http://localhost:8080/encargados-logistica/504 { "nombre": "Isidora", "apellido": "Rivera", "email": "isidora.fuentes@example.com" }

-DELETE ENCARGADO DE VENTAS: http://localhost:8080/encargados-ventas/401

///////////////////////////////////////////////////////////////////////////////////////

//ENDPOINTS PRODUCTOS (2DO MICROSERVICIO)

-Pagina web GET: http://localhost:8080/productos

-GET PRODUCTOS (ID): http://localhost:8080/api/productos/1

-POST PRODUCTOS: http://localhost:8080/api/productos { "nombre": "Auriculares XBeat", "descripcion": "Auriculares inalámbricos con cancelación de ruido", "precio": 45990 }

-PUT PRODUCTOS: http://localhost:8080/api/productos/1

{ "nombre": "Lámpara flexible pro", "descripcion": "Lámpara LED con brazo flexible", "precio": 14990.0 }

-DELETE PRODUCTOS: http://localhost:8080/api/productos/2

////////////////////////////////////////////////////////////////////////////

//ENDPOINTS SUCURSALES (3ER MICROSERVICIO)

-Pagina web GET: http://localhost:8080/sucursales

-GET SUCURSALES (ID): http://localhost:8080/api/sucursales/1

-POST SUCURSALES: http://localhost:8080/api/sucursales { "nombre": "Sucursal Las Condes", "direccion": "Av. Apoquindo 4567" }

-PUT SUCURSALES: http://localhost:8080/api/sucursales/1 { "nombre": "Sucursal Santiago", "direccion": "Av. Pajaritos 2067" }

-DELETE SUCURSALES: http://localhost:8080/api/sucursales/6

////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////
En esta etapa el proyecto posee una buena base backend, se utiliza la arquitectura de Microservicios. Se entregan los paths de los nuevos microservicios implementados. 
Y se implementó testing (Pruebas Unitarias y de integración).

Hola, PATHS DE LOS 3 MICROSERVICIOS DE LA EXPERIENCIA 3:

VISTAS HTML:

http://localhost:8080/inventarios
http://localhost:8080/pagos
http://localhost:8080/pedidos



PATH DE SWAGGER

http://localhost:8080/swagger-ui/index.html


Inventario PATHS


GET 
http://localhost:8080/api/inventario

GET BY ID
http://localhost:8080/api/inventario/1

DELETE 
http://localhost:8080/api/inventario/1

POST

http://localhost:8080/api/inventario

{
   
    "nombre": "Tecladillo ",
    "descripcion": "Teclado 111",
    "precio": 5000.0
}


PUT

http://localhost:8080/api/inventario/1

{
   
    "nombre": "Tecladillo ",
    "descripcion": "Teclado 111",
    "precio": 10000.0
}




/////////////////////////////////////////////////////////			

PATHS PAGOS

GET

http://localhost:8080/api/pagos



GET BY ID

http://localhost:8080/api/pagos/1

DELETE

http://localhost:8080/api/pagos/1


POST

http://localhost:8080/api/pagos

{
   
    "nombre": "Audifonos ",
    "descripcion": "Audifonos ESPECIAL",
    "precio": 5000.0
}


PUT
http://localhost:8080/api/pagos/1

{
   
    "nombre": "Audifonos ",
    "descripcion": "Audifonos ESPECIAL",
    "precio": 10000.0
}

////////////////////////////////////////////////////////////////

PEDIDOS PATHS

GET 

http://localhost:8080/api/pedidos

GET BY ID

http://localhost:8080/api/pedidos/1

DELETE

http://localhost:8080/api/pedidos/1

POST 

http://localhost:8080/api/pedidos

{
   
    "nombre": "Audifonos 33 ",
    "descripcion": "Audifonos ESPECIAL",
    "precio": 5000.0
}

PUT

http://localhost:8080/api/pedidos/1


{
   
    "nombre": "Audifonos 33 ",
    "descripcion": "Audifonos ESPECIAL",
    "precio": 10000.0
}
