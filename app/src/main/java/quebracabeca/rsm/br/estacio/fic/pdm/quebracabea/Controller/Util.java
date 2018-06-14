package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class Util {

    public static void AlertDialogNeutral(Context context, String Title, String Message){
        AlertDialog.Builder alertaIncompleto = new AlertDialog.Builder(context);
        alertaIncompleto.setTitle(Title);
        alertaIncompleto.setMessage(Message);
        alertaIncompleto.setNeutralButton("Fechar", null);
        alertaIncompleto.create();
        alertaIncompleto.show();
    }
}
