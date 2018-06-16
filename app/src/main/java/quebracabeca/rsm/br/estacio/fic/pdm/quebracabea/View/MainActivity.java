package quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import quebracabeca.rsm.br.estacio.fic.pdm.quebracabea.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrQuebraCabeca(View view){
        Intent intentQuebracabecca = new Intent(this, QuebraCabecaActivity.class);
        startActivity(intentQuebracabecca);
    }

    public void IrSlider(View view){
        Intent intentSlider = new Intent(this, SliderActivity.class);
        startActivity(intentSlider);
    }

    public void IrSobre(View view){
        Intent intentSlider = new Intent(this, SobreActivity.class);
        startActivity(intentSlider);
    }
}
