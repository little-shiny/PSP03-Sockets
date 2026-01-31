# Tarea PSP03 
## Actividad 3.1: Juego "Adivina el número" (Puerto 2000)
En esta actividad, el servidor mantiene el "estado" del juego (el número secreto) y el cliente actúa como la interfaz de usuario.

### Código del Servidor

El servidor debe usar un `Random` para el número y un bucle que no termine hasta que el cliente acierte.

```java
// Fragmento clave del Servidor
int numeroSecreto = new Random().nextInt(101);
ServerSocket servidor = new ServerSocket(2000);
Socket cliente = servidor.accept();

DataInputStream entrada = new DataInputStream(cliente.getInputStream());
DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

int intento;
do {
    intento = entrada.readInt();
    if (intento < numeroSecreto) salida.writeUTF("Mayor");
    else if (intento > numeroSecreto) salida.writeUTF("Menor");
    else salida.writeUTF("Correcto");
} while (intento != numeroSecreto);

```

### Código del Cliente

El cliente necesita un `Scanner` para leer de la consola y enviar los datos al servidor.



## Actividad 3.2: Transferencia de Ficheros (Puerto 1500)

Aquí la clave es la gestión de archivos con la clase `File` y el envío de flujos de texto (puedes usar `BufferedReader` y `PrintWriter`).

### Lógica del Servidor

1. Escuchar en el puerto **1500**.
2. Leer el nombre del fichero enviado por el cliente.
3. Verificar existencia: `File fichero = new File(nombre);`.
4. Si existe, leer su contenido línea a línea y enviarlo. Si no, enviar "Error: Fichero no encontrado".

### Lógica del Cliente

1. Conectarse y enviar el nombre del archivo.
2. Recibir la respuesta. Si es contenido, imprimirlo; si es error, informarlo.
3. **Cerrar la conexión** inmediatamente después (requisito del enunciado).

### Tabla de diferencias técnicas

| Característica | Actividad 3.1 | Actividad 3.2 |
| --- | --- | --- |
| **Puerto** | 2000 | 1500 |
| **Tipo de flujo** | Datos simples (`int`, `String`) | Flujo de archivos / Texto |
| **Persistencia** | Bucle hasta acierto | Conexión y cierre tras envío |

