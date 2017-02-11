package nl.duo.team2045.governchain.domain;

import lombok.Data;

/**
 * @author gjsterenborg
 */
public @Data class Identiteit {
    private final String bsn;

    public Identiteit(final String bsn) {
        this.bsn = bsn;
    }
}
