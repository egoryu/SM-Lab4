public enum Algo {
    LINE("линейная функция"),
    LINE2("полиномиальная функция 2-й степени"),
    LINE3("полиномиальная функция 3-й степени"),
    EXP("экспоненциальная функция"),
    LOG("логарифмическая функция"),
    POW("степенная функция");

    private final String name;

    Algo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
