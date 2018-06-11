package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;

public class Tabuleiro {

    private static final int TAM_TABULEIRO = 9;
    private static int[] TagsTabuleiro = {0,1,2,3,4,5,6,7,8};

    // Verifica se o tabauleiro está completo
    public static boolean VerificarTabuleiro(TableLayout tableLayout){

        ArrayList<Integer> TagPecas = new ArrayList<>();

        for(int i = 0; i < tableLayout.getChildCount(); i++){

            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < tableRow.getChildCount(); j++){

                LinearLayout containerPeca = (LinearLayout) tableRow.getChildAt(j);

                if(containerPeca.getChildCount() != 0){

                    View peca = containerPeca.getChildAt(0);
                    Integer tag = Integer.getInteger(peca.getTag().toString());

                    if(peca.getTag().equals(containerPeca.getTag())){
                        TagPecas.add(tag);
                    }
                }
            }
        }

        return ValidarTabuleiro(TagPecas);
    }

    private static boolean ValidarTabuleiro(ArrayList<Integer> pecas){

        for (int i = 0; i < TAM_TABULEIRO; i++){

            if(pecas.isEmpty() || pecas.size() < TAM_TABULEIRO ){

                if(pecas.get(i) == null || Integer.parseInt(pecas.get(i).toString()) != TagsTabuleiro[i]){
                    return false;
                }
            }
        }
        return true;
    }

    // Iniciar e Reiniciar tabuleiro
    public static void IniciarTabuleiro(){


    }
}
