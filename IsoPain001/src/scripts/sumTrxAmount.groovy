package scripts

import java.util.regex.Matcher
import java.util.regex.Pattern

//cd C:\developer\projects\intelliJ\groovy\IsoPain001\src\scripts
//set JAVA_OPTS="-Xmx4G"
//set GROOVY_OPTS="-Xmx4G"
//groovy sumTrxAmount.groovy

println '*** Start ***'

def inFileName = 'isoPain001_6400.xml'
//def inFileName = 'isoPain001_12800.xml'
def inFolder = '../../resources/in/'

File inputFile = new File(inFolder + inFileName)
if (!inputFile.exists()) {
    println 'File [' + inputFile + '] dose not exist.'
} else {
    def lines = inputFile as String[]
    def sum = 0

    Pattern p = Pattern.compile('.*\\<Amt><InstdAmt Ccy=\"CHF\"> *(.*) *\\</InstdAmt></Amt>.*');

    for (def i = 0; i < lines.size(); i++) {
        def line = lines[i].trim()
        if (line.size() != 0) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                sum += Double.valueOf(m.group(1));;
            }
        }
    }
    println 'Total amount sum: ' + sum
}
println '*** End ***'


