package liquibase.sqlgenerator;

import static org.junit.Assert.assertTrue;
import liquibase.database.DB2Database;
import liquibase.database.Database;
import liquibase.database.DerbyDatabase;
import liquibase.database.FirebirdDatabase;
import liquibase.database.HsqlDatabase;
import liquibase.database.InformixDatabase;
import liquibase.database.OracleDatabase;
import liquibase.exception.ValidationErrors;
import liquibase.statement.AddColumnStatement;
import liquibase.statement.AutoIncrementConstraint;

import org.junit.Test;

public class AddColumnGeneratorDefaultClauseBeforeNotNullTest extends AddColumnGeneratorTest {
    public AddColumnGeneratorDefaultClauseBeforeNotNullTest() throws Exception {
        super(new AddColumnGeneratorDefaultClauseBeforeNotNull());
    }

    @Test
    public void validate_noAutoIncrementWithDerby() {
        ValidationErrors validationErrors = generatorUnderTest.validate(new AddColumnStatement(null, "table_name", "column_name", "int", null, new AutoIncrementConstraint("column_name")), new DerbyDatabase());
        assertTrue(validationErrors.getErrorMessages().contains("Cannot add an identity column to a database"));
    }

    @Override
    protected boolean shouldBeImplementation(Database database) {
        return database instanceof OracleDatabase
                || database instanceof HsqlDatabase
                || database instanceof DerbyDatabase
                || database instanceof DB2Database
                || database instanceof FirebirdDatabase
                || database instanceof InformixDatabase;
    }
}
