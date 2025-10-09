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
        String result = article.getNombre();
        assertEquals(result, "Laptop");
    }

    @Test
    @DisplayName("setNombre test")
    void testSetNombre(){
        assertEquals("Laptop", article.getNombre());
        article.setNombre("Tablet");
        assertEquals("Tablet", article.getNombre());
    }

    @Test
    @DisplayName("getCantidad test")
    void testGetCantidad(){
        int result = article.getCantidad();
        assertEquals(result, 2);
    }

    @Test
    @DisplayName("setCantidad test")
    void testSetCantidad(){
        assertEquals(2, article.getCantidad());
        article.setCantidad(1);
        assertEquals(1, article.getCantidad());
    }

    @Test
    @DisplayName("getPrecioTotal test")
    void testGetPrecioTotal(){
        double result = article.getPrecioTotal();
        assertEquals(result, 1000);
    }

    @Test
    @DisplayName("getPrecioPorUnidad test")
    void testGetPrecioPorUnidad(){
        double result = article.getPrecioPorUnidad();
        assertEquals(result, 500);
    }

    @Test
    @DisplayName("setPrecio test")
    void testSetPrecio(){
        assertEquals(500, article.getPrecioPorUnidad());
        article.setPrecio(250);
        assertEquals(250, article.getPrecioPorUnidad());
    }

    @Test
    @DisplayName("getDescuento test")
    void testGetDescuento(){
        double result = article.getDescuento();
        assertEquals(result, 0.1);
    }

    @Test
    @DisplayName("setDescuento test")
    void testDescuento() {
        assertEquals(0.1, article.getDescuento());
        article.setDescuento(20);
        assertEquals(20, article.getDescuento());
    }
    @Test
    @DisplayName("GetPrecioConDescuento test")
    void testGetPrecioConDescuento(){
        double result = article.getPrecioConDescuento();
        assertEquals(result, 900);
    }
}