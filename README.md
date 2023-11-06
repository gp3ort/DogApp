# DogApp

¡Bienvenido al repositorio de la aplicación DogApp! Esta aplicación para Android, desarrollada en Kotlin utilizando Android Studio, está diseñada para ayudar a los usuarios a encontrar y adoptar adorables perros. Utiliza la API pública "Puppies" para proporcionar a los usuarios fotos e información sobre diversas razas de perros. 

# Grupo 3 
Integrantes:
- Lucas Funes @lucasmfunes94
- Patricio Simosis @psimosis
- Jonathan Caucota @JonaCaucota
- Sebastian Alfonso @SebaasTiAn04
- Guido Compagno @GuidoComp
  
## Características

### Pestañas de Navegación

La aplicación cuenta con un sistema de navegación fácil de usar con las siguientes pestañas:

- **Inicio**: Explora una lista de perros disponibles para su adopción.
- **Favoritos**: Visualiza y gestiona los perfiles de tus perros favoritos.
- **Adopciones**: Lleva un registro de los perros que has adoptado.
- **Crear Publicación**: Crea nuevas publicaciones de adopción, con la opción de crear otra después de guardar. Recibirás una notificación tipo "toast" al crear una publicación con éxito.

### Perfil de Usuario y Configuración

Accede a tu perfil de usuario y a la configuración de la aplicación a través de un menú desplegable que se activa deslizando de izquierda a derecha. Puedes alternar entre el modo claro y oscuro, y tu información de usuario incluye una imagen, número de teléfono y nombre.

### Exploración y Búsqueda de Perros

Como usuario, puedes explorar y filtrar las publicaciones de adopción de perros según los siguientes criterios:

- **Raza**: Filtra por raza o subraza.
- **Ubicación**: Filtra por ubicación, considerando todas las provincias de Argentina.
- **Fecha de Publicación**: Ordena las publicaciones por su fecha de publicación.
- **Barra de Búsqueda**: Busca fácilmente una raza o subraza específica.

Las opciones de filtro se pueden combinar, y hemos implementado vistas de estado vacío amigables en caso de que no se encuentren resultados después de aplicar los filtros.

### Detalles de la Publicación de Adopción

Cuando seleccionas una publicación de adopción de un perro, encontrarás detalles completos, que incluyen:

- **Raza y Subraza (si corresponde)**.
- **Nombre del Perro**.
- **Edad**.
- **Género** (Macho/Hembra).
- **Descripción**.
- **Peso**.
- **Observaciones** del cuidador del perro.

Desde esta vista detallada, puedes agregar o quitar perros de tus favoritos e iniciar el proceso de adopción. Los perros adoptados se marcan como "Adoptados" y ya no son visibles en la lista de inicio, pero se pueden encontrar en la pestaña "Adopciones".

## Diseño de la Aplicación y Personalización

La paleta de colores y el diseño general de la aplicación serán cuidadosamente diseñados por nuestro talentoso equipo. También puedes alternar entre el modo claro y oscuro en la configuración del perfil de usuario.

El diseño de la interfaz de usuario de los filtros y las sugerencias de búsqueda de raza y subraza se ha creado pensando en la facilidad de uso. Las sugerencias aseguran que los usuarios ingresen criterios válidos, reduciendo errores.

## Base de Datos

La lista de adopciones se obtiene de una base de datos bien estructurada que proporciona actualizaciones en tiempo real.

## Instalacion

Para comenzar con la aplicación, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en Android Studio.
3. Settear local properties apuntando al android SDK (Generalmente: sdk.dir=C\:\\Users\\{Username}\\AppData\\Local\\Android\\Sdk
)
4. Compila y ejecuta la aplicación en tu dispositivo Android o emulador.
