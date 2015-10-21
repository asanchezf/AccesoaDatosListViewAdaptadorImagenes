package antonio.ejemplos.accesoadatos01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import Beans.Contactos;
import controlador.SQLControlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class VerUsuarios extends AppCompatActivity {


    private ListView lista;// OBJETO LISTVIEW
    private SQLControlador dbConnection;//CONTIENE LAS CONEXIONES A BBDD (CREADA EN DBHELPER.CLASS) Y LOS M�TODOS INSERT, UPDATE, DELETE, BUSCAR....
    private ArrayList<Contactos> contactos;//COLECCION DE TIPO CONTACTOS (BEAN CON LA MISMA ESTRUTURA DE CAMPOS QUE LA BBDD)

    //ADAPTADORES....===========================================================
    private ArrayAdapter<Contactos> adaptadorsimple;//Primer adaptador utilizado. EJEMPLO 1
    //private ContactosAdapter contactosAdapter;// Segundo adaptador utilizado.Ejemplo sin im�genes..
    //private ContactosAdapter_old contactosAdapter_Jarroba;// Ejemplo con im�genes.
    private ContactosAdapter_Imagenes contactosAdapter_imagenes;
    //FIN ADAPTADORES....======================================================


    //CONSTANTES PARA EL MODO FORMULARIO Y PARA LOS TIPOS DE LLAMADA.============================
    public static final String C_MODO = "modo";
    public static final int C_VISUALIZAR = 551;
    public static final int C_CREAR = 552;
    public static final int C_EDITAR = 553;
    public static final int C_ELIMINAR = 554;
    public static final int C_CALL = 555;
    //FIN CONSTANTES==============================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.ver_usuarios);
        setContentView(R.layout.inicio);
        lista = (ListView) findViewById(android.R.id.list);
        //lista=(ListView)findViewById(R.id.lista);

        consultar();

        //En pulsación prolongada de la lista borramos el elemento seleccionado
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //String sql="DELETE from Contactos where _id=" +lista.getId();
                //cn.exec(sql);

                try {
                    dbConnection = new SQLControlador(getApplicationContext());
                    dbConnection.abrirBaseDeDatos(2);//Modo escritura
                    dbConnection.delete(id);

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                return false;
            }
        });


        //Al pulsar se abrirá una nueva activiti para poder modificar el registro seleccionado.....
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Recogemos los valores y se los pasamos a la ventana de modificación....
                int id_contacto = (int) contactos.get(position).get_id();
                String nombre=contactos.get(position).getNombre();
                String email=contactos.get(position).getEmail();
                Intent intent=new Intent(getApplicationContext(),EditarUsuarios.class);
                intent.putExtra("id",id_contacto);
                intent.putExtra("nombreContacto",nombre);
                intent.putExtra("emailContacto",email);
                startActivity(intent);

            }
        });
    }


    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     * <p/>
     * <p>For activities that are using raw {@link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be requeried (because you had deactivated it in
     * {@link #onStop}.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @Override
    protected void onRestart() {
        super.onRestart();


        consultar();
    }

    private void consultar(){


        dbConnection = new SQLControlador(getApplicationContext());
        try {
            dbConnection.abrirBaseDeDatos(1);//Modo lectura
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// Lectura. Solo para ver

        //En el arraylist contactos devolvemos lo que traiga el método BuscarTodos
        contactos = dbConnection.BuscarTodos();// llamamos a BuscarTodos() que devuelve un arraylist de contactos...

        /////-----------CASO 1:
        //Utilizando el ArrayAdapter personalizado que infla el layout  image_list_item3
        contactosAdapter_imagenes = new ContactosAdapter_Imagenes(this,contactos);
        lista.setAdapter(contactosAdapter_imagenes);

        /////-----------CASO 2:
        //Utilizando un ArrayAdapter normal que utiliza un layout por defecto de android:simple_list_item2
        //adaptadorsimple=new ArrayAdapter<Contactos>(getApplicationContext(),android.R.layout.simple_list_item_2,contactos);
        //lista.setAdapter(adaptadorsimple);


        dbConnection.cerrar();


    }


}
