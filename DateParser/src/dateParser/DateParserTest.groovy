package dateParser

import org.joda.time.DateTime

class DateParserTest extends GroovyTestCase {
    def void testDateParser(){
        def parser = new DateParser()
        def dateTime = new DateTime(2013,1,1,9,30)
        def result = parser.parse(dateTime.toString())
        assert '01/01/2013 - 09:30 AM' == result
    }
}
