/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Alson(Group 12H)
 */

class Box<T> {

  private final T contents;
  private static final Box<? extends Object> EMPTY_BOX = new Box<>(null);

  @Override 
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Box<?>) {
      Box<?> temp = (Box<?>) obj;
      if (this.contents == temp.contents) {
        return true;
      }
      
      if(this.contents == null || temp.contents == null) {
        return false;
      }
      return this.contents.equals(temp.contents);
    }
    return false;
  }

  @Override
  public String toString() {
    if (this.contents == null) {
      return "[]";
    }
    return "[" + this.contents + "]";
  }

  private Box(T contents) {
    this.contents = contents;
  }

  public static <T> Box<T> of(T contents) {
    if (contents == null) {
      return null;
    }
    return new Box<T>(contents);
  }
  
  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty(){
    return (Box<T>)EMPTY_BOX; 
  }

  public boolean isPresent() {
    if (this.contents != null) {
      return true;
    }
    return false;
  }

  public static <T> Box<T> ofNullable(T contents) {
    if (contents == null) {
      return empty();
    }
    return new Box<T>(contents);
  } 

  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (isPresent()) {
      if (condition.test(this.contents)) {
        return this;
      } else {
        return empty();
      }
    } else {
      return empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T,? extends U> transformer) {
    if (isPresent()) {
      return new Box<U>(transformer.transform(this.contents));
    } else {
      return empty();
    }
  }
}
