class Equipos {

    private String name;
    private int stars;
    private int intlParticipations;
    private int intlFinals;
    private int localFinals;
    private long fans;
    private double marketValue;
    private double payroll;

    public Equipos() {}

    public Equipos(String name, int stars, int intlParticipations,
                int intlFinals, int localFinals, long fans,
                double marketValue, double payroll) {

        this.name = name;
        this.stars = stars;
        this.intlParticipations = intlParticipations;
        this.intlFinals = intlFinals;
        this.localFinals = localFinals;
        this.fans = fans;
        this.marketValue = marketValue;
        this.payroll = payroll;
    }

    // ===== ORDENAMIENTOS POR ATRIBUTO =====

    public final static void sortByStars(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].stars > current.stars) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByIntlParticipations(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].intlParticipations > current.intlParticipations) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByIntlFinals(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].intlFinals > current.intlFinals) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByLocalFinals(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].localFinals > current.localFinals) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByFans(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].fans > current.fans) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByMarketValue(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].marketValue > current.marketValue) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    public final static void sortByPayroll(Equipos[] teams) {
        int n = teams.length;
        int i = 1;
        while (i < n) {
            Equipos current = teams[i];
            int j = i - 1;
            while (j >= 0 && teams[j].payroll > current.payroll) {
                teams[j + 1] = teams[j];
                j = j - 1;
            }
            teams[j + 1] = current;
            i = i + 1;
        }
    }

    // ===== UTILIDAD PARA MOSTRAR =====
    @Override
    public String toString() {
        return String.format("%s | stars=%d | intlPart=%d | intlFinals=%d | localFinals=%d | fans=%d | market=%.2f | payroll=%.2f",
                name, stars, intlParticipations, intlFinals, localFinals, fans, marketValue, payroll);
    }

    // ===== MAIN PARA PROBAR =====
    public final static void main(String[] args) {
        Equipos t1 = new Equipos("Once Caldas",        4, 3, 3,  6, 1_800_000L, 11.5,  4.5);
        Equipos t2 = new Equipos("Atlético Nacional", 18, 7,13, 30,11_591_000L, 25.3, 12.0);
        Equipos t3 = new Equipos("Millonarios",       16, 4, 2, 26, 8_996_000L, 15.8, 10.0);

        Equipos[] teams = { t1, t2, t3 };

        System.out.println("\n--- Original ---");
        for (Equipos t : teams) System.out.println(t);

        sortByStars(teams);
        System.out.println("\n--- Ordenado por Estrellas ---");
        for (Equipos t : teams) System.out.println(t);

        sortByIntlParticipations(teams);
        System.out.println("\n--- Ordenado por Participaciones Internacionales ---");
        for (Equipos t : teams) System.out.println(t);

        sortByIntlFinals(teams);
        System.out.println("\n--- Ordenado por Finales Internacionales ---");
        for (Equipos t : teams) System.out.println(t);

        sortByLocalFinals(teams);
        System.out.println("\n--- Ordenado por Finales Locales ---");
        for (Equipos t : teams) System.out.println(t);

        sortByFans(teams);
        System.out.println("\n--- Ordenado por Aficionados ---");
        for (Equipos t : teams) System.out.println(t);

        sortByMarketValue(teams);
        System.out.println("\n--- Ordenado por Valor del Mercado ---");
        for (Equipos t : teams) System.out.println(t);

        sortByPayroll(teams);
        System.out.println("\n--- Ordenado por Nomina ---");
        for (Equipos t : teams) System.out.println(t);

    }
}
