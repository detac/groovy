package scripts

//cd C:\developer\projects\intelliJ\HelperApps\LogCleaner\src\scripts
//set JAVA_OPTS="-Xmx4G"
//set GROOVY_OPTS="-Xmx4G"
//groovy splitTextFile.groovy

//input
def inFileName = 'desktop_debug.log.20170321'
def linesPerFile = 10000

println '*** SPLIT TEXT FILE - START ***'

def inFolder = '../../resources/in/'
def outFolder = '../../resources/out/'

File inputFile = new File(inFolder + inFileName)
if (!inputFile.exists()) {
    println 'File [' + inputFile + '] dose not exist.'
} else {
    def lines = inputFile as String[]
    def noOfLines = lines.size()

    def noOfFiles
    if (noOfLines <= linesPerFile) {
        noOfFiles = 1
    } else {
        noOfFiles = noOfLines / linesPerFile
    }

    println 'File [' + inputFile + '] has ' + noOfLines + ' lines.'
    println 'File will be split in ' + noOfFiles + 'files.'

    def j = 0
    for (def i = 1; i < noOfFiles; i++) {
        def outFileName = generateOutFileName(inFileName, i)

        File outputFile = new File(outFolder + outFileName)
        if (outputFile.exists()) {
            outputFile.delete()
        }

        Writer writer = outputFile.newWriter('UTF-8', true)
        StringBuilder result = new StringBuilder()

        def lastLineForThisFile = i * linesPerFile
        while (j <= noOfLines && j < lastLineForThisFile) {
            def line = lines[j].trim()
            if (line.size() != 0) {
                result.append(line + '\n')
            }
            j++
        }

        writer.write(result.toString())
        writer.close()
        println outFileName + ' is done.'
    }
}

println '*** SPLIT TEXT FILE - END ***'

def String generateOutFileName(def inFileName, def no) {
    def array = inFileName.split('\\.')
    return array[0] + '_out_#' + no + '.' + array[1]
}