/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@TypeDefs({
        @TypeDef(name = JsonTypes.JSON, typeClass = JsonType.class),
        @TypeDef(name = JsonTypes.JSON_BIN, typeClass = JsonBinaryType.class)
})
package com.monitobot.search;


import io.quarkiverse.hibernate.types.json.JsonBinaryType;
import io.quarkiverse.hibernate.types.json.JsonType;
import io.quarkiverse.hibernate.types.json.JsonTypes;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;