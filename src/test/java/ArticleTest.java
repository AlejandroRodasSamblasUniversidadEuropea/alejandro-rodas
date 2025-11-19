import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.model.Article;

public class ArticleTest {
    
    private Article article;

    @BeforeEach
    public void setUp() {
        article = new Article("Laptop", 2, 500.0, 0.1);
    }

    @Test
    @DisplayName("getNombre test")
    void testGetNombre (){
        assertEquals("Laptop", article.getNombre());
    }

    @Test
    @DisplayName("setNombre test")
    void testSetNombre(){
        article.setNombre("Tablet");
        assertEquals("Tablet", article.getNombre());
    }

    @Test
    @DisplayName("getCantidad test")
    void testGetCantidad(){
        assertEquals(2, article.getCantidad());
    }

    @Test
    @DisplayName("setCantidad test")
    void testSetCantidad(){
        article.setCantidad(1);
        assertEquals(1, article.getCantidad());
    }

    @Test
    @DisplayName("getPrecioTotal test")
    void testGetPrecioTotal(){
        assertEquals(1000, article.getPrecioTotal());
    }

    @Test
    @DisplayName("getPrecioPorUnidad test")
    void testGetPrecioPorUnidad(){
        assertEquals(500, article.getPrecioPorUnidad());
    }

    @Test
    @DisplayName("setPrecio test")
    void testSetPrecio(){
        article.setPrecio(250);
        assertEquals(250, article.getPrecioPorUnidad());
    }

    @Test
    @DisplayName("getDescuento test")
    void testGetDescuento(){
        assertEquals(0.1, article.getDescuento());
    }

    @Test
    @DisplayName("setDescuento test")
    void testSetDescuento() {
        article.setDescuento(0.5);
        assertEquals(0.5, article.getDescuento());
    }

    @Test
    @DisplayName("getPrecioConDescuento test")
    void testGetPrecioConDescuento(){
        assertEquals(900, article.getPrecioConDescuento());
    }


    @Test
    @DisplayName("setGrossAmount test")
    void testSetGrossAmount() {
        assertEquals(2, article.setGrossAmount());
    }

    @Test
    @DisplayName("Precio total con cantidad 0")
    void testCantidadCero() {
        article.setCantidad(0);
        assertEquals(0, article.getPrecioTotal());
        assertEquals(0, article.getPrecioConDescuento());
    }

    @Test
    @DisplayName("Precio con descuento 0")
    void testDescuentoCero() {
        article.setDescuento(0);
        assertEquals(1000, article.getPrecioConDescuento());
    }

    @Test
    @DisplayName("Precio unitario 0")
    void testPrecioCero() {
        article.setPrecio(0);
        assertEquals(0, article.getPrecioPorUnidad());
        assertEquals(0, article.getPrecioTotal());
    }

    @Test
    @DisplayName("Descuento alto (50%)")
    void testDescuentoAlto() {
        article.setDescuento(0.5);
        assertEquals(500, article.getPrecioConDescuento());
    }

    @Test
    @DisplayName("Nombre vacío")
    void testNombreVacio() {
        article.setNombre("");
        assertEquals("", article.getNombre());
    }
}
