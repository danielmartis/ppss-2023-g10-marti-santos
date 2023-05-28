import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import org.junit.jupiter.api.*;

import ppss.matriculacion.dao.DAOException;
import ppss.matriculacion.dao.FactoriaDAO;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;

public class AlumnoDAOIT {

    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/matriculacion", "root", "ppss");
        connection = databaseTester.getConnection();
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA1() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        alumno.setFechaNacimiento(LocalDate.of(1985,02,22));
        alumno.setDireccion("");
        alumno.setEmail("");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        Assertions.assertDoesNotThrow(()->new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @Tag("Integracion-fase1")
    public void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        alumno.setFechaNacimiento(LocalDate.of(1982,02,22));
        alumno.setDireccion("");
        alumno.setEmail("");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException de = Assertions.assertThrows(DAOException.class,()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD", de.getMessage());
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA3() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setFechaNacimiento(LocalDate.of(1985,02,22));


        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException de = Assertions.assertThrows(DAOException.class,()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD", de.getMessage());
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA4() throws Exception {
        AlumnoTO alumno = null;



        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException de = Assertions.assertThrows(DAOException.class,()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        Assertions.assertEquals("Alumno nulo", de.getMessage());
    }
    @Tag("Integracion-fase1")
    @Test
    public void testA5() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982,02,22));


        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException de = Assertions.assertThrows(DAOException.class,()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        Assertions.assertEquals("Error al conectar con BD", de.getMessage());
    }
    @Tag("Integracion-fase1")
    @Test
    public void testB1() throws Exception {
        String nif = "11111111A";

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        Assertions.assertDoesNotThrow(()->new FactoriaDAO().getAlumnoDAO().delAlumno(nif));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Tag("Integracion-fase1")
    @Test
    public void testB2() throws Exception {
        String nif = "33333333A";

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException de = Assertions.assertThrows(DAOException.class, ()->new FactoriaDAO().getAlumnoDAO().delAlumno(nif));

        Assertions.assertEquals("No se ha borrado ningun alumno", de.getMessage());
    }





}
