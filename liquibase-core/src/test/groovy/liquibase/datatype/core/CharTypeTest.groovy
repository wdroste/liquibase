package liquibase.datatype.core

import DerbyDatabase
import HsqlDatabase
import MSSQLDatabase
import MySQLDatabase
import OracleDatabase
import liquibase.database.core.postgresql.PostgresDatabase
import spock.lang.Specification
import spock.lang.Unroll

class CharTypeTest extends Specification {
    @Unroll
    def "toDatabaseType"() {
        when:
        def type = new CharType()
        for (param in params) {
            type.addParameter(param)
        }

        then:
        type.toDatabaseDataType(database).toString() == expected

        where:
        params       | database               | expected
        [13]         | new DerbyDatabase()    | "CHAR(13)"
        [13]         | new HsqlDatabase()     | "CHAR(13)"
        [13]         | new PostgresDatabase() | "CHAR(13)"
        [13]         | new OracleDatabase()   | "CHAR(13)"
        [13]         | new MSSQLDatabase()    | "CHAR(13)"
        [2147483647] | new MSSQLDatabase()    | "CHAR(2147483647)"
        [13]         | new MySQLDatabase()    | "CHAR(13)"
    }

}
