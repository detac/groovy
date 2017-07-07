package scripts

//cd C:\developer\projects\intelliJ\HelperApps\StringUtils\src\scripts
//set JAVA_OPTS="-Xmx4G"
//set GROOVY_OPTS="-Xmx4G"
//groovy stringifyNumbers.groovy

//input
def inFileName = 'in.txt'
def outFileName = 'out.txt'

println '*** STRINGIFY NUMBERS FROM TEXT FILE - START ***'

def resources = '../../resources/'

File inputFile = new File(resources + inFileName)
if (!inputFile.exists()) {
    println 'File [' + inputFile + '] dose not exist.'
} else {
    def lines = inputFile as String[]
    def noOfLines = lines.size()

    if (noOfLines > 0) {

        File outputFile = new File(resources + outFileName)
        if (outputFile.exists()) {
            outputFile.delete()
        }

        Writer writer = outputFile.newWriter('UTF-8', true)
        StringBuilder result = new StringBuilder()

        def i = 0;
        while (i < noOfLines) {
            def line = lines[i].trim()
            if (line.size() != 0) {
                result.append('\'' + line + '\',')
            }

            i++
            if (i % 10 == 0) {
                result.append('\n')
            }
        }

        result.setLength(result.size() - 1);

        writer.write(result.toString())
        writer.close()
    }
}

println '*** STRINGIFY NUMBERS FROM TEXT FILE - END ***'
