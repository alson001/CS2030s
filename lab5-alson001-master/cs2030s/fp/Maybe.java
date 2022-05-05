/**
 * CS2030S Lab 5
 * AY21/22 Semester 2
 *
 * @author Alson (Group 12H)
 */

package cs2030s.fp;
import java.util.NoSuchElementException;

public abstract class Maybe<T> { 
  
  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> temp = (Maybe<T>) None.none;
    return temp;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T t) {
   if (t == null) {
    return none();
   } else {
    return some(t);
   }
  }

  protected abstract T get();

  public abstract boolean equals(Object obj);

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer);

  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> producer); 

  private static class None extends Maybe<Object> {
    private final static None none = new None();
    
    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof None) {
          return true;
      } else {
          return false;
      }
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transformer) { 
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U >> transformer) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object t) {
      return t;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public String toString() {
      return "[]";
    }
  }

  private final static class Some<T> extends Maybe<T> {
    private final T t;

    public Some(T t) {
      this.t = t;
    }

    @Override
    protected T get() {
      return this.t;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof Some<?>) {
        Some<?> temp = (Some<?>) obj;
        if (this.t == temp.t) {
          return true;
        }
        if (this.t == null || temp.t == null) {
          return false;
        }   
        return this.t.equals(temp.t);
      }
      return false;
    }

    @Override 
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.t == null) {
        return this;
      }
      if (condition.test(this.t)) {
        return this;
      } else {
        return Maybe.none();
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      if (this.t == null) {
        throw new NullPointerException();
      } else {
        return Maybe.some(transformer.transform(this.t));
      }
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      if (this.t == null) {
        throw new NullPointerException();
      } else {
        @SuppressWarnings("unchecked")
        Maybe<U> temp = (Maybe<U>) transformer.transform(this.t);
        return temp;
      }
    }

    @Override
    public T orElse(T t) {
      return this.t;
    }

    @Override
    public T orElseGet(Producer<? extends T> p) {
      return this.t;
    }

    @Override
    public String toString() {
      if (t == null) {
        return "[null]";
      } else {
        return "[" + t.toString() + "]";
      }
    }
  }     
}
