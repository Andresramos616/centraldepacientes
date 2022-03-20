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
package centralPacientes.interfaz;

import centralPacientes.mundo.Paciente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Diálogo para mostrar la información de un paciente
 */
public class DialogoMostrarInformarcionPaciente extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    private final static String CERRAR = "Cerrar";
    private final static String REGISTRAR_CAMBIOS = "Registrar Cambios";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazCentralPacientes principal;

    /**
     * El paciente del que se va a mostrar la información
     */
    private Paciente paciente;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para mostrar la información del paciente
     */
    private PanelInformacionPaciente panelInformacion;

    /**
     * Botón para cerrar el diálogo
     */
    private JButton botonCerrar;

    /**
     * Botón para actualizar la información médica
     */
    private JButton botonRegistrarCambios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     *
     * @param ventana    Referencia a la ventana principal - ventana!=null
     * @param elPaciente El paciente del que se va a mostrar la información - elPaciente!=null
     */
    public DialogoMostrarInformarcionPaciente(InterfazCentralPacientes ventana, Paciente elPaciente) {
        super(ventana, true);
        principal = ventana;
        paciente = elPaciente;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(298, 307));
        setTitle("Búsqueda Pacientes");
        setResizable(false);

        // Panel para ingresar la información
        panelInformacion = new PanelInformacionPaciente();
        GridBagConstraints gbc = new GridBagConstraints();
        panelInformacion.cambiarInformacionComboClinicas(principal.darClinicasCentral());
        panelInformacion.deshabilitarComponentes();
        panelInformacion.habilitarAreaInformacionMedica();
        panelInformacion.mostrarInformacionPaciente(paciente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelInformacion, gbc);

        // Panel con el botón de cerrar y actualizar
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        botonCerrar = new JButton(CERRAR);
        botonCerrar.setActionCommand(CERRAR);
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);
        botonRegistrarCambios = new JButton(REGISTRAR_CAMBIOS);
        botonRegistrarCambios.setActionCommand(REGISTRAR_CAMBIOS);
        botonRegistrarCambios.addActionListener(this);
        panelBotones.add(botonRegistrarCambios, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelBotones, gbc);

        pack();

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos de los botones
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals(CERRAR)) {
            dispose();
        }
        else if (comando.equals(REGISTRAR_CAMBIOS)) {
            String nuevaInformacion = panelInformacion.darInformacionMedicaPaciente();

            if (nuevaInformacion != null && !nuevaInformacion.equals("")) {
                paciente.cambiarInformacionMedica(nuevaInformacion);
                JOptionPane.showMessageDialog(this, "La información médica del paciente ha sido actualizada", "Búsqueda de Pacientes", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "La información médica del paciente no puede ser vacía", "Búsqueda de Pacientes", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
