package br.pucpr.planet;

import br.pucpr.user.Theme;
import java.util.ArrayList;

public class PlanetasPrinter {
  public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
    if (planets == null || planets.isEmpty()) {
      System.out.println("ERRO: Lista de planetas vazia ou nula.");
      return;
    }

    final var borderChar = theme.getBorderChar();

    final var BORDER_WIDTH = 80;
    var sb = new StringBuilder();

    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    sb.append(
        String.format(
            "| %-15s | %-12s | %-15s | %-13s | %-10s |%n",
            "NOME", "DIÂMETRO", "DIST. SOL (KM)", "DIST. SOL (UA)", "TIPO"));
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");

    for (var planet : planets) {
      if (planet == null) {
        continue;
      }

      sb.append(
          String.format(
              "| %-15s | %12s | %15s | %13s | %-10s |%n",
              formatName(planet.name()),
              formatDiameter(planet.diameterKm()),
              formatDistanceKm(planet.sunDistanceKm()),
              formatDistanceUa(planet.sunDistanceKm()),
              formatType(planet.type())));
    }

    sb.repeat(borderChar, BORDER_WIDTH).append("\n");

    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }

  private static String formatName(String name) {
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }

    if (name.length() > 15) {
      return name.substring(0, 12) + "...";
    }

    return name;
  }

  private static String formatDiameter(double diameterKm) {
    return String.format("%,.1f", diameterKm);
  }

  private static String formatDistanceKm(long distanceKm) {
    return String.format("%,d", distanceKm);
  }

  private static String formatDistanceUa(long distanceKm) {
    double ua = distanceKm / 149_597_870.7;
    return String.format("%,.2f", ua);
  }

  private static String formatType(PlanetType type) {
    if (type == null) {
      return "NÃO INFORMADO";
    }

    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
