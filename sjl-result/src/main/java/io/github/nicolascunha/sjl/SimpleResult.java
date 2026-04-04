package io.github.nicolascunha.sjl;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * SimpleResult represents the result of an operation with a status,
 * an optional message, and an optional value.
 *
 * <p>Instances are usually created through static factory methods such as
 * {@link #success()}, {@link #success(Object)}, {@link #error()}, and
 * {@link #error(Object, String)}.</p>
 *
 * <p>Sample usages:</p>
 * {@snippet lang="java" :
 *     final SimpleResult<String> result = SimpleResult.success("saved", "Operation completed!");
 *     if (result.succeeded()) {
 *         System.out.println(result.getValue());
 *     }
 * }
 *
 * {@snippet lang="java" :
 *     SimpleResult.error("invalid payload", "Validation failed!")
 *         .onError(value -> System.out.println("Error value: " + value));
 * }
 *
 * @param <T> the type of the wrapped value.
 */
public class SimpleResult<T> {

    private final Status status;
    private final String message;
    private final T value;

    /**
     * Creates a new result instance with the provided status, message, and value.
     *
     * <p>This constructor is private and intended to be used by static factory methods.</p>
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     // Internal construction used by factory methods.
     *     return new SimpleResult<>(Status.SUCCESS, "Operation completed!", 42);
     * }
     *
     * @param status {@link Status} of the operation.
     * @param message optional {@link String} message describing the result.
     * @param value optional value produced by the operation.
     */
    private SimpleResult(final Status status, final String message, final T value) {
        this.status = status;
        this.message = message;
        this.value = value;
    }

    /**
     * Creates a successful result without value and without message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<Void> result = SimpleResult.success();
     *     result.onSuccess(() -> System.out.println("Done!"));
     * }
     *
     * @param <T> result value type.
     * @return a successful {@link SimpleResult} with {@code null} value and message.
     */
    public static <T> SimpleResult<T> success() {
        return new SimpleResult<>(Status.SUCCESS, null, null);
    }

    /**
     * Creates a successful result with the provided value and no message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<Integer> result = SimpleResult.success(10);
     *     result.onSuccess(value -> System.out.println(value + 5));
     * }
     *
     * @param value value to wrap in the result.
     * @param <T> result value type.
     * @return a successful {@link SimpleResult} containing {@code value}.
     */
    public static <T> SimpleResult<T> success(final T value) {
        return new SimpleResult<>(Status.SUCCESS, null, value);
    }

    /**
     * Creates a successful result with the provided value and message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<String> result = SimpleResult.success("token", "Authenticated!");
     *     System.out.println(result.getMessage());
     * }
     *
     * @param value value to wrap in the result.
     * @param message descriptive {@link String} message.
     * @param <T> result value type.
     * @return a successful {@link SimpleResult} containing {@code value} and {@code message}.
     */
    public static <T> SimpleResult<T> success(final T value, final String message) {
        return new SimpleResult<>(Status.SUCCESS, message, value);
    }

    /**
     * Creates an error result without value and without message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<Void> result = SimpleResult.error();
     *     if (result.failed()) {
     *         System.out.println("Operation failed!");
     *     }
     * }
     *
     * @param <T> result value type.
     * @return an error {@link SimpleResult} with {@code null} value and message.
     */
    public static <T> SimpleResult<T> error() {
        return new SimpleResult<>(Status.ERROR, null, null);
    }

    /**
     * Creates an error result with the provided value and no message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<String> result = SimpleResult.error("invalid state");
     *     result.onError(System.out::println);
     * }
     *
     * @param value value to wrap in the result.
     * @param <T> result value type.
     * @return an error {@link SimpleResult} containing {@code value}.
     */
    public static <T> SimpleResult<T> error(final T value) {
        return new SimpleResult<>(Status.ERROR, null, value);
    }

    /**
     * Creates an error result with the provided value and message.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<String> result = SimpleResult.error("username", "Already exists!");
     *     System.out.println(result.getMessage());
     * }
     *
     * @param value value to wrap in the result.
     * @param message descriptive {@link String} message.
     * @param <T> result value type.
     * @return an error {@link SimpleResult} containing {@code value} and {@code message}.
     */
    public static <T> SimpleResult<T> error(final T value, final String message) {
        return new SimpleResult<>(Status.ERROR, message, value);
    }

    /**
     * Creates a copy of the provided result.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<String> source = SimpleResult.success("ok", "Done!");
     *     final SimpleResult<String> clone = SimpleResult.copy(source);
     * }
     *
     * @param result source {@link SimpleResult} to copy.
     * @param <T> result value type.
     * @return a new {@link SimpleResult} with the same status, message, and value as {@code result}.
     */
    public static <T> SimpleResult<T> copy(final SimpleResult<T> result) {
        return new SimpleResult<>(result.status, result.message, result.value);
    }

    /**
     * Indicates whether this result has {@link Status#SUCCESS}.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean ok = SimpleResult.success().succeeded();
     * }
     *
     * @return {@code true} when status is {@link Status#SUCCESS}; otherwise {@code false}.
     */
    public boolean succeeded() {
        return status == Status.SUCCESS;
    }

    /**
     * Indicates whether this result has {@link Status#ERROR}.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final boolean hasFailed = SimpleResult.error().failed();
     * }
     *
     * @return {@code true} when status is {@link Status#ERROR}; otherwise {@code false}.
     */
    public boolean failed() {
        return status == Status.ERROR;
    }

    /**
     * Creates a copy of this result.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final SimpleResult<Integer> original = SimpleResult.success(7);
     *     final SimpleResult<Integer> duplicate = original.copy();
     * }
     *
     * @return a new {@link SimpleResult} with the same status, message, and value.
     */
    public SimpleResult<T> copy() {
        return SimpleResult.copy(this);
    }

    /**
     * Returns the wrapped value as an {@link Optional}.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final Optional<String> value = SimpleResult.success("data").asOptional();
     * }
     *
     * @return an {@link Optional} containing the value when present; otherwise an empty optional.
     */
    public Optional<T> asOptional() {
        return Optional.ofNullable(value);
    }

    /**
     * Executes a consumer when this result is successful.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     SimpleResult.success("saved")
     *         .onSuccess(value -> System.out.println("Saved: " + value));
     * }
     *
     * @param consumer callback executed only when this result succeeded.
     * @return this result instance for chaining.
     */
    public SimpleResult<T> onSuccess(final Consumer<T> consumer) {
        if (succeeded()) {
            consumer.accept(value);
        }
        return this;
    }

    /**
     * Executes a runnable when this result is successful.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     SimpleResult.success()
     *         .onSuccess(() -> System.out.println("Success callback executed!"));
     * }
     *
     * @param runnable callback executed only when this result succeeded.
     * @return this result instance for chaining.
     */
    public SimpleResult<T> onSuccess(final Runnable runnable) {
        if (succeeded()) {
            runnable.run();
        }
        return this;
    }

    /**
     * Executes a consumer when this result is an error.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     SimpleResult.error("not found")
     *         .onError(value -> System.out.println("Error: " + value));
     * }
     *
     * @param consumer callback executed only when this result failed.
     * @return this result instance for chaining.
     */
    public SimpleResult<T> onError(final Consumer<T> consumer) {
        if (failed()) {
            consumer.accept(value);
        }
        return this;
    }

    /**
     * Executes a runnable when this result is an error.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     SimpleResult.error()
     *         .onError(() -> System.out.println("Error callback executed!"));
     * }
     *
     * @param runnable callback executed only when this result failed.
     * @return this result instance for chaining.
     */
    public SimpleResult<T> onError(final Runnable runnable) {
        if (failed()) {
            runnable.run();
        }
        return this;
    }

    /**
     * Returns the result status.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final Status status = SimpleResult.success().getStatus();
     * }
     *
     * @return current {@link Status}.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the optional message associated with this result.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final String message = SimpleResult.error("x", "Validation failed!").getMessage();
     * }
     *
     * @return message text, or {@code null} when no message is set.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the wrapped value.
     *
     * <p>Sample usage:</p>
     * {@snippet lang="java" :
     *     final Integer value = SimpleResult.success(100).getValue();
     * }
     *
     * @return wrapped value, or {@code null} when no value is set.
     */
    public T getValue() {
        return value;
    }

}
