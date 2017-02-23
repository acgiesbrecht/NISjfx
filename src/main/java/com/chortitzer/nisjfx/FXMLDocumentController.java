/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.nisjfx;

import com.chortitzer.nisjfx.domain.TblIndUsiRegistroLecturas;
import com.vividsolutions.jts.geom.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Waypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class FXMLDocumentController implements Initializable {

    private static final Logger logger = LogManager.getLogger(FXMLDocumentController.class);
    @FXML
    private TextArea txtStatus;
    @FXML
    private TextField txtFileRegistro;
    @FXML
    private Button cmdFileChooserRegistro;
    @FXML
    private Button cmdActionRegistro;
    @FXML
    private Button cmdActionUpdateDB;
    @FXML
    private TextField txtFileUpdateDB;
    @FXML
    private Button cmdFileChooserUpdateDB;
    @FXML
    private Button cmdActionConvert;

    EntityManager em = Persistence.createEntityManagerFactory("energia_PU").createEntityManager();
    Task taskRegistro;
    Task taskUpdateDB;
    Task taskConvert;

    File registroDir;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtStatus.setText("");
        txtFileRegistro.setOnDragOver((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        // Dropping over surface
        txtFileRegistro.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                String filePath = null;
                for (File file : db.getFiles()) {
                    filePath = file.getAbsolutePath();
                    txtFileRegistro.setText(filePath);
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    @FXML
    private void chooseFileRegistro(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            /*fileChooser.getExtensionFilters().add(new ExtensionFilter("Archivos de Lectura", "*.xml"));
            /if (registroDir != null) {
                fileChooser.setInitialDirectory(registroDir);
            }
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                txtFileRegistro.setText(selectedFile.getPath());
                registroDir = selectedFile.getParentFile();
            }*/
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if ((new File(txtFileRegistro.getText())).exists()) {
                directoryChooser.setInitialDirectory(new File(txtFileRegistro.getText()));
            }
            File selectedFile = directoryChooser.showDialog(null);
            if (selectedFile != null) {
                txtFileRegistro.setText(selectedFile.getPath());
                registroDir = selectedFile.getParentFile();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    @FXML
    private void actionRegistro(ActionEvent event) {
        try {
            taskRegistro = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        List<File> registroFileList = new ArrayList<>();
                        String status;
                        if (!txtFileRegistro.getText().equals("")) {
                            Path startPath = Paths.get(txtFileRegistro.getText());
                            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                                @Override
                                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                    String path = file.toString();
                                    String extension = path.substring(path.length() - 3, path.length());

                                    if (extension.equals("xml") && path.contains("registros_")) {
                                        registroFileList.add(new File(path));
                                        System.out.println((new File(path)).getName());
                                    }
                                    return FileVisitResult.CONTINUE;
                                }
                            });
                            status = "";
                            if (registroFileList.isEmpty()) {
                                status = status + "No se encotnraron archivos de registro en la cartpeta seleccionada.\n";
                                updateMessage(status);
                            }
                            for (File registroFile : registroFileList) {
                                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                                Document doc = docBuilder.parse(registroFile);
                                doc.getDocumentElement().normalize();

                                NodeList listOfReg = doc.getElementsByTagName("tblRegistros");
                                int count = 0;
                                status = status + "INICIADO.\n\n" + registroFile.getName() + "\n";
                                updateMessage(status);
                                for (int s = 0; s < listOfReg.getLength(); s++) {

                                    Node regNode = listOfReg.item(s);

                                    if (regNode.getNodeType() == Node.ELEMENT_NODE) {

                                        Element nisElement = (Element) regNode;
                                        NodeList listData = nisElement.getChildNodes();

                                        int nroSerie = Integer.parseInt(listData.item(1).getTextContent());
                                        Date fechaHora = javax.xml.bind.DatatypeConverter.parseDateTime(listData.item(3).getTextContent()).getTime();

                                        em.getTransaction().begin();

                                        TblIndUsiRegistroLecturas lectura = em.find(TblIndUsiRegistroLecturas.class, fechaHora);

                                        if (lectura == null) {
                                            TblIndUsiRegistroLecturas lecturaNew = new TblIndUsiRegistroLecturas();

                                            lecturaNew.setNroserie(nroSerie);
                                            lecturaNew.setFechahora(fechaHora);
                                            lecturaNew.setKwh(Integer.parseInt(listData.item(5).getTextContent()));
                                            lecturaNew.setKvar(Integer.parseInt(listData.item(7).getTextContent()));

                                            em.persist(lecturaNew);

                                            count++;
                                        }
                                        em.getTransaction().commit();
                                        updateMessage(status + "Se guardaron " + String.valueOf(count) + " lecturas.");
                                    }
                                }

                                status = status + "Se guardaron " + String.valueOf(count) + " lecturas.\n\n" + "FINALIZADO.";
                                updateMessage(status);
                            }
                        }

                    } catch (Exception ex) {
                        String s = ex.getMessage();
                        if (s.indexOf("Duplicate entry") > 0) {
                            JOptionPane.showMessageDialog(null, "Ya se guardò este archivo.");
                        } else {
                            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
                            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
                        }
                    }
                    return null;
                }
            };
            txtStatus.textProperty().bind(taskRegistro.messageProperty());
            new Thread(taskRegistro).start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    @FXML
    private void actionUpdateDB(ActionEvent event) {
        try {
            taskUpdateDB = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        String status = "";
                        List<File> shapeFileList;
                        int total = 0;
                        int count = 0;

                        status = "INICIADO.\n\n";
                        updateMessage(status);
                        shapeFileList = new ArrayList<>();
                        Path startPath = Paths.get(txtFileUpdateDB.getText());
                        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                //file = rename(file);
                                String path = file.toString();
                                String extension = path.substring(path.length() - 3, path.length());

                                if (extension.equals("shp")) {
                                    shapeFileList.add(new File(path));
                                }
                                return FileVisitResult.CONTINUE;
                            }
                        });
                        total = shapeFileList.size();
                        status += "Archivos encontrados: " + String.valueOf(total) + "\n\nGuardando en Base:\n\n";
                        updateMessage(status);
                        for (File file : shapeFileList) {

                            Map shpConnectParams = new HashMap();
                            shpConnectParams.put("url", file.toURI().toURL());

                            FileDataStore shpStore = FileDataStoreFinder.getDataStore(file);

                            String shpTypeName = shpStore.getTypeNames()[0];
                            FeatureSource shpFS = shpStore.getFeatureSource(shpTypeName);

                            SimpleFeatureType shpSchema = (SimpleFeatureType) shpFS.getSchema();
                            CoordinateReferenceSystem crs = shpSchema.getCoordinateReferenceSystem();

                            Map pgParams = new HashMap();
                            pgParams.put("dbtype", "postgis");                   //must be postgis
                            pgParams.put("host", "192.168.1.26");      //the name or ip address of the machine running PostGIS
                            pgParams.put("port", 5432);             //the port that PostGIS is running on (generally 5432)
                            pgParams.put("database", "industria_usi");              //the name of the database to connect to.
                            pgParams.put("user", "postgres");                    //the user to connect with
                            pgParams.put("passwd", "123456789");                 //the password of the user.
                            pgParams.put("schema", "public");   //the schema of the database
                            pgParams.put("create spatial index", Boolean.TRUE);

                            SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
                            builder.addAll(shpSchema.getAttributeDescriptors());
                            builder.setCRS(crs);
                            builder.setName(shpTypeName);
                            DataStore pgDatastore = DataStoreFinder.getDataStore(pgParams);

                            if (pgDatastore.getNames().contains(shpSchema.getName())) {
                                pgDatastore.removeSchema(shpSchema.getName());
                            }
                            pgDatastore.createSchema(builder.buildFeatureType());
                            SimpleFeatureSource pgFS = pgDatastore.getFeatureSource(shpTypeName);
                            SimpleFeatureType pgSchema = pgFS.getSchema();
                            Transaction transaction = new DefaultTransaction("create");

                            if (pgFS instanceof SimpleFeatureStore) {
                                SimpleFeatureStore pgFeatureStore = (SimpleFeatureStore) pgFS;

                                SimpleFeatureCollection features = (SimpleFeatureCollection) shpFS.getFeatures();
                                pgFeatureStore.setTransaction(transaction);
                                try {
                                    pgFeatureStore.addFeatures(features);
                                    transaction.commit();
                                    status += pgSchema.getName().toString() + " - " + String.valueOf(features.size()) + " registros.\n";
                                    updateMessage(status);
                                    count++;
                                } catch (Exception problem) {
                                    status += problem.getMessage() + "\n";
                                    updateMessage(status);
                                    JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + problem.getMessage());
                                    logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), problem);
                                    transaction.rollback();
                                } finally {
                                    transaction.close();
                                }
                            } else {
                                System.out.println(shpTypeName + " does not support read/write access");
                            }
                            pgDatastore.dispose();
                        }
                        status += "\n FINALIZADO.";
                        updateMessage(status);
                    } catch (Exception ex) {
                        updateMessage(ex.getMessage() + "\n");
                        JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
                        logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
                    }
                    return null;
                }
            };
            txtStatus.textProperty().bind(taskUpdateDB.messageProperty());
            new Thread(taskUpdateDB).start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    @FXML
    private void chooseFileUpdateDB(ActionEvent event) {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if ((new File(txtFileUpdateDB.getText())).exists()) {
                directoryChooser.setInitialDirectory(new File(txtFileUpdateDB.getText()));
            }
            File selectedFile = directoryChooser.showDialog(null);
            if (selectedFile != null) {

                txtFileUpdateDB.setText(selectedFile.getPath());
            } else {
                txtFileUpdateDB.setText("No has seleccionado la carpeta de ArcView!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    @FXML
    private void actionConvert(ActionEvent event) {
        try {
            taskConvert = new Task<Void>() {
                @Override
                public Void call() {
                    try {
                        String status = "";

                        Map pgParams = new HashMap();
                        pgParams.put("dbtype", "postgis");                   //must be postgis
                        pgParams.put("host", "192.168.1.26");      //the name or ip address of the machine running PostGIS
                        pgParams.put("port", 5432);             //the port that PostGIS is running on (generally 5432)
                        pgParams.put("database", "industria_usi");              //the name of the database to connect to.
                        pgParams.put("user", "postgres");                    //the user to connect with
                        pgParams.put("passwd", "123456789");                 //the password of the user.
                        pgParams.put("schema", "public");   //the schema of the database
                        pgParams.put("create spatial index", Boolean.TRUE);
                        DataStore pgDatastore = DataStoreFinder.getDataStore(pgParams);

                        SimpleFeatureSource featureSource = pgDatastore.getFeatureSource("nis_usuario");
                        CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:4326");
                        CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:32721");
                        SimpleFeatureCollection collection = featureSource.getFeatures();

                        GPX gpx = new GPX();

                        SimpleFeatureIterator iterator = collection.features();
                        int count = 0;
                        while (iterator.hasNext()) {
                            SimpleFeature feature = iterator.next();

                            boolean lenient = true;

                            MathTransform mathTransform = CRS.findMathTransform(sourceCrs, targetCrs, lenient);
                            Point point = (Point) feature.getDefaultGeometry();
                            DirectPosition2D srcDirectPosition2D = new DirectPosition2D(sourceCrs, point.getX(), point.getY());
                            DirectPosition2D destDirectPosition2D = new DirectPosition2D();
                            mathTransform.transform(srcDirectPosition2D, destDirectPosition2D);

                            Waypoint wpnt = new Waypoint();
                            wpnt.setLatitude(destDirectPosition2D.getX());
                            wpnt.setLongitude(destDirectPosition2D.getY());
                            wpnt.setName(feature.getAttribute("NIS").toString() + "-" + feature.getAttribute("USUARIO").toString());
                            wpnt.setTime(new Date());
                            gpx.addWaypoint(wpnt);

                            count++;
                            status = "Se encontraron " + String.valueOf(count) + " puntos.\n\n";
                            updateMessage(status);
                        }
                        iterator.close();

                        gpx.setVersion("1.1");
                        gpx.setCreator("Giesbrecht");

                        GPXParser parser = new GPXParser();

                        FileOutputStream out = new FileOutputStream("C:\\ArcView\\nis.gpx");
                        parser.writeGPX(gpx, out);
                        out.close();
                        pgDatastore.dispose();
                        status += "FINALIZADO.";
                        updateMessage(status);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
                        logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
                    }
                    return null;
                }
            };
            txtStatus.textProperty().bind(taskConvert.messageProperty());
            new Thread(taskConvert).start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }
}
