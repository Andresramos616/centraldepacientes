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
 * Di�logo para mostrar la informaci�n de un paciente
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
     * El paciente del que se va a mostrar la informaci�n
     */
    private Paciente paciente;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para mostrar la informaci�n del paciente
     */
    private PanelInformacionPaciente panelInformacion;

    /**
     * Bot�n para cerrar el di�logo
     */
    private JButton botonCerrar;

    /**
     * Bot�n para actualizar la informaci�n m�dica
     */
    private JButton botonRegistrarCambios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo
     *
     * @param ventana    Referencia a la ventana principal - ventana!=null
     * @param elPaciente El paciente del que se va a mostrar la informaci�n - elPaciente!=null
     */
    public DialogoMostrarInformarcionPaciente(InterfazCentralPacientes ventana, Paciente elPaciente) {
        super(ventana, true);
        principal = ventana;
        paciente = elPaciente;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(298, 307));
        setTitle("B�squeda Pacientes");
        setResizable(false);

        // Panel para ingresar la informaci�n
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

        // Panel con el bot�n de cerrar y actualizar
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
    // M�todos
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
                JOptionPane.showMessageDialog(this, "La informaci�n m�dica del paciente ha sido actualizada", "B�squeda de Pacientes", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "La informaci�n m�dica del paciente no puede ser vac�a", "B�squeda de Pacientes", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
