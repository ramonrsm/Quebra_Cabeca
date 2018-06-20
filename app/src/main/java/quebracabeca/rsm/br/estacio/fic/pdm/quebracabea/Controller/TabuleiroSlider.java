package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class TabuleiroSlider {

    private static final int TAM_TABULEIRO = 9;
    private static int       containerVazio;

    private static final int[] IMAGENS =
            {R.drawable.zero, R.drawable.um, R.drawable.dois,
             R.drawable.tres, R.drawable.quatro, R.drawable.cinco,
             R.drawable.seis, R.drawable.sete};

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

                    ImageView imageView = (ImageView) linearLayout.getChildAt(0);

                    if(Integer.parseInt(imageView.getTag().toString()) == containerVazio){
                        linearLayout.removeViewAt(0);
                    }
                }
            }
        }

        ColocarImagens(tableLayout);
    }

    private static void ColocarImagens(TableLayout tableLayout){

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout linearLayout = (LinearLayout) tableRow.getChildAt(j);

                if(Integer.parseInt(linearLayout.getTag().toString()) != containerVazio){

                    ImageView imageView = (ImageView) linearLayout.getChildAt(0);

                    imageView.setImageResource(IMAGENS[j]);
                }
            }
        }
    }

    public static int ConsultarContainerVazio(){
        return containerVazio;
    }

    public static boolean PecasDisponiveis(int tag){

        Log.i("PECAS", "CONTAINER VAZIO: "+containerVazio);

        ArrayList valores = new ArrayList();

        for (int valor : camposValidos[containerVazio]) {
            Log.i("PECAS", "CONTAINERS DISPONIVEIS: "+valor);
            valores.add(valor);
        }

        return valores.contains(tag);
    }

    public static void setContainerVazio(int valor){
        containerVazio = valor;
    }

    // Verifica se o tabauleiro está completo
    public static boolean VerificarTabuleiroSlider(TableLayout tableLayout){

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout containerPeca = (LinearLayout) tableRow.getChildAt(j);

                if(Integer.parseInt(containerPeca.getTag().toString()) != containerVazio | containerPeca.getChildCount() != 0) {

                    View peca = containerPeca.getChildAt(0);
                    String tagPeca = peca.getTag().toString();
                    String tagContainerPeca = containerPeca.getTag().toString();

                    if (!tagPeca.equals(tagContainerPeca)) {
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
