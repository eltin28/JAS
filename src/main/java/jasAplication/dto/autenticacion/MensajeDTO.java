package jasAplication.dto.autenticacion;

public record MensajeDTO<T>(
        boolean error,
        T respuesta,
        String mensaje
) {
}
