package hackatonasdra.estoylisto;

import hackatonasdra.estoylisto.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Tutotial extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    public Actividad actividad ;
    public DAO dao;
    public Paso pasoACtual;
    private ImageView imagenTutorial;
    private TextView descripcionTutorial;
    private LinearLayout botoneraTutorial;
    private static final String dirBaseImagenes = "/data/EL/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutotial);
        dao = new DAO(openOrCreateDatabase("estoylisto", Context.MODE_PRIVATE, null));
        Intent i = getIntent();
        actividad = dao.buscarActividad(i.getLongExtra("actividadID",0));
        pasoACtual = actividad.primerPaso;
        imagenTutorial = (ImageView) findViewById(R.id.imagenTutorial);
        descripcionTutorial = (TextView) findViewById(R.id.descripcionTutorial);
        botoneraTutorial = (LinearLayout) findViewById(R.id.botoneraTutorial);

        cargarPaso();
    }
    public void actualizarPaso(Paso nuevoPaso){
        this.pasoACtual = nuevoPaso;
        cargarPaso();
    }
private Bitmap cargarImagen(String dirImagen){
    File imgFile = new  File(dirBaseImagenes + dirImagen);
    Bitmap retorno = null;
    if(imgFile.exists()) {

        retorno = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

    }
    return retorno;
}

    private void cargarPaso() {
        if(null == pasoACtual.imagen){
            //no la cargue
            pasoACtual = dao.buscarPaso(pasoACtual.id);
        }

        this.imagenTutorial.setImageBitmap(cargarImagen(pasoACtual.imagen));

        this.descripcionTutorial.setText(pasoACtual.descripcion);
        this.botoneraTutorial.removeAllViews();
        if(pasoACtual.pasoAnterior != null){
            Button anterior = new Button(this) ;
            anterior.setText("Volver");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            anterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizarPaso(pasoACtual.pasoAnterior);
                }
            });

            this.botoneraTutorial.addView(anterior, lp);
        }
        if(pasoACtual.proximoPaso != null){
            Button siguiente = new Button(this) ;
            siguiente.setText("Próximo");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizarPaso(pasoACtual.proximoPaso);
                }
            });

            this.botoneraTutorial.addView(siguiente, lp);
        }else{
            Button terminar = new Button(this) ;
            terminar.setText("Terminar");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            terminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalizar();
                }
            });

            this.botoneraTutorial.addView(terminar, lp);
        }
    }

    private void finalizar() {

    }


}
