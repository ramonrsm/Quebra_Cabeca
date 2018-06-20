package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Random;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class TabuleiroSlider {

    private static final int TAM_TABULEIRO = 9;
    private static int       containerVazio;


    private static final int[] IMAGENS = {R.drawable.zero, R.drawable.um, R.drawable.dois,
                                          R.drawable.tres, R.drawable.quatro, R.drawable.cinco,
                                          R.drawable.seis, R.drawable.sete};

    private static int[][] camposValidos = {{1, 3},
                                           {0, 4, 2},
                                           {1, 5},
                                           {0, 4, 6},
                                           {1, 3, 7, 5},
                                           {2, 4, 8},
                                           {3, 7},
                                           {6, 4, 8},
                                           {7, 5}};

    public static void InicializarSlider(TableLayout tableLayout) {

        Random random = new Random();
        containerVazio = random.nextInt(TAM_TABULEIRO-1);

        for (int i = 0; i < tableLayout.getChildCount(); i++) {

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {

                ViewGroup owner = (ViewGroup) tableRow.getChildAt(j);

                if (owner.getChildCount() > 0 && Integer.parseInt(owner.getTag().toString()) == containerVazio) {
                    owner.removeViewAt(0);
                }
            }
        }
        ColocarImagens(tableLayout);
    }

    private static void ColocarImagens(TableLayout tableLayout) {

        int img = 0;

        for (int i = 0; i < tableLayout.getChildCount(); i++) {

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {

                LinearLayout linearLayout = (LinearLayout) tableRow.getChildAt(j);

                if (Integer.parseInt(linearLayout.getTag().toString()) != containerVazio && linearLayout.getChildCount() != 0) {

                    ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                    imageView.setImageResource(IMAGENS[img]);
                    imageView.setTag(img);
                    img++;
                }
            }
        }
    }

    public static int ConsultarContainerVazio() {
        return containerVazio;
    }

    public static boolean PecasDisponiveis(int tag) {

        Log.i("PECAS", "CONTAINER VAZIO: "+containerVazio);

        ArrayList<Integer> valores = new ArrayList<>();

        for (int valor : camposValidos[containerVazio]) {
            Log.i("PECAS", "CONTAINERS DISPONIVEIS: "+valor);
            valores.add(valor);
        }

        return valores.contains(tag);
    }

    public static void setContainerVazio(int valor) {
        containerVazio = valor;
    }

    // Verifica se o tabauleiro está completo
    public static boolean VerificarTabuleiroSlider(TableLayout tableLayout) {

        for (int i = 0; i < tableLayout.getChildCount(); i++) {

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {

                LinearLayout containerPeca = (LinearLayout) tableRow.getChildAt(j);

                if(containerPeca.getChildCount() != 0){

                    if(containerPeca.getChildAt(0).getTag().equals(containerPeca.getTag())){
                        Log.i("PECAS", "TAG CONTAINER: "+containerPeca.getTag());
                        Log.i("PECAS", "TAG PEÇA: "+containerPeca.getChildAt(0).getTag());
                        return false;
                    }

                }else if(containerPeca.getChildCount() == 0 && !containerPeca.getTag().equals("8")){
                    Log.i("PECAS", "TAG CONTAINER: "+containerPeca.getTag());
                    return false;
                }
            }
        }
        return true;
    }
}
