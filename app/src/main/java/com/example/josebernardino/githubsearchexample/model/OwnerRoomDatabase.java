package com.example.josebernardino.githubsearchexample.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.josebernardino.githubsearchexample.model.dao.OwnerDao;


@Database(entities = {Owner.class}, version = 1)
public abstract class OwnerRoomDatabase extends RoomDatabase {
        public abstract OwnerDao ownerDao();
}
