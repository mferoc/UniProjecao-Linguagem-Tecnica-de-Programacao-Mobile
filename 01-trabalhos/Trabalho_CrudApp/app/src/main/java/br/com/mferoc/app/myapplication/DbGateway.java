package br.com.mferoc.app.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbGateway {
    //classe implementada usando os patterns Gateway e Singleton
    //Gateway -> responsável por gerenciar conexões com o db
    //Singleton -> responsável por garantir que existe apenas um cliente de DB no app
    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx){
        DbHelper helper = new DbHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public static DbGateway getInstance(Context ctx){
        if(gw == null)
            gw = new DbGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
