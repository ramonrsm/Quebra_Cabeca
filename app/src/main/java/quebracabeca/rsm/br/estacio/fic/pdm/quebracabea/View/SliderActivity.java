package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.View;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller.TabuleiroSlider;
import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.Controller.Util;
import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class SliderActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    private Drawable    enterShape;
    private Drawable    normalShape;
    private Drawable    unavailableShape;

    private static int  tagInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        setTitle("Slide Quebra Cabeça");

        enterShape       = getResources().getDrawable(R.drawable.bg_quebra_cabeca_over);
        normalShape      = getResources().getDrawable(R.drawable.bg_quebra_cabeca);
        unavailableShape = getResources().getDrawable(R.drawable.bg_quebra_cabeca_unavailable);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        findViewById(R.id.peca0).setOnTouchListener(this);
        findViewById(R.id.peca1).setOnTouchListener(this);
        findViewById(R.id.peca2).setOnTouchListener(this);
        findViewById(R.id.peca3).setOnTouchListener(this);
        findViewById(R.id.peca4).setOnTouchListener(this);
        findViewById(R.id.peca5).setOnTouchListener(this);
        findViewById(R.id.peca6).setOnTouchListener(this);
        findViewById(R.id.peca7).setOnTouchListener(this);
        findViewById(R.id.peca8).setOnTouchListener(this);

        findViewById(R.id.recipiente0).setOnDragListener(this);
        findViewById(R.id.recipiente1).setOnDragListener(this);
        findViewById(R.id.recipiente2).setOnDragListener(this);
        findViewById(R.id.recipiente3).setOnDragListener(this);
        findViewById(R.id.recipiente4).setOnDragListener(this);
        findViewById(R.id.recipiente5).setOnDragListener(this);
        findViewById(R.id.recipiente6).setOnDragListener(this);
        findViewById(R.id.recipiente7).setOnDragListener(this);
        findViewById(R.id.recipiente8).setOnDragListener(this);

        TabuleiroSlider.InicializarSlider(tableLayout);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()){

            case DragEvent.ACTION_DRAG_ENTERED:

                if (Integer.parseInt(v.getTag().toString()) != TabuleiroSlider.ConsultarContainerVazio() /*|| containerPeca.getTag().equals(v.getTag())*/) {
                    v.setBackground(unavailableShape);
                }
                else {
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

                View view2 = (View) event.getLocalState();
                view2.setVisibility(View.VISIBLE);
                v.setBackground(normalShape);

                int tagContainer = Integer.parseInt(view2.getTag().toString());

                if(tagInicial != tagContainer){
                    TabuleiroSlider.setContainerVazio(tagContainer);
                }
                break;
        }

        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        LinearLayout container = (LinearLayout) v.getParent();

        int tag = Integer.parseInt(container.getTag().toString());

        if(!TabuleiroSlider.PecasDisponiveis(tag)){
            return false;
        }else{
            ClipData dragData = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(dragData, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            tagInicial = tag;
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_novoJogo:
                Util.AlertDialogNeutral(this, "Novo Jogo", "Monte todas as peças na ordem certa!");
                break;
            case R.id.button_Sair:
                finish();
                break;
        }
    }
}
