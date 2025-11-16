package app.gui;

import datastructures.SearchUtils;
import datastructures.SortUtils;
import domain.Plaza;
import domain.Ticket;
import domain.Vehiculo;
import services.ParkingService;
import services.PersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SwingApp extends JFrame {

  private final ParkingService svc = new ParkingService(12);

  private final DefaultListModel<String> plazasModel = new DefaultListModel<>();
  private final JList<String> plazasList = new JList<>(plazasModel);

  private final DefaultListModel<String> colaModel = new DefaultListModel<>();
  private final JList<String> colaList = new JList<>(colaModel);

  private final JTextField placaField = new JTextField();
  private final JRadioButton rbCarro = new JRadioButton("Carro", true);
  private final JRadioButton rbMoto = new JRadioButton("Moto");
  private final JLabel status = new JLabel("Listo.");

  private final JLabel infoLabel = new JLabel();

  // Formato de fecha "Mar, 16 Nov 13:00 pm"
  private static final Locale LOCALE_ES = new Locale("es", "ES");
  private static final SimpleDateFormat DF_FECHA = new SimpleDateFormat("EEE, dd MMM", LOCALE_ES);
  private static final SimpleDateFormat DF_HORA = new SimpleDateFormat("HH:mm", LOCALE_ES);

  private String formatFecha(long millis) {
    Date date = new Date(millis);

    String base = DF_FECHA.format(date); // "mar, 16 nov"
    String hora = DF_HORA.format(date); // "13:00"

    // Determinar am/pm manualmente, pero dejando la hora en 24h
    Calendar cal = Calendar.getInstance(LOCALE_ES);
    cal.setTime(date);
    int h24 = cal.get(Calendar.HOUR_OF_DAY);
    String sufijo = (h24 < 12) ? "am" : "pm";

    String raw = base + " " + hora + " " + sufijo;

    // Capitalizar primera letra (Mar, 16 nov...)
    if (raw.isEmpty())
      return raw;
    return raw.substring(0, 1).toUpperCase() + raw.substring(1);
  }

  public SwingApp() {
    super("Aparco – Gestión de Parqueadero (Swing)");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(1040, 600));
    setLocationRelativeTo(null);

    crearMenuBar();

    var root = new JPanel(new BorderLayout(10, 10));
    root.setBorder(new EmptyBorder(12, 12, 12, 12));

    // --- Panel superior de controles ---
    var control = new JPanel(new GridBagLayout());
    var gbc = new GridBagConstraints();
    gbc.insets = new Insets(4, 4, 4, 4);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;
    control.add(new JLabel("Placa:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1;
    placaField.setColumns(10);
    control.add(placaField, gbc);

    var tipoGroup = new ButtonGroup();
    tipoGroup.add(rbCarro);
    tipoGroup.add(rbMoto);
    var tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
    tipoPanel.add(rbCarro);
    tipoPanel.add(rbMoto);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weightx = 0;
    control.add(new JLabel("Tipo:"), gbc);
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.weightx = 1;
    control.add(tipoPanel, gbc);

    var btnLlegada = new JButton("Llegada");
    var btnAsignar = new JButton("Asignar cola");
    var btnAsignarDir = new JButton("Asignar directo");
    var btnSalida = new JButton("Registrar salida");
    var btnBuscar = new JButton("Buscar (dentro)");
    var btnProx = new JButton("Próximos en salir");
    var btnReporte = new JButton("Reporte tickets");
    var btnUndo = new JButton("Deshacer (UNDO)");
    var btnRefresh = new JButton("Refrescar");

    var botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
    botones.add(btnLlegada);
    botones.add(btnAsignar);
    botones.add(btnAsignarDir);
    botones.add(btnSalida);
    botones.add(btnBuscar);
    botones.add(btnProx);
    botones.add(btnReporte);
    botones.add(btnUndo);
    botones.add(btnRefresh);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 4;
    gbc.weightx = 1;
    control.add(botones, gbc);

    // --- Panel central: plazas (izq) y cola (der) ---
    plazasList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    colaList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

    var plazasPanel = new JPanel(new BorderLayout());
    plazasPanel.add(new JLabel("Plazas (selecciona una para asignar directo)"), BorderLayout.NORTH);
    plazasPanel.add(new JScrollPane(plazasList), BorderLayout.CENTER);

    var colaPanel = new JPanel(new BorderLayout());
    colaPanel.add(new JLabel("Cola de entrada (FIFO)"), BorderLayout.NORTH);
    colaPanel.add(new JScrollPane(colaList), BorderLayout.CENTER);

    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plazasPanel, colaPanel);
    split.setResizeWeight(0.7);

    // --- Barra de estado inferior ---
    var statusPanel = new JPanel(new BorderLayout());
    statusPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
    status.setForeground(new Color(0, 100, 0));
    statusPanel.add(status, BorderLayout.WEST);

    infoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    statusPanel.add(infoLabel, BorderLayout.EAST);

    root.add(control, BorderLayout.NORTH);
    root.add(split, BorderLayout.CENTER);
    root.add(statusPanel, BorderLayout.SOUTH);
    setContentPane(root);

    // Handlers
    btnLlegada.addActionListener(e -> onLlegada());
    btnAsignar.addActionListener(e -> onAsignarDesdeCola());
    btnAsignarDir.addActionListener(e -> onAsignarDirecto());
    btnSalida.addActionListener(e -> onSalida());
    btnBuscar.addActionListener(e -> onBuscarDentro());
    btnProx.addActionListener(e -> onProximos());
    btnReporte.addActionListener(e -> onReporteTickets());
    btnUndo.addActionListener(e -> onUndo());
    btnRefresh.addActionListener(e -> refresh());

    refresh();
    actualizarInfo();

    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowActivated(java.awt.event.WindowEvent e) {
        actualizarInfo();
      }
    });
  }

  // --------- Menú superior ----------

  private void crearMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    JMenu menuArchivo = new JMenu("Archivo");
    JMenuItem miExport = new JMenuItem("Exportar tickets (CSV)...");
    JMenuItem miImport = new JMenuItem("Importartickets (CSV)...");
    JMenuItem miSalir = new JMenuItem("Salir");

    miExport.addActionListener(e -> onExportTickets());
    miImport.addActionListener(e -> onImportTickets());
    miSalir.addActionListener(e -> dispose());

    menuArchivo.add(miExport);
    menuArchivo.add(miImport);
    menuArchivo.addSeparator();
    menuArchivo.add(miSalir);

    JMenu menuConfig = new JMenu("Configuración");
    JMenuItem miTarifa = new JMenuItem("Tarifa por hora...");
    miTarifa.addActionListener(e -> onConfigTarifa());
    menuConfig.add(miTarifa);

    menuBar.add(menuArchivo);
    menuBar.add(menuConfig);

    setJMenuBar(menuBar);
  }

  // --------- Info y helpers ----------

  private void actualizarInfo() {
    int cap = svc.getCapacidad();
    int enCola = svc.getColaEntradaSize();
    int ocupadas = 0;
    for (Plaza p : svc.getPlazas())
      if (!p.libre())
        ocupadas++;
    int cerrados = svc.getTicketsCerradosSnapshot().length;
    infoLabel.setText(
        String.format("Capacidad: %d | Ocupadas: %d | En cola: %d | Tickets cerrados: %d | Tarifa: $%d/h",
            cap, ocupadas, enCola, cerrados, svc.getTarifaHora()));
  }

  private void setStatus(String msg, boolean error) {
    status.setText(msg);
    status.setForeground(error ? new Color(150, 0, 0) : new Color(0, 100, 0));
  }

  // --------- Acciones principales ----------

  private void onLlegada() {
    var placa = placaField.getText().trim();
    if (placa.isEmpty()) {
      setStatus("Ingresa una placa válida", true);
      return;
    }
    var tipo = rbMoto.isSelected() ? Vehiculo.Tipo.MOTO : Vehiculo.Tipo.CARRO;
    svc.llegada(new Vehiculo(placa, tipo));
    setStatus("Vehículo en cola: " + placa, false);
    placaField.setText("");
    refresh();
  }

  private void onAsignarDesdeCola() {
    var t = svc.asignarPlaza();
    if (t == null) {
      setStatus("No hay vehículos en cola o no hay plazas libres.", true);
    } else {
      setStatus("Asignada (cola) plaza #" + t.getPlazaId() + " a " + t.getPlaca(), false);
    }
    refresh();
  }

  /** NUEVO: asignar usando la plaza seleccionada en la lista. */
  private void onAsignarDirecto() {
    int index = plazasList.getSelectedIndex();
    if (index < 0) {
      setStatus("Selecciona una plaza en la lista para asignar directo.", true);
      return;
    }
    Plaza p = svc.getPlazas()[index];
    if (!p.libre()) {
      setStatus("La plaza #" + p.getId() + " no está libre.", true);
      return;
    }
    var placa = placaField.getText().trim();
    if (placa.isEmpty()) {
      setStatus("Ingresa una placa en el campo antes de asignar directo.", true);
      return;
    }
    var tipo = rbMoto.isSelected() ? Vehiculo.Tipo.MOTO : Vehiculo.Tipo.CARRO;
    Vehiculo v = new Vehiculo(placa, tipo);
    Ticket t = svc.asignarPlazaDirecta(p.getId(), v);
    if (t == null) {
      setStatus("No se pudo asignar la plaza seleccionada.", true);
    } else {
      setStatus("Asignada (directo) plaza #" + t.getPlazaId() + " a " + t.getPlaca(), false);
      placaField.setText("");
    }
    refresh();
  }

  private void onSalida() {
    var placa = JOptionPane.showInputDialog(this, "Placa a salir:", "Salida", JOptionPane.QUESTION_MESSAGE);
    if (placa == null || placa.trim().isEmpty())
      return;
    var t = svc.registrarSalidaPorPlaca(placa.trim().toUpperCase(), 0); // usa tarifa configurada
    if (t == null)
      setStatus("Vehículo no encontrado dentro.", true);
    else
      setStatus("Salida OK. Plaza #" + t.getPlazaId() + " | Valor: $" + t.getValor(), false);
    refresh();
  }

  private void onBuscarDentro() {
    var placa = JOptionPane.showInputDialog(this, "Placa a buscar (dentro):", "Buscar vehículo",
        JOptionPane.QUESTION_MESSAGE);
    if (placa == null || placa.trim().isEmpty())
      return;
    Ticket t = svc.buscarTicketPorPlaca(placa.trim().toUpperCase());
    if (t == null || !t.isAbierto()) {
      JOptionPane.showMessageDialog(this, "La placa " + placa + " no se encuentra dentro del parqueadero.",
          "Resultado búsqueda (BST)", JOptionPane.INFORMATION_MESSAGE);
    } else {
      String msg = String.format(
          "Placa: %s\nPlaza: #%d\nHora de entrada: %s",
          t.getPlaca(), t.getPlazaId(), formatFecha(t.getEntradaMillis()));
      JOptionPane.showMessageDialog(this, msg,
          "Resultado búsqueda (BST)", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void onProximos() {
    String kStr = JOptionPane.showInputDialog(this,
        "¿Cuántos próximos en salir quieres ver?", "3");
    if (kStr == null || kStr.trim().isEmpty())
      return;
    int k;
    try {
      k = Integer.parseInt(kStr.trim());
    } catch (Exception e) {
      k = 3;
    }

    List<Ticket> lista = svc.getProximosEnSalir(k);
    if (lista.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No hay vehículos dentro actualmente.",
          "Próximos en salir (Min-Heap)", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    StringBuilder sb = new StringBuilder("Próximos en salir (según hora de entrada / Min-Heap):\n\n");
    int i = 1;
    for (Ticket t : lista) {
      sb.append(i++)
          .append(". Placa: ").append(t.getPlaca())
          .append(" | Plaza: #").append(t.getPlazaId())
          .append(" | Entrada: ").append(formatFecha(t.getEntradaMillis()))
          .append("\n");
    }

    JOptionPane.showMessageDialog(this, sb.toString(),
        "Próximos en salir", JOptionPane.INFORMATION_MESSAGE);
  }

  // --------- Reporte: ordenamientos + búsquedas ---------

  private void onReporteTickets() {
    Ticket[] arr = svc.getTicketsCerradosSnapshot();
    if (arr.length == 0) {
      JOptionPane.showMessageDialog(this, "No hay tickets cerrados todavía.",
          "Reporte tickets", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String[] campos = { "Placa", "Valor pagado", "Hora de entrada" };
    String campoSel = (String) JOptionPane.showInputDialog(
        this,
        "¿Por qué campo quieres ordenar?",
        "Reporte tickets",
        JOptionPane.PLAIN_MESSAGE,
        null,
        campos,
        campos[0]);
    if (campoSel == null)
      return;

    Comparator<Ticket> cmp;
    switch (campoSel) {
      case "Valor pagado" ->
        cmp = Comparator.comparingLong(Ticket::getValor);
      case "Hora de entrada" ->
        cmp = Comparator.comparingLong(Ticket::getEntradaMillis);
      default ->
        cmp = (a, b) -> a.getPlaca().compareToIgnoreCase(b.getPlaca());
    }

    String[] algos = {
        "Burbuja (O(n^2))",
        "Inserción (O(n^2))",
        "Quicksort (O(n log n))",
        "MergeSort (O(n log n))"
    };
    String algoSel = (String) JOptionPane.showInputDialog(
        this,
        "Elige el algoritmo de ordenamiento:",
        "Reporte tickets",
        JOptionPane.PLAIN_MESSAGE,
        null,
        algos,
        algos[2]);
    if (algoSel == null)
      return;

    Ticket[] copia = arr.clone();

    if (algoSel.startsWith("Burbuja")) {
      SortUtils.bubbleSort(copia, cmp);
    } else if (algoSel.startsWith("Inserción")) {
      SortUtils.insertionSort(copia, cmp);
    } else if (algoSel.startsWith("Quicksort")) {
      SortUtils.quickSort(copia, cmp);
    } else {
      SortUtils.mergeSort(copia, cmp);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Tickets cerrados ordenados por ").append(campoSel)
        .append("\nAlgoritmo: ").append(algoSel).append("\n\n");

    for (Ticket t : copia) {
      String entradaStr = formatFecha(t.getEntradaMillis());
      String salidaStr = t.getSalidaMillis() == null ? "-" : formatFecha(t.getSalidaMillis());
      sb.append("Placa: ").append(t.getPlaca())
          .append(" | Plaza: #").append(t.getPlazaId())
          .append(" | Valor: $").append(t.getValor())
          .append(" | Entrada: ").append(entradaStr)
          .append(" | Salida: ").append(salidaStr)
          .append("\n");
    }

    JOptionPane.showMessageDialog(this, sb.toString(),
        "Reporte ordenado", JOptionPane.INFORMATION_MESSAGE);

    if (campoSel.equals("Placa")) {
      int opt = JOptionPane.showConfirmDialog(
          this,
          "El arreglo está ordenado por placa.\n¿Quieres buscar una placa con búsqueda BINARIA?",
          "Búsqueda binaria",
          JOptionPane.YES_NO_OPTION);
      if (opt == JOptionPane.YES_OPTION) {
        String placaB = JOptionPane.showInputDialog(this, "Placa a buscar:", "Búsqueda binaria",
            JOptionPane.QUESTION_MESSAGE);
        if (placaB != null && !placaB.trim().isEmpty()) {
          Comparator<Ticket> cmpPlaca = (a, b) -> a.getPlaca().compareToIgnoreCase(b.getPlaca());
          Ticket target = new Ticket("dummy", placaB.trim().toUpperCase(), 0, 0L);
          int idx = SearchUtils.binarySearch(copia, target, cmpPlaca);
          if (idx >= 0) {
            Ticket t = copia[idx];
            String entradaStr = formatFecha(t.getEntradaMillis());
            String salidaStr = t.getSalidaMillis() == null ? "-" : formatFecha(t.getSalidaMillis());
            String msg = String.format(
                "Encontrado en índice %d del arreglo ordenado.\nPlaca: %s\nPlaza: #%d\nValor: $%d\nEntrada: %s\nSalida: %s",
                idx, t.getPlaca(), t.getPlazaId(), t.getValor(), entradaStr, salidaStr);
            JOptionPane.showMessageDialog(this, msg,
                "Resultado búsqueda binaria", JOptionPane.INFORMATION_MESSAGE);
          } else {
            JOptionPane.showMessageDialog(this, "La placa no se encontró en el arreglo.",
                "Resultado búsqueda binaria", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
    }
  }

  // --------- Persistencia ----------

  private void onExportTickets() {
    Ticket[] arr = svc.getTicketsCerradosSnapshot();
    if (arr.length == 0) {
      JOptionPane.showMessageDialog(this, "No hay tickets cerrados para exportar.",
          "Exportar tickets", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Guardar tickets como CSV");
    int result = chooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      try {
        PersistenceService.saveTicketsAsCsv(arr, file);
        setStatus("Tickets exportados a " + file.getAbsolutePath(), false);
      } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void onImportTickets() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Importar tickets desde CSV");
    int result = chooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      try {
        Ticket[] imported = PersistenceService.loadTicketsFromCsv(file);
        svc.addTicketsExternos(imported);
        setStatus("Tickets importados: " + imported.length, false);
        refresh();
      } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al importar: " + ex.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // --------- Configuración ----------

  private void onConfigTarifa() {
    String actual = String.valueOf(svc.getTarifaHora());
    String input = JOptionPane.showInputDialog(
        this,
        "Tarifa por hora actual: $" + actual + "\nIngresa nueva tarifa (entero positivo):",
        actual);
    if (input == null || input.trim().isEmpty())
      return;
    try {
      long nueva = Long.parseLong(input.trim());
      if (nueva <= 0)
        throw new NumberFormatException();
      svc.setTarifaHora(nueva);
      setStatus("Tarifa actualizada a $" + nueva + " por hora.", false);
      actualizarInfo();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Valor inválido. Debe ser un número entero positivo.",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void onUndo() {
    boolean ok = svc.undo();
    setStatus(ok ? "Última acción revertida." : "Nada que deshacer.", !ok);
    refresh();
  }

  private void refresh() {
    plazasModel.clear();
    for (Plaza p : svc.getPlazas()) {
      String line = String.format("#%-2d  %-8s  %s",
          p.getId(),
          p.getEstado(),
          (p.getPlaca() != null ? p.getPlaca() : ""));
      plazasModel.addElement(line);
    }
    colaModel.clear();
    for (Vehiculo v : svc.getColaEntradaSnapshot()) {
      colaModel.addElement(v.toString());
    }
    actualizarInfo();
  }

  public static void main(String[] args) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception ignored) {
    }

    SwingUtilities.invokeLater(() -> new SwingApp().setVisible(true));
  }
}
