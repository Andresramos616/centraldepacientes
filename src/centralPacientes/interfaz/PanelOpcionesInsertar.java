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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones para la inserción de un paciente
 */
public class PanelOpcionesInsertar extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private final static String ANTES = "Antes";

    private final static String DESPUES = "Despues";

    private final static String PRIMERO = "Primero";

    private final static String ULTIMO = "Ultimo";

    private final static String CONTINUAR = "Continuar";

    private final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al diálogo en el que se encuentra el panel
     */
    private DialogoOpcionesInsertar dialogo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Opción para agregar el paciente de primero
     */
    private JRadioButton botonPrimero;

    /**
     * Opción para agregar el paciente de ultimo
     */
    private JRadioButton botonUltimo;

    /**
     * Opción para agregar el paciente antes de otro paciente
     */
    private JRadioButton botonAntes;

    /**
     * Opción para agregar el paciente después de otro paciente
     */
    private JRadioButton botonDespues;

    /**
     * Campo para insertar el código del paciente antes del que se va a realizar la adición
     */
    private JTextField textoAntes;

    /**
     * Campo para insertar el código del paciente después del que se va a realizar la adición
     */
    private JTextField textoDespues;

    /**
     * Grupo para los botones del género
     */
    private ButtonGroup grupo;

    /**
     * Botón para continuar con la adición
     */
    private JButton botonContinuar;

    /**
     * Botón para cancelar la adición
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param diag Dialogo Opciones a insertar
     */
    public PanelOpcionesInsertar(DialogoOpcionesInsertar diag) {
        dialogo = diag;
        setBorder(new TitledBorder("Opciones para agregar el Paciente"));

        setLayout(new GridBagLayout());

        // Botón para agregar de primero
        botonPrimero = new JRadioButton("Al comienzo");
        botonPrimero.addActionListener(this);
        botonPrimero.setActionCommand(PRIMERO);
        botonPrimero.setSelected(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(botonPrimero, gbc);

        // Botón para agregar de último
        botonUltimo = new JRadioButton("Al final");
        botonUltimo.addActionListener(this);
        botonUltimo.setActionCommand(PRIMERO);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(botonUltimo, gbc);

        // Botón para agregar antes de otro paciente
        botonAntes = new JRadioButton("Antes del paciente con código");
        botonAntes.addActionListener(this);
        botonAntes.setActionCommand(ANTES);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(botonAntes, gbc);

        textoAntes = new JTextField("");
        textoAntes.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridwidth = 2;
        add(textoAntes, gbc);

        // Botón para agregar después de otro paciente
        botonDespues = new JRadioButton("Después del paciente con código");
        botonDespues.addActionListener(this);
        botonDespues.setActionCommand(DESPUES);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.BOTH;
        add(botonDespues, gbc);

        textoDespues = new JTextField("");
        textoDespues.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        add(textoDespues, gbc);

        // Grupo para los botones
        grupo = new ButtonGroup();
        grupo.add(botonAntes);
        grupo.add(botonDespues);
        grupo.add(botonPrimero);
        grupo.add(botonUltimo);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(panelBotones, gbc);

        // Botón para realizar la adición
        botonContinuar = new JButton();
        botonContinuar.addActionListener(this);
        botonContinuar.setActionCommand(CONTINUAR);
        botonContinuar.setIcon(new ImageIcon("data/continuar.gif"));
        botonContinuar.setToolTipText("Continuar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);
        panelBotones.add(botonContinuar, gbc);

        // Botón para cancelar la adición
        botonCancelar = new JButton();
        botonCancelar.addActionListener(this);
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.setIcon(new ImageIcon("data/cancelar.gif"));
        botonCancelar.setToolTipText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelBotones.add(botonCancelar, gbc);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Deshabilita los campos del panel
     */
    private void deshabilitarTextos() {
        textoAntes.setEnabled(false);
        textoDespues.setEnabled(false);
    }

    /**
     * Limpia los campos del panel
     */
    private void limpiarTextos() {
        textoAntes.setText("");
        textoDespues.setText("");
    }

    /**
     * Retorna la forma de inserción seleccionada por el usuario
     *
     * @return La forma de inserción seleccionada
     */
    public int darFormaInsercion() {
        int forma = -1;
        if (botonAntes.isSelected()) {
            forma = InterfazCentralPacientes.ANTES;
        }
        else if (botonDespues.isSelected()) {
            forma = InterfazCentralPacientes.DESPUES;
        }
        else if (botonPrimero.isSelected()) {
            forma = InterfazCentralPacientes.COMIENZO;
        }
        else if (botonUltimo.isSelected()) {
            forma = InterfazCentralPacientes.FINAL;
        }

        return forma;
    }

    /**
     * Retorna el código del paciente con relación al cual se va a realizar la inserción
     *
     * @return El código del paciente con relación al cual se va a realizar la adición. Si la forma de adición es primero o último se retorna -1.
     */
    public int darCodigoPaciente() {
        int codigo = -1;
        if (botonAntes.isSelected()) {
            codigo = Integer.parseInt(textoAntes.getText());
        }
        else if (botonDespues.isSelected()) {
            codigo = Integer.parseInt(textoDespues.getText());
        }
        return codigo;
    }

    /**
     * Manejo de eventos de los botones
     *
     * @param e Evento que generó la acción - e != null.
     */
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if (comando.equals(CONTINUAR)) {
            dialogo.continuarAdicionPaciente();
        }
        else if (comando.equals(CANCELAR)) {
            dialogo.cancelar();
        }
        else if (comando.equals(PRIMERO)) {
            deshabilitarTextos();
            limpiarTextos();
        }
        else if (comando.equals(ULTIMO)) {
            deshabilitarTextos();
            limpiarTextos();
        }
        else if (comando.equals(ANTES)) {
            deshabilitarTextos();
            limpiarTextos();
            textoAntes.setEnabled(true);
        }
        else if (comando.equals(DESPUES)) {
            deshabilitarTextos();
            limpiarTextos();
            textoDespues.setEnabled(true);
        }
    }

}
