package sample;
import java.io.Serializable;


public class JournalDto implements Serializable {

    private final String nameTask;


    private final int timeTask;

    private final int kkalTask;

    public JournalDto(String nameTask, int timeTask, int kkalTask )
    {


        this.nameTask = nameTask;
        this.timeTask = timeTask;
        this.kkalTask = kkalTask;


    }

    /*
    *
    *
    *
    * Геттеры
    *
    *
    *
    * */
    public String getNameTask() {


        return nameTask;
    }


    public int getTimeTask() {


        return timeTask;
    }


    public int getKkalTask() {


        return kkalTask;
    }
}

