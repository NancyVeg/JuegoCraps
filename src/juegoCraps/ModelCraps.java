package juegoCraps;

/**
 * ModelCraps apply craps rules
 * estado = 1 natural winner
 * estado = 2 craps looser
 * estado = 3 Establish punto
 * estado = 4 punto winner
 * estado = 5 punto looser
 * @author Nancy Vega
 * @version V.1.0.0 date 07/12/2021
 */
public class ModelCraps {
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String[] estadoToString;
    private int[] caras; //objeto de tipo vector

    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2]; //indicar tamaño de vector
        estadoToString = new String[2];
        flag = 0;
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void calcularTiro(){
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Establish game state according to estado attribute value
     */
    public void determinarJuego(){
        if(flag==0){
            if(tiro==7 ||  tiro==11){
                estado = 1;
            }
            else{
                if(tiro==3 || tiro==2 || tiro==12){
                    estado = 2;
                }
                else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;
                }
            }
        }
        else{
            //ronda punto
            rondaPunto();
        }

    }

    /**
     * Establish game state according to estado attribute value
     */
    private void rondaPunto() {
        if(tiro==punto){
            estado=4;
            flag=0;
        }
        else{
            if(tiro==7){
                estado=5;
                flag=0;
            }
            else{
                estado = 6;
            }
        }

    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado attribute value
     * @return
     */
    public String[] getEstadoToString() {
        switch (estado){
            case 1: estadoToString[0] = "Tiro de Salida = "+tiro;
                    estadoToString[1]="Sacaste natural, Ganaste!!";
                    break;
            case 2: estadoToString[0] = "Tiro de Salida = "+tiro;
                    estadoToString[1]="Sacaste Craps, Perdiste!!";
                    break;
            case 3: estadoToString[0] = "Tiro de Salida = "+tiro+"\n Punto = "+punto;
                    estadoToString[1]="Estableciste punto en "+punto+" continua lanzando"+"\ndebes" +
                    " volver a sacar este valor antes que un 7 o pierdes";
                    break;
            case 4: estadoToString[0] = "Tiro de Salida = "+punto+"\n Punto = "+punto+
                    "\n Valor del nuevo tiro es: "+tiro;
                    estadoToString[1]="Sacaste "+punto+" otra vez, Ganaste!!";
                    break;
            case 5: estadoToString[0] = "Tiro de Salida = "+punto+"\n Punto = "+punto+
                    "\n Valor del nuevo tiro es: "+tiro;
                    estadoToString[1]="Sacaste 7, perdiste :(";
                    break;
            case 6: estadoToString[0] = "Tiro de Salida = "+punto+"\n Punto = "+punto+
                    "\n Valor del nuevo tiro es: "+tiro;
                    estadoToString[1]="Tienes un punto en "+punto+", continua lanzando"+"\ndebes" +
                        " volver a sacar este valor antes que un 7 o pierdes";
                    break;

        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
