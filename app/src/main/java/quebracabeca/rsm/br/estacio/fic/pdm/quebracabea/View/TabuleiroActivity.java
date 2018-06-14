package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.View;

import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller.Tabuleiro;
import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class TabuleiroActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener, View.OnClickListener {

    private TableLayout tableLayout;
    private View        barra_rolagem_pecas;

    private Drawable    enterShape;
    private Drawable    unavailableShape;
    private Drawable    normalShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);

        enterShape       = getResources().getDrawable(R.drawable.bg_quebra_cabeca_over);
        normalShape      = getResources().getDrawable(R.drawable.bg_quebra_cabeca);
        unavailableShape = getResources().getDrawable(R.drawable.bg_quebra_cabeca_unavailable);

        tableLayout = findViewById(R.id.tableLayout);

        findViewById(R.id.peca0).setOnLongClickListener(this);
        findViewById(R.id.peca1).setOnLongClickListener(this);
        findViewById(R.id.peca2).setOnLongClickListener(this);
        findViewById(R.id.peca3).setOnLongClickListener(this);
        findViewById(R.id.peca4).setOnLongClickListener(this);
        findViewById(R.id.peca5).setOnLongClickListener(this);
        findViewById(R.id.peca6).setOnLongClickListener(this);
        findViewById(R.id.peca7).setOnLongClickListener(this);
        findViewById(R.id.peca8).setOnLongClickListener(this);

        findViewById(R.id.recipiente0).setOnDragListener(this);
        findViewById(R.id.recipiente1).setOnDragListener(this);
        findViewById(R.id.recipiente2).setOnDragListener(this);
        findViewById(R.id.recipiente3).setOnDragListener(this);
        findViewById(R.id.recipiente4).setOnDragListener(this);
        findViewById(R.id.recipiente5).setOnDragListener(this);
        findViewById(R.id.recipiente6).setOnDragListener(this);
        findViewById(R.id.recipiente7).setOnDragListener(this);
        findViewById(R.id.recipiente8).setOnDragListener(this);



        barra_rolagem_pecas = findViewById(R.id.barra_rolagem_pecas);
        barra_rolagem_pecas.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                if(event.getAction() == DragEvent.ACTION_DROP){
                    View view = (View) event.getLocalState();

                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:

                LinearLayout containerPeca = (LinearLayout) v;

                if (containerPeca.getChildCount() != 0) {
                    v.setBackground(unavailableShape);
                } else {
                    v.setBackground(enterShape);
                }
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackground(normalShape);
                break;

            case DragEvent.ACTION_DROP:

                LinearLayout container = (LinearLayout) v;

                if (container.getChildCount() == 0) {

                    View view = (View) event.getLocalState();

                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:

                View view1 = (View) v.getParent();

                View view2 = (View) event.getLocalState();
                view2.setVisibility(View.VISIBLE);

                Log.i("VIEW", "View1 Tag: " + view1.getTag());
                Log.i("VIEW", "View2 Tag: " + view2.getTag());

                if (view2.getTag().equals(view1.getTag())) {
                    v.setBackground(enterShape);
                } else {
                    v.setBackground(normalShape);
                }
                break;
        }
        return true;
    }


    @Override
    public boolean onLongClick(View v) {

        ClipData dragData = ClipData.newPlainText("","");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(dragData, shadowBuilder, v, 0);
        v.setVisibility(View.INVISIBLE);

        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_novoJogo:
                AlertDialog.Builder alertaNovoJogo = new AlertDialog.Builder(TabuleiroActivity.this);
                alertaNovoJogo.setTitle("Novo Jogo");
                alertaNovoJogo.setMessage("Monte todas as peças na ordem certa!");
                alertaNovoJogo.setNeutralButton("Fechar", null);
                alertaNovoJogo.create();
                alertaNovoJogo.show();
                Tabuleiro.Inicializar(tableLayout, barra_rolagem_pecas);
                break;
            case R.id.button_Sair: finish();break;
            case R.id.button_Verificar:

                if(Tabuleiro.VerificarTabuleiro(tableLayout)){
                    AlertDialog.Builder alertaSucesso = new AlertDialog.Builder(TabuleiroActivity.this);
                    alertaSucesso.setTitle("Sucesso");
                    alertaSucesso.setMessage("Parabéns você montou o quebra-cabeça!");
                    alertaSucesso.setPositiveButton("Novo Jogo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Tabuleiro.Inicializar(tableLayout, barra_rolagem_pecas);
                        }
                    });
                    alertaSucesso.create();
                    alertaSucesso.show();
                }else{
                    AlertDialog.Builder alertaIncompleto = new AlertDialog.Builder(TabuleiroActivity.this);
                    alertaIncompleto.setTitle("Incompleto");
                    alertaIncompleto.setMessage("Verifique a ordem das peças!");
                    alertaIncompleto.setNeutralButton("Fechar", null);
                    alertaIncompleto.create();
                    alertaIncompleto.show();
                }
                break;
        }
    }
}
