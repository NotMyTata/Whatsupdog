package com.example.whatsupdog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class Whatsupdog_db extends RoomDatabase {
    public abstract ContactDao contactDao();
    private static volatile Whatsupdog_db INSTANCE;

    public static Whatsupdog_db getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Whatsupdog_db.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                            Whatsupdog_db.class, "DB_Whatsupdog")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
