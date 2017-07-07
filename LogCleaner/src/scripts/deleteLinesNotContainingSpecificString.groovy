package scripts

import java.nio.file.Path
import java.nio.file.Paths

println '*** Start ***'

def criteriaArray = ['[iso]', '.iso.']
def inFileName = 'desktop_debug.log.20170321'

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

    inputFile.eachLine { line ->
        line = line.trim()
        if (line.size() != 0) {
            for (def criteria : criteriaArray) {
                if (line.contains(criteria)) {
                    result.append(line + '\n')
                    break;
                }
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

