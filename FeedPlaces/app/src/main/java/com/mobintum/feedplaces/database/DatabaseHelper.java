package com.mobintum.feedplaces.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ricardo on 24/05/17.
 * www.mobintum.com
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "places.db";
    private static final Integer VER_1 = 1;
    private static final Integer VER_2 = 2;
    private static final Integer VER_3 = 3;
    private static final Integer DATABASE_VERSION = VER_3;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Address (" +
                "    addressId integer NOT NULL CONSTRAINT Address_pk PRIMARY KEY," +
                "    country text NOT NULL," +
                "    state text NOT NULL," +
                "    addressLine text," +
                "    lat double NOT NULL," +
                "    lng double NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Picture (" +
                "    pictureId integer NOT NULL CONSTRAINT Picture_pk PRIMARY KEY AUTOINCREMENT," +
                "    path text NOT NULL," +
                "    isMain integer NOT NULL," +
                "    fk_placeId integer NOT NULL," +
                "    CONSTRAINT Picture_Place FOREIGN KEY (fk_placeId)" +
                "    REFERENCES Place (placeId)" +
                ");");

        db.execSQL("CREATE TABLE Place (" +
                "    placeId integer NOT NULL CONSTRAINT Place_pk PRIMARY KEY," +
                "    name text NOT NULL," +
                "    description text," +
                "    fk_addressId integer NOT NULL," +
                "    isFavorite integer NOT NULL DEFAULT 0," +
                "    CONSTRAINT Place_Address FOREIGN KEY (fk_addressId)" +
                "    REFERENCES Address (addressId)" +
                ");");

        db.execSQL("INSERT INTO Picture (path,isMain,fk_placeId) VALUES ('http://www.pueblosmexico.com.mx/IMG/arton171.jpg',1,1);");
        db.execSQL("INSERT INTO Address (addressId,country,state,addressLine,lat,lng) VALUES (1,'México','Quintana Roo','Quintana Roo',18.548878,-93.763647);");
        db.execSQL("INSERT INTO Place (placeId,name,description,fk_addressId) VALUES (1,'Bacalar','Pueblo Magico',1);");
        db.execSQL("INSERT INTO Picture (path,isMain, fk_placeId) values ( 'http://e-oaxaca.com/sites/default/files/oaxaca-night_0.jpg', 1, 1 )");
        db.execSQL("INSERT INTO Address (addressId, country, state, addressLine, lat, lng) VALUES (2, 'México', 'Oaxaca', 'Oaxaca', 16.898056, -96.414167)");
        db.execSQL("INSERT INTO Place (placeId, name, description, fk_addressId ) VALUES (2, 'Oaxaca', 'es uno de los treinta y un estados que, junto con la Ciudad de México, forman los Estados Unidos Mexicanos. Su capital y ciudad más poblada es Oaxaca de Juárez.',2)");
        db.execSQL("INSERT INTO Picture (path, isMain, fk_placeId) VALUES ('http://pueblosmexico.com.mx/IMG/arton173.jpg', 3, 3);");
        db.execSQL("INSERT INTO Address (addressId, country, state, addressLine, lat, lng) VALUES (3, 'Mexico', 'Hidalgo', 'Hidalgo', 20.2038721, -98.5803654)");
        db.execSQL("INSERT INTO Place (placeId, name, description,fk_addressId) VALUES (3, 'Huasca de Ocampo', 'Pueblo Magico', 3);");
        db.execSQL("INSERT INTO Picture (path,isMain,fk_placeId) VALUES ('http://cdn.ntrzacatecas.com/archivos/2016/12/real-de-catorce-5.jpg',4,4);");
        db.execSQL("INSERT INTO Address (addressId,country,state,addressLine,lat,lng) VALUES (4,'México','San Luis Potosí','Catorce',23.688889,100.887778);");
        db.execSQL("INSERT INTO Place (placeId,name,description,fk_addressId) VALUES (4,'Real de Catorce','Pueblo Mágico',4);");
        db.execSQL("INSERT INTO Picture (path,isMain,fk_placeId) VALUES ('https://destinos-impresionesaerea.netdna-ssl.com/assets/images/destinos/cancun/principal.jpg',1,5);");
        db.execSQL("INSERT INTO Address (addressId,country,state,addressLine,lat,lng) VALUES (5,'México','Quintana Roo','Cancun',21.1213284,-86.9195224);");
        db.execSQL("INSERT INTO Place (placeId,name,description,fk_addressId) VALUES (5,'Cancun','Cancún (en maya: kaan y kun, (cuna o nido de serpientes), es una ciudad en el estado de Quintana Roo, siendo cabecera del municipio de Benito Juárez, se úbica en el oriente de México, a más de 1700 km de la Ciudad de México.',5);");
        db.execSQL("INSERT INTO Picture (path,isMain,fk_placeId) VALUES ('https://www.dondeir.com/wp-content/uploads/2016/09/campamento-de-terror-teotihuacan-c.jpg',6,6);");
        db.execSQL("INSERT INTO Address (addressId,country,state,addressLine,lat,lng) VALUES (6,'México','Estado de México','Estado de México',19.6881324,-98.891942);");
        db.execSQL("INSERT INTO Place (placeId,name,description,fk_addressId) VALUES (6,'Teotihuacán','Zona Arqueologica',6);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("DEBUG", "ov "+oldVersion);
        Log.e("DEBUG", "nv "+newVersion);
        Log.e("DEBUG", "dv "+DATABASE_VERSION);

        if (oldVersion!= DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXISTS Place");
            db.execSQL("DROP TABLE IF EXISTS Address");
            db.execSQL("DROP TABLE IF EXISTS Picture");
            onCreate(db);
        }
    }
}
