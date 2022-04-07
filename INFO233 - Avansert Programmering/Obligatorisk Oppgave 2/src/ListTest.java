
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    private IList<Integer> myList;
    private IList<Integer> enList;
    private IList<Integer> flerList;

    @BeforeEach
    void setup(){
        // Lag base lister for mange av testene
        myList = new LinkedList<>();
        enList = new LinkedList<>(69);
        flerList = new LinkedList<>(420);
        flerList.add(666);
        flerList.put(8008);
    }
    // Tester for deloppg 1.1 - 0 elements
    @Test
    void test_first_zero(){
        assertThrows(NoSuchElementException.class, ()-> myList.first());
    }

    @Test
    void test_rest_zero(){
        test_rest_zero_or_one(myList);
        assertTrue(myList.isEmpty());
    }

    @Test
    void test_add_zero(){
        // sjekk at den er tom først, add et par integer og sjekk at size går opp
        // Sjekk at 5 og 2 er i listen, random sjekk at 0 ikke er der og at den ikke er "tom"
        assertTrue(myList.isEmpty());
        myList.add(5);
        assertEquals(1, myList.size());
        assertEquals(5, (int) myList.first());
        myList.add(2);
        assertEquals(2, myList.size());
        //Make sure first er still.. first..
        assertEquals(5, (int) myList.first());
        assertTrue(myList.contains(5));
        assertTrue(myList.contains(2));
        assertFalse(myList.contains(0));
        assertFalse(myList.isEmpty());

    }

    @Test
    void test_put_zero(){
        // sjekk at den er tom
        assertTrue(myList.isEmpty());
        // Put 3 i listen
        myList.put(3);
        //Sjekk at size gikk opp, og at 3 er i listen
        assertEquals(1, myList.size());
        assertEquals(3, (int) myList.first());
        myList.put(7);
        // samme for 7, size og at den er first
        assertEquals(2, myList.size());
        assertEquals(7, (int) myList.first());
        // sjekk at contains finner dem
        assertTrue(myList.contains(3));
        assertTrue(myList.contains(7));
        // sjekk at 0 ikker der og at liten ikke regnes som tom
        assertFalse(myList.contains(0));
        assertFalse(myList.isEmpty());
    }

    @Test
    void test_remove_zero(){
        // Forventer at den straight ut kaster NoSuchElementException
        assertThrows(NoSuchElementException.class, ()->myList.remove());
        assertTrue(myList.isEmpty());
    }

    // Test for deloppg 1.2 - 1 element
    @Test
    void test_first_one(){
        // sjekk at listen ikke er tom, sjekk at 69 er first og at den er i listen
        assertFalse(enList.isEmpty());
        assertEquals(69, (int) enList.first());
        assertTrue(enList.contains(69));
    }

    @Test
    void test_rest_one(){
        // Rest med 1 fungere på lik måte som 0, derfor brukes amme funksjon her
        test_rest_zero_or_one(enList);
        assertFalse(enList.isEmpty());
    }

    @Test
    void test_add_one(){
        // Tester å adde, sjekker at 69 fremdeles er first i listen
        assertEquals(69, (int) enList.first());
        assertTrue(enList.add(123));
        assertEquals(69, (int) enList.first());
        // sjekker size blir incrementet
        assertEquals(2, enList.size());
        assertTrue(enList.add(777));
        assertEquals(69, (int) enList.first());
        assertEquals(3, enList.size());
        // sjekk at de vi added er der
        assertTrue(enList.contains(123));
        assertTrue(enList.contains(777));
    }

    @Test
    void test_put_one(){
        // putt elementer i listen, sjekker at first forandrer seg
        assertEquals(69, (int) enList.first());
        assertTrue(enList.put(321));
        assertEquals(321, (int) enList.first());
        // sjekker size
        assertEquals(2, enList.size());
        assertTrue(enList.put(11111));
        assertEquals(11111, (int) enList.first());
        assertEquals(3, enList.size());
        // sjekker at de er i listen
        assertTrue(enList.contains(321));
        assertTrue(enList.contains(11111));
    }

    @Test
    void test_remove_one(){
        // tester å remove first, sjekker at den som ble removed er 69, sjekk at listen er tom nå, og at den caster exception
        assertFalse(enList.isEmpty());
        int removed = enList.remove();
        assertEquals(69, removed);
        assertTrue(enList.isEmpty());
        assertThrows(NoSuchElementException.class, ()-> enList.remove());
    }
    // Siden rest fungere likt for tom og liste med 1 har jeg en felles sjekk for dem her
    void test_rest_zero_or_one(IList<Integer> listo){
        IList<Integer> restList = listo.rest();
        assertEquals(0, restList.size());
        assertTrue(restList instanceof LinkedList);

        assertTrue(restList.isEmpty());
        assertThrows(NoSuchElementException.class, restList::first);
    }
    // Test for deloppg 1.3 - 2 or more elements
    @Test
    void test_first_more(){
        // sjekk at den ikker er tom, sjekk at size er korrekt, og at first fungere
        assertFalse(flerList.isEmpty());
        assertEquals(3, flerList.size());
        assertEquals(8008, (int) flerList.first());
    }

    @Test
    void test_rest_more(){
        // lag en liste med rest, sjekk at listen ikke er tom, at size er 1 mindre en flerlist, og at first er nr2 i lista
        IList<Integer> restList = flerList.rest();
        assertFalse(restList.isEmpty());
        assertEquals(2, restList.size());
        assertEquals(8008, (int) flerList.first());
    }

    @Test
    void test_add_more(){
        // add mange tall til listen, sjekk size, og tilslutt sjekk at talla er der
        assertEquals(8008, (int) flerList.first());
        assertEquals(3, flerList.size());
        assertTrue(flerList.add(4444));
        assertEquals(4, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertTrue(flerList.add(42));
        assertEquals(5, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertTrue(flerList.contains(42));
        assertTrue(flerList.contains(4444));
        assertTrue(flerList.contains(420));
        assertTrue(flerList.contains(666));
        assertTrue(flerList.contains(8008));
    }

    @Test
    void test_put_more(){
        // putter mange tall, sjekker at de er der og at size går opp
        assertEquals(8008, (int) flerList.first());
        assertEquals(3, flerList.size());
        assertTrue(flerList.put(4444));
        assertEquals(4, flerList.size());
        assertEquals(4444, (int) flerList.first());
        assertTrue(flerList.put(42));
        assertEquals(5, flerList.size());
        assertEquals(42, (int) flerList.first());
        assertTrue(flerList.contains(42));
        assertTrue(flerList.contains(4444));
        assertTrue(flerList.contains(420));
        assertTrue(flerList.contains(666));
        assertTrue(flerList.contains(8008));
    }

    @Test
    void test_remove_more(){
        int removed;
        // fjerner et tall av gangen, sjekker at size går ned, at first forandrer seg til forventet
        // og at isempty bare er true når listen er tom
        assertTrue(flerList.remove(420));
        assertEquals(2, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertFalse(flerList.isEmpty());

        assertFalse(flerList.isEmpty());
        removed = flerList.remove();
        assertEquals(8008, removed);
        assertEquals(1, flerList.size());
        assertEquals(666, (int) flerList.first());


        removed = flerList.remove();
        assertEquals(666, removed);
        assertEquals(0, flerList.size());
        assertTrue(flerList.isEmpty());

    }

    @Test
    void test_remove_object(){
        // adder litt flere elementer til flerlist, sjekker at size og first fungere
        flerList.add(111111);
        flerList.add(222222);
        flerList.put(333333);
        assertEquals(6, flerList.size());
        assertEquals(333333,(int) flerList.first());
        // sjekker at 222222 er i listen
        assertTrue(flerList.contains(222222));
        // fjerner 222222, sjekker at den ikke er der, og confirmer size
        flerList.remove(222222);
        assertFalse(flerList.contains(222222));
        flerList.remove(8008);
        assertEquals(4, flerList.size());

        // Samme tester som over men med string...
        IList<String> stringList = new LinkedList<>("Hello");
        stringList.add("Hei");
        stringList.add("4321");
        assertEquals(3, stringList.size());
        assertEquals("Hello", stringList.first());
        assertTrue(stringList.contains("Hei"));
        stringList.remove("Hei");
        assertFalse(stringList.contains("Hei"));
        assertEquals(2, stringList.size());
        assertEquals("Hello", stringList.first());
    }

    @Test
    void test_contains_isEmpty_size(){
        // Tester Size, contains og isEmpty etter put, add, og remove / tom liste
        assertEquals(0, myList.size());
        assertFalse(myList.contains(321));
        assertTrue(myList.isEmpty());

        myList.put(321);

        assertEquals(1, myList.size());
        assertTrue(myList.contains(321));
        assertFalse(myList.isEmpty());

        myList.add(123);

        assertEquals(2, myList.size());
        assertTrue(myList.contains(321));
        assertTrue(myList.contains(123));
        assertFalse(myList.isEmpty());

        myList.remove();
        myList.remove();

        assertEquals(0, myList.size());
        assertFalse(myList.contains(321));
        assertFalse(myList.contains(123));
        assertTrue(myList.isEmpty());
    }

    @Test
    void test_append(){
        // Test liste å append
        IList<String> append1 = new LinkedList<>("Hello");
        append1.add("I am");
        append1.add("String");
        // Liste å appende på
        IList<String> stringList = new LinkedList<>("This");
        stringList.add("is");
        stringList.add("original");
        stringList.add("string");
        stringList.add("list");
        // sjekker sizen på begge
        assertEquals(3, append1.size());
        assertEquals(5, stringList.size());

        stringList.append(append1);
        // bekrefter at size fungere, first er still first og at Hello er der
        assertFalse(stringList.isEmpty());
        assertEquals(8, stringList.size());
        assertEquals("This", stringList.first());
        assertTrue(stringList.contains("Hello"));

        // Test liste å legge til
        IList<Integer> append2 = new LinkedList<>(5463456);
        append2.add(5435345);
        append2.add(345345345);
        append2.add(234234234);
        flerList.append(append2);
        // sjekker at alt gikk som det skal, at first er first og size gikk opp og at et tall fra append2 er der
        assertFalse(flerList.isEmpty());
        assertEquals(7, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertTrue(flerList.contains(5435345));

        // tester append tom på tom
        IList<Integer> append3 = new LinkedList<>();
        myList.append(append3);
        assertEquals(0, myList.size());
        assertTrue(myList.isEmpty());
    }

    @Test
    void test_prepend(){
        // Nermest akkurat samme tester, men at first forandrer seg og
        IList<String> prepend1 = new LinkedList<>("Hello");
        prepend1.add("I am");
        prepend1.add("String");

        IList<String> stringList = new LinkedList<>("This");
        stringList.add("is");
        stringList.add("original");
        stringList.add("string");
        stringList.add("list");

        assertEquals(3, prepend1.size());
        assertEquals(5, stringList.size());

        stringList.prepend(prepend1);

        assertFalse(stringList.isEmpty());
        assertEquals(8, stringList.size());
        assertEquals("String", stringList.first());
        assertTrue(stringList.contains("Hello"));

        IList<Integer> prepend2 = new LinkedList<>(5463456);
        prepend2.add(5435345);
        prepend2.add(345345345);
        prepend2.add(234234234);
        flerList.prepend(prepend2);

        assertFalse(flerList.isEmpty());
        assertEquals(7, flerList.size());
        assertEquals(234234234, (int) flerList.first());
        assertTrue(flerList.contains(5435345));

        IList<Integer> prepend3 = new LinkedList<>();
        myList.prepend(prepend3);
        assertEquals(0, myList.size());
        assertTrue(myList.isEmpty());

    }

    @Test
    void test_concat(){

        // 3 test lister
        IList<String> conc1 = new LinkedList<>("Hello");
        conc1.add("I am");
        conc1.add("String");

        IList<String> conc2 = new LinkedList<>("This");
        conc2.add("is");
        conc2.add("2nd");
        conc2.add("string");
        conc2.add("list");

        IList<String> conc3 = new LinkedList<>("1");
        conc3.add("2");
        conc3.add("3");
        conc3.add("4");
        conc3.add("5");

        IList<String> newList = new LinkedList<>();
        // lager en ny liste hvor alle 3 blir slått sammen
        newList = newList.concat(conc1, conc2, conc3);
        // tester for at ett element fra hver liste er der, og at size fungere
        assertFalse(newList.isEmpty());
        assertEquals("Hello", newList.first());
        assertEquals(13, newList.size());
        assertTrue(newList.contains("I am"));
        assertTrue(newList.contains("is"));
        assertTrue(newList.contains("1"));

        // test på tomme lister
        IList<String> tom1 = new LinkedList<>();
        IList<String> tom2 = new LinkedList<>();
        IList<String> tom3 = new LinkedList<>();
        IList<String> addTo = new LinkedList<>();
        addTo = addTo.concat(tom1, tom2, tom3);
        assertTrue(addTo.isEmpty());
        assertEquals(0, addTo.size());
    }

    @Test
    void test_filter_a(){
        // test liste fylltes
        myList.add(555);
        myList.add(444);
        myList.add(1000);
        myList.add(111);
        myList.put(2000);
        // fjern alle over 999
        myList.filter(s->s  > 999);
        // sjekk at det fungerte..
        assertEquals(3, myList.size());
        assertFalse(myList.isEmpty());
        assertTrue(myList.contains(555));
        assertTrue(myList.contains(111));
        assertFalse(myList.contains(1000));
        assertFalse(myList.contains(2000));
    }

    @Test
    void test_filter_b(){
        // test liste
        IList<String> iList = new LinkedList<>();
        iList.add("Bjørn");
        iList.add("Slange");
        iList.put("Banan");
        iList.put("Kake");
        // fjerne slange
        iList.filter(s->s.equals("Slange"));
        // sjekk at slange er vekke og ikke resten
        assertEquals(3, iList.size());
        assertFalse(iList.isEmpty());
        assertTrue(iList.contains("Bjørn"));
        assertTrue(iList.contains("Banan"));
        assertFalse(iList.contains("Slange"));
    }

    @Test
    void test_map(){
        // test liste
        myList.add(33);
        myList.add(66);
        myList.put(99);
        myList.put(435);
        // ligg til 5 på alle
        IList<Integer> result = myList.map(s->s + 5);
        assertEquals(440, (int)result.first());
        // sjekk at result ikke er tom, at tall +5 er der, men ikke original tallet
        assertFalse(result.isEmpty());
        assertTrue(result.contains(38));
        assertTrue(result.contains(104));
        assertFalse(result.contains(33));
        assertFalse(result.contains(99));
        assertEquals(4, result.size());
    }
    @Test
    void test_iterator(){
        int size = flerList.size();
        Integer first = -1;
        // random test som iterere gjennom, fjerner stuff
        for (Integer item : flerList){
            assertNotEquals(first, flerList.first());
            first = item;
            flerList.remove(first);
            size--;
            assertEquals(size, flerList.size());
        }
    }


    @Test
    void test_toArray(){
        LinkedList<Integer> testList = new LinkedList<>(69);
        testList.put(666);
        testList.add(420);
        Object[] testArray =  testList.toArray();
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(testArray[i]);
        }
    }

        @Test
        void oppg8_sortIntegers() {
            // Se oppgave 8
            IList<Integer> list = new LinkedList<>();
            List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6,
                    1, 2, 9, 5);

            for (Integer value : values) {
                list.add(value);
            }
            list.sort(Comparator.comparingInt(x -> x));


            int n = list.remove();
            while (list.size() > 0) {
                int m = list.remove();
                if (n > m) {
                    fail("Integer list is not sorted.");
                }
                n = m;
            }
        }

        @Test
        void oppg8_sortStrings() {
            // Se oppgave 8
            IList<String> list = new LinkedList<>();
            List<String> values = Arrays.asList(
                    "g", "f", "a", "c", "b", "d", "e", "i", "j", "h"
            );
            for (String value : values) {
                list.add(value);
            }

            list.sort(Comparator.naturalOrder());

            String n = list.remove();
            while (list.size() > 0) {
                String m = list.remove();
                if (n.compareTo(m) > 0) {
                    fail("String list is not sorted.");
                }
                n = m;
            }
        }

        @Test
        void oppg9_filter() {
            // Se oppgave 9
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            IList<Integer> list = new LinkedList<>();
            for (Integer value : values) {
                list.add(value);
            }

            list.filter(n -> n % 2 == 1);


            while(list.size() > 0) {
                int n = list.remove();
                if (n % 2 == 1) {
                    fail("List contains filtered out elements.");
                }
            }

        }

        @Test
        void oppg10_map() {
            // Se oppgave 10
            List<String> values = Arrays.asList("1", "2", "3", "4", "5");

            IList<String> list = new LinkedList<>();
            for (String value : values) {
                list.add(value);
            }

            IList<Integer> result = list.map(Integer::parseInt);

            List<Integer> target = Arrays.asList(1, 2, 3, 4, 5);


            for (int t : target) {
                if (result.remove() != t) {
                    fail("Result of map gives the wrong value.");
                }
            }
        }

        @Test
        void oppg11_reduceInts() {
            // Se oppgave 11
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

            IList<Integer> list = new LinkedList<>();
            for (Integer value : values) {
                list.add(value);
            }

            int result = list.reduce(0, Integer::sum);

            assertEquals(result, 5*((1 + 5)/2));
        }

        @Test
        void oppg11_reduceStrings() {
            List<String> values = Arrays.asList("e", "s", "t");
            IList<String> list = new LinkedList<>();
            for (String s : values) {
                list.add(s);
            }

            String result = list.reduce("t", (acc, s) -> acc + s);

            assertEquals(result, "test");
        }

        @Test
        void ex1_FastSort() {
            // Se ekstraoppgave 1
            Random r = new Random();
            IList<Integer> list = new LinkedList<>();
            for (int n = 0; n < 1000000; n++) {
                list.add(r.nextInt());
            }

            assertTimeout(Duration.ofSeconds(2), () -> list.sort(Integer::compare));

            int n = list.remove();
            for(int m = list.remove(); !list.isEmpty(); m = list.remove()) {
                if (n > m) {
                    fail("List is not sorted");
                }
                n = m;
            }
        }
    }