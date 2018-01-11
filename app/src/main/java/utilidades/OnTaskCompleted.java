package utilidades;

/**
 * Interface para listener de ClienteRest para captura de notificaciones de ClienteRest
 * @autor Cristian Timbi on 24/05/2015.
 */
public interface OnTaskCompleted {
    void onTaskCompleted(int idSolicitud, String result);
}
