package hackatonasdra.estoylisto;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fede on 15-Apr-15.
 */
public class Actividad {
    public Long id;
    public String nombre;
    public String thumbnail;
    public Paso primerPaso;
    public String color;

    public Actividad(){

    }

    public Actividad(Long _id, String _nombre, String _src, Long _primerPaso, String _color ){
        this.id = _id;
        this.nombre = _nombre;
        this.thumbnail = _src;
        this.primerPaso = new Paso(_primerPaso);
        this.color = _color;
    }
}
