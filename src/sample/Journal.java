package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import java.util.List;

public class Journal implements Serializable {

    private final SimpleStringProperty nameTask;

    private final SimpleIntegerProperty timeTask;

    private final SimpleIntegerProperty kkalTask;



    public Journal(String nameTask, int timeTask, int kkalTask )
    {

        this.nameTask =new SimpleStringProperty(nameTask);

        this.timeTask =new SimpleIntegerProperty(timeTask);

        this.kkalTask =new SimpleIntegerProperty(kkalTask);

    }
    public String getNameTask() {

        return nameTask.get();
    }
    public int getTimeTask() {

        return timeTask.get();
    }
    public int getKkalTask() {

        return kkalTask.get();
    }
}