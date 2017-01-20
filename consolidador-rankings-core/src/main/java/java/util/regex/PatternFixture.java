package java.util.regex;

import java.util.Set;

/**
 * 
 * @author Thiago Miranda
 * @since 20 de jan de 2017
 */
public final class PatternFixture {

    private PatternFixture() {

    }

    /**
     * 
     * @param pattern
     * @return
     */
    public static Set<String> getNamedGroups(Pattern pattern) {
        return pattern.namedGroups().keySet();
    }
}
