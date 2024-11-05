package com.example.whatsupdog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact WHERE id IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM Contact ORDER BY id DESC LIMIT 1")
    Contact getLastContact();

    @Query("SELECT * FROM Contact WHERE name LIKE :name LIMIT 1")
    Contact findByName(String name);

    @Query("UPDATE Contact SET name = :name, phone = :phone WHERE id = :id")
    void updateContactFromId(int id, String name, String phone);

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Insert
    void insertAll(Contact... contacts);

    @Insert
    void insertContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Update
    void updateContact(Contact contact);
}
