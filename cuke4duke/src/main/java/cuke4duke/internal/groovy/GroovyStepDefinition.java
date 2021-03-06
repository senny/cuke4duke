package cuke4duke.internal.groovy;

import cuke4duke.internal.language.AbstractStepDefinition;
import cuke4duke.internal.language.JdkPatternArgumentMatcher;
import cuke4duke.internal.language.StepArgument;
import groovy.lang.Closure;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

public class GroovyStepDefinition extends AbstractStepDefinition {
    private final GroovyLanguage groovyLanguage;
    private final Pattern regexp;
    private final Closure body;

    public GroovyStepDefinition(GroovyLanguage groovyLanguage, Pattern regexp, Closure body) throws Throwable {
        super(groovyLanguage);
        this.groovyLanguage = groovyLanguage;
        this.regexp = regexp;
        this.body = body;
        register();
    }

    public String regexp_source() {
        return regexp.pattern();
    }

    public String file_colon_line() {
        return body.toString();
    }

    public Object invokeWithArgs(Object[] args) throws Throwable {
        return groovyLanguage.invokeClosure(body, args);
    }

    public List<StepArgument> arguments_from(String stepName) throws UnsupportedEncodingException {
        return JdkPatternArgumentMatcher.argumentsFrom(regexp, stepName);
    }
}
