package bigdreams.example.alexbig.smartape.database.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.NO_ACTION;

@Entity(tableName = "resuelto_table")
public class Resuelto {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String  id;
    @ForeignKey(entity = Usuario.class, parentColumns = "_id", childColumns = "idusuario",
            onDelete = NO_ACTION)
    @ColumnInfo(name = "usuarioid")
    private String idusuario;
    @ForeignKey(entity = Quiz.class, parentColumns = "_id", childColumns = "idquiz",
            onDelete = NO_ACTION)
    @ColumnInfo(name = "quizid")
    private int idquiz;
    @ColumnInfo(name = "titulo")
    private int Titulo;
    @ColumnInfo(name = "nota")
    private int Nota;
    @ColumnInfo(name = "tiempo_limite")
    private String Tiempo_limite;
    @ColumnInfo(name = "created_date")
    private String Created_date;


    @NonNull
    public String getId() {
        return id;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public int getIdquiz() {
        return idquiz;
    }

    public int getTitulo() {
        return Titulo;
    }

    public int getNota() {
        return Nota;
    }

    public String getTiempo_limite() {
        return Tiempo_limite;
    }

    public String getCreated_date() {
        return Created_date;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public void setIdquiz(int idquiz) {
        this.idquiz = idquiz;
    }

    public void setTitulo(int titulo) {
        Titulo = titulo;
    }

    public void setNota(int nota) {
        Nota = nota;
    }

    public void setTiempo_limite(String tiempo_limite) {
        Tiempo_limite = tiempo_limite;
    }

    public void setCreated_date(String created_date) {
        Created_date = created_date;
    }
}
