package bigdreams.example.alexbig.smartape.database.daos;

/**
 * Created by Andres on 5/7/2018.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import bigdreams.example.alexbig.smartape.database.objects.Quiz;

import java.util.List;

import bigdreams.example.alexbig.smartape.database.objects.Quiz;

@Dao
public interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Quiz... quizzes);

    @Delete
    void delete(Quiz quiz);

    @Update
    void update(Quiz... quizzes);

    @Query("DELETE FROM quiz_table")
    void DeleteAllQuiz();

    @Query("SELECT * FROM quiz_table WHERE _id IN (:quizIds)")
    List<Quiz> loadAllByIds(String... quizIds);

    @Query("SELECT * FROM quiz_table WHERE _id IN (:quizIds)")
    LiveData<Quiz> FindOne(String quizIds);

    // Ejemplo de query para sacar quiz con referencia del usuario
    @Query("SELECT * FROM quiz_table WHERE Creador=:userId")
    List<Quiz> findRepositoriesForUser(String userId);
}