package com.monitobot.common;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public final class CollectionHelper {

    private CollectionHelper() { }

    public static <T> Optional<T> getFirstElement(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(collection.iterator().next());
        }
    }
}
