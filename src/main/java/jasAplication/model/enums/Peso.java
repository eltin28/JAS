package jasAplication.model.enums;

public enum Peso {
    PESO_125(125),
    PESO_250(250),
    PESO_500(500);

    private final int valor;

    Peso(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}