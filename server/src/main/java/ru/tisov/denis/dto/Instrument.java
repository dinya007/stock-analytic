package ru.tisov.denis.dto;

public class Instrument {

    private final String securityId;
    private boolean loaded;

    public Instrument(String securityId, boolean loaded) {
        this.securityId = securityId;
        this.loaded = loaded;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "securityId='" + securityId + '\'' +
                ", loaded=" + loaded +
                '}';
    }
}
