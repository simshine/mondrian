package mondrian.spi.impl;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import mondrian.rolap.SqlStatement;
import mondrian.spi.Dialect.DatabaseProduct;

public class DmDialect extends JdbcDialectImpl {

    public static final JdbcDialectFactory FACTORY =
        new JdbcDialectFactory(
        		DmDialect.class,
            DatabaseProduct.DM);

    /**
     * Creates a ClickHouseDialect.
     *
     * @param connection Connection
     *
     * @throws SQLException on error
     */
    public DmDialect(Connection connection) throws SQLException {
        super(connection);
    }
    
//    public String toUpper(String expr) {
////    	if (StringUtils.isNumeric(expr)) {
////    		
////    	} else {
////        return "upper(" + expr + ")";
//    	return "upper(toString(" + expr + "))";
////    	}
//    }
    public SqlStatement.Type getType(
            ResultSetMetaData metaData, int columnIndex)
            throws SQLException
        {
    		SqlStatement.Type internalType = super.getType(metaData, columnIndex);
            final int columnType = metaData.getColumnType(columnIndex + 1);
            if (columnType == Types.NUMERIC || columnType == Types.DECIMAL) {
            	internalType = SqlStatement.Type.DOUBLE;
            }
            return internalType;
        }
}
