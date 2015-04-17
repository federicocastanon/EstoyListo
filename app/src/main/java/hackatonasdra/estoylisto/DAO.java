package hackatonasdra.estoylisto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fede on 15-Apr-15.
 */
public class DAO {

    SQLiteDatabase db;

    /*SCRIPTS SQL*/
    private final static String buscarTodasLasActividades = "select * from actividad" ;
    private final static String buscarActividadPorId = "select * from actividad where id = " ;
    private final static String buscarPasoPorId = "select `id`,\n" +
            "\t`descripcion`\t,\n" +
            "\t`src`,\n" +
            "\t`idProximoPaso`,\n" +
            "\t`idPasoAnterior` from paso where id = ";

    public DAO(SQLiteDatabase database){

        db=database;
    }

    public Actividad buscarActividad(Long id){
        Actividad retorno = null;
        Cursor c=db.rawQuery(buscarActividadPorId + id, null);

        if(c.moveToFirst())
        {
            retorno = new Actividad(c.getLong(0), c.getString(1), c.getString(2),c.getLong(3), c.getString(4));
        }

        return retorno;
    };

    public List<Actividad> buscarActividades(){

        List<Actividad> actividades = new ArrayList<>();
        Cursor c=db.rawQuery(buscarTodasLasActividades, null);

        while(c.moveToNext()) {
            actividades.add(new Actividad(c.getLong(0), c.getString(1), c.getString(2),c.getLong(3), c.getString(4)));
        }
        //ArrayList<Actividad> retorno = generadorActividades(5);

        return actividades;
    }

    public  ArrayList<Actividad> generadorActividades(int cant){
        ArrayList<Actividad> retorno = new ArrayList();
        Actividad act;
        for(int i = 0; i<cant ; i++) {
            act = new Actividad();
            act.id = Long.valueOf(i) ;
            act.nombre = "TEST" + i;
           // act.color = 0x111111 * i ;

            retorno.add(act);
        }
        return retorno;

    }
    public Paso buscarPaso(Long id){
        Cursor c=db.rawQuery(buscarPasoPorId + id, null);
        Paso paso = null;
        if(c.moveToFirst())
        {
            paso = new Paso(c.getLong(0), c.getString(1), c.getString(2), c.getLong(3),c.getLong(4));

        }
        return paso;
    }

    private List<Paso> buscarPasos(Long id) {
        List<Paso> retorno = generadorPasos(1);
        for(int p = 0; p < retorno.size() ; p++){
            if(p-1 >= 0){
                retorno.get(p).pasoAnterior = retorno.get(p-1);
            }

            if (p+1 < retorno.size()){
                retorno.get(p).proximoPaso = retorno.get(p+1);
            }

        }

        return retorno;
    }

    private List<Paso> generadorPasos(int cantidad){
        ArrayList<Paso> retorno = new ArrayList();
        for (int i = 0; i< cantidad; i++){
            retorno.add(new Paso(Long.valueOf(i),String.valueOf(i),"aste.jpeg"));
        }
        return retorno;
    }
}
