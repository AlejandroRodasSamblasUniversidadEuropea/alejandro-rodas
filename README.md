# Proyecto Java Maven - [Gestión de Pedidos]

Este proyecto es una plantilla base para trabajar con aplicaciones Java utilizando Maven. Ahora incluye un sistema de gestión de pedidos con creación, búsqueda, edición parcial y borrado de pedidos, además de cálculo de totales en EUR y USD con tipo de cambio real.

## Tabla de Contenidos

- [Introducción](#introducción)
- [Requisitos](#requisitos)
- [Configuración del Entorno](#configuración-del-entorno)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Funcionalidades](#funcionalidades)
- [Compilación y Ejecución](#compilación-y-ejecución)
- [Contribución](#contribución)
- [Licencia](#licencia)

---

## Introducción

Este proyecto permite gestionar pedidos de artículos mediante una interfaz gráfica. Los pedidos se cargan desde un fichero `orders.json` y se guardan automáticamente tras cualquier modificación. Permite ver los totales en euros y en dólares utilizando el tipo de cambio real EUR → USD.

---

## Requisitos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

- **Java 8+** (JDK)
- **Maven** ([https://maven.apache.org/install.html](https://maven.apache.org/install.html))
- **IDE recomendada:** Visual Studio Code, IntelliJ IDEA o Eclipse.

---

## Configuración del Entorno

### 1. Instalar Java
Verifica tu versión de Java:
```bash
java -version

2. Instalar Maven

Verifica Maven:

mvn -version

3. Configurar el IDE

Asegúrate de que tu IDE tenga soporte para Java y Maven, y que la carpeta src esté marcada como fuente de código.

Estructura del proyecto
[PY_TEST_UNITARIOS]/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   ├── controller/
│                   │   └── OrderController.java
│                   ├── model/
│                   │   ├── Main.java
│                   │   ├── Order.java
│                   │   ├── Article.java
│                   │   └── OrderRepository.java
│                   ├── service/
│                   │   ├── ExchangeService.java
│                   │   └── OrderService.java
│                   └── view/
│                       └── OrderView.java
├── data/
│   └── orders.json
├── pom.xml
└── README.md

Funcionalidades

Visualizar lista de IDs de pedidos al iniciar la aplicación.

Buscar un pedido por ID y mostrar sus detalles.

Crear un nuevo pedido mediante formulario. Validación de ID único y persistencia en orders.json.

Borrar un pedido mediante botón, actualizando memoria y JSON.

Editar cantidad y descuento de los artículos de un pedido existente.

Mostrar totales del pedido en EUR y USD usando tipo de cambio real de exchangerate.host.

Persistencia automática: todos los cambios se reflejan en orders.json.

Compilación y ejecución

Compila el proyecto:

mvn clean install


Ejecuta la aplicación:

mvn exec:java -Dexec.mainClass="com.example.model.Main"


Nota: Asegúrate de que orders.json esté en la carpeta data/. Si no existe, la aplicación lo crea automáticamente.

Contribución

Para contribuir al proyecto:

Haz un fork del repositorio.

Crea una nueva rama:

git checkout -b feature-nueva-funcionalidad


Realiza tus cambios y haz commit:

git commit -am "Agregué nueva funcionalidad"


Empuja tus cambios:

git push origin feature-nueva-funcionalidad


Crea un pull request.

Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.