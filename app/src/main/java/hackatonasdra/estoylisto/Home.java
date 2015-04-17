package hackatonasdra.estoylisto;

import hackatonasdra.estoylisto.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Home extends Activity {
     /*scripts de creacion*/
    SQLiteDatabase db;
    private final static String createActividad = "CREATE TABLE IF NOT EXISTS `Actividad` (\n" +
            "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`nombre`\tTEXT NOT NULL,\n" +
            "\t`src`\tTEXT NOT NULL,\n" +
            "\t`primerPaso`\tINTEGER,\n" +
            "\t`color`\tTEXT\n" +
            ");";
    private final static String createPaso = "CREATE TABLE IF NOT EXISTS`Paso` (\n" +
            "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`descripcion`\tTEXT NOT NULL,\n" +
            "\t`src`\tTEXT,\n" +
            "\t`idActividad`\tINTEGER NOT NULL,\n" +
            "\t`idProximoPaso`\tINTEGER,\n" +
            "\t`idPasoAnterior`\tINTEGER\n" +
            ");";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        db=openOrCreateDatabase("estoylisto", Context.MODE_PRIVATE, null);
        db.execSQL(createActividad);
        db.execSQL(createPaso);

    }
    public void todasMisActividades(View view){
        Intent intent = new Intent(this, TodasMisActividades.class);
        startActivity(intent);
    }
    public void ejemploSQL(View view){
        Intent intent = new Intent(this, EjemploMysql.class);
        startActivity(intent);
    }
    public void crearActividad(View view){
        Intent intent = new Intent(this, CrearActividad.class);
        startActivity(intent);
    }
}
