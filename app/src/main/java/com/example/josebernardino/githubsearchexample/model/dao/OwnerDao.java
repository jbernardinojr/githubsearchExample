package com.example.josebernardino.githubsearchexample.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.josebernardino.githubsearchexample.model.Owner;


import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OwnerDao {

    @Insert(onConflict = REPLACE)
    void save(Owner owner);

    @Query("SELECT * FROM owner WHERE ownerId=:ownerId")
    LiveData<Owner> load(int userId);
}
