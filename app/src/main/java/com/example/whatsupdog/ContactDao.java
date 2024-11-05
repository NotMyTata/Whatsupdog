package com.example.whatsupdog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact WHERE id IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM Contact WHERE name LIKE :first LIMIT 1")
    Contact findByName(String first);

    @Insert
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact contact);
}
