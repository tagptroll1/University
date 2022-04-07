import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> implements IList<E>{
    private Node<E> node;
    private Node<E> first;
    private Node<E> last;
    private Integer size;

    public LinkedList(){
        node = new Node<>();
        this.first = node;
        this.last = node;
        this.size = 0;
    }

    public LinkedList(E first){
        node = new Node<>(first);
        this.first = node;
        this.last = node;
        this.size = 1;
    }

    public LinkedList(E first, IList<E> list){
        append(list);
        put(first);
    }

    /**
     * ,* Gir det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E first(){
        if (size <= 0){
            throw new NoSuchElementException("List is empty");      //O(1)
        }
        return this.first.getData();                                //O(1)
    }


    /**
     * ,* Returnerer alle elementene i listen bortsett fra det
     * ,* første.
     * ,*
     * ,* @return Resten av listen.
     * ,
     */
    @Override
    public IList<E> rest() {                                        //O(n)
        if (size <= 1){
            // returner en tom liste om det er 0 eller 1 elementer i originalen.
          return new LinkedList<>();                                //O(1)
        }
        // skip første elemntet
        Node<E> firstNew = first.getNext();                         //O(1)
        IList<E> tempList = new LinkedList<>();                     //O(1)
        // Iterer gjennom alt utensom first og lag ny liste med
        while (firstNew != null){                                   //O(n)
            if (firstNew.hasNext()){
                node = new Node<>(firstNew.getData(), firstNew.getNext());  //O(1)
            } else {
                node = new Node<>(firstNew.getData(), null);   //O(1)
            }
            tempList.add(node.getData());                           //O(1)
            firstNew = firstNew.getNext();                          //O(1)
        }


        return tempList;                                            //O(1)

    }

    /**
     * ,* Legger til et element på slutten av listen.
     * ,
     *
     * @param elem
     */
    @Override
    public boolean add(E elem) {                                    //O(1)
        node = new Node<>(elem);                                    //O(1)
        if (isEmpty()){                                             //O(1)
            first = node;                                           //O(1)
        } else {
            last.changeNext(node);                                  //O(1)
        }
        last = node;                                                //O(1)
        size++;                                                     //O(1)
        return true;                                                //O(1)
    }

    /**
     * ,* Legger til et element på begynnelsen av listen.
     * ,
     *
     * @param elem
     */
    @Override
    public boolean put(E elem) {                                    //O(1)
        node = new Node<>(elem, first);                             //O(1)
        if (isEmpty()){                                             //O(1)
            last = node;                                            //O(1)
        }
        first = node;                                               //O(1)
        size++;                                                     //O(1)
        return true;                                                //O(1)
    }

    /**
     * ,* Fjerner det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E remove() {                                             //O(1)
        if (isEmpty()){                                             //O(1)
            throw new NoSuchElementException("List is empty");      //O(1)
        }

        Node<E> tempStart = first;                                  //O(1)
        if (first.hasNext()){                                       //O(1)
            first = first.getNext();                                //O(1)
        } else {
            first = null;                                           //O(1)
        }
        size--;                                                     //O(1)

        return tempStart.getData();                                 //O(1)
    }

    /**
     * ,* Fjerner det angitte objektet fra listen.
     * ,*
     * ,* @param o Objektet som skal fjernes.
     * ,* @return true hvis et element ble fjernet, false
     * ,* ellers.
     * ,
     *
     * @param o
     */
    @Override
    public boolean remove(Object o) {                               //O(n)
        Node<E> startNode = first;                                  //O(1)
        // Hvis første noden er den vi vil slette
        if (o.equals(startNode.getData())){                         //O(1)
            first = startNode.getNext();                            //O(1)
            size--;                                                 //O(1)
            return true;                                            //O(1)
        }
        // Hvis siste noden er den vi vil slette
        if (o.equals(last)){                                        //O(1)
            while(startNode.getNext() != last){                     //O(n)
                startNode = startNode.getNext();                    //O(1)
            }
            last = startNode;                                       //O(1)
            size--;                                                 //O(1)
            return true;
        }
        // Ellers iterer gjennom alt
        while (startNode!=null){                                    //O(n)
            if (o.equals(startNode.getNext().getData())){           //O(1)
                startNode.changeNext(startNode.getNext().getNext());//O(1)
                size--;                                             //O(1)

                return true;                                        //O(1)
            }
            startNode = startNode.getNext();                        //O(1)

        }
        return false;                                               //O(1)
    }

    /**
     * ,* Sjekker om et element er i listen.
     * ,*
     * ,* @param o objektet vi sjekker om er i listen.
     * ,* @return true hvis objektet er i listen, false ellers.
     * ,
     *
     * @param o
     */
    @Override
    public boolean contains(Object o) {                             //O(n)
        // Enkel iterasjon og sammenligning
        for (E item : this){                                        //O(n)
            if (o.equals(item)){                                    //O(1)
                return true;                                        //O(1)
            }
        }
        return false;                                               //O(1)
    }

    /**
     * ,* Sjekker om listen er tom.
     * ,*
     * ,* @return true hvis listen er tom, false ellers.
     * ,
     */
    @Override
    public boolean isEmpty() {                                      //O(1)
        if (size <= 0){                                             //O(1)
            return true;                                            //O(1)
        } else {
            return false;                                           //O(1)
        }
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* slutten av listen.
     * ,*
     * ,* @param listen som blir lagt til.
     * ,
     *
     * @param list
     */
    @Override
    public void append(IList<? extends E> list) {                   //O(n)
        if (list.isEmpty()) return;                                 //O(1)

        for (E item : list){                                        //O(n)
            if (item != null) {                                     //O(1)
                add(item);                                          //O(1)
            }
        }
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* begynnelsen av listen.
     * ,*
     * ,* @param list listen som blir lagt til
     * ,
     *
     * @param list
     */
    @Override
    public void prepend(IList<? extends E> list) {                  //O(n)
        if (list.isEmpty()) return;                                 //O(1)

        for (E item : list){                                        //O(n)
            put(item);                                              //O(1)
        }
    }

    /**
     * ,* Slår sammen flere lister
     * ,*
     * ,* @param lists listene som skal slås sammen
     * ,* @return Ny liste med alle elementene fra listene som
     * ,* skal slås sammen.
     * ,
     *
     * @param lists
     */
    @Override
    public IList<E> concat(IList<? extends E>... lists) {           //O(n*m)
        IList<E> newList = new LinkedList<>();

        for (IList<? extends  E> list : lists){                     //O(n)
            newList.append(list);                                   //Append er O(m)
        }
        return  newList;                                            //O(1)
    }

    /**
     * ,* Sorterer listen ved hjelp av en
     * ,* sammenligningsfunksjon
     * ,* @param c sammenligningsfunksjon som angir rekkefølgen
     * ,* til elementene i listen
     * ,
     *
     * @param c
     */

    @Override
    public void sort(Comparator<? super E> c) {                     //O(n^2)
        E[] a = this.toArray();
        // Bubble sort arrayet med å swappe 2 verdier
        for (E item : a){                                           //O(n^2)
            for (int i = 0; i < a.length-1; i++) {
                if (c.compare(a[i], a[i+1]) > 0 ){
                    swap(a, i, i+1);
                }
            }
        }
        clear();                                                    //O(1)
        // Clear listen og legg til alle elementene i arrayet.
        for (E item: a) {                                           //O(n)
            add(item);                                              //O(1)
        }
    }


    private void swap(E[] a, int first, int second){                //O(1)
        E firstData = a[first];
        E secondData = a[second];
        a[first] = secondData;
        a[second] = firstData;
    }


    /**
     * ,* Fjerner elementer fra listen som svarer til et
     * ,* predikat.
     * ,* @param filter predikat som beskriver hvilken
     * ,* elementer som skal fjernes.
     * ,
     *
     * @param filter
     */
    @Override
    public void filter(Predicate<? super E> filter) {               //O(n)
        for (E item : this){                                        //O(n)
            if(filter.test(item)){                                  //O(1)
                remove(item);                                       //O(1)
            }
        }
    }

    /**
     * ,* Kjører en funksjon over hvert element i listen
     * ,*
     * ,* @param f en funksjon fra typen til elementene i
     * ,* listen til en annen type
     * ,* @return En liste over elementene som funksjonen
     * ,* returnerer
     * ,
     *
     * @param f
     */
    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {   //O(n)
        IList<U> newList = new LinkedList<>();                      //O(1)

        for (E item : this){                                        //O(n)
            U result = f.apply(item);                               //O(1)
            newList.add(result);                                    //O(1)
        }
        return newList;                                             //O(1)
    }

    /**
     * ,* Slår sammen alle elementene i listen ved hjelp av en
     * ,* kombinasjonsfunksjon.
     * ,*
     * ,* @param t Det første elementet i sammenslåingen
     * ,* @param f funksjonen som slår sammen elementene
     * ,* @return Den akkumulerte verdien av sammenslåingene
     * ,
     */
    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {       //O(n)
        T reduced = t;                                              //O(1)
        for (E item : this) {                                       //O(n)
             reduced = f.apply(reduced, item);                      //O(1)
        }
        return reduced;                                             //O(1)
    }

    /**
     * ,* Gir størrelsen på listen
     * ,*
     * ,* @return Størrelsen på listen
     * ,
     */
    @Override
    public int size() {                                             //O(1)
        return size;                                                //O(1)
    }

    /**
     * ,* Fjerner alle elementene i listen.
     * ,
     */
    @Override
    public void clear() {                                           //O(1)
        first = null;                                               //O(1)
        last = null;                                                //O(1)
        size = 0;                                                   //O(1)
    }

    //Skrev denne litt på gøy
    @SuppressWarnings("unchecked")
    public E[] toArray(){                                           //O(n)
        E[]result = (E[])new Object[size];                          //O(1)
        Iterator i = this.iterator();                               //O(1)
        for (int j = 0; j < result.length; j++) {                   //O(n)
            result[j] = (E) i.next();                               //O(1)
        }
        return result;                                              //O(1)
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> prev = first;
            Node<E> start = first;

            @Override
            public boolean hasNext() {
                return start != null && start.getData() != null;
            }

            @Override
            public E next() {
                if (start == null || start.getData() == null ) throw new NoSuchElementException();
                E temp = start.getData();
                prev = start;
                start = start.getNext();
                return temp;
            }

            @Override
            public void remove(){
                if(start != null ) {
                    if (start == last) {
                        prev.changeNext(null);
                        last = prev;
                        size--;
                    } else if (start == first) {
                        prev.changeNext(start.getNext());
                        first = prev;
                        size--;
                    } else {
                        prev.changeNext(start.getNext());
                        size--;
                    }
                }
            }
        };
    }

}
