package tg.gouv.anid.rspm.core.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
public class DatabaseNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    @Override
    protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        return new Identifier(name.toUpperCase(), true);
    }
}
