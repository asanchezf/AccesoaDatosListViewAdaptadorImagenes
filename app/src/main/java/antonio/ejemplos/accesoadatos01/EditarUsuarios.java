package antonio.ejemplos.accesoadatos01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import controlador.SQLControlador;

public class EditarUsuarios extends AppCompatActivity {

    private EditText nombre;
    private EditText email;
    private Button btnGuardarCambios;
    private int id_recogido;
    private SQLControlador dbConnection;

    private String nom;
    private String correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_usuarios);

        nombre=(EditText)findViewById(R.id.txtNombre);
        email=(EditText)findViewById(R.id.txtEmail);
        btnGuardarCambios=(Button)findViewById(R.id.btn_modificarUsuario);

        //Recogemos los valores que nos han llegado en un objeto Bundle
        Bundle bundle=getIntent().getExtras();
        nom=bundle.getString("nombreContacto");
        correo=bundle.getString("emailContacto");
        id_recogido=bundle.getInt("id");

        //Mostramos los valoeres en los EditTexy
        nombre.setText(nom);
        email.setText(correo);

        //Al hacer click en el botón...
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // CREA UN OBJETO DEL CONTROLADOR Y ABRE LA BBDD EN MODO
                // EXCRITURA...
                dbConnection = new SQLControlador(getApplicationContext());

                try {
                    dbConnection.abrirBaseDeDatos(2);//objeto SQLcontrolador
                    dbConnection.ModificarContacto(id_recogido, nombre
                            .getText().toString(), email.getText().toString());

                    // Log.i(this.getClass().toString(), id_recogido +
                    // "UPDATE_1" + "  "+ nombre.getText().toString() );

                    Toast.makeText(EditarUsuarios.this,
                            R.string.agenda_editar_confirmacion,
                            Toast.LENGTH_SHORT).show();
                    dbConnection.cerrar();

                    // Devolvemos el control y cerramos la Activity
//					Intent i = new Intent(ModificarUsuarios.this, MainActivity.class);
//					startActivity(i);

                    setResult(RESULT_OK);
                    finish();
                    //Para actualizar datos en MainActivity Se va a llamar a Consultar() desde Onrestart() del main.


                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });




    }


}
