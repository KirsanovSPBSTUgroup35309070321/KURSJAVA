/*
*
*
* "Приложение позволяет указать вид тренировки: отжимание, скакалка, приседания.
Доступны команды: начать тренировку (запускается таймер тренировки),
закончить тренировку (таймер останавливается).
За отработанное время высчитывается количество потраченных
калорий по формуле K*t=cal, где K - количество калорий в час, затрачиваемое
на определенный вид тренировки, t - время, засеченное трекером.
От запуска к запуску программы данные должны сохраняться и общее количество калорий - суммироваться.
Персистенция данных приложения с помощью ObjectOutputStream +5 баллов или JAXB + 10 баллов
Поддержка нескольких профилей пользователей приложением + 5 баллов"
*
*
* */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileReader;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.swing.*;
import java.awt.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Timer;
import java.util.jar.JarException;
import java.io.*;
import java.io.Serializable;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.*;


/*
*
* //Инициализация инструментария
*
* */

public class Controller implements Initializable {
    @FXML
    public ComboBox<String> Choise;
    @FXML
    private Button StartButton;
    @FXML
    private TextField TIMER;
    @FXML
    private Label CaloryMeter;
    @FXML
    private TableView<Journal> tableTask;
    @FXML
    private TableColumn<Journal, String> ExercizeName;
    @FXML
    private TableColumn<Journal, Integer> ExercizeTime;
    @FXML
    private TableColumn<Journal, Integer> ExercizeCalory;

    public String BT;

    public String TM;

    public String CBV;

    public int FTimer;

    public int FCAl;

    public int ALLCAL;



    ObservableList<Journal> data = FXCollections.observableArrayList();
    public ObservableList<Journal> listfromfile;

    @Override
    public void initialize(URL url, ResourceBundle resources) {


        TaskDeserialized();
        CaloryMeter.setText(String.valueOf(ALLCAL));


        ExercizeName.setCellValueFactory(
                new PropertyValueFactory<Journal, String>("nameTask")

        );

        ExercizeTime.setCellValueFactory(
                new PropertyValueFactory<Journal, Integer>("timeTask")
        );

        ExercizeCalory.setCellValueFactory(
                new PropertyValueFactory<Journal, Integer>("kkalTask")
        );

        Choise.getItems().setAll


                ("Отжимания", "Скакалка", "Приседания");


        Choise.setValue("Отжимания");


        ObservableList<Journal> listfromfile = read();
        System.out.println("Lists equal? "+ listfromfile.equals(data));




        //ReadData();
        tableTask.setItems(listfromfile);
    }

    public class TASK implements java.io.Serializable

    {



        public int CALORY;
        private static final long serialVersionUID = 1L;



        public TASK(int Cal)
        {

            CALORY=Cal;
        }
    }

    @XmlRootElement
    public class JM {

        Journal JData;

        @XmlElement(name="journal")
        public Journal getJData() { return JData; }

        public void setJData(Journal JData) { this.JData = JData; }

    }



    public void buttonClicked(javafx.event.ActionEvent actionEvent) throws IOException  {
        BT = StartButton.getText();

        if (BT == "Конец Упражнений") { StartButton.setText("Начать");


            stop();

            TM = TIMER.getText();

            FTimer = Integer.parseInt(TM);

            reset();


            FCAl = FTimer * getCbTextKkal();

            ALLCAL =+ FCAl;

            CaloryMeter.setText(
                    String.valueOf(ALLCAL)
            );
            TaskSerialized();
            data.add(new Journal(

                    Choise.getValue(), FTimer, FCAl)
            );

            tableTask.setItems(data);
            write(data);

        } else { StartButton.setText("Конец Упражнений");
            start();
        }
    }
    public static void main(String[] args){}

    Runnable run = new Runnable() {
        @Override
        public synchronized void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (STOP == false) {
                        sec++;TIMER.setText(""+sec);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    public void start() {

        Thread thread = new Thread(run);
        thread.start();

        STOP = false;
    }
    public void reset() {

        sec = 0;


        TIMER.setText(""+sec);
    }
    public void stop() {

        STOP = true;
    }
    int sec = 0;
    boolean STOP = false;


    public String NameOfFile = "src/sample/file.dat";
    public void TaskSerialized()
    {
        TASK object = new TASK(ALLCAL);

        try
        {



            FileOutputStream file = new FileOutputStream(NameOfFile);

            ObjectOutputStream Output = new ObjectOutputStream(file);


            Output.writeObject(object);
            Output.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }


    public void TaskDeserialized()
    {
        TASK object1 = null;
        try
        {



            FileInputStream file;

            file = new FileInputStream(NameOfFile);

            ObjectInputStream input;

            input = new ObjectInputStream(file);


            object1 = (TASK)input.readObject();

            input.close();
            file.close();
            ALLCAL = object1.CALORY;
        }
        catch(IOException ex)

        { System.out.println("IOException is caught"); } catch(ClassNotFoundException ex)
        { System.out.println("ClassNotFoundException is caught"); }
    }

    public int getCbTextKkal() {
        int CalVal = 0;
        CBV = Choise.getValue();
        if (CBV=="Отжимания") { CalVal = 100; } else if(CBV=="Приседания") { CalVal = 150; }
        else CalVal = 50;
        return CalVal;
    }

    private static void write(List<Journal> Member ) {
        try {
            Path file = Paths.get("src/sample/journal.ser");

            OutputStream FS;

            FS = Files.newOutputStream(file);

            ObjectOutputStream Output;

            Output = new ObjectOutputStream(FS);

            List<JournalDto> dtos = Member.stream().map(d -> new JournalDto(

                    d.getNameTask(),

                    d.getTimeTask(),

                    d.getKkalTask()))
                    .collect(Collectors.toList());

            Output.writeObject(dtos);

            Output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static ObservableList<Journal> read() {
        try {


            Path file = Paths.get("src/sample/journal.ser");

            InputStream input;

            input = Files.newInputStream(file);

            ObjectInputStream ObjectInput;

            ObjectInput = new ObjectInputStream(input);


            List<Journal> data = ((List<JournalDto>) ObjectInput.readObject()).stream()
                    .map(o -> new Journal(
                            o.getNameTask(),
                            o.getTimeTask(),
                            o.getKkalTask()))
                    .collect(Collectors.toList());

            return FXCollections.observableList(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }
}