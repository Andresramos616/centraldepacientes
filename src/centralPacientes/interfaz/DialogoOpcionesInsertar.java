/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�as de la Informaci�n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Di�logo con las opciones para realizar la inserci�n de un paciente
 */
public class DialogoOpcionesInsertar extends JDialog {

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la ventana principal de la aplicaci�n
     */
    private InterfazCentralPacientes principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones
     */
    private PanelOpcionesInsertar panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo
     */
    public DialogoOpcionesInsertar(InterfazCentralPacientes ventana) {
        super(ventana, true);
        principal = ventana;
        setLayout(new BorderLayout());
        setResizable(false);
        setTitle("Central de Pacientes");
        setPreferredSize(new Dimension(339, 197));

        // Panel con las opciones
        panelOpciones = new PanelOpcionesInsertar(this);
        add(panelOpciones, BorderLayout.NORTH);

        pack();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cancela la adici�n del paciente cerrando el di�logo
     */
    public void cancelar() {
        dispose();
    }

    /**
     * Muestra el di�logo para proseguir con la adici�n del paciente
     */
    public void continuarAdicionPaciente() {
        try {
            int forma = panelOpciones.darFormaInsercion();
            int codigo = panelOpciones.darCodigoPaciente();
            dispose();
            principal.mostrarDialogoInsertarPaciente(forma, codigo);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El c�digo del paciente con relaci�n al cual se va a realizar la adici�n debe ser un valor num�rico", "Adici�n Pacientes", JOptionPane.ERROR_MESSAGE);
        }
    }

}
