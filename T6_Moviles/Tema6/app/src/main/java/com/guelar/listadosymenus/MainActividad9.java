package com.guelar.listadosymenus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActividad9 extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener {

    private static final int ID_DIAS      = 1;
    private static final int ID_LUNES     = 2;
    private static final int ID_MARTES    = 3;
    private static final int ID_MIERCOLES = 4;
    private static final int ID_JUEVES    = 5;
    private static final int ID_VIERNES   = 6;
    private static final int ID_SABADO    = 7;
    private static final int ID_DOMINGO   = 8;

    private static final int ID_MESES     = 9;
    private static final int ID_ENERO     = 10;
    private static final int ID_FEBRERO   = 11;
    private static final int ID_MARZO     = 12;
    private static final int ID_ABRIL     = 13;
    private static final int ID_MAYO      = 14;
    private static final int ID_JUNIO     = 15;
    private static final int ID_JULIO     = 16;
    private static final int ID_AGOSTO    = 17;
    private static final int ID_SEPTIEMBRE= 18;
    private static final int ID_OCTUBRE   = 19;
    private static final int ID_NOVIEMBRE = 20;
    private static final int ID_DICIEMBRE = 21;

    private static final int ID_ALUMNO    = 22;

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad9_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvResultado = findViewById(R.id.tvResultado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenuDias = menu.addSubMenu(Menu.NONE, ID_DIAS, 1, "DÍAS DE LA SEMANA");
        subMenuDias.add(Menu.NONE, ID_LUNES,      1, "LUNES")     .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_MARTES,     2, "MARTES")    .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_MIERCOLES,  3, "MIÉRCOLES") .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_JUEVES,     4, "JUEVES")    .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_VIERNES,    5, "VIERNES")   .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_SABADO,     6, "SÁBADO")    .setOnMenuItemClickListener(this);
        subMenuDias.add(Menu.NONE, ID_DOMINGO,    7, "DOMINGO")   .setOnMenuItemClickListener(this);
        SubMenu subMenuMeses = menu.addSubMenu(Menu.NONE, ID_MESES, 2, "MESES DEL AÑO");
        subMenuMeses.add(Menu.NONE, ID_ENERO,      1, "ENERO")     .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_FEBRERO,    2, "FEBRERO")   .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_MARZO,      3, "MARZO")     .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_ABRIL,      4, "ABRIL")     .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_MAYO,       5, "MAYO")      .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_JUNIO,      6, "JUNIO")     .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_JULIO,      7, "JULIO")     .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_AGOSTO,     8, "AGOSTO")    .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_SEPTIEMBRE, 9, "SEPTIEMBRE").setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_OCTUBRE,   10, "OCTUBRE")   .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_NOVIEMBRE, 11, "NOVIEMBRE") .setOnMenuItemClickListener(this);
        subMenuMeses.add(Menu.NONE, ID_DICIEMBRE, 12, "DICIEMBRE") .setOnMenuItemClickListener(this);
        menu.add(Menu.NONE, ID_ALUMNO, 3, "David Guelar").setOnMenuItemClickListener(this);

        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";

        if      (id == ID_LUNES)      mensaje = "Pulsado el día: LUNES";
        else if (id == ID_MARTES)     mensaje = "Pulsado el día: MARTES";
        else if (id == ID_MIERCOLES)  mensaje = "Pulsado el día: MIÉRCOLES";
        else if (id == ID_JUEVES)     mensaje = "Pulsado el día: JUEVES";
        else if (id == ID_VIERNES)    mensaje = "Pulsado el día: VIERNES";
        else if (id == ID_SABADO)     mensaje = "Pulsado el día: SÁBADO";
        else if (id == ID_DOMINGO)    mensaje = "Pulsado el día: DOMINGO";

        else if (id == ID_ENERO)      mensaje = "Pulsado el mes: ENERO";
        else if (id == ID_FEBRERO)    mensaje = "Pulsado el mes: FEBRERO";
        else if (id == ID_MARZO)      mensaje = "Pulsado el mes: MARZO";
        else if (id == ID_ABRIL)      mensaje = "Pulsado el mes: ABRIL";
        else if (id == ID_MAYO)       mensaje = "Pulsado el mes: MAYO";
        else if (id == ID_JUNIO)      mensaje = "Pulsado el mes: JUNIO";
        else if (id == ID_JULIO)      mensaje = "Pulsado el mes: JULIO";
        else if (id == ID_AGOSTO)     mensaje = "Pulsado el mes: AGOSTO";
        else if (id == ID_SEPTIEMBRE) mensaje = "Pulsado el mes: SEPTIEMBRE";
        else if (id == ID_OCTUBRE)    mensaje = "Pulsado el mes: OCTUBRE";
        else if (id == ID_NOVIEMBRE)  mensaje = "Pulsado el mes: NOVIEMBRE";
        else if (id == ID_DICIEMBRE)  mensaje = "Pulsado el mes: DICIEMBRE";
        else if (id == ID_ALUMNO)     mensaje = "Alumno: David Guelar";

        tvResultado.setText(mensaje);
        return true;
    }
}