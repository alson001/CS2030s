package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class that builds a list with a possibly infinite number of elements.
 * CS2030S Lab 7
 * AY21/22 Semester 2
 *
 * @author Alson (Group 12H)
 */

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  /** A private constructor for InfiniteList.
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * Create an instance of an InfiniteList by taking in a producer.
   *
   * @param <T> The type of the element in the InfiniteList.
   * @param producer The producer to produce the elements in the InfiniteList.
   *
   * @return A new InfiniteList instance with values produced by the given producer.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> generate(producer)));
  }

  /**
   * Create an instance of an InfiniteList by taking in a seed and transformer;
   * Transforms the seed and adds it to the list.
   *
   * @param <T> The type of element stored in the InfiniteList
   * @param seed The element to apply the transformation on
   * @param next The transformer to apply on the seed
   *
   * @return A new InfiniteList instance with values produced by applying transformation to seed.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<>(seed, () -> iterate(next.transform(seed), next));
  }

  /**
   * A private constructor to initialise the InfiniteList;
   * Applies Lazy.of to the head and tail of InfiniteList with the given parameters.
   *
   * @param head The value of the first element of the InfiniteList 
   * @param tail The producer to generate subsequent elements in the InfiniteList
   *
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * A private constructor to initialise the InfiniteList;
   * Replaces the head and tail of InfiniteList with the given parameters.
   *
   * @param head The value of the head
   * @param tail The value of the tail
   *
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = head;
    this.tail = tail;
  }

  /**
   * Return the first element at the start of the InfiniteList;
   * If the value is filtered out, then we return the first subsequent returnable item.
   *
   * @return Value of type T that is at the start of the InfiniteList.
   */
  public T head() {
    // TODO
    Maybe<T> tempHead = this.head.get();
    return tempHead.orElseGet(() -> this.tail.get().head());
  }

  /**
   * Return the InfiniteList without the first item;
   * If the first item has already been filtered out, then we return the tail of the
   * first subsequent returnable item.
   *
   * @return InfiniteList containing all items less the head of the original InfiniteList.
   */
  public InfiniteList<T> tail() {
    // TODO
    Maybe<T> tempHead = this.head.get();
    return tempHead.map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Return a new InfiniteList with the given transformation applied to each element in the
   * original InfiniteList.
   *
   * @param <R> The type of the transformed value
   * @param mapper The transformer used to transform each element in the original InfiniteList
   *
   * @return A new InfiniteList with transformed values
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))), 
        Lazy.of(() -> this.tail().map(mapper)));
  }

  /**
   * Return a new InfiniteList with all the items that pass the given BooleanCondition;
   * filter marks out any elements that fails the BooleanCondition as Maybe.none().
   *
   * @param predicate The BooleanCondition of which to apply on the items in the list
   *
   * @return A new InfiniteList that includes only elements which pass the BooleanCondition 
   and all elements that failed are set as Maybe.none().
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    Maybe<T> tempHead = this.head.get();
    return new InfiniteList<>(
        Lazy.of(() -> tempHead.filter(predicate)),
        Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  /**
   * A nested class which represents a list that extends InfiniteList;
   * Sentinel is used to mark the end of an InfiniteList.
   */
  private static class Sentinel extends InfiniteList<Object> {
    /** An instance of Sentinel, which extends InfiniteList;
     * It is used to represent the end of a list.
     */
    private static final Sentinel SENTINEL = new Sentinel();
    
    /**
     * There is no head in a Sentinel, so throws an exception.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /**
     * There is no tail in a Sentinel, so throws an exception.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    /**
     * There is nothing stored in a sentinel so a sentinel is returned.
     *
     * @param <R> Type R to be returned from transformer.
     * @param mapper A transformer to evaluate Object to type R.
     * @return A sentinel.
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return sentinel();
    }

    /**
     * Filtering a sentinel is still a sentinel so this is returned.
     *
     * @param predicate A BooleanCondition to test elements.
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return this;
    }

    /**
     * A sentinel is sentinel so true is returned.
     *
     * @return true
     */
    @Override
    public boolean isSentinel() {
      return true;
    }

    /**
     * Whatever limit that is set to a sentinel is just a sentinel.
     *
     * @param n A number of primitive type long.
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return sentinel();
    }
    
    /**
     * There are no elements in sentinel to List.
     *
     * @return An empty List.
     */
    @Override
    public List<Object> toList() {
      // TODO
      return List.of();
    }

    /**
     * Returns a sentinel.
     *
     * @param predicate A BooleanCondition to test which part to truncate.
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return this;
    }

    /**
     * Counts number of elements in Sentinel, which is 0.
     *
     * @return 0 of type long.
     */
    @Override
    public long count() {
      // TODO
      return 0;
    }

    /**
     * There is no elements in Sentinel to be combined with the identity.
     *
     * @param <U> Type U.
     * @param identity An element of type U.
     * @param accumulator A combiner that combines 2 elements.
     * @return identity of type U.
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    /**
     * String representation of Sentinel.
     *
     * @return A string -.
     */
    @Override
    public String toString() {
      return "-";
    }
  }

  /**
   * Return the instance of SENTINEL as an InfiniteList with type T.
   *
   * @param <T> The typcasted type of the InfiniteList of the sentinel
   *
   * @return The single cached instance of the Sentinel
   */
  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) Sentinel.SENTINEL;
    return temp;
  }

  /**
   * Return a (finite) InfiniteList with at most n elements, with the end marked by a sentinel;
   * These elements are not inclusive of those filtered out by the above filter function.
   *
   * @param n The maximum number of elements in the new InfiniteList
   *
   * @return A new InfiniteList with the first n elements of the original list, exclusive of
   those set to Maybe.none().
   */
  public InfiniteList<T> limit(long n) {
    // TODO
    if (n <= 0) {
      return sentinel();
    }
    return new InfiniteList<T>(this.head, 
        Lazy.of(() -> { 
          int count = this.head.get().map(x -> 1).orElse(0); 
          return this.tail.get().limit(n - count);
        }));
  }

  /**
   * Return an InfiniteList of elements that pass the BooleanCondition;
   * The InfiniteList is truncated at the first element which evaluates the condition
   * to be false;
   * Elements already filtered out by filter are ignored.
   *
   * @param predicate The BooleanCondition to test elements against.
   *
   * @return A new InfiniteList with only elements that pass the BooleanCondition.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Maybe<T>> tempHead = Lazy.of(() -> Maybe.some(this.head()).filter(predicate));
    return new InfiniteList<T>(tempHead, 
        Lazy.of(() -> tempHead.get().map(x -> this.tail().takeWhile(predicate)) 
        .orElseGet(() -> InfiniteList.sentinel())));
  }

  /**
   * Check if an InfiniteList is an instance of a Sentinel.
   *
   * @return True if it is an instance of a Sentinel and false otherwise
   */
  public boolean isSentinel() {
    if (this instanceof Sentinel) {
      return true;
    }
    return false;
  }


  /**
   * Performs a reduction on the elements of the InfiniteList iteratively, using the provided 
   * identity and an associative accumulation function, and returns a value U with the reduced 
   * value.
   *
   * @param <U> The type of the identity and the product of using the combiner
   * @param identity The value of the provided identity for the initial combination operation
   * @param accumulator The combiner which we apply the combination to the elements of the list
   and the identity.
   *
   * @return The reduced value of the same type as the identity
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    Maybe<T> tempHead = this.head.get();
    return tempHead.map(x -> this.tail.get()
      .reduce(accumulator.combine(identity, tempHead.get()), accumulator))
      .orElseGet(() -> this.tail.get().reduce(identity, accumulator));
  }

  /**
   * Returns the number of elements in the InfiniteList, excluding the filtered items.
   *
   * @return Number of items
   */
  public long count() {
    // TODO
    Maybe<T> tempHead = this.head.get();
    return tempHead.map(x -> 1 + this.tail.get().count())
      .orElseGet(() -> 0 + this.tail.get().count());
  }


  /**
   * Collects the items in the InfiniteList into a java.util.List;
   *
   * @return The InfiniteList as a List.
   */
  public List<T> toList() {
    // TODO
    Maybe<T> temp = this.head.get();
    List<T> result = new ArrayList<>();
    temp.consumeWith(x -> result.add(x));
    List<T> prevconcat = this.tail.get().toList();
    result.addAll(prevconcat);
    return result;
  }

  /**
     * String representation of InfiniteList.
     *
     * @return A string [head tail].
     */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
