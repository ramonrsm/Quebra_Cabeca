package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.View;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class TabuleiroActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private Button      button_novoJogo;
    private Button      button_Sair;

    private Drawable    enterShape;
    private Drawable    normalShape;

    private LinearLayout barra_rolagem_pecas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);

        button_novoJogo = findViewById(R.id.button_novoJogo);
        button_Sair     = findViewById(R.id.button_Sair);

        enterShape      = getResources().getDrawable(R.drawable.bg_quebra_cabeca_over);
        normalShape     = getResources().getDrawable(R.drawable.bg_quebra_cabeca);

        barra_rolagem_pecas = findViewById(R.id.barra_rolagem_pecas);

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
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackground(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackground(normalShape);
                break;
            case DragEvent.ACTION_DROP:

                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);

                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackground(normalShape);
                View view2 = (View) event.getLocalState();
                view2.setVisibility(View.VISIBLE);
                break;
            default:break;
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
}
