package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class TabuleiroSlider {

    private static final int TAM_TABULEIRO = 9;
    private static int       containerVazio;

    public static void InicializarSlider(TableLayout tableLayout){

        Random random = new Random();

        containerVazio = random.nextInt(TAM_TABULEIRO);

        //Log.i("INICIALIZAR", "Valor Random: "+containerVazio);

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout linearLayout = (LinearLayout) tableRow.getChildAt(j);

                if(linearLayout.getChildCount() > 0){

                    View view = linearLayout.getChildAt(0);

                    //Log.i("INICIALIZAR", "Tag: "+view.getTag());

                    if(Integer.parseInt(view.getTag().toString()) == containerVazio){
                        linearLayout.removeViewAt(0);
                    }
                }
            }
        }
    }

    public static int ContainerDisponivel(){
        return containerVazio;
    }

    public static void setContainerVazio(int valor){
        containerVazio = valor;
    }
}
