package org.cacois.JettyJerseyCassandra.services;

public class StringService {
    private int _count = 0;

    public String getString() {
        _count++;
        return String.format("injected string: %d", _count);
    }
}
