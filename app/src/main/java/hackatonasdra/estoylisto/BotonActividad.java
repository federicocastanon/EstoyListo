package hackatonasdra.estoylisto;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by fede on 15-Apr-15.
 */
public class BotonActividad extends Button  {

    private Actividad actividad;

    public BotonActividad(Context context) {
        super(context);
    }

    public void onClick(){

    }
    public Actividad getActividad(){
        return this.actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
