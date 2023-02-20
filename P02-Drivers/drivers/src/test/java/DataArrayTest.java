package ppss;

import java.util.stream.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;



public class DataArrayTest {
    ppss.DataArray da;
    int [] datos;
    int [] datos_esperados;
    @Test
    public void dataArrayTestC1() {
        int datos[] = {1,3,5,7};
        int datos_esperados[] = {1,3,7};
        da = new ppss.DataArray(datos);
        assertDoesNotThrow(() -> da.delete(5), "Excepcion lanzada");
        assertAll("Butacas test 3",
                () -> assertArrayEquals(da.getColeccion(),datos_esperados),
                () -> assertEquals(da.size(),3)
        );
    }

    @Test
    public void dataArrayTestC2() {
        int datos[] = {1,3,3,5,7};
        int datos_esperados[] = {1,3,5,7};
        da = new ppss.DataArray(datos);
        assertDoesNotThrow(() -> da.delete(3), "Excepcion lanzada");
        assertAll("Butacas test 3",
                () -> assertArrayEquals(da.getColeccion(),datos_esperados),
                () -> assertEquals(da.size(),4)
        );
    }

    @Test
    public void dataArrayTestC3() {
        int datos[] = {1,2,3,4,5,6,7,8,9,10};
        int datos_esperados[] = {1,2,3,4,5,6,7,8,9};
        da = new ppss.DataArray(datos);
        assertDoesNotThrow(() -> da.delete(10), "Excepcion lanzada");

        assertAll("Butacas test 3",
                () -> assertArrayEquals(da.getColeccion(),datos_esperados),
                () -> assertEquals(da.size(),9)
        );
    }

    @Test
    public void dataArrayTestC4() {
        int datos[] = {};
        int datos_esperados[] = {};
        da = new ppss.DataArray(datos);
        ppss.DataException exception = assertThrows(ppss.DataException.class, ()-> da.delete(8));
        assertEquals("No hay elementos en la colección", exception.getMessage());
    }

    @Test
    public void dataArrayTestC5() {
        int datos[] = {1,3,5,7};
        int datos_esperados[] = {};
        da = new ppss.DataArray(datos);
        ppss.DataException exception = assertThrows(ppss.DataException.class, ()-> da.delete(-5));
        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void dataArrayTestC6() {
        int datos[] = {};
        int datos_esperados[] = {};
        da = new ppss.DataArray(datos);
        ppss.DataException exception = assertThrows(ppss.DataException.class, ()-> da.delete(0));
        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void dataArrayTestC7() {
        int datos[] = {1,3,5,7};
        int datos_esperados[] = {};
        da = new ppss.DataArray(datos);
        ppss.DataException exception = assertThrows(ppss.DataException.class, ()-> da.delete(8));
        assertEquals("Elemento no encontrado", exception.getMessage());
    }

    @ParameterizedTest()
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @MethodSource("casosArray")
    public void testParametrizadoC8(DataArray da, int elim, String mensaje){
        DataException de = assertThrows(DataException.class, () -> da.delete(elim));
        assertEquals(mensaje, de.getMessage());

    }

    private static Stream<Arguments> casosArray() {
        return Stream.of(
                Arguments.of(new DataArray(new int[]{}), 8, "No hay elementos en la colección"),
                Arguments.of(new DataArray(new int[]{1,3,5,7}), -5 , "El valor a borrar debe ser > 0"),
                Arguments.of(new DataArray(new int[]{}), 0 ,"Colección vacía. Y el valor a borrar debe ser > 0"),
                Arguments.of(new DataArray(new int[]{1,3,5,7}), 8 , "Elemento no encontrado")
        );
    }

    @ParameterizedTest()
    @Tag("parametrizado")
    @MethodSource("casosArray2")
    public void testParametrizadoC9(DataArray da, int elim, int[] res, int cant){
        assertDoesNotThrow(() -> da.delete(elim), "Excepcion lanzada");

        assertAll("Butacas test 3",
                () -> assertArrayEquals(da.getColeccion(),res),
                () -> assertEquals(da.size(),cant)
        );
    }

    private static Stream<Arguments> casosArray2() {
        return Stream.of(
                Arguments.of(new DataArray(new int[]{1,3,5,7}), 5, new int[]{1,3,7}, 3),
                Arguments.of(new DataArray(new int[]{1,3,3,5,7}), 3 , new int[]{1,3,5,7}, 4),
                Arguments.of(new DataArray(new int[]{1,2,3,4,5,6,7,8,9,10}), 10 ,new int[]{1,2,3,4,5,6,7,8,9}, 9)
        );
    }
}
