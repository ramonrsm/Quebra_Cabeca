package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class SliderActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        setTitle("Slide Quebra Cabeça");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_novoJogo:
                Toast.makeText(this, "Implementar novo jogo!", Toast.LENGTH_SHORT).show(); break;
            case R.id.button_Sair: finish(); break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
