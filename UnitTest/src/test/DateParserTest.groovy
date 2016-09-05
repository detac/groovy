package test

@Grab(group='joda-time', module='joda-time', version='2.9.3')
import org.joda.time.DateTime

import groovy.util.GroovyTestCase
import jdk.nashorn.internal.parser.DateParser

class DateParserTest extends GroovyTestCase {

    private DateParser parser

    def void setUp() {
        parser = new DateParser()
    }

    def void testCanParseDateTime() {
        def dateTime = new DateTime(2016, 5, 3, 12, 15);
        def result = parser.parse(dateTime.toString())

        assert '05/03/2016 - 12:15 AM' == result
    }

    def void testWillThrowAnErrorWhenNullDateTimeIsProvided() {
        shouldFail(IllegalArgumentException) {
            parser.parse(null)
        }
    }

}
