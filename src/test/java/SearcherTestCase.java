import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Searcher;

public class SearcherTestCase {

     private Searcher searcher;

    @BeforeEach
     @SuppressWarnings("unused")
    void setUp(){
        searcher= new Searcher();
    }

    @Test
    @DisplayName("Exact phrase exist in the list")
    void exactPhrase(){
        List<String> frases= new ArrayList<>();
        frases.add("Hola");
        frases.add("Adios");
        
        boolean result = searcher.searchExactPhrase("Adios", frases);
        assertTrue(result);
    }

    @Test
    @DisplayName("Exact phrase dont exist in the list")
    void falseList(){
        List<String> frases= new ArrayList<>();
        frases.add("Cuatro");
        frases.add("Cinco");
        
        Boolean result = searcher.searchExactPhrase("Hola", frases);
        assertFalse(result);
    }

    @Test
    @DisplayName("Empty list")
    void emptyList(){
        List<String> frases= new ArrayList<>();
        
        Boolean result = searcher.searchExactPhrase("Hola", frases);
        assertFalse(result);
    }

    @Test
    @DisplayName("Search with null phrase returns false")
    void nullPhrase() {
        List<String> frases = new ArrayList<>();
        frases.add("Hola");
        frases.add("Adios");

        boolean result = searcher.searchExactPhrase(null, frases);
        assertFalse(result);
}

    @Test
    @DisplayName("Search word")
    void searchWord(){
        List<String> frases = new ArrayList<>();
        frases.add("Hola");
        frases.add("Adios");
        boolean result = searcher.searchWord("Hola", frases);
        assertTrue(result);
    }

    @Test
    @DisplayName("Word is in an index")
    void wordInIndex() {
        List<String> frases = new ArrayList<>();
        frases.add("Mesa");
        frases.add("Silla");

        String result = searcher.getWordByIndex(frases, 1);
        assertEquals("Silla", result);
    }

    @Test
    @DisplayName("invalid index")
    void invalidIndex() {
        List<String> frases = new ArrayList<>();
        frases.add("Mesa");
        frases.add("Silla");

        String result = searcher.getWordByIndex(frases, 3);
        assertEquals(null, result);
    }

    @Test
    @DisplayName("invalid index")
    void invalidIndex2() {
        List<String> frases = new ArrayList<>();
        frases.add("Mesa");
        frases.add("Silla");

        String result = searcher.getWordByIndex(frases, -2);
        assertEquals(null, result);
    }

    @Test
    @DisplayName("Search word that start by the prefix")
    void prefixWord(){
        List<String> frases = new ArrayList<>();
        frases.add("Hola");
        frases.add("Adios");
        List<String> result = searcher.searchByPrefix("H", frases);
        assertEquals("Hola", result.get(0));
    }
    @Test
    @DisplayName("Search word that dont are in the prefix list")
    void falsePrefix(){
        List<String> frases = new ArrayList<>();
        frases.add("Hola");
        frases.add("Adios");
        List<String> result = searcher.searchByPrefix("S", frases);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Search for the words that have the keywords")
    void wordsByKeywords(){
        List<String> frases = new ArrayList<>();
        frases.add("Hola mi casa es muy buena");
        frases.add("Adios");
        List<String> result = searcher.filterByKeyword("casa", frases);
        assertEquals("Hola mi casa es muy buena", result.get(0));
    }

    @Test
    @DisplayName("Search for the words that dont have the keywords")
    void noWordsByKeywords(){
        List<String> frases = new ArrayList<>();
        frases.add("Hola mi casa es muy buena");
        frases.add("Adios");
        List<String> result = searcher.filterByKeyword("perro", frases);
        assertTrue(result.isEmpty());
    }
}
