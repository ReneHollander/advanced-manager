package at.renehollander.advancedmanager.util;

/**
 * A NodeJS style callback
 *
 * @param <T> type of the callback value
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Callback<T> {

    /**
     * Gets call once the operation is finished.
     *
     * @param err  If an error occured, this will be set. {@code null} otherwise.
     * @param data Data that the operation returns
     */
    public void call(Throwable err, T data);

}
