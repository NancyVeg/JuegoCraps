package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view craps class
 * @autor Nancy Stella Vega - 2040216; nancy.vega@correounivalle.edu.co
 * @version v.1.0.0 date:30/11/2021
 */
public class GUI extends JFrame {
    private static final String MENSAJE_INICIO = "Bienvenido a Craps \n" //asi se define una constante
            + "Oprime el boton lanzar para iniciar el juego"
            + "\n Si tu tiro de salida es 7 u 11 ganas con natural"
            + "\n Si tu tiro de salida es 2, 3 o 12 pierdes con craps"
            + "\n Si sacas cualquier otro valor estableceras el punto"
            + "\n Estando en punto podras seguir lanzando los dados"
            + "\n pero ahora ganaras si sacas nuevamente el valor del punto "
            + "\n sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea resultadosDados, mensajesSalida;
    private JSeparator separador;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa para juego craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

         imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
         dado1 = new JLabel(imageDado);
         dado2 = new JLabel(imageDado);

         lanzar = new JButton("Lanzar");
         lanzar.addActionListener(escucha);

         panelDados = new JPanel();
         panelDados.setPreferredSize(new Dimension(300, 180));
         panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
         panelDados.add(dado1);
         panelDados.add(dado2);
         panelDados.add(lanzar);

         this.add(panelDados,BorderLayout.CENTER);

        mensajesSalida = new JTextArea(7,31);
        mensajesSalida.setText((MENSAJE_INICIO));
        //mensajesSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
         JScrollPane scroll = new JScrollPane(mensajesSalida);

         panelResultados = new JPanel();
         panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
         panelResultados.add(scroll);
         panelResultados.setPreferredSize(new Dimension(370,180));

         this.add(panelResultados, BorderLayout.EAST);

         resultadosDados = new JTextArea(4,31);
         separador = new JSeparator();
         separador.setPreferredSize(new Dimension(320,7));
         separador.setBackground(Color.RED);


    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[1]+".png"));
            dado2.setIcon(imageDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados Tiros "));
            panelResultados.add(resultadosDados);
            panelResultados.add(separador);
            panelResultados.add(mensajesSalida);
            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalida.setRows(4); //asignacion de nueva cantidad de filas para la ventana
            mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
            revalidate();
            repaint();
        }
    }
}
