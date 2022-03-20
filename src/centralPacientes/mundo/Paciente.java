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

/**
 * Esta clase representa un paciente del hospital <br>
 */
public class Paciente {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para representar a un Hombre
     */
    public final static int HOMBRE = 1;

    /**
     * Constante para representar a una mujer
     */
    public final static int MUJER = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El código del paciente
     */
    private int codigo;

    /**
     * El nombre del paciente
     */
    private String nombre;

    /**
     * Clínica a la que asiste el paciente
     */
    private String clinica;

    /**
     * La información médica del paciente
     */
    private String informacionMedica;

    /**
     * Sexo del paciente
     */
    private int sexo;

    /**
     * El siguiente paciente de la lista
     */
    private Paciente siguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un paciente
     */
    public Paciente(int cod, String nom, String clin, String infoMed, int sex) {
        codigo = cod;
        nombre = nom;
        clinica = clin;
        informacionMedica = infoMed;
        sexo = sex;
        siguiente = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el código del paciente
     */
    public int darCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre del paciente
     */
    public String darNombre() {
        return nombre;
    }

    /**
     * Retorna la clínica asignada al paciente
     */
    public String darClinica() {
        return clinica;
    }

    /**
     * Retorna la información médica del paciente
     *
     * @return La información médica del paciente
     */
    public String darInformacionMedica() {
        return informacionMedica;
    }

    /**
     * Retorna el sexo del paciente
     */
    public int darSexo() {
        return sexo;
    }

    /**
     * Retorna el siguiente paciente de la lista
     */
    public Paciente darSiguiente() {
        return siguiente;
    }

    /**
     * Cambia el paciente que le sigue al actual
     */
    public void cambiarSiguiente(Paciente pac) {
        siguiente = pac;
    }

    /**
     * Desconecta el paciente que le sigue en la lista al paciente actual. <br>
     */
    public void desconectarSiguiente() {
        siguiente = siguiente.siguiente;
    }

    /**
     * Inserta el paciente después del actual. <br>
     */
    public void insertarDespues(Paciente pac) {
        pac.siguiente = siguiente;
        siguiente = pac;
    }

    /**
     * Retorna una cadena con la información del paciente
     */
    public String toString() {
        return "[ " + codigo + " ]: " + nombre;
    }

    /**
     * Cambia la información médica del paciente
     */
    public void cambiarInformacionMedica(String informacion) {
        informacionMedica = informacion;
    }
}
