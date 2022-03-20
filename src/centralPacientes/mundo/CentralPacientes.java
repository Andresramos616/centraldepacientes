/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa una central en la que se maneja una lista de pacientes
 */
public class CentralPacientes {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primer paciente de la lista
     */
    private Paciente primero;

    /**
     * Número de pacientes en la central
     */
    private int numPacientes;

    /**
     * Vector de clínicas manejadas por la central
     */
    private ArrayList<String> listaClinicas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva central sin pacientes y con una lista predefinida de clínicas
     */
    public CentralPacientes() {
        primero = null;
        numPacientes = 0;

        listaClinicas = new ArrayList<>();
        listaClinicas.add("Clínica del Country");
        listaClinicas.add("Clínica Palermo");
        listaClinicas.add("Clínica Reina Sofía");
        listaClinicas.add("Clínica El Bosque");
        listaClinicas.add("Clínica San Ignacio");
        listaClinicas.add("Otra");
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el número de pacientes de la clínica
     *
     * @return El número de pacientes de la clínica
     */
    public int darNumeroPacientes() {
        return numPacientes;
    }

    /**
     * Adiciona un paciente al principio de la lista
     *
     * @param pac El paciente a ser agregado al comienzo de la lista. <br>
     *            pac!=null y no existe un paciente con código igual a pac.codigo
     */
    public void agregarPacienteAlComienzo(Paciente pac) {
        if (primero == null) // Crea la cabeza si no existe
        {
            primero = pac;
        }
        else
        // Realiza la adición antes del paciente que está al inicio de la lista
        {
            pac.cambiarSiguiente(primero);
            primero = pac;
        }
        numPacientes++;
    }

    /**
     * Adiciona un paciente al final de la lista. Si la lista está vacía el paciente queda de primero
     *
     * @param pac El paciente a ser agregado al final la lista. <br>
     *            pac!=null y no existe un paciente con código igual a pac.codigo
     */
    public void agregarPacienteAlFinal(Paciente pac) {
        if (primero == null) // Si la cabeza no existe adiciona de primero el paciente
        {
            primero = pac;
        }
        else {
            // Busca el último paciente de la lista
            Paciente p = localizarUltimo();

            // Adiciona el paciente después del último paciente de la lista
            p.insertarDespues(pac);
        }
        numPacientes++;
    }

    /**
     * Adiciona un paciente a la lista de pacientes antes del paciente con el código especificado. <br>
     */
    public void agregarPacienteAntesDe(int cod, Paciente pac) throws NoExisteException {
        if (primero == null) {
            throw new NoExisteException(cod);
        }
        else if (cod == primero.darCodigo()) {
            pac.cambiarSiguiente(primero);
            primero = pac;
        }
        else {
            Paciente anterior = localizarAnterior(cod);
            if (anterior == null) {
                throw new NoExisteException(cod);
            }
            anterior.insertarDespues(pac);
        }
        numPacientes++;
    }

    /**
     * Adiciona un paciente a la lista de pacientes después del paciente con el código especificado.
     */
    public void agregarPacienteDespuesDe(int cod, Paciente pac) throws NoExisteException {
        Paciente anterior = localizar(cod);

        if (anterior == null) // Si no existe el paciente después del que se desea realizar la adición se arroja la excepción
        {
            throw new NoExisteException(cod);
        }
        else
        // Se adiciona el paciente
        {
            anterior.insertarDespues(pac);
        }
        numPacientes++;
    }

    /**
     * Busca el paciente con el código dado en la lista de pacientes.
     */
    public Paciente localizar(int codigo) {
        Paciente actual = primero;
        while (actual != null && actual.darCodigo() != codigo) {
            actual = actual.darSiguiente();
        }
        return actual;
    }

    /**
     * Busca el paciente anterior al paciente con el código especificado
     */
    public Paciente localizarAnterior(int cod) {
        Paciente anterior = null;
        Paciente actual = primero;

        while (actual != null && actual.darCodigo() != cod) {
            anterior = actual;
            actual = actual.darSiguiente();
        }

        return actual != null ? anterior : null;
    }

    /**
     * Retorna el último paciente de la lista
     */
    public Paciente localizarUltimo() {
        Paciente actual = primero;

        if (actual != null) {
            while (actual.darSiguiente() != null) {
                actual = actual.darSiguiente();
            }
        }
        return actual;
    }

    /**
     * Elimina el paciente con el código especificado.
     */
    public void eliminarPaciente(int cod) throws NoExisteException {
        if (primero == null) {
            throw new NoExisteException(cod);
        }
        else if (cod == primero.darCodigo()) {
            // El paciente es el primero de la lista
            primero = primero.darSiguiente();
        }
        else {
            // El paciente es un elemento intermedio de la lista
            Paciente anterior = localizarAnterior(cod);
            if (anterior == null) {
                throw new NoExisteException(cod);
            }
            anterior.desconectarSiguiente();
        }
        numPacientes--;
    }

    /**
     * Devuelve una lista con los pacientes de la central
     */
    public ArrayList<Paciente> darPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Paciente actual = primero;
        while (actual != null) {
            pacientes.add(actual);
            actual = actual.darSiguiente();
        }
        return pacientes;
    }

    /**
     * Retorna la lista de clínicas manejadas por la central
     */
    public ArrayList<String> darListaClinicas() {
        return listaClinicas;
    }

    /**
     * Retorna la longitud de la lista
     */
    private int darLongitud() {
        Paciente actual = primero;
        int longitud = 0;

        while (actual != null) {
            longitud++;
            actual = actual.darSiguiente();
        }
        return longitud;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Retorna la cantidad de hombres que hay en la lista
     */
    public int cantHombres() {
        Paciente actual = primero;
 int i =0;
        while (actual != null ){
            if (actual.darSexo()==1){
                actual = actual.darSiguiente();
                    i++;}

                else { actual = actual.darSiguiente();}

        }
        return i;
    }
    /**
     * Retorna la cantidad de mujeres que hay en la lista
     */
    public int cantMujeres() {
        Paciente actual = primero;
        int i =0;
        while (actual != null ){
            if (actual.darSexo()==2){
                actual = actual.darSiguiente();
                i++;}

            else { actual = actual.darSiguiente();}

        }
        return i;

    }

    /**
     * Método para la extensión3
     *
     * @return respuesta3
     */
    public String metodo3() {
        return "Respuesta 3";
    }

    /**
     * Método para la extensión4
     *
     * @return respuesta4
     */
    public String metodo4() {
        return "Respuesta 2";
    }

    /**
     * Método para la extensión2
     *
     * @return respuesta5
     */
    public String metodo5() {
        return "Respuesta 5";
    }
}
