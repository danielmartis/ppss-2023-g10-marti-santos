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


public class CineTest {
    int solicitados;
    boolean resultado;
    boolean [] array;
    Cine c;
    @Test
    public void reservaButacasC1() {
        c = new Cine();
        array = new boolean[0];
        solicitados = 3;
        ButacasException exception = assertThrows(ButacasException.class, ()-> c.reservaButacasV1(array,solicitados));
        assertEquals("No se puede procesar la solicitud", exception.getMessage());
    }

    @Test
    public void reservaButacasC2() {
        boolean resultado;
        boolean [] resultado_array = new boolean[0];
        c = new Cine();
        array = new boolean[0];
        solicitados = 0;
        resultado = assertDoesNotThrow(() -> c.reservaButacasV1(array,solicitados), "Excepcion lanzada.");
        assertAll("Butacas test 2",
                () -> assertArrayEquals(array,resultado_array),
                () -> assertEquals(resultado, false)
        );
    }
    @Test
    public void reservaButacasC3() {
        c = new Cine();
        boolean array[] = {false,false,false,true,true};
        solicitados = 2;
        boolean resultado_array[] = {true,true,false,true,true};
        resultado = assertDoesNotThrow(() -> c.reservaButacasV1(array,solicitados), "Excepcion lanzada.");
        assertAll("Butacas test 3",
                () -> assertArrayEquals(array,resultado_array),
                () -> assertEquals(resultado, true)
        );
    }
    @Test
    public void reservaButacasC4() {
        c = new Cine();
        boolean array[] = {true,true,true};
        solicitados = 2;
        boolean resultado_array[] = {true,true,true};
        resultado = assertDoesNotThrow(() -> c.reservaButacasV1(array,solicitados), "Excepcion lanzada.");
        assertAll("Butacas test 3",
                () -> assertArrayEquals(array,resultado_array),
                () -> assertEquals(resultado, false)
        );
    }

    @Tag("parametrizado")
    @ParameterizedTest()
    @MethodSource("casosDePrueba")
    void reservaButacasC5(boolean[] array, boolean[] expected,int sol, boolean expe ){
        c = new Cine();
        resultado = assertDoesNotThrow(() -> c.reservaButacasV1(array,sol), "Excepcion lanzada.");
    assertAll("test5",
            () -> assertArrayEquals(array,expected),
            () -> assertEquals(resultado, expe)
            );
    }

    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(new boolean []{}, new boolean[]{}, 0, false),
                Arguments.of(new boolean[] {false,false,false,true,true}, new boolean[]{true,true,false,true,true}, 2, true),
                Arguments.of(new boolean[]{true,true,true}, new boolean[]{true,true,true}, 1, false)
        );
    }

}
