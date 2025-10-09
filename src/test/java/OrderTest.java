import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;  
import com.example.model.Article;
import com.example.model.Order;

public class OrderTest {

    Order prueba;
    
    @BeforeEach
    public void setUp(){
        prueba = new Order("PED-001");
        prueba.addArticulo(new Article("Laptop",2, 1200.0, 0.15));
        prueba.addArticulo(new Article("Teléfono",2, 600.0, 0.10));
        prueba.addArticulo(new Article("Auriculares",1, 50.0, 0.0));
    }
    
    @Test
    @DisplayName("Test to check order total")
    void testOrderTotal(){
        double result = prueba.getGrossTotal();
        assertEquals(result, 3650.0);
    }
    @Test
    @DisplayName("Test to get the pedido id")
    void testgetIdPedido(){
        String result = prueba.getidPedido();
        assertEquals(result, "PED-001");
    }

    @Test
    @DisplayName("Test to check order total")
    void testSetIdPedido(){
        prueba.setidPedido("3650");
        assertEquals("3650", prueba.getidPedido());
    }

    @Test
    @DisplayName("Test to check Discounted Total")
    void testDiscountedTotal(){
        double result = prueba.getDiscountedTotal();
        assertEquals(result, 3170.0);
    }

    @Test
    @DisplayName("add article test")
    void testAddArticle(){
        Article nuevo = new Article("Tablet", 1, 300.0, 0.05);
        int sizeBefore = prueba.getArticulos().size();
        prueba.addArticulo(nuevo);
        assertEquals(sizeBefore + 1, prueba.getArticulos().size());
        assertTrue(prueba.getArticulos().contains(nuevo));
    }

    @Test
    @DisplayName("Test setArticulos method")
        void testSetArticulos() {
        List<Article> nuevaLista = new ArrayList<>();
        nuevaLista.add(new Article("Monitor", 1, 200.0, 0.1));
        prueba.setArticulos(nuevaLista);
        assertEquals(1, prueba.getArticulos().size());
        assertEquals("Monitor", prueba.getArticulos().get(0).getNombre());
    }

    @Test
    @DisplayName("Test totals with empty order")
        void testEmptyOrderTotals() {
        Order emptyOrder = new Order("EMPTY");
        assertEquals(0.0, emptyOrder.getGrossTotal());
        assertEquals(0.0, emptyOrder.getDiscountedTotal());
    }
    
    @Test
    @DisplayName("Test toString method")
        void testToString() {
        String str = prueba.toString();
        assertTrue(str.contains("PED-001"));
        assertTrue(str.contains("Laptop"));
        assertTrue(str.contains("grossTotal="));
        assertTrue(str.contains("discountedTotal="));
    }
}
