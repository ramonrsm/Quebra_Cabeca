package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TabuleiroQuebraCabeca {

    // Verifica se o tabauleiro está completo
    public static boolean VerificarTabuleiro(TableLayout tableLayout){

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout containerPeca = (LinearLayout) tableRow.getChildAt(j);

                if(containerPeca.getChildCount() != 0) {

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

    public static void Inicializar(TableLayout tableLayout, View containerPecas){

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout containerPeca = (LinearLayout) tableRow.getChildAt(j);

                if(containerPeca.getChildCount() != 0) {

                    LinearLayout container = (LinearLayout) containerPecas;
                    View view = containerPeca.getChildAt(0);

                    containerPeca.removeViewAt(0);

                    container.addView(view);
                }
            }
        }
    }
}
