# API Visibility
Al solicitar al endpoint GET /api/v1/visibility se ejecuta el algoritmo para verificar
el stock y devolver un array con los productos ordenados por su sequence. Donde toma los valores de los productos, stock y size de
archivos csv que se encuentran en *resources*.

## Estructuras de datos utilizadas en el algoritmo

Para resolver el algoritmo se utilizaron las siguientes estructuras de datos:

Lista: Se utilizó una lista para almacenar los productos en el orden en que aparecen en la parrilla. Cada elemento de la lista contendrá información sobre el producto,
como su identificador y posición en la parrilla.

Map: Se utilizó un map (HashMap) para almacenar la información de las tallas de cada producto. La clave del map será el identificador de talla y el valor asociado será un
objeto que contiene información sobre si la talla es "back soon" y si es especial.

También se utilizó un map (HashMap) para almacenar la información del stock de cada talla. La clave del map será el identificador de talla y el valor asociado será
la
cantidad de unidades disponibles en almacén.

El uso de una lista para los productos es adecuado, ya que se permite mantener un orden específico de los productos en la parrilla (con base en su sequence). Además, las búsquedas por posición o secuencia son
más eficientes en una lista.

El uso de maps para las tallas y el stock permite un acceso rápido a la información relacionada con cada talla y facilita la comprobación de las condiciones de visibilidad. Al usar
los identificadores de talla como claves, se puede acceder rápidamente a los datos correspondientes a una talla específica.

## Complejidad temporal del algoritmo

Se puede considerar que el algoritmo tiene una complejidad de O(n), donde n es el número total de productos. Esto se debe a que se necesita
recorrer todos los productos para verificar las condiciones de visibilidad y generar la lista de identificadores de productos visibles. Los maps permiten un acceso rápido a los datos,
por lo que no afectan significativamente la complejidad del algoritmo.

Dado que se debe recorrer toda la lista de productos, no se puede mejorar la complejidad del algoritmo en términos de tiempo. Sin embargo, se pueden realizar optimizaciones en el código para
mejorar la eficiencia, como evitar la realización de operaciones innecesarias o reducir la cantidad de bucles anidados.