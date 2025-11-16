package services;

import domain.Ticket;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersistenceService {

    /** Guarda los tickets en un archivo CSV sencillo. */
    public static void saveTicketsAsCsv(Ticket[] tickets, File file) throws IOException {
        if (tickets == null)
            tickets = new Ticket[0];
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            // Encabezado
            pw.println("id;placa;plazaId;entradaMillis;salidaMillis;valor");
            for (Ticket t : tickets) {
                if (t == null)
                    continue;
                pw.printf("%s;%s;%d;%d;%s;%d%n",
                        t.getId(),
                        t.getPlaca(),
                        t.getPlazaId(),
                        t.getEntradaMillis(),
                        t.getSalidaMillis() == null ? "" : t.getSalidaMillis().toString(),
                        t.getValor());
            }
        }
    }

    /** Carga tickets desde un CSV generado por saveTicketsAsCsv. */
    public static Ticket[] loadTicketsFromCsv(File file) throws IOException {
        List<Ticket> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = br.readLine(); // saltar encabezado
            while ((line = br.readLine()) != null) {
                if (line.isBlank())
                    continue;
                String[] parts = line.split(";");
                if (parts.length < 6)
                    continue;
                String id = parts[0];
                String placa = parts[1];
                int plazaId = Integer.parseInt(parts[2]);
                long entrada = Long.parseLong(parts[3]);
                String salidaStr = parts[4];
                long valor = Long.parseLong(parts[5]);

                Ticket t = new Ticket(id, placa, plazaId, entrada);
                if (!salidaStr.isEmpty()) {
                    long salida = Long.parseLong(salidaStr);
                    t.cerrar(salida, valor);
                } else {
                    // si no tiene salida la marcamos igualmente como cerrada con valor 0
                    t.cerrar(entrada, valor);
                }
                list.add(t);
            }
        }
        return list.toArray(new Ticket[0]);
    }
}
