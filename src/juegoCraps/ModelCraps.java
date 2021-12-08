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
    private String estadoToString;
    private int[] caras; //objeto de tipo vector

    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2]; //indicar tamaño de vector
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
        if(tiro==7){
            estado=5;
            flag=0;
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
    public String getEstadoToString() {
        switch (estado){
            case 1: estadoToString="Sacaste natural, Ganaste!!";
                break;
            case 2: estadoToString="Sacaste Craps, Perdiste!!";
                break;
            case 3: estadoToString="Estableciste punto en "+punto+" continua lanzando"+"\n debes" +
                    " volver a sacar este valor antes que un 7 o pierdes";
                break;
            case 4: estadoToString="Sacaste "+punto+" otra vez, Ganaste!!";
                break;
            case 5: estadoToString="Sacaste 7, perdiste :(";
                break;

        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
