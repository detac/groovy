package scripts

//cd  C:\developer\projects\intelliJ\HelperApps\LogCleaner\src\scripts
//set JAVA_OPTS="-Xmx4G"
//set GROOVY_OPTS="-Xmx4G"
//groovy extractLines.groovy

println '*** Start ***'

def criteria = 'javax.xml.bind.JAXBContext'
def inFileName = 'desktop_debug.log.20170321'
def noOfLinesAfter = 50;

def outFileName = generateOutFileName(inFileName)

def inFolder = '../../resources/in/'
def outFolder = '../../resources/out/'

File inputFile = new File(inFolder + inFileName)
if (!inputFile.exists()) {
    println 'File [' + inputFile + '] dose not exist.'
} else {
    File outputFile = new File(outFolder + outFileName)
    if (outputFile.exists()) {
        outputFile.delete()
    }
    Writer writer = outputFile.newWriter('UTF-8', true)
    StringBuilder result = new StringBuilder()

    def lines = inputFile as String[]
    def write = false
    def count = 0
    for (def i = 0; i < lines.size(); i++) {
        def line = lines[i].trim()
        if (line.size() != 0) {
            if (write) {
                result.append(line + '\n')
                ++count
                if (count == noOfLinesAfter) {
                    write = false
                    result.append('\n************************\n')
                }
            }
            if (!write && line.contains(criteria)) {
                result.append(line + '\n')
                write = true
                count = 0
            }
        }
    }
    writer.write(result.toString())
    writer.close()
}
println '*** End ***'

def String generateOutFileName(String inFileName) {
    def array = inFileName.split('\\.')

    return array[0] + '_out.' + array[1]
}


