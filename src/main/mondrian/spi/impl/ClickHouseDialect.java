package mondrian.spi.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

/**
 * Implementation of {@link mondrian.spi.Dialect} for the yandex ClickHouse database.
 *
 * @author liu jie
 * @since Mar 1, 2018
 */
public class ClickHouseDialect extends JdbcDialectImpl {

    public static final JdbcDialectFactory FACTORY =
        new JdbcDialectFactory(
        	ClickHouseDialect.class,
            DatabaseProduct.CLICKHOUSE);

    /**
     * Creates a ClickHouseDialect.
     *
     * @param connection Connection
     *
     * @throws SQLException on error
     */
    public ClickHouseDialect(Connection connection) throws SQLException {
        super(connection);
    }
    
    public String toUpper(String expr) {
//    	if (StringUtils.isNumeric(expr)) {
//    		
//    	} else {
//        return "upper(" + expr + ")";
    	return "upper(toString(" + expr + "))";
//    	}
    }
}
