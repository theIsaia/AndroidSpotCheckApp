package com.nedreboe.spotcheckapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SpotCheck.class}, version = 2, exportSchema = false)
public abstract class spotCheckRoomDatabase extends RoomDatabase {

    public abstract spotCheckDao spotCheckDao();

    private static spotCheckRoomDatabase INSTANCE;

    public static spotCheckRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (spotCheckRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            spotCheckRoomDatabase.class, "spotCheck_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
