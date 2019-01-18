package cn.com.simpleuse.validator.internal.constraintvalidators;

import cn.com.simpleuse.validator.constraints.Unique;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final Log log = LoggerFactory.make();
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UniqueValidator.class);
    private String column;
    private String table;

    public void initialize(Unique annotation) {
        column = annotation.column();
        table = annotation.table();
        if (column == null || column.length() == 0) {
            log.getPropertyNameCannotBeNullOrEmptyException();
        }
        if (table == null || table.length() == 0) {
            log.getPropertyNameCannotBeNullOrEmptyException();
        }
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.length() == 0) {
            return false;
        }

        try {
            Integer count = jdbcTemplate.queryForObject("select count(1) from ".concat(table).concat(" where ").concat(column).concat(" = ?"), Integer.class, value);
            if (count.intValue() == 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("UniqueValidator.isValid.err", e);
            return false;
        }

        return false;
    }


}
