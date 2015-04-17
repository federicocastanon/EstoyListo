package hackatonasdra.estoylisto;

import hackatonasdra.estoylisto.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TodasMisActividades extends Activity {
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
    private static final boolean TOGGLE_ON_CLICK = false;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    private DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todas_mis_actividades);

        this.dao = new DAO(openOrCreateDatabase("estoylisto", Context.MODE_PRIVATE, null));

        LinearLayout wrapperActividades = (LinearLayout) findViewById(R.id.wrapperActividades);
        agregarActividades(wrapperActividades);



    }

    private void agregarActividades(LinearLayout wrapperActividades) {
        List<Actividad> act = dao.buscarActividades();

        BotonActividad boton ;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(0,30,0,0);
        for(Actividad a : act) {
            boton   =new BotonActividad(this);
            boton.setText(a.nombre);
            boton.setActividad(a);
            boton.setBackgroundColor(Color.parseColor(a.color));

            boton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       empezarTutorial((BotonActividad)v);
                   }
               });
            wrapperActividades.addView(boton,lp);
        }
    }

    public void empezarTutorial(BotonActividad ba){
        Intent intent = new Intent(this, Tutotial.class);

        intent.putExtra("actividadID", ba.getActividad().id);
        startActivity(intent);
    }


}
