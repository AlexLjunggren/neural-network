package com.ljunggren.neural.network.data;

public enum Color {

    RED(new double[] {255, 0, 0}),
    ORANGE(new double[] {255, 165, 0}),
    YELLOW(new double[] {255, 255, 0}),
    GREEN(new double[] {0, 128, 0}),
    BLUE(new double[] {0, 0, 255}),
    PURPLE(new double[] {128, 0, 128}),
    BROWN(new double[] {165, 42, 42}),
    BLACK(new double[] {0, 0, 0}),
    GREY(new double[] {128, 128, 128}),
    WHITE(new double[] {255, 255, 255});
    
    private double[] rgb;
    
    Color(double[] rgb) {
        this.rgb = rgb;
    }
    
    public double[] getRGB() {
        return rgb;
    }
    
    public String getName() {
        return this.name();
    }
    
}
