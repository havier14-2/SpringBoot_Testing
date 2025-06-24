Hola profe, en el repositorio, el archivo que dice perfulandia es el microservicio USUARIOS, y los otros 2 tienen los nombres bien asignados y
en el archivo scripts-sql estan los endpoints.

Integrantes: Javier Albornoz, Vicente Hormazabal, Diego neira.

README

//ENDPOINTS USUARIO (1ER MICROSERVICIO)

-Pagina web GET: http://localhost:8080/cliente

-GET CLIENTE (ID): http://localhost:8080/clientes/101

-POST ADMINISTRADOR (ID): http://localhost:8080/administradores
{
  "nombre": "Matías",
  "apellido": "González",
  "email": "matias.gonzalez@example.com"
}

-PUT ENCARGADO DE LOGISTICA: http://localhost:8080/encargados-logistica/504
{
  "nombre": "Isidora",
  "apellido": "Rivera",
  "email": "isidora.fuentes@example.com"
}

-DELETE ENCARGADO DE VENTAS: http://localhost:8080/encargados-ventas/401


////////////////////////////////////////////////////////////////////////////

//ENDPOINTS PRODUCTOS (2DO MICROSERVICIO)


-Pagina web GET: http://localhost:8080/productos

-GET PRODUCTOS (ID): http://localhost:8080/api/productos/1

-POST PRODUCTOS: http://localhost:8080/api/productos
{
  "nombre": "Auriculares XBeat",
  "descripcion": "Auriculares inalámbricos con cancelación de ruido",
  "precio": 45990
}

-PUT PRODUCTOS: http://localhost:8080/api/productos/1

{
  "nombre": "Lámpara flexible pro",
  "descripcion": "Lámpara LED con brazo flexible",
  "precio": 14990.0
}

-DELETE PRODUCTOS: http://localhost:8080/api/productos/2



////////////////////////////////////////////////////////////////////////////

//ENDPOINTS SUCURSALES (3ER MICROSERVICIO)


-Pagina web GET: http://localhost:8080/sucursales

-GET SUCURSALES (ID): http://localhost:8080/api/sucursales/1

-POST SUCURSALES: http://localhost:8080/api/sucursales
{
  "nombre": "Sucursal Las Condes",
  "direccion": "Av. Apoquindo 4567"
}

-PUT SUCURSALES: http://localhost:8080/api/sucursales/1
{
  "nombre": "Sucursal Santiago",
  "direccion": "Av. Pajaritos 2067"
}

-DELETE SUCURSALES: http://localhost:8080/api/sucursales/6
