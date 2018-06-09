package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller;


public class Tabuleiro {

    public static final int QTD_PECAS = 9;
    public int pecasRestantes = QTD_PECAS;

    public void ContarPecasRestantes() {

        pecasRestantes--;
    }
}
