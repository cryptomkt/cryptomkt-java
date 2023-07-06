package com.cryptomarket.sdk;

/**
 * Variations on the standard functional interfaces which throw Throwable.
 */
public interface Throwing {
  /**
   * Variations on the standard functional interfaces which throw a specific
   * subclass of Throwable.
   */
  public interface Specific {
    @FunctionalInterface
    public interface Runnable<E extends Throwable> {
      void run() throws E;
    }

    @FunctionalInterface
    public interface Supplier<T, E extends Throwable> {
      T get() throws E;
    }

    @FunctionalInterface
    public interface Consumer<T, E extends Throwable> {
      void accept(T t) throws E;
    }

    @FunctionalInterface
    public interface Function<T, R, E extends Throwable> {
      R apply(T t) throws E;
    }

    @FunctionalInterface
    public interface Function2<T, R, E extends Throwable, F extends Throwable> {
      R apply(T t) throws E, F;
    }

    @FunctionalInterface
    public interface Function3<T, R, E extends Throwable, F extends Throwable, G extends Throwable> {
      R apply(T t) throws E, F, G;
    }

    @FunctionalInterface
    public interface Predicate<T, E extends Throwable> {
      boolean test(T t) throws E;
    }

    @FunctionalInterface
    public interface BiConsumer<T, U, E extends Throwable> {
      void accept(T t, U u) throws E;
    }

    @FunctionalInterface
    public interface BiFunction<T, U, R, E extends Throwable> {
      R apply(T t, U u) throws E;
    }

    @FunctionalInterface
    public interface BiPredicate<T, U, E extends Throwable> {
      boolean accept(T t, U u) throws E;
    }
  }

  @FunctionalInterface
  public interface Runnable extends Specific.Runnable<Throwable> {
  }

  @FunctionalInterface
  public interface Supplier<T> extends Specific.Supplier<T, Throwable> {
  }

  @FunctionalInterface
  public interface Consumer<T> extends Specific.Consumer<T, Throwable> {
  }

  @FunctionalInterface
  public interface Function<T, R> extends Specific.Function<T, R, Throwable> {
  }

  @FunctionalInterface
  public interface Predicate<T> extends Specific.Predicate<T, Throwable> {
  }

  @FunctionalInterface
  public interface BiConsumer<T, U> extends Specific.BiConsumer<T, U, Throwable> {
  }

  @FunctionalInterface
  public interface BiFunction<T, U, R> extends Specific.BiFunction<T, U, R, Throwable> {
  }

  @FunctionalInterface
  public interface BiPredicate<T, U> extends Specific.BiPredicate<T, U, Throwable> {
  }
}
