package hackatonasdra.estoylisto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.File;

/**
 * Created by fede on 15-Apr-15.
 */
public class Paso {
    public Long id;
    public Paso proximoPaso;
    public Paso pasoAnterior;
    public String imagen;
    public String descripcion;

    public Paso(){

    }
    public Paso(Long _id){
        this.id = _id;
    }

    public Paso(Long _id, String desc, String dirImagen){
        this.descripcion = "Esta es una descripcion" + desc;
        this.id = _id;
        this. imagen = dirImagen;
    }

    public Paso(long _id, String desc, String _src,long _idProximoPaso, long _idPasoAnterior) {
        this.descripcion = desc;
        this.id = _id;
        this.imagen = _src;
        this.proximoPaso = new Paso(_idProximoPaso);
        this.pasoAnterior = new Paso(_idPasoAnterior);
    }
}
