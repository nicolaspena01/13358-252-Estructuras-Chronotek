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

    public final static void selectionByStars(Equipos[] teams) {
        int n = teams.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (teams[j].stars < teams[min].stars) {
                    min = j;
                }
            }
            if (min != i) swap(teams, i, min);
        }
    }

    public final static void selectionByIntlParticipations(Equipos[] teams) {
        int n = teams.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (teams[j].intlParticipations < teams[min].intlParticipations) {
                    min = j;
                }
            }
            if (min != i) swap(teams, i, min);
        }
    }

    public final static void selectionByMarketValue(Equipos[] teams) {
        int n = teams.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (teams[j].marketValue < teams[min].marketValue) {
                    min = j;
                }
            }
            if (min != i) swap(teams, i, min);
        }
    }

    public final static void bubbleByIntlFinals(Equipos[] teams) {
        int n = teams.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (teams[i].intlFinals > teams[i + 1].intlFinals) {
                    swap(teams, i, i + 1);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public final static void bubbleByLocalFinals(Equipos[] teams) {
        int n = teams.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (teams[i].localFinals > teams[i + 1].localFinals) {
                    swap(teams, i, i + 1);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public final static void bubbleByFans(Equipos[] teams) {
        int n = teams.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (teams[i].fans > teams[i + 1].fans) {
                    swap(teams, i, i + 1);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    private static void swap(Equipos[] a, int i, int j) {
        Equipos tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Override
    public String toString() {
        return String.format(
            "%s | stars=%d | intlPart=%d | intlFinals=%d | localFinals=%d | fans=%d | market=%.2f | payroll=%.2f",
            name, stars, intlParticipations, intlFinals, localFinals, fans, marketValue, payroll
        );
    }

    public final static void main(String[] args) {
        Equipos t1 = new Equipos("Once Caldas",        4, 3, 3,  6, 1_800_000L, 11.5,  4.5);
        Equipos t2 = new Equipos("Atlético Nacional", 18, 7,13, 30,11_591_000L, 25.3, 12.0);
        Equipos t3 = new Equipos("Millonarios",       16, 4, 2, 26, 8_996_000L, 15.8, 10.0);
        Equipos[] teams;

        System.out.println("\n--- Original ---");
        teams = new Equipos[]{ t1, t2, t3 };
        for (Equipos t : teams) System.out.println(t);

        // Inserción
        sortByStars(teams);
        System.out.println("\n--- Inserción por Estrellas ---");
        for (Equipos t : teams) System.out.println(t);

        // Selección: Estrellas
        teams = new Equipos[]{ t1, t2, t3 };
        selectionByStars(teams);
        System.out.println("\n--- Selección por Estrellas ---");
        for (Equipos t : teams) System.out.println(t);

        // Selección: Participaciones Internacionales
        teams = new Equipos[]{ t1, t2, t3 };
        selectionByIntlParticipations(teams);
        System.out.println("\n--- Selección por Participaciones Internacionales ---");
        for (Equipos t : teams) System.out.println(t);

        // Selección: Valor de Mercado
        teams = new Equipos[]{ t1, t2, t3 };
        selectionByMarketValue(teams);
        System.out.println("\n--- Selección por Valor de Mercado ---");
        for (Equipos t : teams) System.out.println(t);

        // Burbuja: Finales Internacionales
        teams = new Equipos[]{ t1, t2, t3 };
        bubbleByIntlFinals(teams);
        System.out.println("\n--- Burbuja por Finales Internacionales ---");
        for (Equipos t : teams) System.out.println(t);

        // Burbuja: Finales Locales
        teams = new Equipos[]{ t1, t2, t3 };
        bubbleByLocalFinals(teams);
        System.out.println("\n--- Burbuja por Finales Locales ---");
        for (Equipos t : teams) System.out.println(t);

        // Burbuja: Aficionados
        teams = new Equipos[]{ t1, t2, t3 };
        bubbleByFans(teams);
        System.out.println("\n--- Burbuja por Aficionados ---");
        for (Equipos t : teams) System.out.println(t);
    }
}
