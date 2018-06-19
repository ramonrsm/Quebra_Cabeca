package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuleiroSlider {

    private static final int TAM_TABULEIRO = 9;
    private static int      containerVazio;

    private static int[][] camposValidos =
            {{1,3},
            {0,4,2},
            {1,5},
            {0,4,6},
            {1,3,7,5},
            {2,4,8},
            {3,7},
            {6,4,8},
            {7,5}};

    public static void InicializarSlider(TableLayout tableLayout){

        Random random = new Random();

        containerVazio = random.nextInt(TAM_TABULEIRO);

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout linearLayout = (LinearLayout) tableRow.getChildAt(j);

                if(linearLayout.getChildCount() > 0){

                    View view = linearLayout.getChildAt(0);

                    if(Integer.parseInt(view.getTag().toString()) == containerVazio){
                        linearLayout.removeViewAt(0);
                    }
                }
            }
        }
    }

    public static int ConsultarContainerVazio(){
        return containerVazio;
    }

    public static boolean PecasDisponiveis(int tag){

        boolean parar = false;

        for (int[] valores : camposValidos) {

            if(parar){
                break;
            }

            for (int valore : valores) {

                if (valore != tag) {
                    parar = true;
                    return parar;
                }
            }
        }

        return parar;
    }

    public static void setContainerVazio(int valor){
        containerVazio = valor;
    }
}
