package antonio.ejemplos.accesoadatos01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

import controlador.SQLControlador;
import modelo.Conexion;

public class AltaUsuarios extends AppCompatActivity {

    private EditText nombre;
    private EditText apellidos;
    private EditText direc;
    private EditText telefono;
    private EditText email;

    private Spinner categoria;

    private RadioButton radio1,radio2,radio3,radio4,radio5,radio6;
    private EditText observaciones;

    private Button cancelar;
    private Button guardar;

    private SQLControlador Connection;
    private SQLiteDatabase db;

    private boolean validar=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_usuarios);

        //Si el método de insertar estuviera en la clase conexión, le llamaríamos desde aquí:
        //Conexion cn=new Conexion(getApplicationContext());
        //SQLiteDatabase db=cn.getWritableDatabase();
        //cn.insertarNuevoUsuario(db,nombre, apell......);



        nombre=(EditText) findViewById(R.id.nombre);
        apellidos=(EditText) findViewById(R.id.apellidos);
        direc=(EditText) findViewById(R.id.direc);
        telefono=(EditText) findViewById(R.id.telefono);
        email=(EditText) findViewById(R.id.email);


        radio1=(RadioButton) findViewById(R.id.radio1);
        radio2=(RadioButton) findViewById(R.id.radio2);
        radio3=(RadioButton) findViewById(R.id.radio3);
        radio4=(RadioButton) findViewById(R.id.radio4);
        radio5=(RadioButton) findViewById(R.id.radio5);
        radio6=(RadioButton) findViewById(R.id.radio6);


        //categoria=(Spinner) findViewById(R.id.tipo);


        observaciones=(EditText) findViewById(R.id.observaciones);

        cancelar=(Button) findViewById(R.id.boton_cancelar);
        guardar=(Button) findViewById(R.id.boton_guardar);

        //Para el Spinner:
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
//		//A�adimos el layout para el men�
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		//Le indicamos al spinner el adaptador a usar
//		categoria.setAdapter(adapter);
//


        guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String nom=nombre.getText().toString();
                String apell=apellidos.getText().toString();
                String direccion=direc.getText().toString();
                String tele=telefono.getText().toString();
                String correo=email.getText().toString();
                long Id_Categ=0;

                if (radio1.isChecked()==true) {
                    Id_Categ=1;

                }
                else if(radio2.isChecked()==true)   {
                    Id_Categ=2;
                }
                else if(radio3.isChecked()==true)   {
                    Id_Categ=3;
                }
                else if(radio4.isChecked()==true)   {
                    Id_Categ=4;
                }


                else if(radio5.isChecked()==true)   {
                    Id_Categ=5;
                }


                else if(radio6.isChecked()==true)   {
                    Id_Categ=6;
                }

                String observa=observaciones.getText().toString();

                //Creamos conexi�n a BB.dd
//				cn = new BBDD(getApplicationContext());//Ahora el contexto por defecto no es una activity sino q es un evento onClick. Por eso hay qu pasar getApplicationContext()
//				SQLiteDatabase db = cn.getWritableDatabase();//Modo escritura
//				cn.InsertarUsuario(db, nom, apell, direccion, tele, correo);


//				Connection=new SQLControlador(getApplicationContext());
//				Connection.abrirBaseDeDatos(2);//Modo Escritura




                if (validar (validar) ){


                    try {

                        //COMO EXISTE UN OBJETO CONTROLADOR DEBEMOS INVOCARLE PARA LLAMAR AL MÉTODO DE ALTA DE LOS USUARIOS///////
                        Connection = new SQLControlador(getApplicationContext());//Objeto SQLControlador
                        SQLControlador sqlControlador = Connection.abrirBaseDeDatos(2);
                        Connection.InsertarUsuario(nom, apell, direccion, tele, correo, Id_Categ, observa);


                        Toast.makeText(getApplicationContext(), "Se ha incluido en la agenda a " + nom, Toast.LENGTH_SHORT).show();
                        Connection.cerrar();

//				Intent i = new Intent(AltaUsuarios.this, MainActivity.class);
//				startActivity(i);
                        setResult(RESULT_OK);
                        finish();
                        //Para actualizar datos en MainActivity Se va a llamar a Consultar() desde Onrestart() del main.

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    //Devolvemos el control y cerramos la Activity




                }//Fin validar


            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Devolvemos el control y cerramos la Activity
                setResult(RESULT_CANCELED);
                finish();

            }
        });


    }
    //Validaci�n para que el nombre y el teléfono no se dejen vac�os
    private boolean  validar(boolean validar){
        if (  (nombre.getText().toString().equals("")) ||  (telefono.getText().toString().equals("")) ){
            //if (nombre.getText().toString().length() == 0){

            //Toast.makeText(getApplicationContext(), "Es obligatorio rellenar el nombre" , Toast.LENGTH_LONG).show();

            //Se prepara la alerta creando nueva instancia
            AlertDialog.Builder dialogValidar = new AlertDialog.Builder(this);
            dialogValidar.setIcon(android.R.drawable.ic_dialog_alert);//icono
            dialogValidar.setTitle(getResources().getString(R.string.agenda_crear_titulo));//T�tulo
            dialogValidar.setMessage(getResources().getString(R.string.agenda_texto_vacio));
            //Se a�ade un solo bot�n para que el usuario confirme...
            dialogValidar.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });


            dialogValidar.create().show();



            return false;
        }






        return true;
    }

}
