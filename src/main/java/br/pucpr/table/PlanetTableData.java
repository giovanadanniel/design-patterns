package main.java.br.pucpr.table;

import br.pucpr.planet.Planet;
import br.pucpr.planet.PlanetType;
import java.util.ArrayList;
import java.util.List;

public class PlanetTableData implements TableData {
    private final ArrayList<Planet> planets;

    public PlanetTableData(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public List<String> getColumns() {
        return List.of("Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo");
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        if (planets == null) return rows;

        for (var planet : planets) {
            if (planet == null) continue;
            rows.add(List.of(
                    formatName(planet.name()),
                    String.format("%,.1f", planet.diameterKm()),
                    String.format("%,d", planet.sunDistanceKm()),
                    String.format("%.2f", Planet.kmToAu(planet.sunDistanceKm())),
                    formatType(planet.type())
            ));
        }
        return rows;
    }

    private static String formatName(String name) {
        return (name == null || name.isEmpty()) ? "NÃO INFORMADO" : name;
    }

    private static String formatType(PlanetType type) {
        return switch (type) {
            case ROCK -> "Rochoso";
            case GAS -> "Gasoso";
            case ICE -> "Gelado";
            case DWARF -> "Anão";
        };
    }
}