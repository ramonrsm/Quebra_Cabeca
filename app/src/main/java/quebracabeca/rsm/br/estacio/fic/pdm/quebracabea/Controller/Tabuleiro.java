package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.view.View;

public class Tabuleiro {

    public static final int QTD_PECAS = 9;


    public static boolean VerificarDisponivel(View v) {

        return v.getParent() != null;
    }
}
