package lejos.remote.nxt;

/**
 * This interface defines the capabilities that a stream connection must have.
 *
 * <p>StreamConnections have one underlying InputStream and one OutputStream.
 *
 * <p>Opening a DataInputStream counts as opening an InputStream and opening a DataOutputStream
 * counts as opening an OutputStream.
 *
 * <p>Trying to open another InputStream or OutputStream causes an IOException.
 *
 * <p>Trying to open the InputStream or OutputStream after they have been closed causes an
 * IOException
 */
public interface StreamConnection extends InputConnection, OutputConnection {}
