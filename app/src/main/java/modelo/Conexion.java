package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Susana on 06/08/2015.
 */
public class Conexion extends SQLiteOpenHelper {
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */

    private static int version = 1;
    private static String name = "Agenda_clientes.db";
    private static SQLiteDatabase.CursorFactory factory = null;

    private String sql = "CREATE TABLE Contactos (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Nombre TEXT NOT NULL, "
            + "Direccion TEXT, "
            + "Apellidos TEXT, "
            + "Telefono TEXT, "
            + "Email TEXT,"
            + "Id_Zona INTEGER,"//Campo nuevo
            + "Observaciones TEXT)";//Campo nuevo
/*
 *Id_Zona:
 *          1:Alcorcón y alrededores
 * 			2:Madrid capital
 * 			3:Madrid CC.AA.
 * 			4:Otra CC.AA..
 * 			5:Otro País
 * 		    6:Otros
 * valorar posibilidad de crear otra tabla...
 * */



    public Conexion(Context context) {
        super(context, name, factory, version);
    }





    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */



    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creamos la BB.DD. Agenda_Clientes.db
        db.execSQL(sql);
        Log.i(this.getClass().toString(), "Tabla Contactos creada");

        // Insertamos en primer registro....
        //db.execSQL("INSERT INTO Contactos(Nombre) VALUES('Susana')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Alcorcón','Apellidos','Rubens, 12 M�stoles, MADRID', '676048719','susimail62@gmail.com',1,'Observaciones incluidas por defecto')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Madrid','Apellidos','Rubens, 12 M�stoles, MADRID', '659355808','antoniom.sanchezf@gmail.com',2,'Observaciones incluidas por defecto')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Comunidad de Madrid','Apellidos','Rubens, 12 M�stoles, MADRID', '659355808','antoniom.sanchezf@gmail.com',3,'Observaciones incluidas por defecto')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Otra Comunidad','Apellidos','Rubens, 12 M�stoles, MADRID', '659355808','antoniom.sanchezf@gmail.com',4,'Observaciones incluidas por defecto')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Otro País','Apellidos','Rubens, 12 M�stoles, MADRID', '659355808','antoniom.sanchezf@gmail.com',4,'Observaciones incluidas por defecto')");
        db.execSQL("INSERT INTO Contactos(Nombre,Apellidos,Direccion,Telefono,Email,Id_Zona,Observaciones) VALUES('Otros','Apellidos','Rubens, 12 M�stoles, MADRID', '659355808','antoniom.sanchezf@gmail.com',4,'Observaciones incluidas por defecto')");

        Log.i(this.getClass().toString(), "Datos iniciales creados. BB.DD. creada");

/*
 *Id_Zona:
 *          1:Alcorcón y alrededores
 * 			2:Madrid capital
 * 			3:Madrid CC.AA.
 * 			4:Otra CC.AA..
 * 			5:Otro País
 * 		    6:Otros
 * valorar posibilidad de crear otra tabla...
 * */
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
