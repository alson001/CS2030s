package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  /**
   * A private constructor to initialize value to the given one.
   *
   * @param value The given value to initialize.
   */
  private Lazy(T value) {
    this.producer = null;
    this.value = Maybe.<T>some(value);
  }

  /**
   * A private constructor to initialize producer to the given one.
   *
   * @param producer The given producer to initialize.
   */
  private Lazy(Producer<? extends T> producer) {
    this.producer = () -> {
      T temp = producer.produce();
      this.value = Maybe.<T>some(temp);
      return temp;
    };
    this.value = Maybe.<T>none();
  }

  /**
   * Initialise the Lazy object with the given value.
   *
   * @param <T> the type of value
   * @param v the given value
   * @return the new created Lazy object
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * * Initialise the Lazy object with the given producer.
   * 
   * @param <T> the type of producer
   * @param s the given producer
   * @return the new created Lazy object
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  public T get() {
    return this.value.orElseGet(producer);
  } 

  /**
   * Transform the value of type T to another value of type U while delaying
   * the transformation until needed.
   *
   * @param <U> the type of transformed value
   * @param transformer  the transformation method on the value
   * @return A Lazy object that contains the producer which will transform the value when evaluated
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()));
  }

  /**
   * Unwrap the value of Type T and transform it to another value of type U while delaying
   * the transformation until needed.
   *
   * @param <U> the type of transformed value
   * @param transformer the transformation method on the value
   * @return A Lazy object that contains the producer which will unwrap the 
   *     value and transform the value when evaluated
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()).get());
  }

  /**
   * Check boolean on the value of type T by applying condition while delaying
   * the check until needed.
   *
   * @param condition the boolean method on the value
   * @return A Lazy object that contains the producer which will return
   *     a boolean based on whether the value passes the condition 
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return Lazy.of(() -> condition.test(this.get()));
  }

  /**
   * Combine the value of type T and type S to another value of type R while delaying
   * the combination until needed.
   * 
   * @param <S> the type of the second Lazy object 
   * @param <R> the type of combined value
   * @param obj the second Lazy object
   * @param combiner the combine method on the value
   * @return A Lazy object that contains the producer which will combine the
   *     the two Lazy objects when evaluated
   */
  public <S, R> Lazy<R> combine(Lazy<S> obj, Combiner<? super T, ? super S, ? extends R> combiner) {
    return Lazy.of(() ->  combiner.combine(this.get(), obj.get()));
  } 

  /**
   * Check boolean on the Lazy Object of type T by comparing with another Object.
   *
   * @param obj the Object of comparision
   * @return boolean based on whether this Lazy Object is same when compared to
   *     another Object
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy<?>) {
      Lazy<?> temp = (Lazy<?>) obj;
      if (this.get() == null || temp.get() == null) {
        if (this.get() == null && temp.get() == null) {
          return true;
        } else {
          return false;
        }
      } 
      return this.get().equals(temp.get());
    } 
    return false;
  }

  /**
   * Return the string representation of the value. If value is
   * not evaluated, then return "?".
   *
   * @return The string representation of the Lazy object.
   */
  @Override
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }

}
